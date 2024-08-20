package com.DAL.DAO;

import com.DAL.DTO.DiscountPerSupplierDTO;
import com.DAL.DTO.DiscountSupplierDTO;
import com.DAL.DTO.ProductSupplierDiscountsDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductSupplierDiscountMapper extends AbstractMapper{
    private String column_1_product = "productId";
    private String column_2_suppId = "supplierId";
    private String column_3_discountId = "discountId";



    public ProductSupplierDiscountMapper() {
        super("ProductSuppliersDiscounts");
    }

    protected ProductSupplierDiscountsDTO convertReaderToObject(ResultSet reader) {
        ProductSupplierDiscountsDTO result = null;
        try {
            result = new ProductSupplierDiscountsDTO(reader.getInt(column_1_product), reader.getInt(column_2_suppId), reader.getInt(column_3_discountId));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public List<ProductSupplierDiscountsDTO> getDiscountDTO(int supplierId) {

        List<ProductSupplierDiscountsDTO> objects = new ArrayList<>();
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
