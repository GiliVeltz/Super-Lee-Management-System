package com.Business;

public class ProductInOrder {
    private SupplierProduct supplierProduct;
    private int catalogNum;
    private int amount;
    private Supplier supplier;
    private double totalPrice;
    private double totalPriceBeforeDiscount;
    private  double discount;


    public ProductInOrder(SupplierProduct supplierProduct, int amount , Supplier supplier, int catalogNum){
         this.supplierProduct = supplierProduct;
         this.amount = amount;
         this.supplier= supplier;
         this.totalPrice = supplier.getPriceForProductAndAmount(supplierProduct, amount)*amount;
         this.totalPriceBeforeDiscount = supplier.getPriceBeforeDiscount(supplierProduct)*amount;
         discount = totalPriceBeforeDiscount - totalPrice;
         this.catalogNum = catalogNum;



    }


    public int getAmount() {
        return amount;
    }

    public SupplierProduct getProduct() {
        return supplierProduct;
    }

    public double getTotalPriceBeforeDiscount(){
        return totalPriceBeforeDiscount;
    }
    public double getDiscount(){
        return discount;
    }

   public double getTotalPrice() {
       return totalPrice;
   }

   public  int getCatalogNum(){
        return catalogNum;
   }
    public void setAmount(int amount) {
        this.amount = amount;
        totalPriceBeforeDiscount = supplier.getPriceBeforeDiscount(supplierProduct)*amount;
        totalPrice = supplier.getPriceForProductAndAmount(supplierProduct, amount);
        discount = totalPriceBeforeDiscount - totalPrice;
    }



}
