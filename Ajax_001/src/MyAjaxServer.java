import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Pet on 2014-12-25.
 */
@WebServlet(name = "MyAjaxServer")
public class MyAjaxServer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/xml;charset=utf-8");

        String old = request.getParameter("name");
        PrintWriter out = response.getWriter();

        StringBuilder builder = new StringBuilder();
        builder.append("<message>");
        if (old == null || old.length() == 0) {
            builder.append("用户名为空").append("</message>");
        } else {
            String name = old;
            if (old.equals("admin")) {
                builder.append("用户名[" + name + "]已经使用了，请更换用户名").append("</message>");
            } else {
                builder.append("用户名[" + name + "]未使用了，可以使用").append("</message>");
            }
            out.print(builder.toString());
        }
        System.out.print(1);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
