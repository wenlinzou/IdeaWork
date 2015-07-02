package utils;

import bean.XlsDto;
import bean.Title;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.T;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pet on 2015-06-30.
 */
public class Xls2K3Utils {
    public static List getTitlesAndRowIndex(String readXls) throws IOException {
        InputStream in = new FileInputStream(readXls);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
        XlsDto xlsDto = null;
        List lists = new ArrayList();

        String[] cellTitles = new String[Title.dataTitles.length];
        int rowIndex = -1;
        // 循环工作表sheet
        ok:
        for (int i = 0; i < hssfWorkbook.getNumberOfSheets(); i++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(i);
            if (null == hssfSheet) {
                continue;
            }
            // 循环行row
            for (int j = 0; j <= hssfSheet.getLastRowNum(); j++) {
                HSSFRow hssfRow = hssfSheet.getRow(j);
                if (null == hssfRow) {
                    continue;
                }
                //当读取到第一行数据(非空),查看其关键字,如title,则放入对应的bean属性中
                boolean hasTitle = false;
                for (int k = 0; k < Title.dataTitles.length; k++) {
                    HSSFCell temp = hssfRow.getCell(k);
                    if (null != temp) {
                        String title = getValue(temp);
                        for (int l = 0; l < Title.dataTitles.length; l++) {
                            if (Title.dataTitles[l].equals(title)) {
                                cellTitles[k] = title;
                                rowIndex = j;
                            }
                        }
                    }
                }

            }
            break ok;
            //if(hasTitle)break ok;
        }

        lists.add(rowIndex);//+1数据的当前行
        lists.add(cellTitles);
        return lists;
    }


    public static List arrangeList(List lists, String readXls) throws IOException {
        List myList = new ArrayList();
        InputStream in = new FileInputStream(readXls);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
        XlsDto xlsDto = null;
        List dtolists = new ArrayList();
        // 1工作表sheet
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

        // 循环行row
        int rowIndex = Integer.parseInt(lists.get(0).toString());
        rowIndex++;
        String[] titles = (String[]) lists.get(1);
        for (int j = rowIndex; j <= hssfSheet.getLastRowNum(); j++) {
            HSSFRow hssfRow = hssfSheet.getRow(j);
            if (null == hssfRow) {
                continue;
            }
            xlsDto = new XlsDto();
            // 循环列Cell
            // 0学号 1姓名 2学院 3课程名 4成绩

            for (int i = 0; i < titles.length; i++) {

                setValueWithCell(xlsDto, titles, hssfRow);
            }
            dtolists.add(xlsDto);
        }
        //将数据版本的tile保存至集合

        dtolists.add(0, Title.dataTitles);

        return dtolists;
    }

    private static void setValueWithCell(XlsDto xlsDto, String[] titles, HSSFRow hssfRow) {
        int stunoIndex = readXlsTitleIndex(titles, Title.dataTitles[0]);
        HSSFCell stuNo = hssfRow.getCell(stunoIndex);
        if (null == stuNo) {
            return;
        }
        xlsDto.setStuNo(getValue(stuNo));
        int scoreIndex = readXlsTitleIndex(titles, Title.dataTitles[1]);

        HSSFCell score = hssfRow.getCell(scoreIndex);
        if (null == score) {
            return;
        }
        xlsDto.setScore(Float.parseFloat(getValue(score)));
        int nameIndex = readXlsTitleIndex(titles, Title.dataTitles[2]);
        HSSFCell name = hssfRow.getCell(nameIndex);
        if (null == name) {
            return;
        }
        xlsDto.setName(getValue(name));
        int collegeIndex = readXlsTitleIndex(titles, Title.dataTitles[3]);
        HSSFCell college = hssfRow.getCell(collegeIndex);
        if (null == college) {
            return;
        }
        xlsDto.setCollege(getValue(college));
        int subjectIndex = readXlsTitleIndex(titles, Title.dataTitles[4]);
        HSSFCell courseName = hssfRow.getCell(subjectIndex);
        if (null == courseName) {
            return;
        }

//System.out.println(stunoIndex+",score:"+scoreIndex+",name:"+nameIndex+",college:"+collegeIndex+",subject:"+subjectIndex);
        xlsDto.setCourseName(getValue(courseName));
    }

