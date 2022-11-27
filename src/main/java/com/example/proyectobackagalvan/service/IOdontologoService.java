package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Odontologo;

import java.util.Collection;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo registrarOdontologo(Odontologo odontologo);
    Optional<Odontologo> buscarOdontologo(Long id);
    Optional<Odontologo> buscarOdontologoPorNombreCompleto(String nombre, String apellido);
    Optional<Odontologo> buscarOdontologoPorMatricula(Integer matricula);
    Collection<Odontologo> listarOdontologos();
    void actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Long id);

//    Odontologo registrarOdontologo(OdontologoDTO odontologo);
//    Optional<OdontologoDTO> buscarOdontologo(Long id);
//    Optional<OdontologoDTO> buscarOdontologoPorNombreCompleto(String nombre, String apellido);
//    Optional<OdontologoDTO> buscarOdontologoPorMatricula(Integer matricula);
//    Collection<OdontologoDTO> listarOdontologos();
//    void actualizarOdontologo(OdontologoDTO odontologo);
//    void eliminarOdontologo(Long id);
}
