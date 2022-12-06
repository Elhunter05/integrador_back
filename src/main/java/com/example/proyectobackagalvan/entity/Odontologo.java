package com.example.proyectobackagalvan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Odontologos")
@Getter @Setter
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer matricula;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @JsonIgnore
    @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Turno> turnoSet = new HashSet<>();

    public Odontologo() {
    }

    @Autowired
    public Odontologo(Integer matricula, String nombre, String apellido, Set<Turno> turnoSet) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.turnoSet = turnoSet;
    }

    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", matricula=" + matricula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", turnoSet=" + turnoSet +
                '}';
    }
}
