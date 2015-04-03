package com.servlet;

import com.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Pet on 2015-04-03.
 */
public class SplitFileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String fileSplitPath = request.getParameter("splitFilePath");
        String sizeStr = request.getParameter("sizename");
        String suffixname = request.getParameter("suffixname");
        String putPath = request.getParameter("dirname");

        int size = Integer.parseInt(sizeStr);

        System.out.println("filesplitPath:" + fileSplitPath);
        FileService fs = new FileService();

        FileInputStream fis = new FileInputStream(fileSplitPath);
        if ((size * 1000) > fis.available()) {
            request.setAttribute("boundsSize", "输入分解大小超过文件大小!");
            return;
        }

        System.out.println(fis.available() / 1024);

        fs.splitFile(new File(fileSplitPath), new File(putPath), size, suffixname);

        request.setAttribute("ok", "1");
        request.setAttribute("title", "拆分成功");
        request.setAttribute("message", "拆分文件成功!文件位于" + putPath);

        request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
