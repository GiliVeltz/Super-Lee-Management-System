package com.DAL.DAO;

import com.DAL.DTO.OrderDTO;
import com.DAL.DTO.ProductInOrderDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper extends AbstractMapper {
    private String column_1_price = "price";
    private String column_2_numOfProduct = "numOfProduct";
    private  String column_3_address = "address";
    private String column_4_branchId = "branchId";
    private String column_5_orderDate = "OrderDate";
    private  String column_6_supplierId = "supplierId";
    private  String column_7_Id = "Id";




    public OrderMapper() {
        super("Order");
    }

    protected OrderDTO convertReaderToObject(ResultSet reader) {
        OrderDTO result = null;
        try {
            result = new OrderDTO(reader.getInt(column_1_price), reader.getInt(column_2_numOfProduct),
                    reader.getString(column_3_address),reader.getInt(column_4_branchId), Date.valueOf(reader.getString(column_5_orderDate)),
                    reader.getInt(column_6_supplierId),reader.getInt(column_7_Id));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public Connection connect() {
        return super.connect();
    }
}
