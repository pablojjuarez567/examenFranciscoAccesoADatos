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
            close();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }

        for(int i = 0; i < libro.getEjemplares().size(); i++){
            insertEjemplar(libro.getEjemplares().get(i));
        }
    }

    public HashSet<Libro> findByEstado(String estado) {

        HashSet<Libro> salida = new HashSet<Libro>();

        var libros = getAllLibros();

        for (int j = 0 ; j < libros.size() ;j++){
            int finalJ = j;
            getAllEjemplaresByLibro(libros.get(j)).forEach((e)->{
                if(e.getEstado().equals(estado)){
                    salida.add(libros.get(finalJ));
                }
            });

        }
        return salida;


    }

    public void printInfo () {
        
        var libros = getAllLibros();

        for (int i = 0 ; i < libros.size() ; i++){
            System.out.println("Libro: " + libros.get(i).getTitulo());
            System.out.println("Autor: " + libros.get(i).getAutor());
            System.out.println("Ejemplares: ");
            getAllEjemplaresByLibro(libros.get(i)).forEach((e)->{
                System.out.println("Estado: " + e.getEstado() + " Edicion: " + e.getEdicion());
            });
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

    public ArrayList<Ejemplar> getAllEjemplaresByLibro (Libro libro) {

        try (var s = sessionFactory.openSession()) {
            var q = s.createQuery("from Ejemplar where Ejemplar.libro = :id", Ejemplar.class);
            q.setParameter("id", libro.getId());
            return (ArrayList<Ejemplar>) q.list();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    public void insertEjemplar (Ejemplar ejemplar) {
        try (var s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            s.persist(ejemplar);
            t.commit();
            close();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }
    public void close() {
        sessionFactory.close();
    }
}
