package com.servlet;

import com.util.Html2DocUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pet on 2015-04-03.
 */
public class Html2DocServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String method = request.getMethod();
        String htmlpath = request.getParameter("htmlpath");

        if ("GET".equals(method)) {
            byte[] bs = htmlpath.getBytes("ISO8859-1");
            htmlpath = new String(bs, "UTF-8");
        }
        System.out.println("htmltodoc htmlpath:" + htmlpath);
        //截取路径
        String writepath = htmlpath.substring(0, htmlpath.lastIndexOf("/"));


        //html to doc
        boolean flag = false;
        try {
            flag = Html2DocUtils.writeDocFile(htmlpath, writepath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag) {
            request.setAttribute("ok", "1");
            request.setAttribute("title", "HTML2DOC成功");
            request.setAttribute("message", htmlpath + "转换DOC成功!");
        } else {
            request.setAttribute("ok", "-1");
            request.setAttribute("title", "HTML2DOC失败");
            request.setAttribute("message", htmlpath + "转换DOC失败!");
        }

        request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
