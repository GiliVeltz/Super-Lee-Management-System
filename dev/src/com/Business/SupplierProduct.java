package com.Business;

public class SupplierProduct {
    private int IdNumber;
    private String name;
    private String producer;



    public SupplierProduct(int IdNumber , String name , String producer) {
        this.name = name;
        this.producer = producer;
        this.IdNumber = IdNumber;
    }


        public int getIdNumber() {
        return IdNumber;
    }

    public String getName() {
        return name;
    }

    public String getProducer() {
        return producer;
    }

    public void setDescription(String name) {
        this.name = name;
    }


    public void setProducer(String producer) {
        this.producer = producer;
    }


}

