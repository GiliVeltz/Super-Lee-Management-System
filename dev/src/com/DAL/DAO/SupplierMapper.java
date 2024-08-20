package com.DAL.DAO;

import com.Business.*;
import com.Business.PaymentOptions;
import com.Business.SupplierCard;
import com.DAL.DTO.SupplierCardDTO;
import com.DAL.DTO.SupplierDTO;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class SupplierMapper extends AbstractMapper {




    private static String column_1_name = "name";
    private static String column_2_Id = "supplierId";
    private static String column_3_delivery = "canDeliver";
    private static String column_4_days = "days";
    private static String column_5_address = "address";


    public SupplierMapper() {
        super("Supplier");
    }

    protected SupplierDTO convertReaderToObject(ResultSet reader) {
        SupplierDTO result = null;
        try {

            result = new SupplierDTO(reader.getString(column_1_name), reader.getInt(column_2_Id),
                    reader.getInt(column_3_delivery), reader.getString(column_4_days), reader.getString(column_5_address));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public SupplierDTO selectSupplier(int id) {
        SupplierDTO objects ;
        String sql = "SELECT * FROM " + table_name + " WHERE " + column_2_Id + "=" + id;
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
    public List<SupplierDTO> selectAllSuppliers() {
        List<SupplierDTO> objects = new ArrayList<>();
        String sql = "SELECT * FROM " + table_name ;
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

    public List<SupplierDTO> selectSupplierCard() {
        List<SupplierDTO> objects = new ArrayList<>();
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

    public void addSupplier(int supplierId, String Name,int canDeliver, String days,String address) {

        String sql = MessageFormat.format("INSERT INTO " + table_name + "({0} ,{1}, {2}, {3}, {4} ) VALUES(?,?,?,?,?)",
                column_1_name, column_2_Id, column_3_delivery, column_4_days, column_5_address);
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, Name);
            pstmt.setInt(2, supplierId);
            pstmt.setInt(3, canDeliver);
            pstmt.setString(4, days);
            pstmt.setString(5, address);




            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    /*public SupplierDTO selectSupplier(int id, String columnName) {
        String sql = "SELECT * FROM " + "Supplier" + " WHERE " + columnName + "=" + id;
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if(rs.next())
                return convertReaderToObject(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }*/





    public void removeSupplier(int supplierId) {
        try {
            remove(supplierId, column_2_Id);
        }
        catch (Exception e){
            e.printStackTrace();
    }

}









}


