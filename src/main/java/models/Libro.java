package models;

import com.sun.istack.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.*;

@Entity(name = "libro")
public class Libro implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "titulo")
    private String titulo;
    @Basic
    @Column(name = "autor")
    private String autor;
    @OneToMany (mappedBy = "libro", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ejemplar> ejemplares = new ArrayList<Ejemplar>();


    
    public Libro() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEjemplares(ArrayList<Ejemplar> ejemplares){
        this.ejemplares = ejemplares;
    }

    public List<Ejemplar> getEjemplares(){
        return ejemplares;
    }

    public HashSet<Ejemplar> getEjemplaresHash (){
        HashSet<Ejemplar> a = new HashSet<Ejemplar>(this.ejemplares);
        return  a;
    }

    public void addEjemplar(Ejemplar ejemplar){
        this.ejemplares.add(ejemplar);
    }

    public void removeEjemplar(Ejemplar ejemplar){
        this.ejemplares.remove(ejemplar);
    }



    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + '}';
    }
    
    

    
}
