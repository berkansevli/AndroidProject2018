package com.example.berkan.stoktakipuygulamasi;

public class chatt
{
    String name;
    String message;
    String time;

    public chatt()
    {

    }

    public chatt(String user,String message,String date)
    {
        this.name=user;
        this.message=message;
        this.time=date;
    }

    public String getName()
    {
        return name;
    }

    public String getMessage()
    {
        return message;
    }

    public String getDate()
    {
        return time;
    }
}
