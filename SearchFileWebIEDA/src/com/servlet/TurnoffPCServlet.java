package com.servlet;

import com.service.PCService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pet on 2015-04-03.
 */
public class TurnoffPCServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String tempTime = request.getParameter("turnoffTime");
        tempTime = tempTime.trim();
        System.out.println("tempTime:" + tempTime);
        double time = Double.parseDouble(tempTime);


        PCService ps = new PCService();
        String info = "";
        if (time > 0) {
            ps.turnoffPC(time);
            info = "<p style='color:red;font-weight:blod;'>将在" + (int) (time) + "分钟后关机</p>";
        } else {
            ps.cancelOffPC();
            info = "<p style='color:green;font-weight:blod;'>取消关机成功!</p>";
        }
        request.setAttribute("turnOffSuccess", info);
        request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
