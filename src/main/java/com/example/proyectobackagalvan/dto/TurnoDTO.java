package com.example.proyectobackagalvan.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class TurnoDTO {
    private Long id;
    private Long pacienteId;
    private Long odontologoId;
    private LocalDate fecha;

    @Override
    public String toString() {
        return "TurnoDTO{" +
                "id=" + id +
                ", pacienteId=" + pacienteId +
                ", odontologoId=" + odontologoId +
                ", fecha=" + fecha +
                '}';
    }
}