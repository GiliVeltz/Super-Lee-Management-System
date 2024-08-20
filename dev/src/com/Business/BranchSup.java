package com.Business;

public class BranchSup {
    private String branchName ;
    private int branchId ;
    private String address ;

    public BranchSup(String branchName, int branchId , String address){
        this.address= address;
        this.branchName = branchName;
        this.branchId = branchId;
    }

    public int getBranchId() {
        return branchId;
    }

    public String getAddress() {
        return address;
    }

    public String getBranchName() {
        return branchName;
    }

}
