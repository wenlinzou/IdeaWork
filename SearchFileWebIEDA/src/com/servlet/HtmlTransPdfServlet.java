package com.servlet;

import com.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pet on 2015-04-03.
 */
public class HtmlTransPdfServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String htmlUrl = request.getParameter("htmlUrl");
        String pdfPath = request.getParameter("pdfPath");

        if (htmlUrl == null || htmlUrl.trim().equals("")) {
            System.out.println("htmlurl 为空");
            return;
        }

        FileService fs = new FileService();

        boolean flag = fs.htmlURLTransLocal(htmlUrl, pdfPath);

        if (flag) {
            request.setAttribute("ok", "1");
            request.setAttribute("title", "html2pdf成功");
            request.setAttribute("message", "转换成功的pdf位于 " + pdfPath);
        } else {
            request.setAttribute("ok", "-1");
            request.setAttribute("title", "html2pdf失败");
            request.setAttribute("message", "html2pdf失败!");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
