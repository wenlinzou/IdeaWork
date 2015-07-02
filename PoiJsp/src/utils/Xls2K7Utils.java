package utils;

import bean.XlsDto;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pet on 2015-07-02.
 */
public class Xls2K7Utils {
    private static String[] dataTitles = {"序号", "得分", "学生姓名", "学院名称", "课程名称"};

    public static String getFileName(String name) {
        if (name.contains("/") && name.contains(".")) {
            int startIndex = name.lastIndexOf("/");
            int endIndex = name.lastIndexOf(".");
            name = name.substring(startIndex + 1, endIndex);
        }
        if (name.contains(".") && !name.contains("/")) {
            int endIndex = name.lastIndexOf(".");
            name = name.substring(0, endIndex);
        }
        return name;
    }


    /**
     * 获取标题和其下标
     * @param readXls
     * @return
     * @throws IOException
     */
    public static List getTitlesAndRowIndex(String readXls) throws IOException {
        InputStream in = new FileInputStream(readXls);
        XSSFWorkbook XSSFWorkbook = new XSSFWorkbook(in);
        List lists = new ArrayList();

        String[] cellTitles = new String[dataTitles.length];
        int rowIndex = -1;
        // 循环工作表sheet
        ok:
        for (int i = 0; i < XSSFWorkbook.getNumberOfSheets(); i++) {
            XSSFSheet XSSFSheet = XSSFWorkbook.getSheetAt(i);
            if (null == XSSFSheet) {
                continue;
            }
            // 循环行row
            for (int j = 0; j <= XSSFSheet.getLastRowNum(); j++) {
                XSSFRow XSSFRow = XSSFSheet.getRow(j);
                if (null == XSSFRow) {
                    continue;
                }
                //当读取到第一行数据(非空),查看其关键字,如title,则放入对应的bean属性中
                boolean hasTitle = false;
                for (int k = 0; k < dataTitles.length; k++) {
                    XSSFCell temp = XSSFRow.getCell(k);
                    if (null != temp) {
                        String title = getValue(temp);
                        for (int l = 0; l < dataTitles.length; l++) {
                            //System.out.println("title:"+title+","+j+"datatitle"+dataTitles[l]);
                            if (dataTitles[l].equals(title)) {
                                cellTitles[k] = title;
                                rowIndex = j;
                            }
                        }
                    }
                }
            }
            break ok;
        }

        lists.add(rowIndex);//+1数据的当前行
        lists.add(cellTitles);
        return lists;
    }


    public static List arrangeList(List lists, String readXls) throws IOException {
        List myList = new ArrayList();
        InputStream in = new FileInputStream(readXls);
        XSSFWorkbook XSSFWorkbook = new XSSFWorkbook(in);
        XlsDto xlsDto = null;
        List dtolists = new ArrayList();
        // 1工作表sheet
        XSSFSheet XSSFSheet = XSSFWorkbook.getSheetAt(0);
        System.out.println("下标:" + lists.get(0));
        // 循环行row
        int rowIndex = Integer.parseInt(lists.get(0).toString());
        rowIndex++;
        String[] titles = (String[]) lists.get(1);
        for (int j = rowIndex; j <= XSSFSheet.getLastRowNum(); j++) {
            XSSFRow XSSFRow = XSSFSheet.getRow(j);
            if (null == XSSFRow) {
                continue;
            }
            xlsDto = new XlsDto();
            // 循环列Cell
            // 0学号 1姓名 2学院 3课程名 4成绩

            for (int i = 0; i < titles.length; i++) {

                int stunoIndex = readXlsTitileIndex(titles, dataTitles[0]);
                XSSFCell stuNo = XSSFRow.getCell(stunoIndex);
                if (null == stuNo) {
                    continue;
                }
                xlsDto.setStuNo(getValue(stuNo));
                int scoreIndex = readXlsTitileIndex(titles, dataTitles[1]);

                XSSFCell score = XSSFRow.getCell(scoreIndex);
                if (null == score) {
                    continue;
                }
                xlsDto.setScore(Float.parseFloat(getValue(score)));
                int nameIndex = readXlsTitileIndex(titles, dataTitles[2]);
                XSSFCell name = XSSFRow.getCell(nameIndex);
                if (null == name) {
                    continue;
                }
                xlsDto.setName(getValue(name));
                int collegeIndex = readXlsTitileIndex(titles, dataTitles[3]);
                XSSFCell college = XSSFRow.getCell(collegeIndex);
                if (null == college) {
                    continue;
                }
                xlsDto.setCollege(getValue(college));
                int subjectIndex = readXlsTitileIndex(titles, dataTitles[4]);
                XSSFCell courseName = XSSFRow.getCell(subjectIndex);
                if (null == courseName) {
                    continue;
                }

//System.out.println(stunoIndex+",score:"+scoreIndex+",name:"+nameIndex+",college:"+collegeIndex+",subject:"+subjectIndex);
                xlsDto.setCourseName(getValue(courseName));
            }
            dtolists.add(xlsDto);
        }
        //将数据版本的tile保存至集合

        dtolists.add(0, dataTitles);

        return dtolists;
    }


