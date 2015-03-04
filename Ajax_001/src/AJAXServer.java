import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

/**
 * Created by Pet on 2014-12-21.
 */
//@WebServlet(name = "AJAXServer")
public class AJAXServer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            request.setCharacterEncoding("utf-8");
//            response.setContentType("text/html;charset=gb2312");

            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();

            Integer inte = (Integer) request.getSession().getAttribute("total");
            int temp = 0;
            if(inte == null){
                temp = 1;
            }else{
                temp=inte.intValue()+1;
            }
            request.getSession().setAttribute("total",temp);

            //1取参数信息
            String old = request.getParameter("name");
//            String name = new String(old.getBytes("iso8859-1"),"utf-8");
            String name = URLDecoder.decode(old,"UTF-8");
            //2检查参数是否有问题
            if(old==null || old.length()==0){
                out.print("用户名不能为空");
            }else{
//                String name = URLDecoder.decode(old,"utf-8");
//                byte[] buf = old.getBytes();
//                String name = new String(buf,"utf-8");
//                String name = URLDecoder.decode(old,"utf-8");
//                String name = old;
                //3校验操作
                if(name.equals("admin")){
                    //4和传统不同之处，这一步需要将用户感兴趣的数据返回给页面端，而不是将一个新的页面发送给用户而不是将一个新的 页面发送给用户
                    //写法没有变化，本质是一样的
                    out.println("用户名"+name+"已经存在，请使用其他用户名"+temp);
                } else{
                    out.println("用户名"+name+"尚未存在，可以使用该用户名"+temp);
                }

//                StringBuffer buffer = new StringBuffer();
//                buffer.append(1).append(2);
            }


        }catch (Exception  e){
            e.printStackTrace();
        }

    }
}
