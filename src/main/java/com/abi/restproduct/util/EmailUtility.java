package com.abi.restproduct.util;

public class EmailUtility {

    public String ocultar(String emailCiudadano){
        String primerLetra = String.valueOf(emailCiudadano.charAt(0));
        String[] correoDividido = emailCiudadano.split("@");
        String ultimaLetra = String.valueOf(correoDividido[0].charAt(correoDividido[0].length()-1));
        String dominio = "@"+correoDividido[1];
        return primerLetra+"*****"+ultimaLetra+dominio;
    }
}
