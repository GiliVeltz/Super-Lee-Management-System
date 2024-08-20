package com.DAL.DAO;

import com.DAL.DTO.OrderConstDTO;
import com.DAL.DTO.OrderDTO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderConstMapper extends AbstractMapper {
    private String column_7_price = "price";
    private String column_1_numOfProduct = "numOfProduct";
    private  String column_2_address = "address";
    private String column_3_branchId = "branchId";
    private String column_5_orderDate = "OrderDate";
    private  String column_4_supplierId = "supplierId";
    private  String column_6_Id = "orderId";
    private  String column_8_days = "Days";




    public OrderConstMapper() {
        super("OrderConst");
    }

    protected OrderConstDTO convertReaderToObject(ResultSet reader) {
        OrderConstDTO result = null;
        try {
            result = new OrderConstDTO(reader.getInt(column_1_numOfProduct), reader.getString(column_2_address),
                    reader.getInt(column_3_branchId),reader.getInt(column_4_supplierId), Date.valueOf(reader.getString(column_5_orderDate)),
                    reader.getInt(column_6_Id),reader.getDouble(column_7_price), reader.getString(column_8_days));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

}


