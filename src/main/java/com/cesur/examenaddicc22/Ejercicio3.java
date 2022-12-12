package com.cesur.examenaddicc22;

import dao.BibliotecaDAO;

import java.util.ArrayList;
import java.util.HashSet;
import models.Ejemplar;
import models.Libro;

class Ejercicio3 {

    /**
     * Enunciado:
     * 
     * Queremos gestionar los libros en la biblioteca teniendo en cuenta que no
     * es lo mismo un libro que un ejemplar.
     * De un mismo libro podemos tener varios ejemplares, cada uno en un estado
     * diferente, o de una edición diferente.
     * Por ello vamos a utilizar Hibernate para realizar la gestión.
     * 
     * Modificar esta clase para poder probar el correcto funcionamiento del 
     * programa, la clase BibliotecaDAO para implementar los métodos, las 
     * clases POJO para añadir la relación y el archivo de configuración 
     * de hibernate.
     */

    static void solucion() {
        
        var daoh = new BibliotecaDAO();

        var ej1 = new Ejemplar("bueno",2018);
        var ej2 = new Ejemplar("mal",2019);
        var ej3 = new Ejemplar("excelente",2021);
        
        Libro libro = new Libro();
        libro.setTitulo("Aprende Java en 24h");
        libro.setAutor("Rafael Montes");

        ej1.setLibro(libro);
        ej2.setLibro(libro);
        ej3.setLibro(libro);

        var ejemplares = new ArrayList<Ejemplar>();
        ejemplares.add(ej1);
        ejemplares.add(ej2);
        ejemplares.add(ej3);
        libro.setEjemplares(ejemplares);
        
        daoh.saveLibro(libro);
        daoh.printInfo();
        
        System.out.println("\nEstos son los libros que tienen algun ejemplar en buen estado");
        
        HashSet<Libro> salida = daoh.findByEstado("bueno");
        if(salida!=null) salida.forEach( (e)->{ System.out.println(e.getTitulo()); } );
                
        
    }
    
}
