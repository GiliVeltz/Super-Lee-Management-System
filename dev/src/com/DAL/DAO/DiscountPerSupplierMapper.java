package com.DAL.DAO;

import com.DAL.DTO.DiscountPerSupplierDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DiscountPerSupplierMapper extends AbstractMapper{
    private String column_1_discountId = "discountId";
    private String column_2_suppId = "supplierId";



    public DiscountPerSupplierMapper() {
        super("DiscountPerSupplier");
    }

    protected DiscountPerSupplierDTO convertReaderToObject(ResultSet reader) {
        DiscountPerSupplierDTO result = null;
        try {
            result = new DiscountPerSupplierDTO(reader.getInt(column_1_discountId), reader.getInt(column_2_suppId));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }


    public List<DiscountPerSupplierDTO> getSupplierDiscountsDTOs(int supplierId) {
        List<DiscountPerSupplierDTO> objects = new ArrayList<>();
        String sql = "SELECT * FROM " + table_name + " WHERE " + column_2_suppId + "=" + supplierId;
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            while (resultSet.next())
                objects.add(convertReaderToObject(resultSet));
            return objects;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}


