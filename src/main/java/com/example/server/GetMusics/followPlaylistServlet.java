package com.example.server.GetMusics;

import com.example.server.Entities.AuthorForSearch;
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

@WebServlet("/followPlaylistServlet")
public class followPlaylistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Request-Method", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Expose-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        Cookie[] cookies = request.getCookies();

        String userId = null;

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("JWT_KEY")){
                userId = (String) DecodeJWT.decodeJWT(cookie.getValue()).get("userId");
            }
        }



        FollowPlaylistQuery.followPlaylist(request.getParameter("playlistId"), userId);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
