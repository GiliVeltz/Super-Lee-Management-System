package com.DAL.DTO;

import com.DAL.DAO.AbstractMapper;
import com.DAL.DAO.ProductQuantitiesAndPriceMapper;

public class ProductQuantitiesAndPriceDTO extends AbstractDTO{
    private int productId;
    private int supplierId;
    private int amountCanSupply;
    private double price;
    private int catalogNumber;


    public ProductQuantitiesAndPriceDTO(int supplierId,int productId, int amountCanSupply,  double price,int catalogNumber ) {
        super(new ProductQuantitiesAndPriceMapper());
        this.productId = productId;
        this.price = price;
        this.supplierId = supplierId;
        this.amountCanSupply = amountCanSupply;
        this.catalogNumber = catalogNumber;

    }

    public int getSupplierId() {
        return supplierId;
    }

    public int getProductId() {
        return productId;
    }

    public double getPrice() {
        return price;
    }

    public int getAmountCanSupply() {
        return amountCanSupply;
    }

    public int getCatalogNumber() {
        return catalogNumber;
    }
}
