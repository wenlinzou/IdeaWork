package com.servlet;

import com.util.AsciiImgUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pet on 2015-04-03.
 */
public class AsciiImgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");

        String imgpath = request.getParameter("imgpath");

        String method = request.getMethod();
        System.out.println("method:" + method);

        if (imgpath == null || imgpath.trim().equals("")) {
            request.setAttribute("message", "图像字符画失败!");
            request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
        }
        //get方式提交
        if ("GET".equals(method)) {
            byte[] bs = imgpath.getBytes("ISO8859-1");
            imgpath = new String(bs, "UTF-8");
        }
        if (imgpath.endsWith(".jpg") || imgpath.endsWith(".png") || imgpath.endsWith("gif")
                || imgpath.endsWith(".bmp")) {

            String img = AsciiImgUtils._BitmapConvert(imgpath);
            request.setAttribute("img", img);
            request.getRequestDispatcher("/WEB-INF/jsp/asciiImgSuccess.jsp").forward(request, response);
        } else {
            if (imgpath.endsWith(".jpeg"))
                request.setAttribute("message", "图像字符画失败!暂不支持" + ".jpeg");
            else
                request.setAttribute("message", "图像字符画失败!");
            request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
