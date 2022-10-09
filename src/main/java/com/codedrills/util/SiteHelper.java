package com.codedrills.util;

import com.codedrills.model.Site;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SiteHelper {
  private static Map<String, Site> invMap;

  static {
    invMap = new HashMap<>();
    Arrays.stream(Site.values())
      .forEach(s -> s.getAliases()
        .stream()
        .forEach(a -> invMap.put(a, s)));
  }

  public static Site getSite(String alias) {
    return invMap.get(alias);
  }
}
