/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PRINCIPAL;

import CLIENTES.Clientes;
import DAO.Clientes_DAO;
import java.util.Scanner;

/**
 *
 * @author sara
 */
public class Main {
    static int i;
    static Scanner sc = new Scanner(System.in);
    static Clientes_DAO clientes = new Clientes_DAO();
    
    public static void main(String[] args) {
        Clientes_DAO clientes = new Clientes_DAO();
        Clientes cliente = null;
        lectura(cliente, clientes);
        if (clientes.getConexion() == null){
            System.err.println("Programa terminado. Error la conexión");
            System.exit(1);
        }
        System.out.println("CONEXION ESTABLECIDA");
        System.out.println("Elija una opcion");
        System.out.println("1. Insertar nuevo cliente");
        System.out.println("2. Modificar cliente");
        System.out.println("3. borrar un cliente");
        System.out.println("4. SALIR");
        int opcion = sc.nextInt();
        switch(opcion){
            case 1:
                insertar(clientes);
                break;
            case 2:
                modificar(clientes);
                break;
            case 3:
                borrar(cliente);
                break;
            case 4:
                System.out.println("Gracias por usar nuestra aplicacion");
                break;
            
        }
        
        
        
    }
    public static Clientes existecliente() {
        Clientes cliente = null;

        System.out.print("Indique el id del empleado que desea buscar: ");
        cliente = clientes.read(sc.nextInt());

        return cliente;
    }
    
    public static void insertar(Clientes_DAO clientes){
       Clientes cliente = new Clientes();
        
        System.out.print("Indique el codigo del cliente: ");
        cliente.setCodigo(sc.nextLine());

        System.out.print("Indique la empresa del cliente: ");
        cliente.setEmpresa(sc.nextLine());

        System.out.print("Indique el contacto del cliente: ");
        cliente.setContacto(sc.nextLine());
        
        System.out.print("Indique el cargo contacto del cliente: ");
        cliente.setCargo_contacto(sc.nextLine());

        System.out.print("Indique la direccion del cliente: ");
        cliente.setDireccion(sc.nextLine());

        System.out.print("Indique la ciudad del cliente: ");
        cliente.setCiudad(sc.nextLine());

        System.out.print("Indique la region del cliente: ");
        cliente.setRegion(sc.nextLine());
        
        System.out.print("Indique el código postal del cliente: ");
        cliente.setCp(sc.nextLine());
        
        System.out.print("Indique la ciudad del cliente: ");
        cliente.setPais(sc.nextLine());

        System.out.print("Indique el telefono del cliente: ");
        cliente.setTelefono(sc.nextLine());

        System.out.print("Indique el fax del cliente: ");
        cliente.setFax(sc.nextLine());


        if (clientes.insert(cliente)) {
            System.out.println("El cliente: "+cliente.getCodigo()+ ", "+cliente.getEmpresa()+ ", "+cliente.getContacto()+ ", "+cliente.getCargo_contacto()+ "(...) ha sido añadido correctamente");
        } else {
            System.err.println("El empleado que intenta introducir no es válido.\n");
        }
    }
    
    public static void modificar(Clientes_DAO clientes){
        
    }
    
    public static void lectura(Clientes cliente, Clientes_DAO clientes){
        for(i = 1; i <= 10; i++){ //length
        cliente = clientes.read(i);
            if (cliente != null) {
                System.out.println(cliente.toString());
            }
            else
            {
                System.err.println("El empleado no existe o no se puede leer");
            }
        }
    }
    public static void borrar(Clientes cliente){
      Clientes client = existecliente();
        String resp = null;

        if (client != null) {
                System.out.println("\n¿Está seguro que desea eliminar al siguiente usuario?"
                        + "\n  " + client);
                System.out.print("Su respuesta [Y/N]: ");
                resp = sc.nextLine();
                
                if (resp.equalsIgnoreCase("y")) {
                    clientes.delete(client.getId());
                    System.out.println("Entrada eliminada.");
                } else {
                    System.out.println("Entrada no eliminada.");
                }
        } else {
            System.err.println("El empleado no existe o no se puede leer.");
        }
    }

}
