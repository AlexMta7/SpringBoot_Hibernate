package com.example.demo.dao;

import com.example.demo.models.Usuario;

import java.util.List;

public interface UsuarioDao  {

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrarUsuario(Usuario usuario);

    boolean verificarCredenciales(Usuario usuario);

    boolean verificarUsuario(Usuario usuario);
}
