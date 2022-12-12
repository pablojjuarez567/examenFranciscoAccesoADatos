package dao;

import java.util.ArrayList;
import java.util.HashSet;

import models.Ejemplar;
import models.Libro;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author FranciscoRomeroGuill
 */
public class BibliotecaDAO {

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();

        } catch (Exception ex) {
            System.out.println("Error iniciando Hibernate");
            System.out.println(ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void saveLibro(Libro libro) {

        try (var s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.persist(libro);
            t.commit();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    public HashSet<Libro> findByEstado(String estado) {

        HashSet<Libro> salida = new HashSet<Libro>();

        var libros = getAllLibros();

        for (Libro libro : libros) {
            for (Ejemplar ejemplar : libro.getEjemplares()) {
                if (ejemplar.getEstado().equals(estado)) {
                    salida.add(libro);
                }
            }
        }


        return salida;
    }

    public void printInfo () {
        
        var libros = getAllLibros();

        for (int i = 0 ; i < libros.size() ; i++){
            System.out.println("Libro: " + libros.get(i).getTitulo());
            System.out.println("Autor: " + libros.get(i).getAutor());
            System.out.println("");

            var ejemplares = libros.get(i).getEjemplares();

            if (ejemplares != null)     System.out.println("Ejemplares: ");

            for (int j = 0 ; j < ejemplares.size() ; j++){
                System.out.println("Ejemplar número "+ (j+1));
                System.out.println("Estado: " + ejemplares.get(j).getEstado());
                System.out.println("Edición: " + ejemplares.get(j).getEdicion());
                System.out.println("");
            }
            System.out.println("-------------------------------------------------");
        }
    }

    public ArrayList<Libro> getAllLibros () {

        try (var s = sessionFactory.openSession()) {
            var q = s.createQuery("from libro");
            return (ArrayList<Libro>) q.list();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }


    public void close() {
        sessionFactory.close();
    }
}
