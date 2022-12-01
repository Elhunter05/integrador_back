package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.dto.OdontologoDTO;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    OdontologoDTO guardarOdontologo(OdontologoDTO odontologo);
    Optional<OdontologoDTO> buscarOdontologo(Long id);
    Optional<OdontologoDTO> buscarPorNombreYApellido(String nombre, String apellido);
    Optional<OdontologoDTO> buscarPorMatricula(Integer matricula);
    List<OdontologoDTO> mostrarOdontologos();
    void actualizarOdontologo(OdontologoDTO odontologo);
    void eliminarOdontologo(Long id);

//    Odontologo guardarOdontologo(OdontologoDTO odontologo);
//    Optional<OdontologoDTO> buscarPorId(Long id);
//    Optional<OdontologoDTO> buscarPorNombreYApellido(String nombre, String apellido);
//    Optional<OdontologoDTO> buscarPorMatricula(Integer matricula);
//    Collection<OdontologoDTO> mostrarOdontologos();
//    void actualizarOdontologo(OdontologoDTO odontologo);
//    void eliminarOdontologo(Long id);
}