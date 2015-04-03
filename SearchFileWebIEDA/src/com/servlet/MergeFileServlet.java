package com.servlet;

import com.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by Pet on 2015-04-03.
 */
public class MergeFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String mergeFilePath = request.getParameter("splitFilePath");
        System.out.println("MergeFile-servlet:" + mergeFilePath);

        FileService fs = new FileService();
        boolean flag = fs.mergeFile(new File(mergeFilePath));
        System.out.println("MergeFile:" + flag);

        if (flag) {
            request.setAttribute("ok", "1");
            request.setAttribute("title", "合并成功");
            request.setAttribute("message", "合并文件成功!文件位置" + mergeFilePath);
        } else {
            request.setAttribute("ok", "-1");
            request.setAttribute("title", "合并失败");
            request.setAttribute("message", "合并文件失败!");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
