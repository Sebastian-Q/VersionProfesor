package utez.edu.mx.Actividad.controller;


import utez.edu.mx.Actividad.model.Customers;
import utez.edu.mx.Actividad.model.customersDao;



import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.sql.*;
import java.util.List;

@Path("/customers")
public class Service{
    Connection con;
    PreparedStatement pstm;
    Statement statement;
    ResultSet rs;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customers> getCustomers(){
        return new customersDao().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customers getCustomers(@PathParam("id") int customerNumber){
        return new customersDao().findByCustomersNumber(customerNumber);
    }

    @POST
    @Path("/crear")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Customers crar(MultivaluedMap<String, String> formParams){
        int customerNumber = Integer.parseInt(formParams.get("customerNumber").get(0));
        if(new customersDao().createUpdate(getParams(customerNumber, formParams), true))
            return new customersDao().findByCustomersNumber(customerNumber);
        return null;
    }



    @PUT
    @Path("/actualizar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Customers save(@PathParam("id") int customerNumber, MultivaluedMap<String, String> formParams){
        if(new customersDao().createUpdate(getParams(customerNumber, formParams), false))
            return new customersDao().findByCustomersNumber(customerNumber);
        return null;
    }


    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean eliminar(@PathParam("id") int customerNumber){
        return new customersDao().delete(customerNumber);
    }

    private Customers getParams(int customerNumber, MultivaluedMap<String, String> formParams){
        String customerName = formParams.get("customerName").get(0);
        String contactLastName = formParams.get("contactLastName").get(0);
        String contactFirstName = formParams.get("contactFirstName").get(0);
        String phone = formParams.get("phone").get(0);
        String addressLine1 = formParams.get("addressLine1").get(0);
        String addressLine2 = formParams.get("addressLine2").get(0);
        String city = formParams.get("city").get(0);
        String state = formParams.get("state").get(0);
        String postalCode = formParams.get("postalCode").get(0);
        String country = formParams.get("country").get(0);
        int salesRepEmployeeNumber = Integer.parseInt(formParams.get("salesRepEmployeeNumber").get(0));
        double creditLimit = Integer.parseInt(formParams.get("creditLimit").get(0));

        Customers cliente = new Customers(
        );
        System.out.println(cliente);
        return cliente;
    }


    public void closeConnection(){
        try {
            if (con != null){
                con.close();
            }
            if (pstm != null){
                pstm.close();
            }
            if (rs != null){
                rs.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();

        }
    }

}