package com.Business;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



public class Order {
    private OrderStatus currOrderStatus;
    private Supplier supplier;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private Double totalPrice;
    private int totalNumOfProducts;
    private List<ProductInOrder> products;
    private int branchId ;
    private String address;
    //private String supplierName;
    private int orderId;


    public Order(Supplier supplier , int branchId ,String address , int orderId  ) {
        this.supplier = supplier;
        this.branchId = branchId;
        products = new ArrayList();
        totalPrice = 0.0;
        currOrderStatus = OrderStatus.not_order_yet;
        this.address = address;
        this.orderId= orderId;
        this.orderDate = LocalDate.now();
        this.orderTime= LocalTime.now();
    }

    public void AddProductToOrder(SupplierProduct supplierProduct, int amount, int catalogNum){
        ProductInOrder newProd = new ProductInOrder(supplierProduct,amount ,supplier, catalogNum);
        this.products.add(newProd);
        totalPrice = totalPrice+newProd.getTotalPrice();
        totalNumOfProducts = totalNumOfProducts+ amount;
    }

    public void editProductInOrderAmount(ProductInOrder productInOrder , int newAmount){
        if (currOrderStatus!=OrderStatus.not_order_yet){
            throw new IllegalArgumentException("order has already sent");
        }
        int lastAmount = productInOrder.getAmount();
        double lastPrice = productInOrder.getTotalPrice();
        productInOrder.setAmount(newAmount);
        totalPrice = totalPrice-lastPrice + productInOrder.getTotalPrice();
        totalNumOfProducts = totalNumOfProducts - lastAmount + newAmount;
    }


    public void sendOrder(){
        this.orderDate = LocalDate.now();
        this.orderTime = LocalTime.now();
        this.currOrderStatus= OrderStatus.order_and_wait_for_delivery;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void printOrder(){
        System.out.println("supplier name - " + supplier.getName() + "\n" +
                "branch Id - " + branchId + "\n" +
                "products : \n");
        for (int i=0 ; i<products.size() ; i++){
            System.out.println("product- " + products.get(i).getProduct().getName() + " | catalog number " + products.get(i).getCatalogNum() + " | amount- "+ products.get(i).getAmount()+
                    " | Price before discount- " + products.get(i).getTotalPriceBeforeDiscount() + " | discount- " + products.get(i).getDiscount() + " | price after discount- " + products.get(i).getTotalPrice() + "\n");
        }
        System.out.println("num of Products - " + totalNumOfProducts + "\n" +
                "total order price - " + totalPrice);
        System.out.println("\n");
    }

    public List<ProductInOrder> getProducts(){
        return products;
    }

    public OrderStatus getCurrOrderStatus(){
        return currOrderStatus;
    }

    public int getSupplierId(){
        return supplier.getSupplierId();
    }

    /*public Supplier getSupplier(){
        return supplier;
    }*/

    public void setTotalPrice(double newPrice){
        totalPrice = newPrice;
    }

    public enum OrderStatus{
        not_order_yet,
        order_and_wait_for_delivery,
    }
}


