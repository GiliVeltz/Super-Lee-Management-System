package com.Business;

import com.DAL.DAO.SupplierCardMapper;
import com.DAL.DAO.SupplierContactMapper;
import com.DAL.DTO.SupplierCardDTO;

import java.util.HashMap;
import java.util.List;

;

public class SupplierCard {
    private int BNnumber;
    private int bankAccountNumber;
    private PaymentOptions paymentOptions;
    private HashMap <Integer,Contacts> contactsMap;
    private int contactIdCounter;
    private SupplierCardMapper supplierCardMapper;
    private SupplierContactMapper supplierContactMapper;



    public SupplierCard(int BNNumber , int BankAccount , PaymentOptions pOpt , List<Contacts> contacts){
        this.bankAccountNumber= BankAccount;
        this.BNnumber = BNNumber;
        this.paymentOptions = pOpt;
        this.contactsMap = new HashMap<>() ;
        for (int i=0 ; i<contacts.size(); i++) {
            contactsMap.put(contacts.get(i).getId() , contacts.get(i));
        }
        this.contactIdCounter= 2;


    }
    public SupplierCard(SupplierCardDTO supplierCardDTO){
        this.contactIdCounter= supplierCardDTO.getContactIdCounter();
        this.paymentOptions= supplierCardDTO.getPaymentOptions();
        this.bankAccountNumber= supplierCardDTO.getBankAccountNumber();
        this.BNnumber= supplierCardDTO.getBNNumber();
        supplierContactMapper.getSupplierContacts(supplierCardDTO.getSupplierId());
    }
    public SupplierCard(int BNNumber , int BankAccount , PaymentOptions pOpt ,  Contacts contacts){
        this.bankAccountNumber= BankAccount;
        this.BNnumber = BNNumber;
        this.paymentOptions = pOpt;
        this.contactsMap = new HashMap<>() ;
        this.contactsMap.put(contacts.getId(),contacts);
        this.contactIdCounter= 2;
        SupplierCardMapper supplierCardMapper = new SupplierCardMapper();
        SupplierContactMapper supplierContactMapper = new SupplierContactMapper();

    }

    /*public SupplierCard(int BNNumber , int BankAccount , PaymentOptions pOpt , HashMap <Integer,Contacts> contacts){
        this.bankAccountNumber= BankAccount;
        this.BNnumber = BNNumber;
        this.paymentOptions = pOpt;
        this.contactsMap = contacts ;


    }*/

    public int getContactIdCounter(){
        return contactIdCounter++;
    }

    public int getBNNumber() {
        return BNnumber;
    }
    public int getBankAccountNumber(){
        return bankAccountNumber;
    }

    public PaymentOptions getPaymentOptions() {
        return paymentOptions;
    }

    public HashMap<Integer,Contacts> getContacts() {
        return contactsMap;
    }
    public void setBNNumber(int newBN){
        BNnumber = newBN;}

    /*public int getContactId(){
        int currId = ;
        idCounter++;
        return currId;
    }*/

    public void setBankAccountNumber(int newBAN){
        bankAccountNumber= newBAN;
    }
    public void setPaymentOptions(PaymentOptions newPayment){
        paymentOptions= newPayment;
    }
    public void addContact (Contacts contact){
        contactsMap.put(contact.getId(),contact);
    }
    public void removeContact(int contactId){
       if (!contactsMap.containsKey(contactId)){
           throw new IllegalArgumentException("this contact Id doesn't exist in this supplier");
       }
        contactsMap.remove(contactId);
    }

    public void printContacts(){
        for (int id : contactsMap.keySet()){
            System.out.println("contact Id- " + id + " contact name - " + contactsMap.get(id).getName()+
                    " contact phone number - " + contactsMap.get(id).getPhoneNumber());
        }
    }
}

