package com.example.server.GiveJWT;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/refresh_token")
public class refresh_token extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String sessionid = request.getSession().getId();
//        response.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid + "; secure");
//
//        String name = "Cookie name";
//        String value = "Cookie value";
//
//        Cookie cookie = new Cookie("textCooke", "ASD");
//        cookie.setDomain("localhost");
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);

        Cookie cookie = new Cookie("JWT_key", giveJWTAccess.createJWT("3"));
            cookie.setDomain("localhost");
//            cookie.setHttpOnly(true/);
        response.addCookie(cookie);
//
//
    }
}
