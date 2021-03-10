package com.example.server.GetMusics;

import com.example.server.Entities.Album_Song;
import com.example.server.Entities.Song;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getAlbumServlet")
public class getAlbumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Request-Method", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Expose-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("JWT_KEY")){
                String test = (String) DecodeJWT.decodeJWT(cookie.getValue()).get("userId");
            }
        }


        String s = request.getParameter("songId");
        List<Album_Song> album_songList = GetSongAlbum.getSongAlbum(s);
        List<Song> songList = new ArrayList<>();
        for (Album_Song album_song : album_songList) {
            songList.add(GetSongQueries.getSongQuery(album_song.getSongId(), "false"));
        }


        String songsToReturn = new Gson().toJson(songList);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(songsToReturn);
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
