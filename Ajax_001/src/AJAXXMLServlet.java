import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Pet on 2014-12-23.
 * XML的数据
 */
//@WebServlet(name = "AJAXXMLServlet")
public class AJAXXMLServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //修改点1----响应的Content-Type必须是text/xml
            response.setContentType("text/xml;charset=utf-8");

            PrintWriter out = response.getWriter();

            StringBuilder builder = new StringBuilder();
            builder.append("<message>");
            //1取参数信息
            String old = request.getParameter("name");
            //2检查参数是否有问题
            if (old == null || old.length() == 0) {
                builder.append("用户名不能为空").append("</message>");
            } else {
                String name = old;
                //修改点2-----返回的数据须臾奥拼装成xml格式


                //3校验操作
                if (name.equals("admin")) {
                    //4和传统不同之处，这一步需要将用户感兴趣的数据返回给页面端，而不是将一个新的页面发送给用户而不是将一个新的 页面发送给用户
                    //写法没有变化，本质是一样的
                    builder.append("用户名" + name + "已经存在，请使用其他用户名").append("</message>");
                } else {
                    builder.append("用户名" + name + "尚未存在，可以使用该用户名").append("</message>");
                }
                out.print(builder.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
