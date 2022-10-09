package com.codedrills.model;

import biweekly.component.VEvent;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import java.util.stream.Stream;

public class Contest {
  private static final DateTimeFormatter outputDTF = DateTimeFormatter.ofPattern("EEE dd MMM hh:mm a z");
  private String site;
  private String title;
  private String description;
  private String url;
  private ZonedDateTime startTime;
  private ZonedDateTime endTime;

  public Contest(VEvent event) {
    title = event.getSummary().getValue();
    // Temp fix for codechef long contest bug
    description = event.getDescription().getValue();
    url = event.getLocation().getValue();
    startTime = ZonedDateTime.of(LocalDateTime.ofInstant(event.getDateStart().getValue().toInstant(), ZoneId.of("UTC")), ZoneId.of("UTC"));
    endTime = ZonedDateTime.of(LocalDateTime.ofInstant(event.getDateEnd().getValue().toInstant(), ZoneId.of("UTC")), ZoneId.of("UTC"));

    site = null;
    site = Stream.of("topcoder", "codeforces", "codechef", "hackerrank", "hackerearth", "google", "ipsc")
      .filter(s -> url.contains(s))
      .findFirst()
      .orElse(null);
  }

  public boolean isLive() {
    return ZonedDateTime.now(ZoneId.of("UTC")).isAfter(startTime);
  }

  public boolean endsLater() {
    return ZonedDateTime.now(ZoneId.of("UTC")).isBefore(endTime);
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getUrl() {
    return url;
  }

  public ZonedDateTime getStartTime() {
    return startTime;
  }

  public ZonedDateTime getEndTime() {
    return endTime;
  }

  public String getStartTimeString() {
    return startTime.format(outputDTF);
  }

  public String getEndTimeString() {
    return endTime.format(outputDTF);
  }

  public ZonedDateTime getStartTimeUTC() {
    return startTime.withZoneSameInstant(ZoneId.of("UTC"));
  }

  public ZonedDateTime getEndTimeUTC() {
    return endTime.withZoneSameInstant(ZoneId.of("UTC"));
  }

  public String getSite() {
    return site;
  }

  public String getLiveOrUpcoming() {
    return isLive()? "live": "upcoming";
  }

  public void setTimezone(TimeZone timezone) {
    startTime = startTime.withZoneSameInstant(timezone.toZoneId());
    endTime = endTime.withZoneSameInstant(timezone.toZoneId());
  }
}
