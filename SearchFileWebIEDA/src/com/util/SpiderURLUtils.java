package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pet on 2015-04-03.
 */
public class SpiderURLUtils {
    //发送代数据的HTTP 请求 -->网络爬虫

    public static List<String> getDataByURL(String urlPath) throws IOException, Exception {
//		URL url = new URL("http://192.168.1.100:8080/myweb/mail.html");
        List<String> htmlInfo = new ArrayList<String>();

        URL url = new URL(urlPath);
        BufferedReader bufr = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        String line = null;

//		String mail_reg = "\\w+@\\w+(\\.\\w+)+";
//		String mail_reg = "\\S";
//		Pattern p = Pattern.compile(mail_reg);

        while ((line = bufr.readLine()) != null) {
            htmlInfo.add(line);
//System.out.println(line);
        /*
			htmlInfo.add(m.group());
			Matcher m = p.matcher(line);
			while (m.find()) {
				sb.append(m.group());
			}*/
        }
        return htmlInfo;
        //document.close();
    }
}
