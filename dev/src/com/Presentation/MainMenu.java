package com.Presentation;

import com.Business.*;
import com.Service.SupplierControllerService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenu {
}

   /* private SupplierControllerService suppliersController;

    public MainMenu() {
        this.suppliersController = new SupplierControllerService();
    }

    public void startMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("hey, welcome to the Suppliers System \n" +
                "please select your next action: \n" +
                "1 - add new Supplier \n" +
                "2 - remove supplier from the system \n" +
                "3 - add new order \n" +
                "4 - look at Last order \n" +
                "5 - edit existing supplier \n" +
                "6 - load system data \n" +
                "7 - add new branch \n" +
                "8 - add products to supplier \n" +
                "9 - add new product to the system \n" +
                "10 - add new discount to supplier \n" +
                "11 - add new contact to supplier \n" +
                "12 - remove contact from supplier \n" +
                "13 - remove product from supplier \n" +
                "0 - exit from the system"



        );
        int option = scanner.nextInt();
        while (option < 0|| option >13 ){
            System.out.println("please select an option from the List - \n" +
            "please select your next action: \n" +
                    "1 - add new Supplier \n" +
                    "2 - remove supplier from the system \n" +
                    "3 - add new order \n" +
                    "4 - look at Last order \n" +
                    "5 - edit existing supplier \n" +
                    "6 - load system data \n" +
                    "7 - add new branch \n" +
                    "8 - add products to supplier \n" +
                    "9 - add new product to the system \n" +
                    "10 - add new discount to supplier \n" +
                    "11 - add new contact to supplier \n" +
                    "12 - remove contact from supplier \n " +
                    "0 - exit from the system" );
            option  = scanner.nextInt();
        }
        if (option == 0){

        }
        if (option == 1) {
            addNewSupplier();
            System.out.println();
            startMenu();
        } else if (option == 2) {
            removeSupplier();
            System.out.println();
            startMenu();
        } else if (option == 3) {
            addNewOrder();
            System.out.println();
            startMenu();
        } else if (option == 4) {
            printLastOrder();
            System.out.println();
            startMenu();
        } else if (option == 5) {
            editSupplier();
            System.out.println();
            startMenu();
        } else if (option == 6) {
            setUP();
            System.out.println();
            startMenu();
        } else if (option == 7) {
            addNewBranch();
            System.out.println();
            startMenu();
        } else if (option == 8) {
            addProductsToSupplier();
            System.out.println();
            startMenu();
        }
        else if (option == 9){
            addProductsToTheSystem();
            System.out.println();
            startMenu();
        }

        else if (option == 10){
            addDiscountToSupplier();
            System.out.println();
            startMenu();
        }
        else if (option == 11){
            addContact();
            System.out.println();
            startMenu();
        }
        else if (option == 12){
            removeContact();
            System.out.println();
            startMenu();
        }
        else if (option == 13){
            removeProductFromSupplier();
            System.out.println();
            startMenu();
        }


    }

    public void addDiscountToSupplier(){
        System.out.println("you choose to add new discount to supplier \n" +
                "please enter the supplierId : \n");
        suppliersController.printSuppliers();
        Scanner scanner = new Scanner(System.in);
        int supplierId = scanner.nextInt();
        System.out.println("please select your discount type: \n" +
                "1- add discount for specific product \n" +
                "2- add discount fot total price order");
        int product_Order = 3;
        boolean success = true;
        int productId= 0;
        Discount.DiscountType discountTypeDis = Discount.DiscountType.buy_amount_get_discount_Per_product;
        while (product_Order!= 1 && product_Order!=2) {
            product_Order = scanner.nextInt();
            if (product_Order == 1) {
                System.out.println("please enter the product Id \n");
                productId = scanner.nextInt();
                int discountType = 3;
                while (discountType!= 1 &&discountType!=2 ) {
                    System.out.println("please select your discount type: \n" +
                            "1- buy x amount get y% Discount\n" +
                            "2- buy x amount get y Discount");
                    discountType =scanner.nextInt();
                    if (discountType == 1){
                        discountTypeDis= Discount.DiscountType.buy_amount_get_PercentDiscount_perProduct;
                    }
                    if (discountType==2){
                        discountTypeDis = Discount.DiscountType.buy_amount_get_discount_Per_product;
                    }
                };

            }
            if (product_Order == 2) {
                int discountType = 3;
                while (discountType!= 1 && discountType!=2 ) {
                    System.out.println("please select your discount type: \n" +
                            "1- buy in total price x get y% Discount\n" +
                            "2- buy in total price x get y Discount");
                    discountType =scanner.nextInt();
                    if (discountType == 1){
                        discountTypeDis= Discount.DiscountType.buy_amount_get_PercentDiscount_per_Order;
                    }
                    if (discountType==2){
                        discountTypeDis = Discount.DiscountType.buy_amount_get_discount_Per_Order;
                    }
                };
            }

        }
        System.out.println("please enter the minimum amount for the discount (x)");
        double amount =scanner.nextInt();
        System.out.println("please enter the discount number (y)");
        double discount =scanner.nextInt();
        if (discountTypeDis== Discount.DiscountType.buy_amount_get_discount_Per_Order ||
        discountTypeDis == Discount.DiscountType.buy_amount_get_PercentDiscount_per_Order) {
            success = suppliersController.addDiscountToOrder(supplierId, amount,discount , discountTypeDis );
        }
        else {
            success =suppliersController.addDiscountToProduct(supplierId, productId, (int)amount ,discount ,discountTypeDis );
        }
        if (success){
            System.out.println("the discount was added successfully" );
        }
    }

    public void addProductsToTheSystem(){
        System.out.println("you choose to add new product to the system \n" +
                "please enter the product serial number: \n");
        Scanner scanner = new Scanner(System.in);
        int productID = scanner.nextInt();
        System.out.println("please enter your product name: \n");
        scanner.nextLine();
        String productName = scanner.nextLine();
        System.out.println("please enter the product producer name \n");
        String manufacturer = scanner.nextLine();
        boolean success = suppliersController.addProductToTheSystem(productID, productName, manufacturer);
        if (success){
            System.out.println("the product was added successfully" );
        }
    }


    public void setUP() {
        Contacts con1 = new Contacts(1 ,"nadav" , "03223525");
        boolean success = suppliersController.addNewSupplier(1, DeliveryTerms.delivery_By_Order, 353, 3454, PaymentOptions.plus_30, "Tnuva","nadav" , "045345353");
        success = success & suppliersController.addNewSupplier(2, DeliveryTerms.delivery_By_Order, 334, 3434, PaymentOptions.plus_60, "Osem","nadav" , "045345353");
        success = success & suppliersController.addNewSupplier(3, DeliveryTerms.delivery_By_Order, 334, 3434, PaymentOptions.plus_60, "Telma","nadav" , "045345353");
        success = success & suppliersController.addNewSupplier(4, DeliveryTerms.delivery_By_Order, 334, 3434, PaymentOptions.plus_60, "Coca-Cola","nadav" , "045345353");
        success = success & suppliersController.addBranch(1, "TelAviv", "Dizingoff ,1");
        success = success & suppliersController.addBranch(2, "Jerusalem", "Rechov ,13522");
        success = success & suppliersController.addBranch(3, "BeerSheva", "Rager ,3");
        success = success & suppliersController.addProductToTheSystem(1 ,"cola" , "a");
        success = success & suppliersController.addProductToTheSystem(2 ,"sprite" , "a");
        success = success & suppliersController.addProductToTheSystem(3 ,"x" , "adf");
        success = success & suppliersController.addProductToTheSystem(4 ,"y" , "adsfs");
        success = success & suppliersController.addProductToTheSystem(5 ,"z" , "adgh");
        success = success & suppliersController.addProductToTheSystem(6 ,"w" , "a");
        success = success & suppliersController.addProductToTheSystem(7 ,"q" , "a");
        success = success & suppliersController.addProductToSupplier(1 ,1 ,1 ,100 ,7.9);
        success = success & suppliersController.addProductToSupplier(1 ,3 ,2 ,200 ,1.9);
        success = success & suppliersController.addProductToSupplier(2 ,3 ,1 ,600 ,146.9);
        success = success & suppliersController.addProductToSupplier(2 ,1 ,5 ,50 ,14.5);
        success = success & suppliersController.addProductToSupplier(4 ,7 ,1 ,200 ,5);
        success = success & suppliersController.addProductToSupplier(1 ,6 ,12 ,2400 ,5.3);
        suppliersController.addDiscountToOrder(1,10 , 13 , Discount.DiscountType.buy_amount_get_discount_Per_Order);
        HashMap<Integer, HashMap<Integer, Integer>> toOrder = new HashMap<>();
        HashMap<Integer, Integer> products = new HashMap<>();
        HashMap<Integer, Integer> products2 = new HashMap<>();
        products.put(1, 5);
        products.put(3, 5);
        products2.put(7, 15);
        products2.put(6, 40);

        toOrder.put(1, products);
        toOrder.put(2, products2);
        success = success & suppliersController.addOrder(toOrder);
        if (success) {
            System.out.println("the information was loaded successfully");
        }
        suppliersController.addBranch(1,"afss","afsaf");
        suppliersController.addProductToTheSystem(1 , "coal" ,"dsfs");
        suppliersController.addNewSupplier(1,DeliveryTerms.delivery_By_Order, 3234, 3242,PaymentOptions.plus_30 , "sdfsd");
        suppliersController.addProductToSupplier(1,1,34,124 ,5);
        suppliersController.addDiscountToProduct(1, 1,12,4, Discount.DiscountType.buy_amount_get_discount_Per_product);
        HashMap<Integer, HashMap<Integer, Integer>> toOrder = new HashMap<>();
        HashMap<Integer, Integer> products = new HashMap<>();
        HashMap<Integer, Integer> products2 = new HashMap<>();
        products.put(1, 13);
        toOrder.put(1,products);
        suppliersController.addOrder(toOrder);
        suppliersController.printLastOrder();


/*
//    }

 //   public void addNewBranch() {
        System.out.println("you choose to add a new branch to the system \n" +
                "please enter your branch Id: \n");
        Scanner scanner = new Scanner(System.in);
        int branchId = scanner.nextInt();
        System.out.println("please enter your branch name: \n");
        scanner.nextLine();
        String branchName = scanner.nextLine();
        System.out.println("please enter your branch address: \n");
        String address = scanner.nextLine();
        boolean success = suppliersController.addBranch(branchId, branchName, address);
        if (success){
            System.out.println("you added successfully branch number: " + branchId + " - with name: " + branchName );
        }

    }

    public void editSupplier() {
        boolean success = false;
        System.out.println("you choose to edit a supplier from the system \n" +
                "please select your supplier Id: \n");
        suppliersController.printSuppliers();
        Scanner scanner = new Scanner(System.in);
        int supplierId = scanner.nextInt();
        System.out.println("please select what info you want to edit:\n" +
                "1- delivery term \n" +
                "2- BN number \n" +
                "3- bank account number \n" +
                "4- payment terms \n" +
                "5- supplier name \n"
        );
        int optionToEdit = scanner.nextInt();
        while (optionToEdit< 1 || optionToEdit> 5){
            System.out.println("please select what info you want to edit:\n" +
                    "1- delivery term \n" +
                    "2- BN number \n" +
                    "3- bank account number \n" +
                    "4- payment terms \n" +
                    "5- supplier name \n"
            );
            optionToEdit = scanner.nextInt();
        }
        if (optionToEdit == 1) {
            System.out.println("please select supplier Delivery terms \n" +
                    "1 - no_delivery,\n" +
                    "2 - delivery By Order,\n" +
                    "3 - delivery on regular days;  \n"
            );
            int deliveryTermsScanner = scanner.nextInt();
            while (deliveryTermsScanner<1 || deliveryTermsScanner>3 ){
                System.out.println("please select supplier Delivery terms \n" +
                        "1 - no_delivery,\n" +
                        "2 - delivery By Order,\n" +
                        "3 - delivery on regular days;  \n"
                );
                deliveryTermsScanner = scanner.nextInt();
            }
            DeliveryTerms deliveryTerms = DeliveryTerms.no_delivery;
            if (deliveryTermsScanner == 1) {
                deliveryTerms = DeliveryTerms.no_delivery;
            } else if (deliveryTermsScanner == 2) {
                deliveryTerms = DeliveryTerms.delivery_By_Order;
            } else if (deliveryTermsScanner == 3) {
                deliveryTerms = DeliveryTerms.delivery_on_regular_days;
            }
            success = suppliersController.editSupplierDeliveryTerms(supplierId, deliveryTerms);
        } else if (optionToEdit == 2) {
            System.out.println("please enter the supplier BN number: \n"
            );
            int bnNum = scanner.nextInt();
            success = suppliersController.editSupplierBnNumber(supplierId, bnNum);
        } else if (optionToEdit == 3) {
            System.out.println("please enter the supplier bank account number: \n"
            );
            int bankAccount = scanner.nextInt();
            success = suppliersController.editSupplierBankAccount(supplierId, bankAccount);
        } else if (optionToEdit == 4) {
            System.out.println("please select supplier Payment terms \n" +
                    "1 - regular,\n" +
                    "2 - shotef plus 30,\n" +
                    "3 - shotef plus 60;  \n"
            );
            int paymentOptionScanner = scanner.nextInt();
            while (paymentOptionScanner<1 || paymentOptionScanner>3){
                System.out.println("please select supplier Payment terms \n" +
                        "1 - regular,\n" +
                        "2 - shotef plus 30,\n" +
                        "3 - shotef plus 60;  \n"
                );
                paymentOptionScanner = scanner.nextInt();
            }
            PaymentOptions payOption = PaymentOptions.regular;
            if (paymentOptionScanner == 1) {
                payOption = PaymentOptions.regular;
            } else if (paymentOptionScanner == 2) {
                payOption = PaymentOptions.plus_30;
            } else if (paymentOptionScanner == 3) {
                payOption = PaymentOptions.plus_60;
            }
            success = suppliersController.editSupplierPaymentTerms(supplierId, payOption);
        } else if (optionToEdit == 5) {
            System.out.println("please enter supplier name and then press enter: \n");
            String supplierName = scanner.nextLine();
            success = suppliersController.editSupplierName(supplierId, supplierName);

        }
        System.out.println("the change was made successfully");

    }


    public void printLastOrder() {
        suppliersController.printLastOrder();

    }

    public void addNewOrder() {
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, HashMap<Integer, Integer>> productsToOrder = new HashMap<>();
        System.out.println("you choose to add New Order we will find you the best supplier to your order \n" +
                "please enter the branch Id for this order: \n");
        int branchId = scanner.nextInt();
        HashMap<Integer, Integer> productsFromSpecificBranch = new HashMap();
        productsToOrder.put(branchId, productsFromSpecificBranch);
        System.out.println("please enter the product Id you want to Order: \n");
        int productId = scanner.nextInt();
        System.out.println("please enter the amount you want to order: \n");
        int amount = scanner.nextInt();
        productsFromSpecificBranch.put(productId,amount);
        while (true) {
            while (true) {
                System.out.println("if you finish your order for this branch please enter: -1 \n" +
                        "else - please enter the product Id you want to Order: \n");
                productId = scanner.nextInt();
                if (productId == -1) {
                    productsToOrder.put(branchId, productsFromSpecificBranch);
                    break;
                }
                System.out.println("please enter the amount you want to order: \n");
                amount = scanner.nextInt();
                productsFromSpecificBranch.put(productId, amount);
            }
            System.out.println("if you finish your order please enter: -1 \n"
                    + "to order to another branch please enter branchId :");
            branchId = scanner.nextInt();
            if (branchId == -1) {
                break;
            }
            if (productsToOrder.get(branchId) != null) {
                productsFromSpecificBranch = productsToOrder.get(branchId);
            } else {
                productsFromSpecificBranch = new HashMap();
            }
        }
        boolean success = suppliersController.addOrder(productsToOrder);
        if (success){
            System.out.println("you choose to finish your order , we will make everything to deliver your order as fast as we can\n");
        }



    }

    public void removeSupplier(){

        System.out.println("you choose to remove a supplier from the system \n" +
                "please select your supplier Id: \n");
        Scanner scanner = new Scanner(System.in);
        suppliersController.printSuppliers();
        int supplierId = scanner.nextInt();

        boolean success = suppliersController.RemoveSupplier(supplierId);
        if (success){
            System.out.println("the supplier has removed successfully");
        }

    }

    public void addContact () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("you choose to add new contact to supplier \n") ;
        suppliersController.printSuppliers();
        System.out.println("please enter the supplier Id: \n");
        int supplierId = scanner.nextInt();
        System.out.println("please enter the contact name: \n");
        scanner.nextLine();
        String contactName = scanner.nextLine();
        System.out.println("please enter the contact phone number: \n");
        String phoneNumber = scanner.nextLine();
        boolean success = suppliersController.addContactToSupplier(supplierId ,contactName ,phoneNumber);
        if (success){
            System.out.println("the contact was added successfully");
        }
    }
    public void removeContact () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("you choose to remove contact from supplier \n") ;
        suppliersController.printSuppliers();
        System.out.println("please enter the supplier Id: \n");
        int supplierId = scanner.nextInt();
        boolean success1 = suppliersController.printSupplierContacts(supplierId);
        if (!success1){
            return;
        }
        System.out.println("please enter the contact Id: \n");
        int contactId = scanner.nextInt();
        boolean success2 =suppliersController.removeContactFromSupplier(supplierId , contactId);
        if (success2){
            System.out.println("contact removed successfully");
        }

    }




    public void addNewSupplier (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("you choose to add new supplier to the system \n" +
                "please enter the supplier Id: \n");
        int supplierId = scanner.nextInt();

        System.out.println("please select supplier Delivery terms \n" +
                "1 - no_delivery,\n" +
                "2 - delivery By Order,\n" +
                "3 - delivery on regular days;  \n"
        );
        int deliveryTermsScanner = scanner.nextInt();

        while (deliveryTermsScanner>3 || deliveryTermsScanner<1){
            System.out.println("please select supplier Delivery terms \n" +
                    "1 - no_delivery,\n" +
                    "2 - delivery By Order,\n" +
                    "3 - delivery on regular days;  \n"
            );
            deliveryTermsScanner = scanner.nextInt();
        }
        DeliveryTerms deliveryTerms = DeliveryTerms.no_delivery ;
        if (deliveryTermsScanner == 1){
            deliveryTerms = DeliveryTerms.no_delivery;
        }
        else if (deliveryTermsScanner == 2){
            deliveryTerms = DeliveryTerms.delivery_By_Order;
        }
        else if (deliveryTermsScanner == 3){
            deliveryTerms = DeliveryTerms.delivery_on_regular_days;
        }
        System.out.println("please enter the supplier BN number: \n"
        );
        int bnNum = scanner.nextInt();
        System.out.println("please enter the supplier bank account number: \n"
        );
        int bankAccount = scanner.nextInt();
        System.out.println("please select supplier payment terms \n" +
                "1 - regular,\n" +
                "2 - shotef plus 30,\n" +
                "3 - shotef plus 60;  \n"
        );
        int paymentOptionScanner = scanner.nextInt();
        while (paymentOptionScanner<1 || paymentOptionScanner>3){
            System.out.println("please select supplier payment terms \n" +
                    "1 - regular,\n" +
                    "2 - shotef plus 30,\n" +
                    "3 - shotef plus 60;  \n"
            );
            paymentOptionScanner = scanner.nextInt();
        }
        PaymentOptions payOption = PaymentOptions.regular ;
        if (paymentOptionScanner == 1){
            payOption = PaymentOptions.regular;
        }
        else if (paymentOptionScanner == 2){
            payOption =PaymentOptions.plus_30 ;
        }
        else if (paymentOptionScanner == 3){
            payOption = PaymentOptions.plus_60;
        }
        System.out.println("please enter the contact name: \n");
        scanner.nextLine();
        String contactName = scanner.nextLine();
        System.out.println("please enter the contact phone number: \n");
        String phoneNumber = scanner.nextLine();


        System.out.print("please enter supplier name and then press enter:\n");
        String supplierName = scanner.nextLine();

        boolean success =  suppliersController.addNewSupplier(supplierId ,deliveryTerms ,bnNum ,bankAccount ,payOption , supplierName , contactName ,phoneNumber);
         if(success){
            System.out.println("the new supplier was added successfully\n");
        }
        else {
            System.out.println("an error occur please check your details and try again");
        }
    }

    public void addProductsToSupplier(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("you choose to add products to suppliers \n" +
                "please enter the supplier Id: \n");
        int supplierId = scanner.nextInt();
        System.out.println("please enter the amount of this product that the supplier can supply:\n");
        int amount = scanner.nextInt();
        System.out.println("please enter price of the product:\n");
        double price = scanner.nextDouble();
        System.out.println("please enter the supplier catalog number of the product:\n");
        int catalogNumber = scanner.nextInt();
        System.out.println("please enter the productId:\n");
        int productId = scanner.nextInt();
        boolean success = suppliersController.addProductToSupplier(supplierId,productId,catalogNumber,  amount , price);
        if (success){
            System.out.println("the product was added successfully to the supplier");
        }
    }

    public void removeProductFromSupplier(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("you choose to remove products from suppliers \n" +
                "please enter the supplier Id: \n");
        suppliersController.printSuppliers();
        int supplierId = scanner.nextInt();
        System.out.println("please enter the product serial number (product Id) you want to remove:\n");
        int productId = scanner.nextInt();
        boolean success = suppliersController.removeProductFromSupplier(supplierId,productId);
        if (success){
            System.out.println("the product was removed successfully from the supplier");
        }
    }



}*/






