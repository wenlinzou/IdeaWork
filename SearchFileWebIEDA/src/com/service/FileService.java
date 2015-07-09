package com.service;

import com.bean.FileI;
import com.util.FileUtils;
import com.util.PhotoUtils;
import com.util.SpiderURLUtils;
import com.util.filter.ContainsSuffixFilter;
import com.util.filter.ContainsWordFilter;
import com.util.filter.FilenameSuffixFilter;
import com.util.filter.PrefixFilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pet on 2015-04-03.
 */
public class FileService {
    private static int COUNT = 0;
    private final String FULL = "搜索上限已到!";
    private final String ERROR_INFO = "请输入硬盘符,以便查询!";
    private final int SIZE_SEARCH = 20;

    private FileUtils fileUtils = new FileUtils();
//    private ITextPdf itd = new ITextPdf();

    public boolean getUrlPhotoMakeItGrey(String imgpath, String webName) {
        boolean pass = false;

        File tempf = new File(System.getProperty("user.dir"));
        //D:\Apache_Tomcat\apache-tomcat-7.0.54
        String tomcat = tempf.getParent();
        //webname /SearchFileWeb
        String saveLocalPhoto = tomcat + "/webapps" + webName + "/resource/images";
        System.out.println("FileService-saveLocalPhoto" + saveLocalPhoto);
        PhotoUtils.saveToFile(saveLocalPhoto);
        System.out.println("save photo ok");

        String filename = FileUtils.getFilePathFileName(imgpath, "/", ".");
        List<String> allFilapath = new ArrayList<String>();

        PrefixFilter filter = new PrefixFilter(filename);

        allFilapath = fileUtils.accpetPrefix(new File(saveLocalPhoto), filter, allFilapath);
        System.out.println("FileService-Photo:size" + allFilapath.size() + allFilapath.get(0).toString());


        try {
            PhotoUtils.newGrayImage(allFilapath.get(0).toString());
            pass = true;
        } catch (IOException e) {
            e.printStackTrace();
            pass = false;
        }
        return pass;
    }
    public List<String> queryFileLists(FileI iFile) {
        List<String> fileList = new ArrayList<String>();
        System.out.println("==================\n搜索次数:" + (++COUNT) + "\t搜索上限:" + SIZE_SEARCH);
        if (COUNT > SIZE_SEARCH) {
            //<center><p style='color:red;font-size:30px;'>!</p></center>
            fileList.add(FULL);
            return fileList;
        }

        String diskname = iFile.getDiskname();
        String foldername = iFile.getFoldername();
        String filename = iFile.getFilename();
        String suffix = iFile.getSuffix();

        //过滤条件

        if (diskname != null && !"".equals(diskname.trim())) {

            //盘符和文件夹都在盘符选项中,文件夹选项空
            if (diskname.indexOf(':') != -1) {

                if (foldername == null || foldername.trim().equals("")) {
                    diskname = diskname.replace("/", "\\");
                    String allFolderInfo = diskname;
                    diskname = fileUtils.getDisknameM(diskname);
                    foldername = fileUtils.getFoldernameM(allFolderInfo);
                }
            }
            if (diskname.indexOf(":") == -1 && diskname.length() > 1) {
                fileList.add(ERROR_INFO);
                return fileList;
            }
            diskname = fileUtils.getDisknameM(diskname);

        } else {
            if (foldername == null || foldername.trim().equals("")) {
                fileList.add(ERROR_INFO);
                return fileList;

            }
            //盘符位置空,但文件夹名称包含了盘符名称
            else {
                if (foldername.indexOf(':') != -1) {
                    foldername = foldername.replace("/", "\\");
                    String allFoldername = foldername;
                    foldername = fileUtils.getFoldernameM(foldername);
                    diskname = fileUtils.getDisknameM(allFoldername);
                } else {
                    //文件夹名称内没有盘符信息
                    fileList.add(ERROR_INFO);
                    return fileList;
                }
            }
        }
        diskname += ":/";

        File dir = new File(diskname, foldername);
        System.out.println("dir:\t" + dir);

        //文件名不为空
        if ((null != filename && !filename.trim().equals(""))) {
            //后缀名为空
            if (suffix == null || suffix.trim().equals("")) {
                System.out.println("1 文件名有值,后缀为空\t" + filename);
                ContainsWordFilter contains = new ContainsWordFilter(filename);
                fileUtils.searchIgnoreFilename(dir, contains, fileList);
            } else {
                System.out.println("2 文件名有值,后缀有值\t" + filename + "," + suffix);
                //文件名+文件后缀
                FilenameSuffixFilter nameSuffixFilter = new FilenameSuffixFilter(filename, suffix);
                fileUtils.searchIngoreNameWithSuffix(dir, nameSuffixFilter, fileList);
            }
        }

        //后缀名
        if (null != suffix && !suffix.trim().equals("")) {
            char[] temps = suffix.toCharArray();
            if (temps[0] != '.')
                suffix = "." + suffix;

            //文件名不为空
            if (filename == null || filename.trim().equals("")) {
                System.out.println("3 文件名为空,后缀有值" + "\t" + suffix);
                ContainsSuffixFilter filter = new ContainsSuffixFilter(suffix);
                fileUtils.accpetSuffix(dir, filter, fileList);
            }
        }
        //文件名为空
        else if (filename == null || filename.trim().equals("")) {
            System.out.println("4 文件名为空,后缀为空");
            fileUtils.searchFolderFile(dir, fileList);
        }
        return fileList;
    }

