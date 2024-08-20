package com.Service;

import com.Business.*;

import java.util.HashMap;
import java.util.Map;

public class SupplierControllerService {


   /* private SuppliersController suppliersController;
    public SupplierControllerService(){
        suppliersController = new SuppliersController();
    }
    public boolean addNewSupplier (int supplierId , DeliveryTerms deliveryTerms , int bNNumber , int bankAccount , PaymentOptions pOpt , String name , String contactsName , String phoneNumber ){
        try {

            suppliersController.addSupplier(supplierId, deliveryTerms, bNNumber, bankAccount, pOpt, name, contactsName ,phoneNumber);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    public boolean addProductToTheSystem (int productId , String name , String producer){
        try {
            suppliersController.addProductToTheSystem(productId, name , producer);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    public boolean addDiscountToProduct (int supplierId , int productId , int amount , double price , boolean percentageDiscount){
        try {
            suppliersController.addDiscountToProduct(supplierId, productId , amount , price, percentageDiscount);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    public boolean addDiscountToOrder (int supplierId , double amount , double price ,boolean percentageDiscount){
        try {
            suppliersController.addDiscountToOrder(supplierId, amount , price, percentageDiscount);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    public boolean addContactToSupplier (int supplierId , String name , String phoneNumber){
        try {
            suppliersController.addContact(supplierId, name ,  phoneNumber);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    public boolean printSupplierContacts (int supplierId ){
        try {
            suppliersController.printSupplierContacts(supplierId);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    public boolean removeProductFromSupplier (int supplierId , int productId){
        try {
            suppliersController.removeProductFromSupplier(supplierId, productId);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    public boolean removeContactFromSupplier (int supplierId , int contactId){
        try {
            suppliersController.removeContact(supplierId, contactId);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }






    public boolean RemoveSupplier (int supplierId){
        try {
            suppliersController.removeSupplier(supplierId);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }
    public boolean addProductToSupplier (int supplierId , int productId , int catalogNum, int amountToSupply , double price){
        try {
            suppliersController.addProductToSupplier(supplierId ,productId, catalogNum,amountToSupply ,price );
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }


    public boolean addOrder(HashMap<Integer, HashMap<Integer, Integer>> productsByBranch){
        try {
            suppliersController.addOrder( productsByBranch);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }


    public boolean addBranch(int branchId , String branchName, String branchAddress){
        try {
            suppliersController.addBranch( branchId ,branchName, branchAddress);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }

    public boolean editOrder(Order order , ProductInOrder product , int newAmount){
        try {
            suppliersController.editOrder(  order,product, newAmount);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }
    public void printSuppliers(){
        try {
            suppliersController.printSuppliers();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void printLastOrder(){
        try {
            suppliersController.printLastOrder();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    public boolean editSupplierName(int supplierId , String name){
        try {
            suppliersController.editSupplierName(supplierId, name);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }
    public boolean editSupplierBnNumber(int supplierId , int bnnumber){
        try {
            suppliersController.editBNNumber(supplierId, bnnumber);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }
    public boolean editSupplierBankAccount(int supplierId , int bankAccount){
        try {
            suppliersController.editBankAccount(supplierId, bankAccount);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }
    public boolean editSupplierPaymentTerms(int supplierId , PaymentOptions paymentOptions){
        try {
            suppliersController.editSupplierPaymentTerms(supplierId, paymentOptions);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }
    public boolean editSupplierDeliveryTerms(int supplierId , DeliveryTerms deliveryTerms){
        try {
            suppliersController.editSupplierDeliveryTerms(supplierId, deliveryTerms);
            return true;
        }
        catch (Exception ex){
            System.out.println(ex);
            return false;
        }
    }


}*/
}

