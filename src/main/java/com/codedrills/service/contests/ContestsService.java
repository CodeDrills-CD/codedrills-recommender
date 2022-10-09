package com.codedrills.service.contests;

import com.codedrills.model.Contest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContestsService {
  @Autowired
  CalendarService calendarService;

  public Map<String, List<Contest>> fetchContests(TimeZone useTZ) {
    List<Contest> contests = calendarService.getContests()
      .stream()
      .filter(c -> c.endsLater())
      .collect(Collectors.toList());

    Map<String, List<Contest>> typeContestsMap = contests.stream()
      .collect(Collectors.groupingBy(c -> c.getLiveOrUpcoming()));

    Map<String, List<Contest>> orderedContestList = new LinkedHashMap<>();
    orderedContestList.put("live", changeToTimezone(nullToEmptyList(typeContestsMap.get("live")), useTZ));
    orderedContestList.put("upcoming", changeToTimezone(nullToEmptyList(typeContestsMap.get("upcoming")), useTZ));

    orderedContestList.values()
      .stream()
      .forEach(l -> l.sort(Comparator.comparing(Contest::getStartTime)));
    return orderedContestList;
  }

  private List<Contest> changeToTimezone(List<Contest> contests, TimeZone tz) {
    return contests.stream()
      .peek(c -> c.setTimezone(tz))
      .collect(Collectors.toList());
  }

  private List<Contest> nullToEmptyList(List<Contest> list) {
    return Optional.ofNullable(list)
      .orElseGet(Collections::emptyList);
  }

}
