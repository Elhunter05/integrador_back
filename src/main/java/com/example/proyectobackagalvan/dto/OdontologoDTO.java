package com.example.proyectobackagalvan.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OdontologoDTO {
    private Long id;
    private Integer matricula;
    private String nombre;
    private String apellido;

    private List<Long> turnoIdList;

    @Override
    public String toString() {
        return "OdontologoDTO{" +
                "id=" + id +
                ", matricula=" + matricula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
