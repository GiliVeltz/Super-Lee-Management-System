package com.DAL.DAO;

import com.Business.DeliveryTerms;
import com.DAL.DTO.ProductQuantitiesAndPriceDTO;
import com.DAL.DTO.ProductSupplierDiscountsDTO;
import com.DAL.DTO.SupplierDTO;
import com.DAL.DTO.SupplyProductDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductQuantitiesAndPriceMapper extends AbstractMapper{

    private  String column_1_supplierId = "supplierId";
    private String column_2_productId = "productId";
    private String column_3_amountCanSupply = "amountCanSupply";
    private String column_4_price = "price";
    private  String column_5_catalogNumber = "catalogNumber";


    public ProductQuantitiesAndPriceMapper() {
        super("ProductQuantitiesAndPrice");
    }

    protected ProductQuantitiesAndPriceDTO convertReaderToObject(ResultSet reader) {
        ProductQuantitiesAndPriceDTO result = null;
        try {
            result = new ProductQuantitiesAndPriceDTO(reader.getInt(column_1_supplierId), reader.getInt(column_2_productId),
                    reader.getInt(column_3_amountCanSupply),reader.getDouble(column_4_price), reader.getInt(column_5_catalogNumber));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public List<ProductQuantitiesAndPriceDTO> getProductQuantitiesAndPriceDTO(int supplierId){
        List<ProductQuantitiesAndPriceDTO> objects = new ArrayList<>();
        String sql = "SELECT * FROM " + table_name + " WHERE " + column_1_supplierId + "=" + supplierId;
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
    public ProductQuantitiesAndPriceDTO selectProductBySupplier(int supplierId , int productId) {
        ProductQuantitiesAndPriceDTO objects ;
        String sql = "SELECT * FROM " + table_name + "Where " + column_1_supplierId + "="  + supplierId
                + "And " + column_2_productId + "="  + productId;
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            if (resultSet.next()) {
                objects = convertReaderToObject(resultSet);
                return objects;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }


}


