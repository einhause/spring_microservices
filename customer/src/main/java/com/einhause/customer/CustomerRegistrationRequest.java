package com.einhause.customer;

public record CustomerRegistrationRequest(String firstName,
                                          String lastName,
                                          String email) {

}
