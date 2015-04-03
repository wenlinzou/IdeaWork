package com.util;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Pet on 2015-04-03.
 */
public class Html2DocUtils {
    // 根据实际情况写路径
    public static boolean writeDocFile(String readpath, String writepath) throws Exception {

        boolean flag = false;
        ByteArrayInputStream bais = null;
        FileOutputStream fos = null;

        try {
            if (!"".equals(writepath)) {
                File fileDir = new File(writepath);
                if (fileDir.exists()) {
                    String content = FileUtils.readFile(readpath);
                    byte b[] = content.getBytes();
                    bais = new ByteArrayInputStream(b);
                    POIFSFileSystem poifs = new POIFSFileSystem();
                    DirectoryEntry directory = poifs.getRoot();
                    DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);

                    //获取html的名字作为doc的名字
                    String docname = FileUtils.getUNeedname(readpath, '/', '.');
                    System.out.println("docname:" + docname);
                    //写入路径 仅仅是路径writepath e.g f:/txt
                    System.out.println("wirtepathUtil: " + writepath + "/" + docname + ".doc");
                    fos = new FileOutputStream(writepath + "/" + docname + ".doc");
                    poifs.writeFilesystem(fos);
                    bais.close();
                    fos.close();
                    flag = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (fos != null)
                fos.close();
            if (bais != null)
                bais.close();
        }
        return flag;

    }

}
