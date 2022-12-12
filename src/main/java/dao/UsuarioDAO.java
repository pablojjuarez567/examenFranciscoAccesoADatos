package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Usuario;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class UsuarioDAO {
    
    private Connection connection;
    
    static final String INSERT_QUERY ="INSERT INTO usuario (nombre, apellidos, dni) VALUES (?,?,?)";
    static final String LIST_QUERY="SELECT * FROM usuario";
    static final String GET_BY_DNI="SELECT * FROM usuario WHERE dni=?";

    static final String URL = "jdbc:mysql://localhost:3306/examen";
    static final String USER = "root";
    static final String PASSWORD = "root";


    public void connect(){
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.getLogger(UsuarioDAO.class.getName()).info("Conexión establecida con exito");
        }catch(Exception ex){
            System.out.println("Error al conectar a la base de datos");
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    public void close(){
        try {
            connection.close();
        } catch (Exception ex) {
            System.out.println("Error al cerrar la base de datos");     
        }
    }
    
    public void save(Usuario user){
        
        try (var pst = connection.prepareStatement(INSERT_QUERY, RETURN_GENERATED_KEYS)) {

            pst.setString(1, user.getNombre());
            pst.setString(2, user.getApellidos());
            pst.setString(3, user.getDni());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Usuario> list(){

        var salida = new ArrayList<Usuario>(0);

        try (var pst = connection.prepareStatement(LIST_QUERY)) {

            ResultSet result = pst.executeQuery();
            while (result.next()) salida.add(construirAlumno(result));

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return salida;
    }    
    
    public Usuario getByDNI(String dni){
        
        Usuario salida = new Usuario();

        try (var pst = connection.prepareStatement(GET_BY_DNI)) {

            pst.setString(1, dni);

            ResultSet result = pst.executeQuery();

            if (result.next()) return construirAlumno(result);
            else return null;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return salida;
    }


    public Usuario construirAlumno(ResultSet result) {
        Usuario usuario = new Usuario();

        try {
            usuario.setId(result.getLong("id"));
            usuario.setNombre(result.getString("nombre"));
            usuario.setApellidos(result.getString("apellidos"));
            usuario.setDni(result.getString("dni"));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;
    }
}
