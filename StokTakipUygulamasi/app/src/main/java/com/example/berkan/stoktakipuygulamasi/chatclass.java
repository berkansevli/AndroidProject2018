package com.example.berkan.stoktakipuygulamasi;


public class chatclass {
    String email;
    String message;

    chatclass(){}
    chatclass(String email,String message)
    {
        this.email = email;
        this.message = message;
    }
    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
    }

}
