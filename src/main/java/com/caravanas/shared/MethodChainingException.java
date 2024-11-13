package com.caravanas.shared;

@SuppressWarnings({"serial"})
public final class MethodChainingException extends RuntimeException {
  public MethodChainingException() {
    super("Invalid method chaining");
  }
}
