/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Bill;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author HP
 */
public class BillDAO {
    private UtilDB utilDB;
    
    public BillDAO() {
        utilDB = new UtilDB();
        utilDB.connect();
    }
    
    // lấy All dữ liệu từ Bill
    public ArrayList<Bill> getAll(){
        ArrayList listBill = new ArrayList();
        try { 
            String sql = "select * from Bill";
            Statement stm = utilDB.getConn().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next())
            {
                Bill bill = new Bill();
                bill.setID(rs.getInt("ID"));
                bill.setEmployeeID(rs.getInt("EmployeeID"));
                bill.setCustomerID(rs.getInt("CustomerID"));
                bill.setPaymentByID(rs.getInt("PaymentByID"));
                bill.setVoucherID(rs.getInt("VoucherID"));
                bill.setDateCreate(rs.getDate("DateCreate"));
                bill.setAmount(rs.getInt("Amount"));
                bill.setAddress(rs.getString("Address"));
                bill.setStatus(rs.getBoolean("Status"));
                listBill.add(bill);
            }         
        } catch (SQLException ex) {
            Logger.getLogger(BillDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listBill;
    }
    
    // Thêm dữ liệu vào Bill
    public boolean add(Bill bill){
        String query="insert into Bill(EmployeeID, CustomerID, PaymentByID, VoucherID, Code, DateCreate, Amount, Address, Status) values(?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pstm=utilDB.getConn().prepareStatement(query);
            pstm.setInt(1, bill.getEmployeeID());
            pstm.setInt(2, bill.getCustomerID());       
            pstm.setInt(3, bill.getPaymentByID());         
            pstm.setInt(4, bill.getVoucherID());    
            pstm.setInt(5, bill.getCode());    
            pstm.setDate(6, (java.sql.Date) bill.getDateCreate());   
            pstm.setInt(7, bill.getAmount());  
            pstm.setString(8, bill.getAddress());  
            pstm.setBoolean(9, bill.isStatus());
            int result = pstm.executeUpdate();;
            if(result!=0){
                return true;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(BillDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // Lấy Bill theo Code
    public Bill getByCode(int code){
        String sql = "Select * from Bill where Code="+code;
        Statement stm;
        try{
            stm = utilDB.getConn().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                Bill bill = new Bill();
                bill.setID(rs.getInt("ID"));
                bill.setEmployeeID(rs.getInt("EmployeeID"));
                bill.setCustomerID(rs.getInt("CustomerID"));
                bill.setPaymentByID(rs.getInt("PaymentByID"));
                bill.setVoucherID(rs.getInt("VoucherID"));
                bill.setDateCreate(rs.getDate("DateCreate"));
                bill.setAmount(rs.getInt("Amount"));
                bill.setAddress(rs.getString("Address"));
                bill.setStatus(rs.getBoolean("Status"));
                return bill;           
           }   
        }
        catch(SQLException ex){
                 Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return null;  
    }
}
