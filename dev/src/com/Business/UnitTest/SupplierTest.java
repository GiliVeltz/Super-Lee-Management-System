package com.Business.UnitTest;

import com.Business.*;
import com.Business.PaymentOptions;
import com.Business.SuppliersController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SupplierTest {

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
/*

    @BeforeEach
    void setUp() {
        suppliersController1 = new SuppliersController();
        deliveryTerms1 = DeliveryTerms.delivery_By_Order;
        supplier1Id = 001;
        BNNumber1 = 100;
        BankAccount1 = 100100100;
        paymentOptions1 = PaymentOptions.plus_60;
        supplierName1 = "David david";

        suppliersController1.addSupplier(supplier1Id,deliveryTerms1,BNNumber1,BankAccount1,paymentOptions1, supplierName1, "nadav" , "045345353");


        deliveryTerms2 = DeliveryTerms.delivery_By_Order;
        supplier2Id = 002;
        BNNumber2 = 200;
        BankAccount2 = 200200200;
        paymentOptions2 = PaymentOptions.plus_60;
        supplierName2 = "Nadav nadav";

        suppliersController1.addSupplier(supplier2Id,deliveryTerms2,BNNumber2,BankAccount2,paymentOptions2, supplierName2, "nadav" , "045345353");

    }

    @Test
    // here we test adding product to supplier and removing it
    void TestAddAndRemoveProductToSupply() {

        Supplier sup1 = suppliersController1.getSupplier(001);

        assertEquals(0,sup1.getProductsByCatalogNum().size());
        assertEquals(0,sup1.getProductsQuantities().size());
        assertEquals(0,sup1.getProductsPrice().size());

        suppliersController1.addProductToTheSystem(9800,"Bamba", "Elit");
        suppliersController1.addProductToTheSystem(9900,"Bisli", "Elit");
        suppliersController1.addProductToTheSystem(9970,"Doritos", "Osem");

        SupplierProduct pr1 = suppliersController1.getSystemProduct().get(9800);
        SupplierProduct pr2 = suppliersController1.getSystemProduct().get(9900);
        SupplierProduct pr3 = suppliersController1.getSystemProduct().get(9970);


       sup1.addProductToSupply(pr1,37,500, 5.9);
        sup1.addProductToSupply(pr2,38,500, 5.9);
        sup1.addProductToSupply(pr3,39,500, 5.9);

        assertEquals(3,sup1.getProductsByCatalogNum().size());
        assertEquals(3,sup1.getProductsQuantities().size());
        assertEquals(3,sup1.getProductsPrice().size());

        sup1.removeProduct(pr1);
        assertEquals(2,sup1.getProductsByCatalogNum().size());
        assertEquals(2,sup1.getProductsQuantities().size());
        assertEquals(2,sup1.getProductsPrice().size());
    }

    @Test
        // here we test updating a price to a product that the supplier has
    void TestUpdateProductPrice() {
        Supplier sup1 = suppliersController1.getSupplier(001);
        suppliersController1.addProductToTheSystem(9800,"Bamba", "Elit");

        SupplierProduct pr1 = suppliersController1.getSystemProduct().get(9800);
        sup1.addProductToSupply(pr1,37,500, 5.9);


        assertEquals(5.9, sup1.getProductsPrice().get(pr1));

        sup1.updateProductPrice(pr1,5.95);
        assertEquals(5.95, sup1.getProductsPrice().get(pr1));

    }


    @Test
        // here we test adding a discount to a product that the supplier has
        // and removing it.
    void TestAddDiscountToProduct() {
        Supplier sup1 = suppliersController1.getSupplier(001);
        suppliersController1.addProductToTheSystem(9800,"Bamba", "Elit");

        SupplierProduct pr1 = suppliersController1.getSystemProduct().get(9800);
        sup1.addProductToSupply(pr1,37,500, 5.9);


        assertEquals(0,sup1.getProductsDiscount().get(pr1).size());

        Discount discount11 = new Discount(Discount.DiscountType.buy_amount_get_discount_Per_product, 200, 10 , 1);

        sup1.addDiscountToProduct(pr1,discount11);
        assertEquals(1,sup1.getProductsDiscount().get(pr1).size());

        sup1.removeDiscountToProduct(pr1,discount11);
        assertEquals(0,sup1.getProductsDiscount().get(pr1).size());

    }

    @Test
        // here we test that the discounts to a product are being sorted from the smallest
        // to the biggest.
    void TestSortDiscounts() {

        Supplier sup1 = suppliersController1.getSupplier(001);
        suppliersController1.addProductToTheSystem(9800,"Bamba", "Elit");

        SupplierProduct pr1 = suppliersController1.getSystemProduct().get(9800);
        sup1.addProductToSupply(pr1,37,500, 5.9);


        assertEquals(0,sup1.getProductsDiscount().get(pr1).size());

        Discount discount11 = new Discount(Discount.DiscountType.buy_amount_get_PercentDiscount_perProduct, 200, 20 ,1);
        Discount discount12 = new Discount(Discount.DiscountType.buy_amount_get_discount_Per_product, 130, 39, 133);
        Discount discount13 = new Discount(Discount.DiscountType.buy_amount_get_discount_Per_product, 199, 50, 314);

        sup1.addDiscountToProduct(pr1,discount11);
        sup1.addDiscountToProduct(pr1,discount12);
        sup1.addDiscountToProduct(pr1,discount13);

        assertEquals(130,sup1.getProductsDiscount().get(pr1).get(0).getAmount());
        assertEquals(199,sup1.getProductsDiscount().get(pr1).get(1).getAmount());
        assertEquals(200,sup1.getProductsDiscount().get(pr1).get(2).getAmount());
    }

    @Test
        // here we test that we check correctly which supplier can supply all by himself
        // an entire order.
    void TestIsSupplyAllTheProducts() {

        Supplier sup1 = suppliersController1.getSupplier(001);
        Supplier sup2 = suppliersController1.getSupplier(002);

        suppliersController1.addProductToTheSystem(9800,"Bamba", "Elit");
        suppliersController1.addProductToTheSystem(9900,"Doritos", "Elit");
        suppliersController1.addProductToTheSystem(250,"Coca-Cola Zero", "Coca-Cola");
        suppliersController1.addProductToTheSystem(260,"Sprite zero", "Coca-Cola");

        SupplierProduct pr1 = suppliersController1.getSystemProduct().get(9800);
        SupplierProduct pr2 = suppliersController1.getSystemProduct().get(9900);
        SupplierProduct pr3 = suppliersController1.getSystemProduct().get(250);
        SupplierProduct pr4 = suppliersController1.getSystemProduct().get(260);

        Map<SupplierProduct,Integer> productsToSupply = new HashMap<>();
        productsToSupply.put(pr1,200);
        productsToSupply.put(pr2,2000);
        productsToSupply.put(pr3,200);
        productsToSupply.put(pr4,200);

        sup1.addProductToSupply(pr1,37,500, 5.9);
        sup1.addProductToSupply(pr2,36,2000, 5.9);
        sup1.addProductToSupply(pr3,35,500, 5.9);
        sup1.addProductToSupply(pr4,34,500, 5.9);

        sup2.addProductToSupply(pr1,37,500, 5.9);
        sup2.addProductToSupply(pr2,36,500, 5.9);
        sup2.addProductToSupply(pr3,35,500, 5.9);
        sup2.addProductToSupply(pr4,34,500, 5.9);

        assertTrue(sup1.isSupplyAllTheProducts(productsToSupply));
        assertFalse(sup2.isSupplyAllTheProducts(productsToSupply));

    }

    @Test
        // here we test that get the right price for all the order from a supplier
        // we check that the discounts are taken into account.
    void getPriceForAllOrder() {
        Supplier sup1 = suppliersController1.getSupplier(001);
        Supplier sup2 = suppliersController1.getSupplier(002);

        suppliersController1.addProductToTheSystem(9800,"Bamba", "Elit");
        suppliersController1.addProductToTheSystem(9900,"Doritos", "Elit");
        suppliersController1.addProductToTheSystem(250,"Coca-Cola Zero", "Coca-Cola");
        suppliersController1.addProductToTheSystem(260,"Sprite zero", "Coca-Cola");
        SupplierProduct pr1 = suppliersController1.getSystemProduct().get(9800);
        SupplierProduct pr2 = suppliersController1.getSystemProduct().get(9900);
        SupplierProduct pr3 = suppliersController1.getSystemProduct().get(250);
        SupplierProduct pr4 = suppliersController1.getSystemProduct().get(260);
        Map<SupplierProduct,Integer> productsToSupply = new HashMap<>();
        productsToSupply.put(pr1,200);
        productsToSupply.put(pr2,2000);
        productsToSupply.put(pr3,200);
        productsToSupply.put(pr4,200);

        sup1.addProductToSupply(pr1,37,500, 4.9);
        sup1.addProductToSupply(pr2,36,2000, 5.9);
        sup1.addProductToSupply(pr3,35,500, 5.9);
        sup1.addProductToSupply(pr4,34,500, 5.9);

        sup2.addProductToSupply(pr1,37,500, 5.9);
        sup2.addProductToSupply(pr2,36,2000, 5.9);
        sup2.addProductToSupply(pr3,35,500, 5.9);
        sup2.addProductToSupply(pr4,34,500, 5.9);

        assertEquals(15140,sup1.getPriceForAllOrder(productsToSupply) );
        assertEquals(15340,sup2.getPriceForAllOrder(productsToSupply) );

        Discount discount1 = new Discount(Discount.DiscountType.buy_amount_get_discount_Per_product, 200, 3 ,232);
        sup1.addDiscountToProduct(pr3,discount1);
        sup1.addDiscountToProduct(pr4,discount1);

        assertEquals(13940,sup1.getPriceForAllOrder(productsToSupply) );

        Discount discount2 = new Discount(Discount.DiscountType.buy_amount_get_PercentDiscount_per_Order, 2000, 7 ,3242);
        sup2.addDiscount(discount2);
        assertEquals(14266.2,sup2.getPriceForAllOrder(productsToSupply) );

        Discount discount3 = new Discount(Discount.DiscountType.buy_amount_get_discount_Per_Order, 2000, 100 ,3242);
        sup1.addDiscount(discount3);
        assertEquals(13840,sup1.getPriceForAllOrder(productsToSupply) );

        Discount discount4 = new Discount(Discount.DiscountType.buy_amount_get_PercentDiscount_per_Order, 3000, 11 ,2342);
        sup2.addDiscount(discount4);
        assertEquals(13652.6,sup2.getPriceForAllOrder(productsToSupply) );

    }




}*/
}