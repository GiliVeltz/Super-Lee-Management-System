package com.DAL.DAO;

import com.Business.DeliveryTerms;
import com.Business.PaymentOptions;
import com.DAL.DTO.SupplierCardDTO;
import com.DAL.DTO.SupplierContactDTO;
import com.DAL.DTO.SupplierDTO;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class SupplierContactMapper extends AbstractMapper{

    public SupplierContactMapper(){
        super("supplierContacts");
    }

    private String column_1_Id = "Id";
    private  String column_2_PhoneNumber = "PhoneNumber";
    private String column_3_name = "Name";
    public  String column_4_supplierId = "supplierId";


    protected SupplierContactDTO convertReaderToObject(ResultSet reader) {
        SupplierContactDTO result = null;
        try {
            result = new SupplierContactDTO(reader.getInt(column_1_Id), reader.getString(column_2_PhoneNumber), reader.getString((column_3_name)),reader.getInt(column_4_supplierId));
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    public void addSupplierContacts(int supplierId, String name, String phoneNumber, int Id) {
        String sql = MessageFormat.format("INSERT INTO {0} ({1}, {2} , {3}) VALUES(?,?,?,?)",
                column_1_Id, column_2_PhoneNumber, column_3_name, column_4_supplierId);

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    pstmt.setInt(1, Id);
                    pstmt.setString(2, phoneNumber);
                    pstmt.setString(3, name);
                    pstmt.setInt(4, supplierId);
                    pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<SupplierContactDTO> getSupplierContacts(int id) {
        List<SupplierContactDTO> objects = new ArrayList<>();
        String sql = "SELECT * FROM " + table_name + " WHERE " + column_1_Id + "=" + id;
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
