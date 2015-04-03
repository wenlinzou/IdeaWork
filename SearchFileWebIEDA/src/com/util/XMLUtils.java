package com.util;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Pet on 2015-04-02.
 */
public class XMLUtils {
    private static String filePath;

    static {
        filePath = XMLUtils.class.getClassLoader().getResource("users.xml").getPath();
    }

    public static Document getDocument() throws Exception {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(filePath));
        return document;
    }

    public static void wirte2XML(Document document) throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");

        XMLWriter writer = new XMLWriter(new FileOutputStream(filePath), format);
        writer.write(document);
        writer.close();
    }
}
