package com.Business;

import com.DAL.DTO.DiscountSupplierDTO;

public class DiscountSuppliersPerOrder extends DiscountSuppliers {

    boolean percentageDiscount;


    public DiscountSuppliersPerOrder(boolean percentageDiscount , double amount , double discount , int Id ){
        super(amount , discount, Id);
        this.percentageDiscount = percentageDiscount;

    }
    public DiscountSuppliersPerOrder(DiscountSupplierDTO discountSupplierDTO){

        super( discountSupplierDTO.getMinimumAmount(),discountSupplierDTO.getDiscountAmount(), discountSupplierDTO.getDiscountId() );
        if ( discountSupplierDTO.getIsPercentageDiscount()!=0) {
            percentageDiscount = true;
        }
        else {
            percentageDiscount = false;
        }
    }

    public double getPriceAfterDiscount(SupplierProduct supplierProduct, double productPrice ) {
        if (percentageDiscount) {
            double price = productPrice * ((1 - getDiscount() / 100));
            return price;
        } else {
            double price = (productPrice - getDiscount());
            return price;
        }

    }

    public boolean getIsPercentage(){
        return percentageDiscount;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isPercentageDiscount() {
        return percentageDiscount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setAmount( int amount){
        this.amount = amount;
    }

    public void setDiscount(double discount){
        this.discount = discount;
    }
}
