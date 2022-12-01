package com.example.proyectobackagalvan.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class PacienteDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private LocalDate fechaIngreso;
    private Long domicilioId;

    private List<Long> turnoIdList;




    @Override
    public String toString() {
        return "PacienteDTO{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", domicilioId=" + domicilioId +
                '}';
    }
}
