package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Pet on 2015-04-03.
 */
public class LinkFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String linkurl = request.getParameter("linkurl");
        //转义编码
        byte[] bt = linkurl.getBytes("ISO8859-1");
        linkurl = new String(bt, "UTF-8");

        linkurl = linkurl.replace("\\", "/");
        System.out.println("LINKServlet link:" + linkurl);

        request.setAttribute("playUrl", linkurl);
        String suffixStr = linkurl;
        int endIndex = suffixStr.lastIndexOf(".");
        //.properties
        suffixStr = suffixStr.substring(endIndex);

        HttpSession sessionPlay = request.getSession();
        sessionPlay.setAttribute("iPlay", linkurl);
        request.setAttribute("suffixStr", suffixStr);
        request.getRequestDispatcher("/WEB-INF/jsp/play.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
