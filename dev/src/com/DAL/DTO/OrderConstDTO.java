package com.DAL.DTO;

import com.DAL.DAO.AbstractMapper;
import com.DAL.DAO.OrderConstMapper;

import java.util.Date;

public class OrderConstDTO extends AbstractDTO {
    private int numOfProducts;
    private String address;
    private int branchId;
    private int supplierId;
    private Date orderDate;
    private int orderId;
    private double price;
    private String Days;

    public OrderConstDTO(int numOfProducts, String address,int branchId, int supplierId,
                    Date orderDate, int orderId  , double price , String days) {
        super(new OrderConstMapper());
        this.numOfProducts = numOfProducts;
        this.address = address;
        this.branchId = branchId;
        this.supplierId = supplierId;
        this.orderDate = orderDate;
        this.price = price;
        this.orderId = orderId;
        this.Days = days;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getNumOfProducts() {
        return numOfProducts;
    }

    public String getAddress() {
        return address;
    }

    public int getBranchId() {
        return branchId;
    }

    public double getPrice() {
        return price;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getDays() {
        return Days;
    }
}