    /**
     * 读取一行的数据将其放入对象中
     *
     * @param hssfRow
     * @param xlsTitles
     * @param dataTitles
     * @return
     */
    public XlsDto setXlsDtoWith(HSSFRow hssfRow, String[] xlsTitles, String dataTitles) {
        XlsDto xlsDto = null;
        String method = "";
        for (int i = 0; i < xlsTitles.length; i++) {
            int thisTitleIndex = readXlsTitleIndex(xlsTitles, Title.dataTitles[i]);
            method = Title.dataTitles[i];

            HSSFCell titleValue = hssfRow.getCell(thisTitleIndex);
            if (null == titleValue) {
                continue;
            }

            xlsDto.setStuNo(getValue(titleValue));
        }
        return xlsDto;

    }
    /**
     * 读取xls中标题所在的下标
     *
     * @return
     */
    public static int readXlsTitleIndex(String[] xlsTitles, String thisTitle) {
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
     * @param hssfCell Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    private static String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串
            return String.valueOf(hssfCell.getStringCellValue());
        }

    }

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
     * @param xls XlsDto实体类的一个对象
     * @throws java.io.IOException
     * @throws Exception           在导入Excel的过程中抛出异常
     */
    /*public static void xlsDto2Excel(List<XlsDto> xls, String writeXls) throws IOException {
        // 获取总列数
        int totalColumn = xls.size();
        // 创建Excel文档
        HSSFWorkbook hwb = new HSSFWorkbook();
        XlsDto xlsDto = null;
        // sheet 对应一个工作页
        String filename = getFileName(writeXls);
        HSSFSheet sheet = hwb.createSheet(filename);
        HSSFRow hssfRow = sheet.createRow(0);// 下标为0的行开始
        HSSFCell[] cells = new HSSFCell[totalColumn];
        String[] titles = new String[totalColumn];

        titles[0] = "学号";
        titles[1] = "姓名";
        titles[2] = "学院";
        titles[3] = "课程名";
        titles[4] = "成绩";

        for (int i = 0; i < totalColumn; i++) {
            cells[i] = hssfRow.createCell(i);
            cells[i].setCellValue(new HSSFRichTextString(titles[i]));
        }
        for (int i = 0; i < xls.size(); i++) {
            // 创建一行
            HSSFRow row = sheet.createRow(i + 1);
            // 得到要插入的每一条记录
            xlsDto = xls.get(i);
            for (int j = 0; j <= titles.length - 1; j++) {
                // 在一行内循环
                HSSFCell stuNo = row.createCell(0);
                stuNo.setCellValue(xlsDto.getStuNo());
                HSSFCell name = row.createCell(1);
                name.setCellValue(xlsDto.getName());
                HSSFCell college = row.createCell(2);
                college.setCellValue(xlsDto.getCollege());
                HSSFCell courseName = row.createCell(3);
                courseName.setCellValue(xlsDto.getCourseName());
                HSSFCell score = row.createCell(4);
                score.setCellValue(xlsDto.getScore());

            }
        }
        // 创建文件输出流,准备输出电子表格
        OutputStream out = new FileOutputStream(writeXls);
        hwb.write(out);
        out.close();
        System.out.println("数据库导出成功");

    }*/
    /**
     * 读取xls文件内容
     *
     * @return List<bean.XlsDto>对象
     * @throws java.io.IOException 输入/输出(i/o)异常
     */
/*    public static List<XlsDto> readXls(String readXls) throws IOException {
        InputStream in = new FileInputStream(readXls);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(in);
        XlsDto xlsDto = null;
        List<XlsDto> dtolists = new ArrayList<XlsDto>();
        // 循环工作表sheet
        for (int i = 0; i < hssfWorkbook.getNumberOfSheets(); i++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(i);
            if (null == hssfSheet) {
                continue;
            }
            // 循环行row
            for (int j = 0; j <= hssfSheet.getLastRowNum(); j++) {
                HSSFRow hssfRow = hssfSheet.getRow(j);

                if (null == hssfRow) {
                    continue;
                }
                //当读取到第一行数据(非空),查看其关键字,如title,则放入对应的bean属性中

                xlsDto = new XlsDto();
                // 循环列Cell
                // 0学号 1姓名 2学院 3课程名 4成绩
                // for (int cellNum = 0; cellNum <=4; cellNum++) {
                HSSFCell stuNo = hssfRow.getCell(0);
                if (null == stuNo) {
                    continue;
                }
                xlsDto.setStuNo(getValue(stuNo));
                HSSFCell name = hssfRow.getCell(1);
                if (null == name) {
                    continue;
                }
                xlsDto.setName(getValue(name));
                HSSFCell college = hssfRow.getCell(2);
                if (null == college) {
                    continue;
                }
                xlsDto.setCollege(getValue(college));
                HSSFCell courseName = hssfRow.getCell(3);
                if (null == courseName) {
                    continue;
                }
                xlsDto.setCourseName(getValue(courseName));
                HSSFCell score = hssfRow.getCell(4);
                if (null == score) {
                    continue;
                }
                xlsDto.setScore(Float.parseFloat(getValue(score)));
                dtolists.add(xlsDto);
            }
        }
        return dtolists;
    }*/
}
