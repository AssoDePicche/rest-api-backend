package com.caravanas.db;

@SuppressWarnings({"serial"})
public final class UnannotedNullColumnException extends RuntimeException {
  public UnannotedNullColumnException(String column) {
    super("Unannoted null column '" + column + "'");
  }
}
