package com.DAL.DTO;

import com.DAL.DAO.AbstractMapper;
import com.DAL.DAO.OrderMapper;

import java.util.Date;

public class OrderDTO extends AbstractDTO {
    private int numOfProducts;
    private String address;
    private int branchId;
    private int supplierId;
    private Date orderDate;
    private int orderId;
    private double price;

    public OrderDTO(int numOfProducts,int supplierId, String address,  int branchId,
                             Date orderDate, int orderId  , double price) {
        super(new OrderMapper());
        this.numOfProducts = numOfProducts;
        this.address = address;
        this.branchId = branchId;
        this.supplierId = supplierId;
        this.orderDate = orderDate;
        this.price = price;
        this.orderId = orderId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public int getOrderId() {
        return orderId;
    }

    public double getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public int getBranchId() {
        return branchId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getNumOfProducts() {
        return numOfProducts;
    }

}
