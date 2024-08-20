package com.Business.UnitTest;


import com.Business.*;
import org.junit.jupiter.api.Test;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
/*
class SuppliersControllerTest {
   private SuppliersController suppliersController1;
   private int supplier1Id;
   private DeliveryTerms deliveryTerms1;
   private int BNNumber1;
   private int BankAccount1;
   private PaymentOptions paymentOptions1;
   private String supplierName1;



    private int supplier2Id;
    private DeliveryTerms deliveryTerms2;
    private int BNNumber2;
    private int BankAccount2;
    private PaymentOptions paymentOptions2;
    private String supplierName2;

    private int supplier3Id;
    private DeliveryTerms deliveryTerms3;
    private int BNNumber3;
    private int BankAccount3;
    private PaymentOptions paymentOptions3;
    private String supplierName3;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        suppliersController1 = new SuppliersController();
        deliveryTerms1 = DeliveryTerms.delivery_By_Order;
        supplier1Id = 001;
        BNNumber1 = 100;
        BankAccount1 = 100100100;
        paymentOptions1 = PaymentOptions.plus_60;
        supplierName1 = "David david";

        deliveryTerms2 = DeliveryTerms.delivery_By_Order;
        supplier2Id = 002;
        BNNumber2 = 200;
        BankAccount2 = 200200200;
        paymentOptions2 = PaymentOptions.plus_60;
        supplierName2 = "Nadav nadav";

        deliveryTerms3 = DeliveryTerms.delivery_By_Order;
        supplier3Id = 003;
        BNNumber3 = 230;
        BankAccount3 = 300200200;
        paymentOptions3 = PaymentOptions.plus_60;
        supplierName3 = "David nadav";

    }

    @org.junit.jupiter.api.Test
    void testAddBranch() {
       assertEquals(0, suppliersController1.getBranchMap().size());
       suppliersController1.addBranch(1,"Branch_1","Ashdod Hertzel blv");
        assertEquals(1, suppliersController1.getBranchMap().size());

    }

    @org.junit.jupiter.api.Test
    void testAddAndRemoveSupplier() {
       assertEquals(0, suppliersController1.getSupplierMapMap().size());
      // this.addSupplier();

        suppliersController1.addSupplier(supplier1Id,deliveryTerms1,BNNumber1,BankAccount1,paymentOptions1, supplierName1 ,"nadav" , "045345353");
        assertEquals(1, suppliersController1.getSupplierMapMap().size());

        suppliersController1.removeSupplier(001);
        assertEquals(0, suppliersController1.getSupplierMapMap().size());
    }

    @org.junit.jupiter.api.Test
    /*
    in this test, we test that we can add product to the system and remove it
    */
/*
    void testAddProductToSystem(){
        assertEquals(0, suppliersController1.getSystemProduct().size());

        suppliersController1.addProductToTheSystem(9800,"Bamba", "Elit");
        assertEquals(1, suppliersController1.getSystemProduct().size());

        suppliersController1.removeProductFromTheSystem(9800);
        assertEquals(0, suppliersController1.getSystemProduct().size());

    }

    @Test
    /*
    here we test that we can add a contact to supplier and can
    remove a contact from supplier
     */
/*
    void TestAddAndRemoveContactToSupplier(){
        suppliersController1.addSupplier(supplier1Id,deliveryTerms1,BNNumber1,BankAccount1,paymentOptions1, supplierName1, "nadav" , "045345353");
        Supplier sup1 = suppliersController1.getSupplier(001);
        assertEquals(1,sup1.getsCard().getContacts().size());

        //Contacts contacts = new Contacts(123, "DAVID DAVID", 05555555555);
        suppliersController1.addContact(001,"DAVID DAVID", "05555555555");
        assertEquals(2,sup1.getsCard().getContacts().size());
        suppliersController1.removeContact(1,1);
        assertEquals(1,sup1.getsCard().getContacts().size());

    }
/*

    @Test
    /*
    here we test that an order is being added correctly, by one supplier only.
    we test that the one supplier is the one with the lowest price
     */
