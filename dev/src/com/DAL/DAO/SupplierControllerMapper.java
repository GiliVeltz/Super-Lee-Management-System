package com.DAL.DAO;

import com.Business.DeliveryTerms;
import com.DAL.DTO.SupplierControllerDTO;
import com.DAL.DTO.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierControllerMapper extends AbstractMapper{


    private static String column_1_orderIndex = "lastOrderIndex";
    private static String column_2_OrderCounter = "orderCounter";
    private static String column_3_discountCounter = "discountCounter";


    public SupplierControllerMapper() {
        super("SupplierController");
    }

    protected SupplierControllerDTO convertReaderToObject(ResultSet reader) {
        SupplierControllerDTO result = null;
        try {

            result = new SupplierControllerDTO(reader.getInt(column_1_orderIndex), reader.getInt(column_2_OrderCounter), reader.getInt(column_3_discountCounter));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }
}
