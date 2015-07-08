package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
        /*List<String> list = readFile("d:/Test_Tencent/新建文本文档.txt");
//        List<String> list = readFile("f:/zzz/delete01.txt");
        printMyNeedInfo(list, '/', ']', '{');
        System.out.println("size: " + list.size());*/
        newGrayImage();
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

    /**
     * Java实现灰度化
     *
     * @throws IOException
     */
    public static void grayImage() throws IOException {
//        File file = new File(System.getProperty("user.dir")+"/test.jpg");
        File file = new File("f:/zzz/test.jpg");
        BufferedImage image = ImageIO.read(file);


        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
            }
        }

//        File newFile = new File(System.getProperty("user.dir")+"/method1.jpg");
        File newFile = new File("f:/zzz/method1.jpg");
        ImageIO.write(grayImage, "jpg", newFile);
    }

    private static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }

    public static void newGrayImage() throws IOException {
//        BufferedImage bufferedImage = ImageIO.read(new File(System.getProperty("user.dir" + "/test.jpg")));
        BufferedImage bufferedImage = ImageIO.read(new File("f:/zzz/test.jpg"));
        BufferedImage grayImage =
                new BufferedImage(bufferedImage.getWidth(),
                        bufferedImage.getHeight(),
                        bufferedImage.getType());

        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                final int color = bufferedImage.getRGB(i, j);
                final int r = (color >> 16) & 0xff;
                final int g = (color >> 8) & 0xff;
                final int b = color & 0xff;
                int gray = (int) (0.3 * r + 0.59 * g + 0.11 * b);
                ;
//                System.out.println(i + " : " + j + " " + gray);
                int newPixel = colorToRGB(255, gray, gray, gray);
                grayImage.setRGB(i, j, newPixel);
            }
        }
        File newFile = new File("f:/zzz/method1.jpg");
//        File newFile = new File(System.getProperty("user.dir") + "/ok.jpg");
        ImageIO.write(grayImage, "jpg", newFile);
    }
}
