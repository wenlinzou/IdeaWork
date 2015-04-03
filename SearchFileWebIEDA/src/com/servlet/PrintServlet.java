package com.servlet;

import com.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pet on 2015-04-03.
 */
public class PrintServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String diskname = request.getParameter("diskname");
        String filename = request.getParameter("filename");

        FileService fs = new FileService();

        if (filename == null || filename.trim().equals("")) {
            response.sendRedirect("jump.do");
            return;
        }
        filename += ".txt";
        String localFile = diskname + ":" + filename;
        request.setAttribute("title", "写入磁盘");
        request.setAttribute("message", "文件位置:" + localFile + "写入成功!");

        HttpSession session = request.getSession();
        List<String> list = (List<String>) session.getAttribute("fileLists");
        fs.write2File(list, new File(localFile));

        request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
