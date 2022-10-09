package com.codedrills.service.contests;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import com.codedrills.model.Contest;
import com.google.common.base.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CalendarService {
  private static Logger logger = Logger.getLogger(CalendarService.class);
  private static String CALENDAR_URL = "https://calendar.google.com/calendar/ical/codedrills%40gmail.com/public/basic.ics";
  List<Contest> contests;

  public CalendarService() {
    fetchContests();
  }

  @Scheduled(fixedDelay = 30 * 60 * 1000, initialDelay = 60 * 1000)
  public void fetchContests() {
    logger.info("Fetching calendar");
    ICalendar ical = Biweekly.parse(executeGET(CALENDAR_URL)).first();
    List<VEvent> events = ical.getEvents();
    contests = events.stream()
      .map(e -> new Contest(e))
      .collect(Collectors.toList());
  }

  public static String executeGET(String targetURL) {
    HttpURLConnection connection = null;

    try {
      URL url = new URL(targetURL);
      connection = (HttpURLConnection) url.openConnection();

      return IOUtils.toString(connection.getInputStream(), Charsets.UTF_8);
    } catch(Exception ex) {
      logger.error("Error when fetching calendar", ex);
      return null;
    } finally {
      if(connection != null) {
        connection.disconnect();
      }
    }
  }

  public List<Contest> getContests() {
    return contests;
  }
}
