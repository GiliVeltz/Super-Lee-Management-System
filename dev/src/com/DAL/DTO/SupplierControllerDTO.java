package com.DAL.DTO;

import com.Business.DeliveryTerms;
import com.DAL.DAO.AbstractMapper;
import com.DAL.DAO.SupplierControllerMapper;

public class SupplierControllerDTO extends AbstractDTO {

    private int lastOrderIndex;
    private int orderIdCounter;
    private int discountCounter ;

    public SupplierControllerDTO(int lastOrderIndex , int orderIdCounter , int discountCounter) {
        super(new SupplierControllerMapper());
        this.discountCounter = discountCounter;
        this.orderIdCounter = orderIdCounter;
        this.lastOrderIndex = lastOrderIndex;
    }

    public int getDiscountCounter() {
        return discountCounter;
    }

    public int getLastOrderIndex() {
        return lastOrderIndex;
    }

    public int getOrderIdCounter() {
        return orderIdCounter;
    }
}
