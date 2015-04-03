package com.servlet;

import com.bean.FileI;
import com.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pet on 2015-04-03.
 */
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String diskname = request.getParameter("diskname");
        String foldername = request.getParameter("foldername");
        String filename = request.getParameter("filename");
        String suffix = request.getParameter("suffix");


        FileService ss = new FileService();
        FileI iFile = new FileI();
        iFile.setDiskname(diskname);
        iFile.setFilename(filename);
        iFile.setFoldername(foldername);
        iFile.setSuffix(suffix);

        List<String> fileLists = ss.queryFileLists(new FileI(diskname, foldername, filename, suffix));
        HttpSession session = request.getSession();

        //定义是否搜索到内容的标记
        int flag = -1;
        if (fileLists == null || fileLists.size() < 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("<center>没能搜索到");
            if (filename != null && !filename.trim().equals("")) {
                sb.append("文件名为:<p style='color:red;font-size:30px;'>").append(filename).append("</p>");
            }
            if (suffix != null && !suffix.trim().equals("")) {
                sb.append("后缀为:<p style='color:green;font-size:30px;'>")
                        .append(suffix)
                        .append("</p>");
            }
            sb.append("的文件!</center>");

            fileLists.add(sb.toString());
            flag = 1;//没有内容
        }
        String fullStr = fileLists.get(0).toString();
        if ("搜索上限已到!".equals(fullStr)) {
            request.setAttribute("fullStr", "<center><p style='color:red;font-size:30px;'>" + fullStr + "!</p></center>");
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
            return;
        }
        session.setAttribute("fileLists", fileLists);
        //request.setAttribute("fileLists", fileLists);
        session.setAttribute("iFile", iFile);
        request.setAttribute("flag", flag);

        request.getRequestDispatcher("/WEB-INF/jsp/searchSuccess.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
