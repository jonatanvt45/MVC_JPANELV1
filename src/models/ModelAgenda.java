/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Salvador Hernandez Mendoza
 */
public class ModelAgenda {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;

    private String nombre;
    private String email;
    private String telefono;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Método que realiza las siguietnes acciones: 1- Conecta con la base
     * agenda_mvc, 2- Consulta todo los registros de la tabla contactos, 3-
     * Obtiene el nombre y el email y los guarda en las variables globales
     * nombre y email.
     */
    public void conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_mvc", "root", "");
            st = conexion.createStatement();
            String sql = "SELECT * FROM contactos;";
            System.out.println(sql);
            rs = st.executeQuery(sql);
            rs.next();
            setValues();
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 001: " + err.getMessage());
        }
    }

    /**
     * Lee los valores del registro seleccionado y los asigna a las variables
     * miembre nombre, email y telefono.
     */
    public void setValues() {
        try {
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            telefono = rs.getString("telefono");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error model 102: " + err.getMessage());

        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al primer registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverPrimerRegistro() {
        try{
            rs.first();
            setValues();
        }catch(Exception err){
            JOptionPane.showMessageDialog(null,"Error ModelAgenda Fail001  "+err.getMessage()); 
            
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al siguiente
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverSiguienteRegistro() {
        try{
            rs.next();
            setValues();
        }catch(Exception err){
            JOptionPane.showMessageDialog(null,"Error ModelAgenda UltimoRegistro:  "+err.getMessage()); 
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al anterior
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverAnteriorRegistro() {
        try{
            rs.previous();
            setValues();
        }catch(Exception err){
            JOptionPane.showMessageDialog(null,"Error ModelAgenda PrimerRegistro:  "+err.getMessage()); 
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al ultimo registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la ariable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverUltimoRegistro() {
        try{
            rs.last();
            setValues();
        }catch(Exception err){
            JOptionPane.showMessageDialog(null,"Error ModelAgenda Fail002  "+err.getMessage()); 
        }
    }

    
}
