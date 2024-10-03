package com.caravanas.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class BCrypt {
  public static String hash(String string) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    return encoder.encode(string);
  }
}
