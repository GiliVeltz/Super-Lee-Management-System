package com.Business;
import java.time.DayOfWeek;
import java.util.*;


public class OrderConst extends Order {
    private List<DayOfWeek> supplyConstantDays;

    public OrderConst(Supplier supplier, int branchId, String branchAddress ,int orderID, List<DayOfWeek> supplyConstantDays) {
        super(supplier, branchId, branchAddress, orderID);
        this.supplyConstantDays = supplyConstantDays;
    }







}
