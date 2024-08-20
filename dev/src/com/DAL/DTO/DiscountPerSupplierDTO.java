package com.DAL.DTO;

import com.DAL.DAO.DiscountPerSupplierMapper;

public class DiscountPerSupplierDTO extends AbstractDTO{
    private int discountId;
    private int supplierId;

    public DiscountPerSupplierDTO(int supplierId, int discountId ) {
        super(new DiscountPerSupplierMapper());
        this.discountId = discountId;
        this.supplierId = supplierId;

    }

    public int getDiscountId() {
        return discountId;
    }

    public int getSupplierId() {
        return supplierId;
    }
}