    /**
     * 读取xls中标题所在的下标
     *
     * @return
     */
    public static int readXlsTitileIndex(String[] xlsTitles, String thisTitle) {
        int temp = -1;
        for (int i = 0; i < xlsTitles.length; i++) {
            if (thisTitle.equals(xlsTitles[i])) {
                temp = i;
            }
        }
        return temp;
    }


    /**
     * 得到Excel表中的值
     *
     * @param XSSFCell Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    private static String getValue(XSSFCell XSSFCell) {
        if (XSSFCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔
            return String.valueOf(XSSFCell.getBooleanCellValue());
        } else if (XSSFCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            // 返回数值
            return String.valueOf(XSSFCell.getNumericCellValue());
        } else {
            // 返回字符串
            return String.valueOf(XSSFCell.getStringCellValue());
        }

    }
    /**
     * 读取xls文件内容
     *
     * @return List<bean.XlsDto>对象
     * @throws java.io.IOException 输入/输出(i/o)异常
     */
//    public static List<XlsDto> readXls(String readXls) throws IOException {
//        InputStream in = new FileInputStream(readXls);
//        XSSFWorkbook XSSFWorkbook = new XSSFWorkbook(in);
//        XlsDto xlsDto = null;
//        List<XlsDto> dtolists = new ArrayList<XlsDto>();
//        // 循环工作表sheet
//        for (int i = 0; i < XSSFWorkbook.getNumberOfSheets(); i++) {
//            XSSFSheet XSSFSheet = XSSFWorkbook.getSheetAt(i);
//            if (null == XSSFSheet) {
//                continue;
//            }
//            // 循环行row
//            for (int j = 0; j <= XSSFSheet.getLastRowNum(); j++) {
//                XSSFRow xssfRow = XSSFSheet.getRow(j);
//
//                if (null == xssfRow) {
//                    continue;
//                }
//                //当读取到第一行数据(非空),查看其关键字,如title,则放入对应的bean属性中
//
//                xlsDto = new XlsDto();
//                // 循环列Cell
//                // 0学号 1姓名 2学院 3课程名 4成绩
//                // for (int cellNum = 0; cellNum <=4; cellNum++) {
//
//                XSSFCell stuNo = xssfRow.getCell(0);
//                if (null == stuNo) {
//                    continue;
//                }
//                xlsDto.setStuNo(getValue(stuNo));
//                XSSFCell name = xssfRow.getCell(1);
//                if (null == name) {
//                    continue;
//                }
//                xlsDto.setName(getValue(name));
//                XSSFCell college = xssfRow.getCell(2);
//                if (null == college) {
//                    continue;
//                }
//                xlsDto.setCollege(getValue(college));
//                XSSFCell courseName = xssfRow.getCell(3);
//                if (null == courseName) {
//                    continue;
//                }
//                xlsDto.setCourseName(getValue(courseName));
//                XSSFCell score = xssfRow.getCell(4);
//                if (null == score) {
//                    continue;
//                }
//                xlsDto.setScore(Float.parseFloat(getValue(score)));
//                dtolists.add(xlsDto);
//            }
//        }
//        return dtolists;
//    }
}