    //写入到txt
    public void write2File(List<String> list, File file) {
        try {
            if (file.exists()) {
                file.mkdirs();
            }
            fileUtils.write2File(list, file);
        } catch (IOException e) {

            System.out.println("MLGB - 找不到" + file);
            e.printStackTrace();
        }
    }

    public boolean renameFile(List<String> lists, String rename) {
        return fileUtils.renameFile(lists, rename);
    }

    public boolean splitFile(File srcFile, File destDir, int split_size, String suffix) {
        boolean flag = false;
        int indexStart = suffix.indexOf(".");
        if (indexStart < 0) suffix = "." + suffix;

        System.out.println("size:" + srcFile);

        try {
            fileUtils.splitFile(srcFile, destDir, split_size, suffix);
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    //合并文件
    public boolean mergeFile(File mergreFile) {
        boolean flag = false;
        try {
            String filePath = mergreFile.getAbsolutePath();
            String fileInPath = filePath.replace("\\", "/");
            int pathEnd = fileInPath.lastIndexOf("/");
            System.out.println("SERVICE-MergeFIle:" + fileInPath);
            fileInPath = filePath.substring(0, pathEnd);

            int suffixStart = filePath.lastIndexOf(".");
            String suffixName = filePath.substring(suffixStart);
            if (!suffixName.equals(".properties")) {
//				throw new RuntimeException("文件后缀名不是"+".properties");
                System.out.println("文件后缀名不是" + ".properties");
            }
            File srcFile = new File(fileInPath);
            System.out.println("SERVICE:fileInPath:" + fileInPath + "\tsuffixname:" + suffixName);

            fileUtils.mergeFile(srcFile, suffixName);
            flag = true;
        } catch (IOException e) {

            e.printStackTrace();
        }
        return flag;
    }

    //html trans pdf
    public void htmlTransPdf(String url, String filePath) {
        try {
            //得到url的文件数据流
            List<String> htmldata = SpiderURLUtils.getDataByURL(url);
            //将数据写到本地
            String htmlUrl = url;
            String pdfPath = filePath;

            int startHtml = htmlUrl.lastIndexOf("/");
            int endHtml = htmlUrl.lastIndexOf(".");
            String htmlName = "";
            if (endHtml < startHtml) {
                if (startHtml > 0) {
                    String temphtmlUrl = htmlUrl.substring(0, htmlUrl.length() - 1);
                    htmlName = temphtmlUrl.substring(temphtmlUrl.lastIndexOf("/") + 1);
                    System.out.println("htmlUrl:" + htmlUrl + "\thtmlname:" + htmlName);
                }
            } else {
                htmlName = htmlUrl.substring(startHtml + 1, endHtml);
            }

            String htmlsuffix = ".html";
            System.out.println("htmlsuffix:" + htmlsuffix);

            String inputTempFile = filePath + "/" + htmlName + htmlsuffix;
            inputTempFile = fileUtils.changeIngellUrlName(inputTempFile);

            System.out.println("inputTempFile:" + inputTempFile);
            fileUtils.write2File(htmldata, inputTempFile);
            System.out.println("over!");
            //读取html文件将其转成pdf
            String pdfsuffix = ".pdf";
            pdfPath = filePath + "/" + htmlName + pdfsuffix;
            System.out.println("pdfPath:" + pdfPath);
//			itd.htmlTransPdf1(inputTempFile, pdfPath);
//			new ITextPdf().htmlTransPdf1(filePath, pdfurl);
        } catch (IOException e) {

            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public boolean htmlURLTransLocal(String url, String filePath) {
        boolean flag = false;
        try {
            //得到url的文件数据流
            List<String> htmldata = SpiderURLUtils.getDataByURL(url);
            //将数据写到本地
            String htmlUrl = url;
            String pdfPath = filePath;

            int startHtml = htmlUrl.lastIndexOf("/");
            int endHtml = htmlUrl.lastIndexOf(".");
            String htmlName = "";
            if (endHtml < startHtml) {
                if (startHtml > 0) {
                    String temphtmlUrl = htmlUrl.substring(0, htmlUrl.length() - 1);
                    htmlName = temphtmlUrl.substring(temphtmlUrl.lastIndexOf("/") + 1);
                    System.out.println("htmlUrl:" + htmlUrl + "\thtmlname:" + htmlName);
                }
            } else {
                htmlName = htmlUrl.substring(startHtml + 1, endHtml);
            }

            String htmlsuffix = ".html";
            System.out.println("htmlsuffix:" + htmlsuffix);

            String inputTempFile = filePath + "/" + htmlName + htmlsuffix;
            inputTempFile = fileUtils.changeIngellUrlName(inputTempFile);

            System.out.println("inputTempFile:" + inputTempFile);
            fileUtils.write2File(htmldata, inputTempFile);
            //读取html文件将其转成pdf
            String pdfsuffix = ".pdf";
            pdfPath = filePath + "/" + htmlName + pdfsuffix;
//System.out.println("pdfPath:"+pdfPath);	暂时没有转pdf
//			itd.htmlTransPdf1(inputTempFile, pdfPath);
//			new ITextPdf().htmlTransPdf1(filePath, pdfurl);
            flag = true;
        } catch (IOException e) {

            e.printStackTrace();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }
}
