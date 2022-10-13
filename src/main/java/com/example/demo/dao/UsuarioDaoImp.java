package com.example.demo.dao;

import com.example.demo.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        //List<Usuario> resultado = entityManager.createQuery(query).getResultList();
        //return resultado;
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public boolean verificarCredenciales(Usuario usuario){
        String query = "FROM Usuario WHERE email = :email";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();

        //Gets the password
        String hashedPassword = lista.get(0).getPassword();

        //TO VERIFY THE PASSWORD
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        /*Compares a Hash with a password. returns a boolean*/
        //Para guardar en una variable
        //boolean passwordEsLaMisma = argon2.verify(hashedPassword, usuario.getPassword());

        if(lista.isEmpty()){
            return false;
        }

        return argon2.verify(hashedPassword, usuario.getPassword());

        /* ***PORTION BELOW DOES ALMOST THE SAME BUT WITH NO ENCRYPTION AND LESS OPTIMIZED*** */

        /* This portion of code verifies if the email and user are the same of the DB with an unencrypted password
        String query = "FROM Usuario WHERE email = :email AND password = :password";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .setParameter("password", usuario.getPassword())
                .getResultList();
         */
        /* This portion of code is to return if the list is empty
        if(lista.isEmpty()){
            return false;
        }
        else{
            return true;
        }
        */
    }

    @Override
    public boolean verificarUsuario(Usuario usuario) {
        String query = "FROM Usuario WHERE email = :email ";
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email", usuario.getEmail())
                .getResultList();
        return !lista.isEmpty();
    }
}
