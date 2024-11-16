package com.caravanas.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class Json {
  public static final String CONTENT_TYPE = "application/json";

  public static Gson gson;

  static {
    GsonBuilder builder = new GsonBuilder();

    builder.setPrettyPrinting();

    gson = builder.create();
  }

  private Map<String, Collection<String>> map = new HashMap<>();

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

    final boolean HAS_MULTIPLE_VALUES = map.get(key).size() > 1;

    final String PREFIX = HAS_MULTIPLE_VALUES ? "\n\t\t\"" : "\"";

    if (HAS_MULTIPLE_VALUES) {
      builder.append("[");
    }

    map.get(key).stream().forEach(value -> {
      builder.append(PREFIX + value + "\",");
    });

    if (builder.length() > 0) {
      builder.setLength(builder.length() - 1);
    }

    if (HAS_MULTIPLE_VALUES) {
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
