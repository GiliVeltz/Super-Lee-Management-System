package com.DAL.DAO;

import com.DAL.DTO.DiscountSupplierDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DiscountSupplierMapper extends AbstractMapper {
    private String column_1_discount_type = "discountType";
    private String column_2_minimumAmount = "minimumAmount";
    private String column_3_DiscountAmount = "DiscountAmount";
    private  String column_4_discountId = "discountId";
    private  String column_5_isPercentage = "isPercentageDiscount";



    public DiscountSupplierMapper() {
        super("DiscountSupplier");
    }

    protected DiscountSupplierDTO convertReaderToObject(ResultSet reader) {
        DiscountSupplierDTO result = null;
        try {
            result = new DiscountSupplierDTO(reader.getInt(column_1_discount_type), reader.getDouble(column_2_minimumAmount),
                    reader.getDouble(column_3_DiscountAmount),reader.getInt(column_4_discountId), reader.getInt(column_5_isPercentage));

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public DiscountSupplierDTO getDiscountDTO(int discountId) {
        //DiscountSupplierDTO object = new
        String sql = "SELECT * FROM " + table_name + " WHERE " + column_4_discountId + "=" + discountId;
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            return convertReaderToObject(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
