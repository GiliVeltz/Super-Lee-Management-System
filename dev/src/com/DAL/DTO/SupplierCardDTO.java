package com.DAL.DTO;

import com.Business.DeliveryTerms;
import com.Business.PaymentOptions;
import com.Business.Supplier;
import com.DAL.DAO.AbstractMapper;
import com.DAL.DAO.SupplierCardMapper;

public class SupplierCardDTO extends AbstractDTO{
    private int bankAccountNumber;
    private int bNNumber;
    private PaymentOptions paymentOptions;
    private int supplierId;
    private int contactIdCounter;

    public SupplierCardDTO( int bNNumber,int bankAccountNumber ,  PaymentOptions paymentOptions, int supplierId, int ContactIdCounter ) {
        super(new SupplierCardMapper());
        this.bankAccountNumber = bankAccountNumber;
        this.bNNumber = bNNumber;
        this.paymentOptions = paymentOptions;
        this.supplierId = supplierId;
        this.contactIdCounter = ContactIdCounter;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public int getBankAccountNumber() {
        return bankAccountNumber;
    }

    public int getBNNumber() {
        return bNNumber;
    }

    public int getContactIdCounter() {
        return contactIdCounter;
    }

    public PaymentOptions getPaymentOptions() {
        return paymentOptions;
    }
}
