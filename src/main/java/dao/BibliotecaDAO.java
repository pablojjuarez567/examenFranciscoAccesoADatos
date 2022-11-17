package dao;

import java.util.ArrayList;
import models.Ejemplar;
import models.Libro;
import org.hibernate.SessionFactory;

/**
 *
 * @author FranciscoRomeroGuill
 */
public class BibliotecaDAO {
    
    private static SessionFactory sessionFactory;
    
    static{
        try{

            /* Completar conexión con hibernate */
            
            System.out.println("Conexión no realizada");
        }catch(Exception ex){
            System.out.println("Error iniciando Hibernate");
            System.out.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public void saveLibro( Libro e ){
        
        /* Guarda un libro con todos sus ejemplares en la base de datos */
        
        System.out.println("Método saveLibro no implementado");
        
    }
    
    
    public void printInfo(){
        
        /* 
          Muestra por pantalla todos los libros de la biblioteca y el número
          de ejemplares disponibles de cada uno.
          
          Un ejemplo de salida es:
        
          Biblioteca
          ----------
          Como aprender java en 24h. (3)
          Como ser buena persona (1)
          Aprobando exámenes fácilmente (5)
          ...
        
        */
        System.out.println("Método printInfo no implementado");
        
    }
    
}
