package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Odontologo;
import com.example.proyectobackagalvan.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoService implements IOdontologoService {
    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) { this.odontologoRepository = odontologoRepository; }
    public Odontologo guardarOdontologo(Odontologo odontologo) { return odontologoRepository.save(odontologo); }
    public Optional<Odontologo> buscarPorId(Long id) { return odontologoRepository.findById(id); }
    public Optional<Odontologo> buscarPorNombreYApellido(String nombre, String apellido) { return odontologoRepository.findByNombreAndApellido(nombre, apellido); }
    public Optional<Odontologo> buscarPorMatricula(Integer matricula) { return odontologoRepository.findByMatricula(matricula); }
    public List<Odontologo> mostrarOdontologos() { return odontologoRepository.findAll(); }
    public void actualizarOdontologo(Odontologo odontologo) { odontologoRepository.save(odontologo); }
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
