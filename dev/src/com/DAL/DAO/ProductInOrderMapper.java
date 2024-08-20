package com.DAL.DAO;

import com.DAL.DTO.ProductInOrderDTO;
import com.DAL.DTO.ProductQuantitiesAndPriceDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductInOrderMapper extends AbstractMapper {

    private String column_1_productId = "productId";
    private String column_2_amount = "amount";
    private  String column_3_supplierId = "supplierId";
    private String column_4_priceBeforeDiscount = "priceBeforeDiscount";
    private String column_5_priceAfterDiscount = "priceAfterDiscount";
    private  String column_6_totalDiscount = "totalDiscount";
    private  String column_7_catalogNumber = "catalogNumber";
    private  String column_8_OrderId = "orderId";



    public ProductInOrderMapper() {
        super("ProductInOrder");
    }

    protected ProductInOrderDTO convertReaderToObject(ResultSet reader) {
        ProductInOrderDTO result = null;
        try {
            result = new ProductInOrderDTO(reader.getInt(column_1_productId), reader.getInt(column_2_amount),
                    reader.getInt(column_3_supplierId),reader.getDouble(column_4_priceBeforeDiscount), reader.getDouble(column_5_priceAfterDiscount),
                    reader.getDouble(column_6_totalDiscount),reader.getInt(column_7_catalogNumber),
                    reader.getInt(column_8_OrderId));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

}

