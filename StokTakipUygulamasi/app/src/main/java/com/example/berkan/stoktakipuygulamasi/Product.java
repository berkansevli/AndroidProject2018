package com.example.berkan.stoktakipuygulamasi;


public class Product
{
    String Barcode;
    String Pname;
    String Date;
    String Time;
    String Discription;

    public Product()
    {

    }

    public Product(String Barcode1 ,String Pname1,String Date1,String Time1,String Discription1)
    {
        this.Barcode=Barcode1;
        this.Pname=Pname1;
        this.Date=Date1;
        this.Time=Time1;
        this.Discription=Discription1;
    }


}