package servlet;

import jxl.read.biff.BiffException;
import service.XlsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pet on 2015-07-13.
 */
public class JxlXlsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        XlsService xlsService = new XlsService();
        String xlsPath = "f:/zzz/刘梦起好人卡.xls";
        String readXls = "f:/zzz/test - 副本.xls";
        try {
            List list = xlsService.getJxlReadXls(readXls);

            System.out.println(list.size());
            /*for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).toString());
            }*/

            /*for (int i = 0; i < list.size(); i++) {
                if (list.get(i).toString().endsWith("<tr>")) {
                    System.out.println(list.get(i).toString());

                }
                if (list.get(i).toString().endsWith("</tr>")) {
                    System.out.println(list.get(i).toString());

                } else
                    System.out.print(list.get(i).toString());

            }
*/
            request.setAttribute("lists", list);
            request.setAttribute("titleinfo", "showJxlXls");
            request.getRequestDispatcher("showXlsAllInfo.jsp").forward(request, response);
        } catch (BiffException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
