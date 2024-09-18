package com.neoteric.fullstackdemo_31082024.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {

    private String add1;

    private String add2;

    private String city;
    private String state;
    private String pincode;


}
