package com.DAL.DTO;

import com.DAL.DAO.SupplierContactMapper;

public class SupplierContactDTO extends AbstractDTO {
    private int id ;
    private String phoneNumber;
    private int SupplierId;
    private String name;

    public SupplierContactDTO(int id, String phoneNumber , String name ,int supplierId){
        super(new SupplierContactMapper());
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.SupplierId = supplierId;
        this.name= name;

    }

    public int getId() {
        return id;
    }

    public int getSupplierId() {
        return SupplierId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
