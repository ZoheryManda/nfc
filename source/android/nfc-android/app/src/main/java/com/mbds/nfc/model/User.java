package com.mbds.nfc.model;

import com.orm.SugarRecord;

/**
 * Created by macbook on 08/07/2015.
 */
public class User extends SugarRecord<User> {
    String unique_id;
    String pseudo;
    String email;
    String encrypt_password;
    String salt;

    public User() {
    }

    public User(String unique_id, String pseudo, String email, String encrypt_password, String salt) {
        this.unique_id = unique_id;
        this.pseudo = pseudo;
        this.email = email;
        this.encrypt_password = encrypt_password;
        this.salt = salt;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncrypt_password() {
        return encrypt_password;
    }

    public void setEncrypt_password(String encrypt_password) {
        this.encrypt_password = encrypt_password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
