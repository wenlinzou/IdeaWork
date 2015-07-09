package com.servlet;

import com.listener.OnlineListener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pet on 2015-07-03.
 */
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OnlineListener countListener = new OnlineListener();
        long count = countListener.getCountNo();
        System.out.println("online user " + count);
        if (count < 1)
            request.getRequestDispatcher("indexLogin.jsp").forward(request, response);
        else
            request.getRequestDispatcher("index.html").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
