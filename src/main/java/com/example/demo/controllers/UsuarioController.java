package com.example.demo.controllers;

import com.example.demo.dao.UsuarioDao;
import com.example.demo.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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
    public String registrarUsuario(@RequestBody Usuario usuario){
        if(!UsuarioDao.verificarUsuario(usuario)){
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            // i: iteraciones,i1:cantidad de memoria, i2:paralelismo (procesos simultaneos, mientras más iteraciones
            // mayor paralelismo para mayor velocidad de procesamiento), usuario.getPassword(): dato a hashear;
            // mientras más iteraciones más segura es la contraseña
            String hash = argon2.hash(1,1024,1,usuario.getPassword());
            //Se establece la contraseña con el hash
            usuario.setPassword(hash);
            //modify DB to accept a long string

            UsuarioDao.registrarUsuario(usuario);
            return "OK";
        }
        else {
            return "FAIL";
        }


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
