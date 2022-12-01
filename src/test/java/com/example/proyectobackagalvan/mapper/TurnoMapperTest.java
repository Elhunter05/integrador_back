//package com.example.proyectobackagalvan.mapper;
//
//import com.example.proyectobackagalvan.dto.TurnoDTO;
//import com.example.proyectobackagalvan.entity.Odontologo;
//import com.example.proyectobackagalvan.entity.Paciente;
//import com.example.proyectobackagalvan.entity.Turno;
//import org.junit.jupiter.api.Test;
//import org.mapstruct.factory.Mappers;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class TurnoMapperTest {
//    @Autowired
//    private final TurnoMapper mapper = Mappers.getMapper(TurnoMapper.class);
//
//    @Test
//    public void turnoSimpleMapperTest() {
//        Turno turno = new Turno();
//        turno.setId(1L);
//        turno.setPaciente(new Paciente());
//        turno.setOdontologo(new Odontologo());
//        turno.setFecha(LocalDate.of(1995, 5, 29));
//
//        TurnoDTO turnoDTO = mapper.turnoToTurnoDTO(turno);
//        System.out.println(turno);
//        System.out.println(turnoDTO);
//
//        assertEquals(turnoDTO.getId(), turno.getId());
//    }
//}