/*

    void TestAddOrderAllByOneSupplier(){
        suppliersController1.addSupplier(supplier1Id,deliveryTerms1,BNNumber1,BankAccount1,paymentOptions1, supplierName1, "nadav" , "045345353");
        suppliersController1.addSupplier(supplier2Id,deliveryTerms2,BNNumber2,BankAccount2,paymentOptions2, supplierName2, "nadav" , "045345353");

        Supplier sup1 = suppliersController1.getSupplier(001);
        Supplier sup2 = suppliersController1.getSupplier(002);
        suppliersController1.addBranch(1,"Branch_1","Ashdod Hertzel blv");


        suppliersController1.addProductToTheSystem(9800,"Bamba", "Elit");
        suppliersController1.addProductToTheSystem(9900,"Doritos", "Elit");
        suppliersController1.addProductToTheSystem(250,"Coca-Cola Zero", "Coca-Cola");
        suppliersController1.addProductToTheSystem(260,"Sprite zero", "Coca-Cola");
        SupplierProduct pr1 = suppliersController1.getSystemProduct().get(9800);
        SupplierProduct pr2 = suppliersController1.getSystemProduct().get(9900);
        SupplierProduct pr3 = suppliersController1.getSystemProduct().get(250);
        SupplierProduct pr4 = suppliersController1.getSystemProduct().get(260);


        sup1.addProductToSupply(pr1,37,500, 4.9);
        sup1.addProductToSupply(pr2,36,2000, 5.9);
        sup1.addProductToSupply(pr3,35,500, 5.9);
        sup1.addProductToSupply(pr4,34,500, 5.9);

        sup2.addProductToSupply(pr1,37,500, 5.9);
        sup2.addProductToSupply(pr2,36,2000, 5.9);
        sup2.addProductToSupply(pr3,35,500, 5.9);
        sup2.addProductToSupply(pr4,34,500, 5.9);

        Discount discount1 = new Discount(Discount.DiscountType.buy_amount_get_discount_Per_product, 200, 33,436);
        sup1.addDiscountToProduct(pr3,discount1);
        sup1.addDiscountToProduct(pr4,discount1);

        Discount discount2 = new Discount(Discount.DiscountType.buy_amount_get_PercentDiscount_per_Order, 2000, 7,23);
        sup2.addDiscount(discount2);

        Discount discount3 = new Discount(Discount.DiscountType.buy_amount_get_discount_Per_Order, 2000, 100,5);
        sup1.addDiscount(discount3);

        HashMap<Integer, HashMap<Integer, Integer>> productsByBranch = new HashMap<>();
        HashMap<Integer,Integer> productsByIdAndAmount = new HashMap<>();;
        productsByIdAndAmount.put(pr1.getIdNumber(),200);
        productsByIdAndAmount.put(pr2.getIdNumber(),2000);
        productsByIdAndAmount.put(pr3.getIdNumber(),200);
        productsByIdAndAmount.put(pr4.getIdNumber(),200);

        productsByBranch.put(1,productsByIdAndAmount);

        assertEquals(0,suppliersController1.getAllOrders().size());
        assertEquals(0,suppliersController1.getLastOrder().size());


        suppliersController1.addOrder(productsByBranch);
        assertEquals(1,suppliersController1.getAllOrders().size());
        assertEquals(1,suppliersController1.getLastOrder().size());

        assertEquals(001,suppliersController1.getAllOrders().get(0).getSupplierId());

    }
    @org.junit.jupiter.api.Test
     /*
    here we test that an order is being added correctly, by 3 different suppliers.
    we test that the one supplier is the one with the lowest price
     */
