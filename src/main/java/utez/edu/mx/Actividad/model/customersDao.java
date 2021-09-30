package utez.edu.mx.Actividad.model;

import utez.edu.mx.Actividad.dataBase.ConnectionMysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class customersDao {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;

    public List<Customers> findAll(){
        List<Customers> listCustomers = new ArrayList<>();

        try{
            con = ConnectionMysql.getConnection();
            cstm = con.prepareCall("SELECT * FROM customers;");
            rs = cstm.executeQuery();

            while(rs.next()){
                Customers cliente = new Customers();

                cliente.setCustomerNumber(rs.getInt("CustomerNumber"));
                cliente.setCustomerName(rs.getString("CustomerName"));
                cliente.setContactLastName(rs.getString("ContactLastName"));
                cliente.setContactFirstName(rs.getString("ContactFirstName"));
                cliente.setPhone(rs.getString("Phone"));
                cliente.setAddressLine1(rs.getString("AddressLine1"));
                cliente.setAddressLine2(rs.getString("AddressLine2"));
                cliente.setCity(rs.getString("City"));
                cliente.setState(rs.getString("State"));
                cliente.setPostalCode(rs.getString("PostalCode"));
                cliente.setCountry(rs.getString("Country"));
                cliente.setSalesRepEmployeeNumber(rs.getInt("SalesRepEmployeeNumber"));
                cliente.setCreditLimit(rs.getDouble("CreditLimit"));
                listCustomers.add(cliente);
            }
        }catch(SQLException e){
            System.out.printf("Ha sucedido algún error: %s", e.getMessage());
        }finally{
            ConnectionMysql.closeConnections(con, cstm, rs);
        }
        return listCustomers;
    }

    public Customers findByCustomersNumber(int CustomerNumber){
        Customers cliente = null;

        try{
            con = ConnectionMysql.getConnection();
            cstm = con.prepareCall("SELECT * FROM customers WHERE CustomerNumber = ?;");
            cstm.setInt(1, CustomerNumber);
            rs = cstm.executeQuery();

            if(rs.next()){
                cliente = new Customers();
                cliente.setCustomerNumber(rs.getInt("CustomerNumber"));
                cliente.setCustomerName(rs.getString("CustomerName"));
                cliente.setContactLastName(rs.getString("ContactLastName"));
                cliente.setContactFirstName(rs.getString("ContactFirstName"));
                cliente.setPhone(rs.getString("Phone"));
                cliente.setAddressLine1(rs.getString("AddressLine1"));
                cliente.setAddressLine2(rs.getString("AddressLine2"));
                cliente.setCity(rs.getString("City"));
                cliente.setState(rs.getString("State"));
                cliente.setPostalCode(rs.getString("PostalCode"));
                cliente.setCountry(rs.getString("Country"));
                cliente.setSalesRepEmployeeNumber(rs.getInt("SalesRepEmployeeNumber"));
                cliente.setCreditLimit(rs.getDouble("CreditLimit"));
            }
        }catch(SQLException e){
            System.out.printf("Ha sucedido algún error: %s", e.getMessage());
        }finally{
            ConnectionMysql.closeConnections(con, cstm, rs);
        }
        return cliente;
    }

    public boolean createUpdate(Customers cliente, boolean isCreate){
        boolean flag = false;

        try{
            con = ConnectionMysql.getConnection();
            if(isCreate){
                cstm = con.prepareCall("INSERT INTO customers(customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, " +
                        "state, postalCode, country, salesRepEmployeeNumber, creditLimit)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);");

                cstm.setInt(1, cliente.getCustomerNumber());
                cstm.setString(2, cliente.getCustomerName());
                cstm.setString(3, cliente.getContactLastName());
                cstm.setString(4, cliente.getContactFirstName());
                cstm.setString(5, cliente.getPhone());
                cstm.setString(6, cliente.getAddressLine1());
                cstm.setString(7, cliente.getAddressLine2());
                cstm.setString(8, cliente.getCity());
                cstm.setString(9, cliente.getState());
                cstm.setString(10, cliente.getPostalCode());
                cstm.setString(11, cliente.getCountry());
                cstm.setInt(12, cliente.getSalesRepEmployeeNumber());
                cstm.setDouble(13, cliente.getCreditLimit());
            } else {
                cstm = con.prepareCall("UPDATE customers SET customerName = ?, contactLastName = ?, contactFirstName = ?, phone = ?, " +
                        "addressLine1 = ?, addressLine2 = ?, city = ?, state = ?, postalCode = ?, country = ?, salesRepEmployeeNumber = ?, creditLimit = ? " +
                        "WHERE customerNumber = ?;");
                cstm.setString(1, cliente.getCustomerName());
                cstm.setString(2, cliente.getContactLastName());
                cstm.setString(3, cliente.getContactFirstName());
                cstm.setString(4, cliente.getPhone());
                cstm.setString(5, cliente.getAddressLine1());
                cstm.setString(6, cliente.getAddressLine2());
                cstm.setString(7, cliente.getCity());
                cstm.setString(8, cliente.getState());
                cstm.setString(9, cliente.getPostalCode());
                cstm.setString(10, cliente.getCountry());
                cstm.setInt(11, cliente.getSalesRepEmployeeNumber());
                cstm.setDouble(12, cliente.getCreditLimit());
                cstm.setInt(13, cliente.getCustomerNumber());
            }

            flag = cstm.executeUpdate() == 1;
        }catch(SQLException e){
            System.out.printf("Ha sucedido algún error: %s", e.getMessage());
        }finally{
            ConnectionMysql.closeConnections(con, cstm, rs);
        }
        return flag;
    }

    public boolean delete(int customerNumber){
        boolean flag = false;

        try{
            con = ConnectionMysql.getConnection();
            cstm = con.prepareCall("DELETE FROM customers WHERE customerNumber = ?;");
            cstm.setInt(1, customerNumber);
            flag = cstm.executeUpdate() == 1;
        }catch(SQLException e){
            System.out.printf("Ha sucedido algún error: %s", e.getMessage());
        }finally{
            ConnectionMysql.closeConnections(con, cstm, rs);
        }
        return flag;
    }
}
