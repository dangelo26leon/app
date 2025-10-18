package com.jwt_security.app.models;

public enum TipoRol {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String value;

    TipoRol(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
