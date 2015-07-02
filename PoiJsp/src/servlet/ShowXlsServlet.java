package servlet;

import bean.XlsDto;
import service.XlsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

/**
 * Created by Pet on 2015-07-01.
 */
public class ShowXlsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");


        String readXls = "f:/zzz/test.xlsx";
        XlsService xlsService = new XlsService();
        List iList = xlsService.getXlsList(readXls);
        String[] temptitles = (String[]) iList.get(0);

        List<XlsDto> volists = new ArrayList<XlsDto>();
        for (int i = 0; i < iList.size(); i++) {
            if (iList.get(i) instanceof XlsDto) {
                XlsDto xls = (XlsDto) iList.get(i);
                volists.add(xls);
            }
        }

        request.setAttribute("titles", temptitles);
        request.setAttribute("xlsVO", volists);

        request.getRequestDispatcher("showXls.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
}
