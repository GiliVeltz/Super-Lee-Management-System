package com.DAL.DTO;

import com.DAL.DAO.DiscountSupplierMapper;

public class DiscountSupplierDTO extends AbstractDTO {
    private double discountAmount;
    private double minimumAmount;
    private int discountId;
    private int discountType;
    private int isPercentageDiscount;


    public DiscountSupplierDTO( int discountType,  double minimumAmount, double discountAmount, int discountId , int isPercentageDiscount) {
        super(new DiscountSupplierMapper());
        this.discountAmount = discountAmount;
        this.minimumAmount = minimumAmount;
        this.discountId = discountId;
        this.discountType = discountType;
        this.isPercentageDiscount = isPercentageDiscount;

    }

    public int getDiscountId() {
        return discountId;
    }
    public int getIsPercentageDiscount(){
        return isPercentageDiscount;
    }

    public int getDiscountType() {
        return discountType;
    }


    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getMinimumAmount() {
        return minimumAmount;
    }
}
