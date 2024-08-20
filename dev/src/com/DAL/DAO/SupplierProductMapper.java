package com.DAL.DAO;

import com.DAL.DTO.ProductQuantitiesAndPriceDTO;
import com.DAL.DTO.SupplierDTO;
import com.DAL.DTO.SupplyProductDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierProductMapper extends AbstractMapper {


    private static String column_2_name = "name";
    private static String column_1_Id = "productId";
    private static String column_3_producer = "producer";


    public SupplierProductMapper() {
        super("SupplyProducts");
    }

    protected SupplyProductDTO convertReaderToObject(ResultSet reader) {
        SupplyProductDTO result = null;
        try {

            result = new SupplyProductDTO(reader.getInt(column_1_Id), reader.getString(column_2_name),
                    reader.getString(column_3_producer));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public List<SupplyProductDTO> getAllProducts() {
        List<SupplyProductDTO> objects = new ArrayList<>();
        String sql = "SELECT * FROM " + table_name;
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

    public List<SupplyProductDTO> getSpecificProducts(List<ProductQuantitiesAndPriceDTO> list) {
        List<SupplyProductDTO> objects = new ArrayList<>();
        String sql = "SELECT * FROM " + table_name + "Where " + column_1_Id + "IN (";
        for (ProductQuantitiesAndPriceDTO productQuantitiesAndPriceDTO : list) {
            sql = sql + productQuantitiesAndPriceDTO.getProductId();
            sql = sql + ",";
        }
        sql = sql.substring(0, sql.length() - 1);
        sql = sql + ")";
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


