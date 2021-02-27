package com.example.server;

import com.example.server.DatabaseQueries.LoginQueries.GetUserId;
import com.example.server.DatabaseQueries.LoginQueries.IsInDatabase;
import com.example.server.GiveJWT.giveJWTAccess;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Request-Method", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,PATCH");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Expose-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        BufferedReader br = request.getReader();
        String s = br.readLine();
        br.close();

        JsonObject jsonObject = JsonParser.parseString(s).getAsJsonObject();

        String email = String.valueOf(jsonObject.get("email"));
        String password = String.valueOf(jsonObject.get("password"));

        email = email.substring(1, email.length()-1);
        password = password.substring(1, password.length()-1);


        boolean isInDatabase = IsInDatabase.isInDatabase(email, password);

        String employeeJsonString = String.valueOf(true);

        if(!isInDatabase){
            employeeJsonString = new Gson().toJson(isInDatabase);
            response.setHeader("Set-Cookie", "JWT_KEY=None; Path=/; Domain=localhost; HttpOnly");

        }  else{
            String userId = GetUserId.getUserId(email);
            response.setHeader("Set-Cookie", "JWT_KEY="+giveJWTAccess.createJWT(userId)+"; Path=/; Domain=localhost; HttpOnly");
        }

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", ".localhost:3000");

        Cookie cookie = new Cookie("JWT_key", giveJWTAccess.createJWT("3"));
        cookie.setDomain("localhost");
//            cookie.setHttpOnly(true/);
        response.addCookie(cookie);

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        boolean isInDatabase = IsInDatabase.isInDatabase(login, pass);

        String employeeJsonString = new Gson().toJson(isInDatabase);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();
    }
}
