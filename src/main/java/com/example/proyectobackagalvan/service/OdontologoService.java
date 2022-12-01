package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.dto.OdontologoDTO;
import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.entity.Turno;
import com.example.proyectobackagalvan.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoService implements IOdontologoService {
    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) { this.odontologoRepository = odontologoRepository; }



    private OdontologoDTO odontologoAOdontologoDTO(Odontologo odontologo) {
        OdontologoDTO respuesta = new OdontologoDTO();
        respuesta.setId(odontologo.getId());
        respuesta.setMatricula(odontologo.getMatricula());
        respuesta.setNombre(odontologo.getNombre());
        respuesta.setApellido(odontologo.getApellido());

        Set<Turno> turnoSet = odontologo.getTurnoSet();
        List<Long> turnoIdList = new ArrayList<>();

        for (Turno turno: turnoSet) {
            turnoIdList.add(turno.getId());
        }

        return respuesta;
    }

    private Odontologo odontologoDTOaOdontologo(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = new Odontologo();

        List<Long> turnoIdList = odontologoDTO.getTurnoIdList();
        Set<Turno> turnoSet = new HashSet<>();

        // Acá iría un for o algo para meter turnos en turnoSet usando turnoIdList, pero no sé cómo conseguir el turno

        odontologo.setId(odontologoDTO.getId());
        odontologo.setMatricula(odontologoDTO.getMatricula());
        odontologo.setNombre(odontologoDTO.getNombre());
        odontologo.setApellido(odontologoDTO.getApellido());


        return odontologo;
    }


    public OdontologoDTO guardarOdontologo(OdontologoDTO odontologo) {
        Odontologo odontologoAGuardar = odontologoDTOaOdontologo(odontologo);
        Odontologo odontologoGuardado = odontologoRepository.save(odontologoAGuardar);
        return odontologoAOdontologoDTO(odontologoGuardado);
    }
    public Optional<OdontologoDTO> buscarOdontologo(Long id) {
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(id);
        return odontologoBuscado.map(this::odontologoAOdontologoDTO);
    }
    public Optional<OdontologoDTO> buscarPorNombreYApellido(String nombre, String apellido) {
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findByNombreAndApellido(nombre, apellido);
        return odontologoBuscado.map(this::odontologoAOdontologoDTO);
    }
    public Optional<OdontologoDTO> buscarPorMatricula(Integer matricula) {
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findByMatricula(matricula);
        return odontologoBuscado.map(this::odontologoAOdontologoDTO);
    }
    public List<OdontologoDTO> mostrarOdontologos() {
        List<Odontologo> odontologosEncontrados = odontologoRepository.findAll();
        List<OdontologoDTO> respuesta = new ArrayList<>();
        for (Odontologo o: odontologosEncontrados) {
            respuesta.add(odontologoAOdontologoDTO(o));
        }
        return respuesta;
    }
    public void actualizarOdontologo(OdontologoDTO odontologo) {
        Odontologo odontologoAActualizar = odontologoDTOaOdontologo(odontologo);
        odontologoRepository.save(odontologoAActualizar);
    }
    public void eliminarOdontologo(Long id) { odontologoRepository.deleteById(id); }


//    private final ObjectMapper mapper;
//
//    @Autowired
//    public OdontologoService(OdontologoRepository odontologoRepository, ObjectMapper mapper) {
//        this.odontologoRepository = odontologoRepository;
//        this.mapper = mapper;
//    }
//
//    public Odontologo guardarOdontologo(OdontologoDTO odontologo) {
//        Odontologo nuevoOdontologo = mapper.convertValue(odontologo, Odontologo.class);
//        odontologoRepository.save(nuevoOdontologo);
//        return nuevoOdontologo;
//    }
//    public Optional<OdontologoDTO> buscarPorId(Long id) { return odontologoRepository.findById(id); }
//    public Optional<OdontologoDTO> buscarPorNombreYApellido(String nombre, String apellido) { return odontologoRepository.findByNombreAndApellido(nombre, apellido); }
//    public Optional<OdontologoDTO> buscarPorMatricula(Integer matricula) { return odontologoRepository.findByMatricula(matricula); }
//    public Collection<OdontologoDTO> mostrarOdontologos() {
//        List<Odontologo> odontologoList = odontologoRepository.findAll();
//        Set<OdontologoDTO> odontologoDTOSet = new HashSet<OdontologoDTO>();
//        for (Odontologo odontologo: odontologoList)
//            odontologoDTOSet.add(mapper.convertValue(odontologo, OdontologoDTO.class));
//
//        return odontologoDTOSet;
//    }
//    public void actualizarOdontologo(OdontologoDTO odontologo) { odontologoRepository.save(odontologo); }
//    public void eliminarOdontologo(Long id) { odontologoRepository.deleteById(id); }

}
