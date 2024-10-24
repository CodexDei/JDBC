package org.codexdei.java.jdbc;

import org.codexdei.java.jdbc.modelo.Producto;
import org.codexdei.java.jdbc.repositorio.ProductoRepositorioImpl;
import org.codexdei.java.jdbc.repositorio.Repositorio;
import org.codexdei.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;

public class EjemploJdbc {

    public static void main(String[] args) {

        try(Connection cnn = ConexionBaseDatos.getConnection();
            ) {

            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();

            repositorio.listar().forEach(p ->

            System.out.println("Id:" + p.getId() + "  ||  " +
                             "Nombre:" + p.getNombre() + "  ||  " +
                             "Precio: " + p.getPrecio() + "  ||  " +
                             "Fecha: "  + p.getFechaRegistro()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
