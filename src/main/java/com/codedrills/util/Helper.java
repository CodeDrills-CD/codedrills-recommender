package com.codedrills.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.util.UUID;

public class Helper {
  public static Gson gson = new GsonBuilder()
    .create();


  public static void sleep(long ms) {
    try {
      Thread.sleep(ms);
    } catch(InterruptedException e) {
      throw new RuntimeException("Interrupted while sleeping");
    }
  }

  public static String randomId() {
    return UUID.randomUUID().toString();
  }

  public static Double parseRatingNumber(String text) {
    Double rating;
    try {
      rating = Double.parseDouble(text);
    } catch(NumberFormatException ex) {
      rating = null;
    }

    return rating;
  }


}
