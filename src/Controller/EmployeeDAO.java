/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class EmployeeDAO {
    private UtilDB utilDB;
    
    public EmployeeDAO() {
        utilDB = new UtilDB();
        utilDB.connect();
    }
    
    public ArrayList<Employee> getAll(){
        ArrayList listEmployee = new ArrayList();
        try { 
            String sql = "select * from Employee";
            Statement stm = utilDB.getConn().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next())
            {
                Employee employee = new Employee();
                employee.setID(rs.getInt("ID"));
                employee.setFullName(rs.getString("FullName"));
                employee.setEmail(rs.getString("Email"));
                employee.setPhoneNumber(rs.getString("PhoneNumber"));
                employee.setAddress(rs.getString("Address"));
                employee.setIndentityCard(rs.getString("IndentityCard"));
                employee.setUsername(rs.getString("Username"));
                employee.setPassword(rs.getString("Password"));
                employee.setIsAdmin(rs.getBoolean("IsAdmin"));
                employee.setStatus(rs.getBoolean("Status"));
                listEmployee.add(employee);
            }         
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listEmployee;
    }
    
    public Employee getByID(int id){
        try{
            String sql = "select * from Employee where ID="+id;
            Statement stm = utilDB.getConn().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Employee employee = new Employee();
                employee.setID(rs.getInt("ID"));
                employee.setFullName(rs.getString("FullName"));
                employee.setEmail(rs.getString("Email"));
                employee.setPhoneNumber(rs.getString("PhoneNumber"));
                employee.setAddress(rs.getString("Address"));
                employee.setIndentityCard(rs.getString("IndentityCard"));
                employee.setUsername(rs.getString("Username"));
                employee.setPassword(rs.getString("Password"));
                employee.setIsAdmin(rs.getBoolean("IsAdmin"));
                employee.setStatus(rs.getBoolean("Status"));
                return employee;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // Lấy theo Username
    public Employee getByUsername(String user){
        try{
            String sql = "select * from Employee where Username='"+user+"'";
            Statement stm = utilDB.getConn().createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Employee employee = new Employee();
                employee.setID(rs.getInt("ID"));
                employee.setFullName(rs.getString("FullName"));
                employee.setEmail(rs.getString("Email"));
                employee.setPhoneNumber(rs.getString("PhoneNumber"));
                employee.setAddress(rs.getString("Address"));
                employee.setIndentityCard(rs.getString("IndentityCard"));
                employee.setUsername(rs.getString("Username"));
                employee.setPassword(rs.getString("Password"));
                employee.setIsAdmin(rs.getBoolean("IsAdmin"));
                employee.setStatus(rs.getBoolean("Status"));
                return employee;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // Kiểm tra thông tin đăng nhập có đúng ko 
    public Employee CheckLogin(String user, String pass){
        try{
            String sql = "select * from Employee where Username = ? and Password = ?";
            PreparedStatement pstm = utilDB.getConn().prepareStatement(sql);
            pstm.setString(1, user);
            pstm.setString(2, pass);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Employee employee = new Employee();
                employee.setID(rs.getInt("ID"));
                employee.setFullName(rs.getString("FullName"));
                employee.setEmail(rs.getString("Email"));
                employee.setPhoneNumber(rs.getString("PhoneNumber"));
                employee.setAddress(rs.getString("Address"));
                employee.setIndentityCard(rs.getString("IndentityCard"));
                employee.setUsername(rs.getString("Username"));
                employee.setPassword(rs.getString("Password"));
                employee.setIsAdmin(rs.getBoolean("IsAdmin"));
                employee.setStatus(rs.getBoolean("Status"));
                return employee;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
