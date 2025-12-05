package com.datafixcomp.monolito.usuario.service;

import com.datafixcomp.monolito.usuario.entity.Usuario;
import com.datafixcomp.monolito.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario crear(Usuario usuario) {
        // Validacion removida: if (usuarioRepository.existsByEmail(usuario.getEmail()))
        // { ... }
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNombre(usuarioActualizado.getNombre());
                    usuario.setApellido(usuarioActualizado.getApellido());
                    usuario.setEmail(usuarioActualizado.getEmail());
                    usuario.setTelefono(usuarioActualizado.getTelefono());
                    usuario.setDireccion(usuarioActualizado.getDireccion());
                    return usuarioRepository.save(usuario);
                })
                .orElse(null);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
