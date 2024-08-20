package com.Business;


import com.DAL.DAO.AbstractMapper;
import com.DAL.DAO.OrderMapper;
import com.DAL.DAO.SupplierMapper;
import com.Presentation.MainMenu;

import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
	// write your code here

       // MainMenu mm = new MainMenu();
       // mm.startMenu();
        SupplierMapper abstractMapper = new SupplierMapper();
        abstractMapper.addSupplier(1,"ddf", 1, "3", "dfssd");

    }
}
