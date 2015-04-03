package com.servlet;

import com.bean.IDCode;
import com.service.IUserService;
import com.service.impl.UserService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Pet on 2015-04-02.
 */
public class IDCodeHTMLServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//IDCode bean
        IDCode idCode = new IDCode();
        idCode.setHeight(30);
        idCode.setWidth(120);

        BufferedImage image = new BufferedImage(idCode.getWidth(), idCode.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        idCode.setG(g);
//		LoginService ls = new LoginService();
        IUserService us = new UserService();
        idCode.setBackColor(Color.WHITE);
        idCode.setBorderColor(Color.BLACK);
//		iword.setFontColor(new Color(197,0,0));
        idCode.setFontColor(Color.BLACK);
        idCode.setLineColor(Color.GRAY);
        idCode.setWordFont(new Font("黑体", Font.BOLD, 23));
        String tempCode = us.getIDCode(idCode);
        /*int height = 30;
		int width = 120;
		//Graphics g, Color backColor, Color borderColor, Color lineColor,Color fontColor, Font wordFont
		String tempCode = ls.getTWordCheck(height,width,g,Color.WHITE,Color.BLACK,Color.GRAY,new Color(197,0,0),new Font("黑体",Font.BOLD,25)
		);*/


        System.out.println("TTTWordServlet:" + tempCode);

        // 5图形写入浏览器
        response.setContentType("image/jpeg");

        //获得session，并把字符串保存在session中，为后面的对比做基础
        HttpSession session = request.getSession();
        session.setAttribute("wordcheck", tempCode);

        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "jpg", os);

        response.flushBuffer();
        os.close();
        os = null;

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
