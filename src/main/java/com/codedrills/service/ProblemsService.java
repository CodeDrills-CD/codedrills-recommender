package com.codedrills.service;

import com.codedrills.model.Problem;
import com.codedrills.model.Site;
import com.codedrills.model.Tag;
import com.codedrills.model.recommendation.ProblemDifficulty;
import com.codedrills.service.db.ProblemsDao;
import com.codedrills.service.sites.SiteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
public class ProblemsService implements ApplicationListener<ContextRefreshedEvent> {
  private static Logger logger = Logger.getLogger(ProblemsService.class);

  private final static long REFETCH_DELAY = 12L * 60 * 60 * 1000;
  private static final long INITIAL_REFETCH_DELAY = 1L * 60 * 60 * 1000;
  private final ConcurrentHashMap<String, Problem> problemsByUid = new ConcurrentHashMap<>();
  private final ConcurrentHashMap<Tag, Set<Problem>> problemsByTag = new ConcurrentHashMap<>();

  @Autowired
  private SiteService siteService;

  @Autowired
  private ProblemsDao problemsDao;


  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    if(problemsByUid.isEmpty()) {
      loadProblems();
    }
  }

  public void loadProblems() {
    logger.info("Loading problems from db");
    List<Problem> problems = problemsDao.findAll();
    addProblems(problems);
    processBySite();
    logger.info(String.format("Fetched %d problems from db", problems.size()));
    if(problems.isEmpty()) {
      refetchProblems();
    }
  }

  @Scheduled(fixedDelay = REFETCH_DELAY, initialDelay = INITIAL_REFETCH_DELAY)
  public void refetchProblems() {
    logger.info("Refetching problems");
    int previousSize = problemsByUid.size();
    List<Problem> problems = siteService.fetchProblemData();
    addProblems(problems);
    saveProblems(problems);
    processBySite();
    logger.info(String.format("Total %d(+%d) problems", problemsByUid.size(), problemsByUid.size() - previousSize));
  }

  private void saveProblems(List<Problem> problems) {
    problems.stream()
      .forEach(problemsDao::save);
  }

  private void processBySite() {
    Map<Site, List<Problem>> problemsBySite = new HashMap<>();
    problemsByUid.values()
      .stream()
      .forEach(p -> problemsBySite.computeIfAbsent(p.getSite(), __ -> new ArrayList<>()).add(p));

    problemsBySite.values()
      .stream()
      .forEach(this::postProcess);
  }

  private void addProblems(List<Problem> problems) {
    Map<String, Problem> problemsByUid = new HashMap<>();
    problems
      .stream()
      .forEach(p -> {
        String uid = p.getUid();
        if(problemsByUid.containsKey(uid)) {
          problemsByUid.get(uid).getTags().addAll(p.getTags());
        } else {
          problemsByUid.put(uid, p);
        }
      });

    this.problemsByUid.putAll(problemsByUid);
    problemsByUid.values()
      .stream()
      .forEach(p -> p.getTags().stream()
        .forEach(t -> problemsByTag.computeIfAbsent(t, __ -> new HashSet<>()).add(p)));

  }

  private void postProcess(List<Problem> problems) {
    normalize(problems);
  }

  /* Sort problems by descending order of submissions. score = relative order of ranks */
  private void normalize(List<Problem> problems) {
    final List<Problem> sortedProblems = problems.stream()
      .sorted(Comparator.comparingInt(Problem::getSolved).reversed())
      .collect(Collectors.toList());

    IntStream.range(0, problems.size())
      .forEach(i -> {
        Problem p = sortedProblems.get(i);
        p.setScore((i+1) * 100.0 / problems.size());
        p.setDifficulty(ProblemDifficulty.getDifficulty(p.getScore()));
      });
  }

  public Map<String, Problem> filteredProblemsByUid(Set<Site> sites) {
    Map<String, Problem> problems = new HashMap<>();
    problemsByUid.entrySet()
      .stream()
      .filter(e -> sites.contains(e.getValue().getSite()))
      .forEach(e -> problems.put(e.getKey(), e.getValue()));

    return problems;
  }

  public Map<Tag, Set<Problem>> filteredProblemsByTag(Set<Site> sites) {
    Map<Tag, Set<Problem>> problems = new HashMap<>();

    problemsByTag.entrySet().stream()
      .forEach(e -> problems.put(e.getKey(),
        e.getValue().stream()
          .filter(p -> sites.contains(p.getSite()))
          .collect(Collectors.toSet())
        )
      );

    return problems;
  }

  public ConcurrentHashMap<String, Problem> getProblemsByUid() {
    return problemsByUid;
  }

  public ConcurrentHashMap<Tag, Set<Problem>> getProblemsByTag() {
    return problemsByTag;
  }

  public Problem getProblemById(String id) {
    return problemsByUid.get(id);
  }
}
