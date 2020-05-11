/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import CLIENTES.Clientes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sara
 */
public class Clientes_DAO {
    private Connection conexion = null;
    
    public Clientes_DAO(){
    try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/neptuno", "root", "");
        } catch (SQLException ex) {
            System.err.println("Error en la conexion: " + ex.getMessage());
        }
}
    public Connection getConexion() {
        return conexion;
    }
    
    public Clientes read(Integer idClientes) {
        Clientes cliente = null;
        PreparedStatement stm = null;
        if (this.conexion == null)
        {
            return null;
        }
        try {
            String sql = "SELECT * from clientes where id = ?";
            stm = conexion.prepareStatement(sql);

            stm.setInt(1, idClientes);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                cliente = new Clientes(
                        rs.getString("codigo"),
                        rs.getString("empresa"),
                        rs.getString("contacto"),
                        rs.getString("cargo_contacto"),
                        rs.getString("direccion"),
                        rs.getString("ciudad"),
                        rs.getString("region"),
                        rs.getString("cp"),
                        rs.getString("pais"),
                        rs.getString("telefono"),
                        rs.getString("fax")
                );
            }

        } catch (SQLException ex) {
            System.err.println("Error en la ejecución de la sentencia: " + ex.getMessage()+ "\nQuery: " + stm.toString());
            
        }

        return cliente;
    }
    
    public Boolean insert(Clientes cliente){
    Boolean resultado = false;
    PreparedStatement stmt = null;
    Integer ultimoID = null;
        if(this.conexion == null ||  cliente == null){
            return false;
        }
            try{
                String query = "SELECT Codigo FROM 'clientes' ORDER BY  'codigo' DESC LIMIT 1";
                PreparedStatement stm = conexion.prepareStatement(query);
                ResultSet rs = stm.executeQuery();
                    if(rs.next()){
                        ultimoID = rs.getInt("id") + 1;
                        cliente.setId(ultimoID);
                    }
                String sql = "INSERT INTO empleados "
                        + "(codigo, empresa, contacto, cargo_contacto, direccion, ciudad, region, cp, pais, telefono, fax)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                stmt = conexion.prepareStatement(sql);
                stmt.setString(1, cliente.getCodigo());
                stmt.setString(2, cliente.getContacto());
                stmt.setString(3, cliente.getCargo_contacto());
                stmt.setString(4, cliente.getDireccion());
                stmt.setString(5, cliente.getCiudad());
                stmt.setString(6, cliente.getRegion());
                stmt.setString(7, cliente.getCp());
                stmt.setString(8, cliente.getPais());
                stmt.setString(9, cliente.getTelefono());
                stmt.setString(10, cliente.getFax());

                stmt .executeUpdate();

                resultado = true;

                stmt.close();
                System.out.println();

            }catch(SQLException exc){
                System.err.println("ERROR EN EL INSERT: " + exc.getMessage());
            }
    return resultado;
}
    
    /*
       public Boolean update(Empleado empleado) {
        Boolean resultado = null;
        PreparedStatement stmt = null;

        if (this.conexion == null || empleado == null) {
            return false;
        }

        try {

            String sql = "UPDATE empleados SET nombre = ?, apellido1 = ?, apellido2 = ?, extension = ?"
                    + ", email = ?, codigooficina = ?, codigojefe = ?, puesto = ? WHERE codigoempleado = ?";

            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido1());
            stmt.setString(3, empleado.getApellido2());
            stmt.setString(4, empleado.getExtension());
            stmt.setString(5, empleado.getEmail());
            stmt.setString(6, empleado.getCodigoOficina());
            stmt.setInt(7, empleado.getCodigoJefe());
            stmt.setString(8, empleado.getPuesto());

            stmt.setInt(9, empleado.getCodigoEmpleado());
            if (stmt.executeUpdate() > 0) {
                resultado = true;

            }
        } catch (SQLException e) {
            System.err.println("Error en el Update: " + e.getMessage()+ " SQL:" + stmt.toString());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión: " + ex.getMessage());
            }
        }

        return resultado;
    }*/
    
    public Boolean delete(Integer id) {
        Boolean resultado = false;
        PreparedStatement stmt = null;

        try {
            String sql = "DELETE FROM clientes WHERE codigo = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id);

            resultado = stmt.execute();

            stmt.close();

            System.out.println();

        } catch (SQLException e) {

            System.err.println("Error en el Delete: " + e.getMessage() + " " + stmt.toString());
        }

        return resultado;

    }
}
