package com.example.proyectobackagalvan.service;

import com.example.proyectobackagalvan.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);
    Optional<Odontologo> buscarPorId(Long id);
    Optional<Odontologo> buscarPorNombreYApellido(String nombre, String apellido);
    Optional<Odontologo> buscarPorMatricula(Integer matricula);
    List<Odontologo> mostrarOdontologos();
    void actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Long id);

//    Odontologo guardarOdontologo(OdontologoDTO odontologo);
//    Optional<OdontologoDTO> buscarPorId(Long id);
//    Optional<OdontologoDTO> buscarPorNombreYApellido(String nombre, String apellido);
//    Optional<OdontologoDTO> buscarPorMatricula(Integer matricula);
//    Collection<OdontologoDTO> mostrarOdontologos();
//    void actualizarOdontologo(OdontologoDTO odontologo);
//    void eliminarOdontologo(Long id);
}
