package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pet on 2015-07-07.
 */
public class FileUtils {
    public static List<String> readFile(String filename) throws Exception {
        List<String> list = new ArrayList<String>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            //判断此流是否已准备好被读取。如果缓冲区不为空，或者底层字符流已准备就绪，则缓冲的字符流准备就绪。
            while (br.ready()) {
//                StringBuffer buffer = new StringBuffer();

//                buffer.append(br.readLine());
//                String temp = buffer.toString();

                String temp = br.readLine();
                int index = temp.indexOf("{");
                if (index != -1) {
                    temp = temp.substring(index);
                    list.add(temp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                br.close();
        }
        return list;
    }

    public static void main(String[] args) throws Exception {
        List<String> list = readFile("d:/Test_Tencent/新建文本文档.txt");
//        List<String> list = readFile("f:/zzz/delete01.txt");
        printMyNeedInfo(list, '/', ']', '{');
        System.out.println("size: " + list.size());
    }

    public static void printMyNeedInfo(List<String> list, Character startWord, Character endWord, Character containsWord) {
        for (int i = 0; i < list.size(); i++) {
            int startIndex = list.get(i).indexOf(startWord);
            int endIndex = list.get(i).indexOf(endWord);
            int flag = list.get(i).indexOf(containsWord);
            if (flag < startIndex) {
                if (startIndex != -1) {
                    String temp = list.get(i);
                    temp = temp.substring(startIndex, endIndex);
                    list.set(i, temp);
                    System.out.println(list.get(i));
                }
            }
        }
    }
}
