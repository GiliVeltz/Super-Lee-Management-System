package com.DAL.DTO;

import com.Business.PaymentOptions;
import com.DAL.DAO.AbstractMapper;
import com.DAL.DAO.ProductSupplierDiscountMapper;

public class ProductSupplierDiscountsDTO extends AbstractDTO {
        private int productId;
        private int supplierId;
        private int discountId;


        public ProductSupplierDiscountsDTO(int productId ,  int supplierId, int discountId ) {
            super(new ProductSupplierDiscountMapper());
            this.productId = productId;
            this.discountId = discountId;
            this.supplierId = supplierId;

        }

    public int getSupplierId() {
        return supplierId;
    }

    public int getDiscountId() {
        return discountId;
    }

    public int getProductId() {
        return productId;
    }
}

