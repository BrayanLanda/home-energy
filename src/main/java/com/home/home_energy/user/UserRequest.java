package com.home.home_energy.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String city;
    private String street;
}
