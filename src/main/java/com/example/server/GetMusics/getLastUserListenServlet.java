package com.example.server.GetMusics;

import com.example.server.Entities.LastListened;
import com.example.server.GiveJWT.DecodeJWT;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getLastUserListen")
public class getLastUserListenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Request-Method", "*");

        Cookie[] cookies = request.getCookies();

        String userId = null;

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("JWT_KEY")){
                userId = (String) DecodeJWT.decodeJWT(cookie.getValue()).get("userId");
            }
        }

        LastListened lastListened = GetLastListened.getLastListened(userId);

        String lastListened1 = new Gson().toJson(lastListened);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(lastListened1);
        out.flush();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
