package com.servlet;

import com.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pet on 2015-04-03.
 */
public class RenameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        FileService fs = new FileService();

        String checkedName = request.getParameter("rename");
        String sameName = request.getParameter("samename");
        if (sameName == null || sameName.equals("")) {
            response.sendRedirect("search.action");
            return;
        }

        String[] temp = checkedName.split(",");
        for (int i = 0; i < temp.length; i++) {
            temp[i] = temp[i].replace("\\", "/");

        }
        List<String> lists = new ArrayList<String>();
        lists = Arrays.asList(temp);
        boolean canRename = fs.renameFile(lists, sameName);
        if (canRename) {
            request.setAttribute("title", "修改成功");
            request.setAttribute("ok", "1");
            request.setAttribute("message", "文件名修改成功!");
        } else {
            request.setAttribute("title", "修改失败");
            request.setAttribute("ok", "-1");
            request.setAttribute("message", "没有找到合适的文件名称,或者修改后的名称与其他文件名称冲突");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
