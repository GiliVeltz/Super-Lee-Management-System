package com.DAL.DAO;

import com.Business.DeliveryTerms;
import com.Business.PaymentOptions;
import com.Business.SupplierCard;
import com.DAL.DTO.SupplierCardDTO;
import com.DAL.DTO.SupplierDTO;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class SupplierCardMapper extends AbstractMapper{


    public SupplierCardMapper(){
        super("SupplierCard");
    }

    private static String column_1_bnNumber = "BNNumber";
    private static String column_2_BANumber = "bankAccountNumber";
    private static String column_3_paymentTerm = "paymentOptions";
    public static String column_4_supplierId = "supplierId";
    private static String column_5_contactCounter = "contact_Id_counter";

    protected SupplierCardDTO convertReaderToObject(ResultSet reader) {
        SupplierCardDTO result = null;
        try {
            result = new SupplierCardDTO(reader.getInt(column_1_bnNumber), reader.getInt(column_2_BANumber), PaymentOptions.valueOf(reader.getString(column_3_paymentTerm)),reader.getInt(column_4_supplierId), reader.getInt(column_5_contactCounter));
        }
        catch (SQLException exception) {
             exception.printStackTrace();
        }
        return result;
    }

    public SupplierCardDTO selectSupplierCard(int id){
        List<SupplierCardDTO> objects = new ArrayList<>();
        String sql = "SELECT * FROM " + table_name + " WHERE " + column_4_supplierId + "=" + id;
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {
            while(resultSet.next())
                objects.add(convertReaderToObject(resultSet));
            return objects.get(0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean addSupplierCard(int supplierId , PaymentOptions paymentOptions , int bankAccount, int bnNumber , int contactCounter){
        String sql = MessageFormat.format("INSERT INTO {0} ({1}, {2} , {3}, {4}) VALUES(?,?,?,?,?)",
                column_1_bnNumber, column_2_BANumber, column_3_paymentTerm, column_4_supplierId ,column_5_contactCounter);
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bnNumber);
            pstmt.setInt(2, bankAccount);
            pstmt.setString(3, paymentOptions.toString());
            pstmt.setInt(4, supplierId);
            pstmt.setInt(5, contactCounter);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }


    }
    public void removeSupplierCard(int supplierId){
        try {
            remove(supplierId, column_4_supplierId);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }













}
