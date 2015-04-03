package com.util;

import com.bean.Email;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by Pet on 2015-04-02.
 */
public class SendMailUtils {
    //添加抄送和附件
    public static boolean sendEMail(Email semail) {
        // 构造mail session
        Properties props = new Properties();
        props.put("mail.smtp.host", semail.getHostname());
        props.put("mail.smtp.auth", "true");
        final String username = semail.getUsername();
        final String password = semail.getContent();
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // 构造MimeMessage 并设定基本的值
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(semail.getSendFromEmailName()));


            //msg.addRecipients(Message.RecipientType.TO, address); //这个只能是给一个人发送email
            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(semail.getSendToEmailName()));
            String subject = transferChinese(semail.getSubject());
            msg.setSubject(subject);

            // 构造Multipart
            Multipart mp = new MimeMultipart();

            // 向Multipart添加正文
            MimeBodyPart mbpContent = new MimeBodyPart();
            mbpContent.setContent(semail.getContent(), "text/html;charset=utf-8");

            // 向MimeMessage添加（Multipart代表正文）
            mp.addBodyPart(mbpContent);

            // 向Multipart添加附件
            Enumeration<String> efiles = Collections.enumeration(semail.getFileList());
            while (efiles.hasMoreElements()) {

                MimeBodyPart mbpFile = new MimeBodyPart();
//				filename = efiles.nextElement().toString();
                semail.setFilename(efiles.nextElement().toString());

                FileDataSource fds = new FileDataSource(semail.getFilename());
                mbpFile.setDataHandler(new DataHandler(fds));
                /*<span style="color: #ff0000;">//这个方法可以解决附件乱码问题。</span>
*/
//						String filename= new String(fds.getName().getBytes(),"ISO-8859-1");
                String filename = new String(fds.getName().getBytes(), "ISO-8859-1");
                mbpFile.setFileName(filename);
                // 向MimeMessage添加（Multipart代表附件）
                mp.addBodyPart(mbpFile);

            }

            semail.getFileList().removeAll(semail.getFileList());
            //.removeAllElements()
            // 向Multipart添加MimeMessage
            msg.setContent(mp);
            msg.setSentDate(new Date());
            msg.saveChanges();
            // 发送邮件

            Transport transport = session.getTransport("smtp");
            transport.connect(semail.getHostname(), semail.getUsername(), semail.getPassword());
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception mex) {
            mex.printStackTrace();
            return false;
        }
        return true;
    }

    private static String transferChinese(String strText) {
        try {
            strText = MimeUtility.encodeText(new String(strText.getBytes(),
//					"UTF-8"), "UTF-8", "B");使用GB2312解决中文乱码
                    "GB2312"), "GB2312", "B");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strText;
    }
}
