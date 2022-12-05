package com.example.proyectobackagalvan.mapper;

import com.example.proyectobackagalvan.dto.TurnoDTO;
import com.example.proyectobackagalvan.entity.Turno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TurnoMapper {
    Turno turnoDTOtoTurno(TurnoDTO turnoDTO);
    TurnoDTO turnoToTurnoDTO(Turno turno);
}