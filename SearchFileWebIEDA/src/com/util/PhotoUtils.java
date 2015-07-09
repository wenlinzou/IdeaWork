package com.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Pet on 2015-07-08.
 */
public class PhotoUtils {
    public static void saveToFile(String destUrl) {
        System.out.println("save Photo path:" + destUrl);
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        int BUFFER_SIZE = 1024;
        byte[] buf = new byte[BUFFER_SIZE];
        int size = 0;
        try {
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            //System.getProperty("user.dir") tomcat bin

//        File newFile = new File(System.getProperty("user.dir")+"/method1.jpg");
            String filename = FileUtils.getFilePathFileName(destUrl, "/", ".");

            String suffix = FileUtils.getFileSuffix(destUrl, ".");
            String newFilePath = destUrl + "/" + filename + "_Copy_ucandelete." + suffix;

            fos = new FileOutputStream(newFilePath);
            System.out.println("saved copy photo path:" + newFilePath);
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            fos.flush();
        } catch (IOException e) {
        } catch (ClassCastException e) {
        } finally {
            try {
                fos.close();
                bis.close();
                httpUrl.disconnect();
            } catch (IOException e) {
            } catch (NullPointerException e) {
            }
        }
    }

    /**
     * Java实现灰度化
     *
     * @throws java.io.IOException
     */
    public static void grayImage(String filePath) throws IOException {
//        File file = new File(System.getProperty("user.dir")+"/test.jpg");
        URL url = new URL(filePath);
        BufferedReader bufr = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        File file = new File(filePath);
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
        String copyname = FileUtils.getFilePathFileName(filePath, "/", ".");
        String filepath = FileUtils.getFilepath(filePath, "/");
        String suffix = FileUtils.getFileSuffix(filePath, ".");
        File newFile = new File(filepath + copyname + "Copy" + "." + suffix);
        ImageIO.write(grayImage, suffix, newFile);
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

    public static void readUrlPhoto(String urlPath) throws IOException {
        URL url = new URL(urlPath);
        BufferedImage bufferedImage = ImageIO.read(url);
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
        String copyname = FileUtils.getFilePathFileName(urlPath, "/", ".");
        String filepath = FileUtils.getFilepath(urlPath, "/");
        String suffix = FileUtils.getFileSuffix(urlPath, ".");

        File newFile = new File(filepath + copyname + "Copy" + "." + suffix);
//        File newFile = new File(System.getProperty("user.dir") + "/ok.jpg");
//        ImageIO.write(grayImage, "jpg", newFile);
        System.out.println(newFile.length());
//        ImageIO.write(grayImage, suffix, newFile);
    }

    public static void newGrayImage(String filePath) throws IOException {
//        BufferedImage bufferedImage = ImageIO.read(new File(System.getProperty("user.dir" + "/test.jpg")));
        BufferedImage bufferedImage = ImageIO.read(new File(filePath));
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
        String copyname = FileUtils.getFilePathFileName(filePath, "/", ".");
        String filepath = FileUtils.getFilepath(filePath, "/");
        String suffix = FileUtils.getFileSuffix(filePath, ".");
        String photoFilePath = filepath + copyname + "Copy" + "." + suffix;
        File newFile = new File(photoFilePath);
        System.out.println(newFile.getAbsolutePath() + "\t" + photoFilePath);
//        File newFile = new File(System.getProperty("user.dir") + "/ok.jpg");
//        ImageIO.write(grayImage, "jpg", newFile);
        ImageIO.write(grayImage, suffix, newFile);
    }
}
