package models;

import org.hibernate.annotations.Table;


import javax.persistence.*;
import java.io.Serializable;
@Entity (name = "Ejemplar")
public class Ejemplar implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "estado")
    private String estado; /* excelente, bueno, regular, malo */
    @Basic
    @Column(name = "edicion")
    private Integer edicion;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "libro_id", referencedColumnName = "id")
    private Libro libro;

    public Ejemplar() {
    }

    public Ejemplar(String estado, Integer edicion) {
        this.estado = estado;
        this.edicion = edicion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getEdicion() {
        return edicion;
    }

    public void setEdicion(Integer edicion) {
        this.edicion = edicion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "Ejemplar{" + "id=" + id + ", estado=" + estado + ", edicion=" + edicion + '}';
    }


}