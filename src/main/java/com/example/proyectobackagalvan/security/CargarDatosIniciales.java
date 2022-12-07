package com.example.proyectobackagalvan.security;

import com.example.proyectobackagalvan.entity.Usuario;
import com.example.proyectobackagalvan.entity.UsuarioRole;
import com.example.proyectobackagalvan.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarDatosIniciales implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public CargarDatosIniciales(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //cargar un usuario para probar
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        String passCifrada = cifrador.encode("miPass");
        Usuario usuarioUser = new Usuario("Andrés", "Galván", "8il.andre@gmail.com", passCifrada, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuarioUser);

        Usuario usuarioAdmin = new Usuario("Javi", "Grande", "prueba@gmail.com", passCifrada, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAdmin);
    }
}
