package com.servlet;

import com.service.FileService;
import com.util.FileUtils;
import com.util.PhotoUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pet on 2015-07-08.
 */
public class GreyPhotoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imgpath = request.getParameter("imgPath");
        if (imgpath == null) {
            System.out.println("is null");
            return;
        }
        String method = request.getMethod();
        FileService fileService = new FileService();
        //get方式提交
        if ("GET".equals(method)) {
            byte[] bs = imgpath.getBytes("ISO8859-1");
            imgpath = new String(bs, "UTF-8");
            fileService.getUrlPhotoMakeItGrey(imgpath);
//            PhotoUtils.saveToFile(imgpath);
        }
//        String filename = FileUtils.getFilePathFileName(imgpath,"/",".");
//        List<String> allFilapath = new ArrayList<String>();
//        FileUtils
//        String saveLocalPhoto= "D:\\Apache_Tomcat\\apache-tomcat-7.0.54\\bin\\delete";
//        PhotoUtils.newGrayImage("");
//        System.out.println(imgpath+"\t method:"+method);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
