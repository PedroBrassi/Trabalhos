package org.example.academic.system.model;

/** Usuário com perfil ADMIN. */
public class Administrator extends User {

    public Administrator(String username, String password) {
        super(username, password, Role.ADMIN);
    }
}
