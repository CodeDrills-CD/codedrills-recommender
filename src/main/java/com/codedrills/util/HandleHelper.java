package com.codedrills.util;

import com.codedrills.model.Handle;
import com.codedrills.model.Site;
import com.codedrills.model.exceptions.InvalidInputException;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class HandleHelper {
  public static String HANDLES_REGEX = "^( )*(((sp/|cc/|cf/|/)*(\\w|\\.|-)+(,| )+)*((sp/|cc/|cf/|/)*(\\w|\\.|-)+))( )*$";
  public static Pattern HANDLES_PATTERN = Pattern.compile(HANDLES_REGEX);
  public static long HANDLES_LIMIT = 10;
  private static Map<Site, String> profileUrlFormat;
  static {
    profileUrlFormat = new HashMap<>();
    profileUrlFormat.put(Site.CODECHEF, "https://www.codechef.com/users/%s");
    profileUrlFormat.put(Site.CODEFORCES, "http://codeforces.com/profile/%s");
    profileUrlFormat.put(Site.SPOJ, "http://www.spoj.com/users/%s/");
  }

  public static List<Handle> splitHandles(String handlesQuery) {
    List<String> plainHandles = Arrays.stream(handlesQuery.split("[ ,]"))
      .filter(s -> !isNull(s) && !s.trim().isEmpty())
      .collect(Collectors.toList());

    List<Handle> handles = new ArrayList<>();
    plainHandles.stream()
      .forEach(u -> {

        String[] parts = u.split("/");
        List<String> prefixes = new ArrayList<>();
        prefixes.addAll(Arrays.asList(Arrays.copyOfRange(parts, 0, parts.length - 1)));
        if(prefixes.isEmpty()) {
          prefixes.add("");
        }

        String handle = parts[parts.length - 1].trim();

        prefixes.stream()
          .map(String::trim)
          .forEach(s -> {
            Site site = SiteHelper.getSite(s);
            handles.add(new Handle(site, handle));
          });
      });

    return handles;
  }

  public static String canonicalHandles(List<Handle> handles) {
    Map<String, List<Site>> siteByHandles = new HashMap<>();
    handles.stream()
      .forEach(h -> {
        siteByHandles.computeIfAbsent(h.getHandle(), __ -> new LinkedList<>()).add(h.getSite());
      });

    return siteByHandles.entrySet().stream()
      .map(e -> {
        String prefixes = e.getValue().stream()
          .map(Site::getShortName)
          .distinct()
          .sorted()
          .collect(Collectors.joining("/", "", "/"));
        return prefixes + e.getKey();
      })
      .distinct()
      .sorted()
      .collect(Collectors.joining(" "));
  }

  public static List<Handle> validateAndSplit(String handlesQuery) {
    if(!HandleHelper.HANDLES_PATTERN.matcher(handlesQuery).matches()) {
      throw new InvalidInputException("The handle(s) you entered were not in the correct format");
    }

    List<Handle> handles = HandleHelper.splitHandles(handlesQuery);

    if(handles.size() > HANDLES_LIMIT) {
      throw new InvalidInputException(String.format("Number of handles to be analyzed can't be more than %d", HANDLES_LIMIT));
    }

    return handles;

  }

  public static String buildProfileUrl(Handle handle) {
    return String.format(profileUrlFormat.get(handle.getSite()), handle.getHandle());
  }

  public static Set<Site> getSites(List<Handle> handles) {
    return handles
      .stream()
      .map(Handle::getSite)
      .collect(Collectors.toSet());
  }
}
