package com.team4.controller;

import com.team4.model.User;
import com.team4.service.user.IUserService;
import com.team4.service.user.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static java.lang.System.out;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    IUserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateUserForm(request, response);
                break;
            case "login":
                loginForm(request,response);
            case "logout":
                logoutUer(request,response);
                break;
            default:
                listUser(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createUser(request, response);
                break;
            case "login":
                loginUser(request,response);
                break;
        }
    }

    private void showCreateUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request, response);
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        if(Objects.equals(password, re_password)) {
            User user = new User(name, password);
            userService.insert(user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }
      else {
             out.print("Sorry so much dude!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/login.jsp");
            dispatcher.forward(request, response);
        }
      out.close();
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.selectAll();
        request.setAttribute("users", users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void defaultIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    private void loginForm (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/login.jsp");
        requestDispatcher.forward(request,response);
    }

    private void loginUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("login_name");
        String password = request.getParameter("login_password");
        boolean isValid = userService.checkLogin(username, password);
        if (isValid) {
            HttpSession session = request.getSession();
            session.setAttribute("loginuser", username);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcome.jsp");
            requestDispatcher.forward(request,response);
        }
        else {
            out.print("<p style=\"color:red\">Sorry username or password error</p>");
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/login.jsp");
            dispatcher.include(request,response);
        }
        out.close();
    }
    private void logoutUer (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request,response);
    }
}
