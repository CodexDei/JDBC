package org.codexdei.java.jdbc.repositorio;

import org.codexdei.java.jdbc.modelo.Producto;
import org.codexdei.java.jdbc.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto>{

    //Implementamos una clase privada para la coneccion a la base de datos
    private Connection getInstance() throws SQLException {

        return ConexionBaseDatos.getConnection();
    }

    @Override
    public List<Producto> listar() {

        List<Producto> productos = new ArrayList<>();

        try(Statement stmt = getInstance().createStatement();
            ResultSet rst = stmt.executeQuery("SELECT * FROM productos")){

            while (rst.next()){

                Producto p = crearProducto(rst);

                productos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return productos;
    }


    @Override
    public Producto buscarId(Long id) {

        Producto producto = null;

        try(PreparedStatement psts = getInstance().
                prepareStatement("SELECT * FROM productos WHERE idproductos = ?");
            ){

            psts.setLong(1,id);
            ResultSet rst = psts.executeQuery();

            if(rst.next()){

                producto = crearProducto(rst);

            }
            rst.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return producto;
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Long id) {

    }

    private static Producto crearProducto(ResultSet rst) throws SQLException {
        Producto p = new Producto();
        p.setId(rst.getLong("idproductos"));
        p.setNombre(rst.getString("nombre"));
        p.setPrecio(rst.getInt("precio"));
        p.setFechaRegistro(rst.getDate("fecha_registro"));
        return p;
    }
}
