package com.example.demo.dao;

import com.example.demo.models.Usuario;

import java.util.List;

public interface UsuarioDao  {

    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrarUsuario(Usuario usuario);

    /*Verifica usuario por email y password
    boolean verificarCredenciales(Usuario usuario);
     */
    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);

    boolean verificarUsuario(Usuario usuario);
}
