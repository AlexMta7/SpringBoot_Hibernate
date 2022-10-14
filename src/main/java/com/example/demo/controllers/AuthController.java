package com.example.demo.controllers;

import com.example.demo.dao.UsuarioDao;
import com.example.demo.models.Usuario;
import com.example.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){

        Usuario loggedUser = usuarioDao.obtenerUsuarioPorCredenciales(usuario);
        if (loggedUser != null){

            String tokenJwt = jwtUtil.create(String.valueOf(loggedUser.getId()), loggedUser.getEmail());
            return tokenJwt;
        }
        return "FAIL";
    }

    /* VERIFICACIÓN DE CREDENCIALES SIN JWT
    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){
       if (usuarioDao.verificarCredenciales(usuario)){
           return "OK";
       }
       return "FAIL";
    }
     */



}
