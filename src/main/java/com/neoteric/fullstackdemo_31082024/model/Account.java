package com.neoteric.fullstackdemo_31082024.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Account {

    private  String name;
    private  String accountNumber;

   private String pan;
   private String  mobileNumber;
   private double balance;
}
