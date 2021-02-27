package com.example.server.GiveJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.xml.bind.DatatypeConverter;

public class DecodeJWT {
    public static Claims decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(Secret_Key_JWT.getSecret_Key_current()))
                .parseClaimsJws(jwt).getBody();
    }
}
