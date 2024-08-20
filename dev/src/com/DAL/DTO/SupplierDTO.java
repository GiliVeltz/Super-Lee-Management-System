package com.DAL.DTO;

import com.DAL.DAO.SupplierMapper;

public class SupplierDTO extends AbstractDTO {
    private int Id;
    private String name;
    private boolean canDeliver;
    private String days;
    private String address;

    public SupplierDTO( String name ,int idNumber , int canDeliver, String days,String address){
        super(new SupplierMapper());
        this.Id= idNumber;
        this.name = name;
        if (canDeliver!=0){
            this.canDeliver =true;
        }
        else this.canDeliver= false;
        this.days= days;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return Id;
    }
    public boolean getCanDeliver(){
        return canDeliver;
    }
    public String getDays(){
        return days;
    }

    public String getAddress() {
        return address;
    }
}
