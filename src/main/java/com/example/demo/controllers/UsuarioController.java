package com.example.demo.controllers;

import com.example.demo.dao.UsuarioDao;
import com.example.demo.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao UsuarioDao;

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Alex");
        usuario.setApellido("Mata");
        usuario.setEmail("alexmata@hotmail.com");
        usuario.setTelefono("1234567");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(){
        return UsuarioDao.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        UsuarioDao.registrarUsuario(usuario);
    }

    @RequestMapping(value = "usuario123")
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Alex");
        usuario.setApellido("Mata");
        usuario.setEmail("alexmata@hotmail.com");
        usuario.setTelefono("1234567");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id){
        UsuarioDao.eliminar(id);{}
    }

    @RequestMapping(value = "usuario678")
    public Usuario buscar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Alex");
        usuario.setApellido("Mata");
        usuario.setEmail("alexmata@hotmail.com");
        usuario.setTelefono("1234567");
        return usuario;
    }
}
