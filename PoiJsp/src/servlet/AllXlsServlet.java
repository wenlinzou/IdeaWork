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


        for (int i = 0; i < list.size(); i++) {
            list.set(i, "<td>" + list.get(i) + "</td>");
            if (i == 0)
                list.set(0, "<tr>" + list.get(0));
            if (list.get(i).toString().contains("br") && i < list.size() - 2) {
                list.set(i, "</tr><tr>");
            }
            if (list.get(i).toString().contains("br") && i == list.size() - 1) {
                list.set(i, "</tr>");
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }


        /*for (int i = 0; i < list.size(); i++) {
            if(i==0){
                list.add(0,"<tr>");
            }
            if(list.get(i).toString().contains("<br/>")){
                list.add(i,"</tr>");
                if(i<list.size()-1){
                    list.add(i+1,"<tr>");
                }
            }
            list.add(i,"<td>"+list.get(i)+"</td>");

        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }*/
        request.setAttribute("lists", list);
        request.getRequestDispatcher("showXlsAllInfo.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
