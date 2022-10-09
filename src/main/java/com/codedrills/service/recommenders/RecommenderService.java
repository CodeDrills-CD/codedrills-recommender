package com.codedrills.service.recommenders;

import com.codedrills.model.Problem;
import com.codedrills.model.analysis.AnalysisContext;
import com.codedrills.model.exceptions.InvalidInputException;
import com.codedrills.model.recommendation.ProblemStatus;
import com.codedrills.model.recommendation.Recommendation;
import com.codedrills.model.recommendation.RecommendationContext;
import com.codedrills.service.AnalysisService;
import com.codedrills.service.ProblemsService;
import com.codedrills.service.db.RecommendationsDao;
import com.codedrills.util.HandleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Strings.isNullOrEmpty;

@Service
@Transactional
public class RecommenderService {
  private final List<RecommenderInterface> recommenders;
  private final ProblemsService problemsService;
  @Autowired
  private RecommendationsDao recommendationsDao;
  @Autowired
  AnalysisService analysisService;

  @Autowired
  public RecommenderService(List<RecommenderInterface> recommenders, ProblemsService problemsService) {
    this.recommenders = recommenders;
    this.problemsService = problemsService;
  }

  public RecommendationContext computeRecommendations(RecommendationContext context) {
    recommenders.stream()
      .forEach(r -> r.recommend(context));

    return context;
  }

  public RecommendationContext recommendations(AnalysisContext analysisContext) {
    RecommendationContext recommendationContext = new RecommendationContext(
      problemsService.filteredProblemsByUid(HandleHelper.getSites(analysisContext.getHandles())),
      problemsService.filteredProblemsByTag(HandleHelper.getSites(analysisContext.getHandles())),
      analysisContext
    );

    computeRecommendations(recommendationContext);

    recommendationContext.getRecommendations()
      .stream()
      .forEach(recommendationsDao::save);

    return recommendationContext;
  }

  public Recommendation getRecommendation(String id) {
    if(isNullOrEmpty(id)) {
      throw new InvalidInputException("Given recommendation id is invalid");
    }
    if(!recommendationsDao.exists(id)) {
      throw new InvalidInputException("No such recommendation exists");
    }

    Recommendation recommendation = recommendationsDao.findOne(id);
    AnalysisContext analysisContext = analysisService.analyzeUser(HandleHelper.splitHandles(recommendation.getHandles()));
    Set<String> solvedIds = analysisContext.getSortedSolved().stream()
      .map(Problem::getUid)
      .collect(Collectors.toSet());

    recommendation.getPracticeProblems()
      .stream()
      .forEach(p -> {
          Problem problem = problemsService.getProblemById(p.getProblem().getUid());
          p.getProblem().setScore(problem.getScore());
          p.getProblem().setDifficulty(problem.getDifficulty());
          if(solvedIds.contains(p.getProblem().getUid())) {
            p.setStatus(ProblemStatus.SOLVED);
          } else {
            p.setStatus(ProblemStatus.UNSOLVED);
          }
        }
      );

    recommendation.getPracticeProblems().sort((p1, p2) -> Problem.compareByScore(p1.getProblem(), p2.getProblem()));


    return recommendation;
  }

}
