package com.codedrills.util;

import com.codedrills.model.Site;
import com.codedrills.model.Verdict;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codedrills.model.Verdict.*;
import static java.util.Arrays.asList;

public class VerdictHelper {
  private static Map<Verdict, List<String>> verdictMap;
  private static Map<String, Verdict> invMap;

  static {
    verdictMap = new HashMap<>();
    verdictMap.put(AC, asList("cf:OK", "cc:solutions_partially_accepted", "cc:solutions_accepted"));
    verdictMap.put(WA, asList("cf:WRONG_ANSWER", "cc:wrong_answers"));
    verdictMap.put(TLE, asList("cf:TIME_LIMIT_EXCEEDED", "cc:time_limit_exceeded"));
    verdictMap.put(RTE, asList("cf:RUNTIME_ERROR", "cc:runtime_error"));
    verdictMap.put(CE, asList("cf:COMPILE_ERROR", "cc:compile_error"));
    verdictMap.put(MLE, asList("cf:MEMORY_LIMIT_EXCEEDED"));

    invMap = new HashMap<>();
    verdictMap.entrySet()
      .stream()
      .forEach(e -> e.getValue().stream()
        .forEach(k -> invMap.put(k, e.getKey())));
  }

  public static Verdict mapVerdict(Site site, String verdict) {
    return invMap.getOrDefault(String.format("%s:%s", site.getShortName(), verdict), OTHER);
  }

  public static List<String> verdictsFor(Site site) {
    return invMap.keySet()
      .stream()
      .filter(s -> s.startsWith(site.getShortName()))
      .map(s -> s.split(":")[1])
      .collect(Collectors.toList());
  }
}