/*

    void TestAddOrderThatNeedMoreThanOneSupplier(){
        suppliersController1.addSupplier(supplier1Id,deliveryTerms1,BNNumber1,BankAccount1,paymentOptions1, supplierName1 ,"nadav" , "045345353");
        suppliersController1.addSupplier(supplier2Id,deliveryTerms2,BNNumber2,BankAccount2,paymentOptions2, supplierName2 ,"nadav" , "045345353");
        suppliersController1.addSupplier(supplier3Id,deliveryTerms3,BNNumber3,BankAccount3,paymentOptions3, supplierName3, "nadav" , "045345353");

        Supplier sup1 = suppliersController1.getSupplier(001);
        Supplier sup2 = suppliersController1.getSupplier(002);
        Supplier sup3 = suppliersController1.getSupplier(003);

        suppliersController1.addBranch(1,"Branch_1","Ashdod Hertzel blv");


        suppliersController1.addProductToTheSystem(9800,"Bamba", "Elit");
        suppliersController1.addProductToTheSystem(9900,"Doritos", "Elit");
        suppliersController1.addProductToTheSystem(250,"Coca-Cola Zero", "Coca-Cola");
        suppliersController1.addProductToTheSystem(260,"Sprite zero", "Coca-Cola");
        SupplierProduct pr1 = suppliersController1.getSystemProduct().get(9800);
        SupplierProduct pr2 = suppliersController1.getSystemProduct().get(9900);
        SupplierProduct pr3 = suppliersController1.getSystemProduct().get(250);
        SupplierProduct pr4 = suppliersController1.getSystemProduct().get(260);


        sup1.addProductToSupply(pr1,37,500, 4.9);
        sup1.addProductToSupply(pr2,36,2000, 5.9);
        sup1.addProductToSupply(pr3,35,500, 5.9);

        sup2.addProductToSupply(pr1,37,500, 5.9);
        sup2.addProductToSupply(pr2,36,2000, 5.9);
        sup2.addProductToSupply(pr3,35,500, 5.9);

        sup3.addProductToSupply(pr4,34,500, 7.5);


        Discount discount1 = new Discount(Discount.DiscountType.buy_amount_get_discount_Per_product, 200, 33,7);
        sup1.addDiscountToProduct(pr3,discount1);

        Discount discount2 = new Discount(Discount.DiscountType.buy_amount_get_PercentDiscount_per_Order, 2000, 7,55);
        sup2.addDiscount(discount2);

        Discount discount3 = new Discount(Discount.DiscountType.buy_amount_get_discount_Per_Order, 2000, 100,546);
        sup1.addDiscount(discount3);

        HashMap<Integer, HashMap<Integer, Integer>> productsByBranch = new HashMap<>();
        HashMap<Integer,Integer> productsByIdAndAmount = new HashMap<>();;
        productsByIdAndAmount.put(pr1.getIdNumber(),200);
        productsByIdAndAmount.put(pr2.getIdNumber(),2000);
        productsByIdAndAmount.put(pr3.getIdNumber(),600);
        productsByIdAndAmount.put(pr4.getIdNumber(),200);

        productsByBranch.put(1,productsByIdAndAmount);

        assertEquals(0,suppliersController1.getAllOrders().size());
        assertEquals(0,suppliersController1.getLastOrder().size());

        suppliersController1.addOrder(productsByBranch);
      assertEquals(3,suppliersController1.getAllOrders().size());

      int j=-1;
      for(int i=0; i<suppliersController1.getAllOrders().size();i++){
          if(suppliersController1.getAllOrders().get(i).getSupplierId()==002){
              j=i;
          }
        }

        assertEquals(002,suppliersController1.getAllOrders().get(j).getSupplierId());
        assertEquals(12620.1,suppliersController1.getAllOrders().get(j).getTotalPrice());

        int k=-1;
        for(int i=0; i<suppliersController1.getAllOrders().size();i++){
            if(suppliersController1.getAllOrders().get(i).getSupplierId()==003){
                k=i;
            }
        }

        assertEquals(003,suppliersController1.getAllOrders().get(k).getSupplierId());
        assertEquals(1500,suppliersController1.getAllOrders().get(k).getTotalPrice());
    }

    @org.junit.jupiter.api.Test
    /*
    here we test that an order's status change after the sendOrder function
     */
/*

    void testSendOrder(){
        suppliersController1.addSupplier(supplier1Id,deliveryTerms1,BNNumber1,BankAccount1,paymentOptions1, supplierName1 , "nadav" , "045345353");
        suppliersController1.addBranch(1,"Branch_1","Ashdod Hertzel blv");
        Supplier sup1 = suppliersController1.getSupplier(001);

        suppliersController1.addProductToTheSystem(9800,"Bamba", "Elit");
        suppliersController1.addProductToTheSystem(9900,"Doritos", "Elit");
        suppliersController1.addProductToTheSystem(250,"Coca-Cola Zero", "Coca-Cola");
        suppliersController1.addProductToTheSystem(260,"Sprite zero", "Coca-Cola");
        SupplierProduct pr1 = suppliersController1.getSystemProduct().get(9800);
        SupplierProduct pr2 = suppliersController1.getSystemProduct().get(9900);
        SupplierProduct pr3 = suppliersController1.getSystemProduct().get(250);
        SupplierProduct pr4 = suppliersController1.getSystemProduct().get(260);


        sup1.addProductToSupply(pr1,37,500, 4.9);
        sup1.addProductToSupply(pr2,36,2000, 5.9);
        sup1.addProductToSupply(pr3,35,500, 5.9);
        sup1.addProductToSupply(pr4,34,500, 5.9);

        Discount discount1 = new Discount(Discount.DiscountType.buy_amount_get_discount_Per_product, 200, 33 ,324);
        sup1.addDiscountToProduct(pr3,discount1);
        sup1.addDiscountToProduct(pr4,discount1);

        HashMap<Integer, HashMap<Integer, Integer>> productsByBranch = new HashMap<>();
        HashMap<Integer,Integer> productsByIdAndAmount = new HashMap<>();;
        productsByIdAndAmount.put(pr1.getIdNumber(),200);
        productsByIdAndAmount.put(pr2.getIdNumber(),2000);
        productsByIdAndAmount.put(pr3.getIdNumber(),200);
        productsByIdAndAmount.put(pr4.getIdNumber(),200);

        productsByBranch.put(1,productsByIdAndAmount);

        suppliersController1.addOrder(productsByBranch);
        Order order1= suppliersController1.getLastOrder().get(0);
        assertEquals(Order.OrderStatus.not_order_yet, order1.getCurrOrderStatus());
        suppliersController1.sendOrder(order1);
        assertEquals(Order.OrderStatus.order_and_wait_for_delivery, order1.getCurrOrderStatus());

    }




}*/