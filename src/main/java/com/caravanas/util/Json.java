package com.caravanas.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Json {
  public static final String CONTENT_TYPE = "application/json";

  public static Gson gson;

  static {
    GsonBuilder builder = new GsonBuilder();

    builder.setPrettyPrinting();

    gson = builder.create();
  }

  private Map<String, List<String>> map = new HashMap<>();

  public Json() {
  }

  public void put(String key, String value) {
    if (!map.containsKey(key)) {
      map.put(key, new ArrayList<String>());
    }

    map.get(key).add(value);
  }

  public String get(String key) {
    if (!map.containsKey(key)) {
      return "";
    }

    StringBuilder builder = new StringBuilder();

    boolean isCollection = map.get(key).size() > 1;

    if (isCollection) {
      builder.append("[");
    }

    map.get(key).stream().forEach(item -> {
      String str = "";

      if (isCollection) {
        str = "\n\t\t\"" + item + "\",";

      } else {
        str = "\"" + item + "\",";
      }

      builder.append(str);
    });

    if (builder.length() > 0) {
      builder.setLength(builder.length() - 1);
    }

    if (isCollection) {
      builder.append("\n\t]");
    }

    return builder.toString();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    for (String key : map.keySet()) {
      String str = "\t\"" + key + "\": " + get(key) + ",\n";

      builder.append(str);
    }

    if (builder.length() > 0) {
      builder.setLength(builder.length() - 2);
    }

    return "{\n" + builder.toString() + "\n}";
  }

  public static <T> T fromJson(String json, Type classType) {
    return gson.fromJson(json, classType);
  }
  
  public static String toJson(Object object) {
    return gson.toJson(object);
  }
}
