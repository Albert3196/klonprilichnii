package com.ystu.neutralesprojekt.servlets;

import com.ystu.neutralesprojekt.classes.Data;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountsServlet extends HttpServlet {

    Long IdUser = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        IdUser = Data.getInstance().getIdPersonByLogPas(login,pass);

        Long id =  (Long) req.getSession().getAttribute("idUser");
        if (id != null) {
            resp.sendRedirect("shop");
        }
        else {
            if (!Data.getInstance().getLogin(login, pass)) {
                req.getSession().setAttribute("idUser", IdUser);
                req.getSession().setAttribute("name",login);
                getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
            }
            else {
                if (login != null && pass != null){
                    req.setAttribute("msgAuth","Неверный логин или пароль");
                }
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
    }

}
