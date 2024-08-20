package com.DAL.DTO;

import com.DAL.DAO.SupplierMapper;
import com.DAL.DAO.SupplierProductMapper;

public class SupplyProductDTO extends AbstractDTO{
    private int Id;
    private String name;
    private String manufacture;

    public SupplyProductDTO( int Id ,String name , String manufacture){
        super(new SupplierProductMapper());
        this.Id= Id;
        this.name = name;
        this.manufacture = manufacture;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return Id;
    }

    public String getManufacture() {
        return manufacture;
    }
}
