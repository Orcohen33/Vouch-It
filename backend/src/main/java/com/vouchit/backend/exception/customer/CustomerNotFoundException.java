package com.vouchit.backend.exception.customer;

public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException(String message) {super(message);}
}
