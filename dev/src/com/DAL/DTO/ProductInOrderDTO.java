package com.DAL.DTO;

import com.Business.PaymentOptions;
import com.DAL.DAO.AbstractMapper;
import com.DAL.DAO.ProductInOrderMapper;

public class ProductInOrderDTO extends AbstractDTO {

        private int productId;
        private int supplierId;
        private int amount;
        private double priceBeforeDiscount;
        private double priceAfterDiscount;
        private double discount;
        private int catalogNumber;
        private int orderId;

        public ProductInOrderDTO(int productId,int supplierId, int amount,  double priceBeforeDiscount,double priceAfterDiscount,
                                 double discount, int catalogNumber, int orderId ) {
            super(new ProductInOrderMapper());
            this.productId = productId;
            this.priceAfterDiscount = priceAfterDiscount;
            this.priceBeforeDiscount = priceBeforeDiscount;
            this.supplierId = supplierId;
            this.amount = amount;
            this.discount = discount;
            this.catalogNumber = catalogNumber;
            this.orderId = orderId;
        }

    public int getProductId() {
        return productId;
    }

    public int getCatalogNumber() {
        return catalogNumber;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public double getDiscount() {
        return discount;
    }

    public int getAmount() {
        return amount;
    }

    public double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public double getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public int getOrderId() {
        return orderId;
    }

}

