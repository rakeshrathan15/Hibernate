package com.neoteric.fullstackdemo_31082024.exception;

public class AccountCreationFailedException  extends  Exception{

      public String message;

    public AccountCreationFailedException(String msg) {
        this.message = msg;
    }
}
