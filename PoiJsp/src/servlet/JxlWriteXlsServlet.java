package servlet;

import service.XlsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pet on 2015-07-14.
 */
public class JxlWriteXlsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        XlsService xlsService = new XlsService();
        String filePath = "f:/zzz/jxlWirte.xls";
        boolean flag = xlsService.writeXlsByJxl(filePath);
        System.gc();
        if (flag) {
            request.setAttribute("lists", "<span style='color:green;'><H1>write ok</H1></span>");
            request.getRequestDispatcher("showXlsAllInfo.jsp").forward(request, response);
            return;
        } else {
            request.setAttribute("lists", "<span style='color:red;'><H1>write failed</H1></span>");
            request.getRequestDispatcher("showXlsAllInfo.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
