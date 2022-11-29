package com.example.proyectobackagalvan.dto;

import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.entity.Paciente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class TurnoDTO {
    private Long id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDate fecha;
}
