package com.caravanas.db;

@SuppressWarnings({"serial"})
public final class NullColumnException extends RuntimeException {
  public NullColumnException(String column) {
    super("Column '" + column + "' cannot be NULL");
  }
}
