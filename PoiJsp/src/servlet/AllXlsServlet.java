package servlet;

import service.XlsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pet on 2015-07-06.
 */
public class AllXlsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        XlsService xlsService = new XlsService();
        String xlsPath = "f:/zzz/刘梦起好人卡.xls";
        List list = xlsService.getXlsAllInfo(xlsPath);
        System.out.println(list.size());

        request.setAttribute("lists", list);
        request.setAttribute("titleinfo", "showAllXls");
        request.getRequestDispatcher("showXlsAllInfo.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
