package service;

import bean.XlsDto;
import jxl.read.biff.BiffException;
import utils.Jxl2K3Utils;
import utils.Xls2K7Utils;
import utils.Xls2K3Utils;

import java.io.IOException;
import java.util.List;

/**
 * Created by Pet on 2015-06-30.
 */
public class XlsService {

    public List getJxlReadXls(String xlsPath) throws IOException, BiffException {
        List list = null;
        if (xlsPath.lastIndexOf(".xls") != -1) {
            list = Jxl2K3Utils.readXls(xlsPath);
            arrangeListByTable(list);
        }
        return list;
    }


    /**
     * @param xlsPath
     * @return
     * @throws IOException
     */
    public List getXlsList(String xlsPath) throws IOException {
        List iList = null;
        if (xlsPath.lastIndexOf(".xlsx") != -1) {
            iList = Xls2K7Utils.arrangeList(Xls2K7Utils.getTitlesAndRowIndex(xlsPath), xlsPath);
        } else {
            iList = Xls2K3Utils.arrangeList(Xls2K3Utils.getTitlesAndRowIndex(xlsPath), xlsPath);
        }
        String[] temptitles = (String[]) iList.get(0);

        for (int i = 0; i < iList.size(); i++) {
            if (iList.get(i) instanceof XlsDto) {
                XlsDto xls = (XlsDto) iList.get(i);
            }
        }

        return iList;
    }

    public List getXlsAllInfo(String xlsPath) throws IOException {
        List list = null;
        if (xlsPath.lastIndexOf("xls") != -1) {
            list = Xls2K3Utils.readXls(xlsPath);
            arrangeListByTable(list);
        }
        return list;
    }


    /**
     * 将list整合成table
     *
     * @param list
     * @return
     */
    private static List arrangeListByTable(List list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, "<td>" + list.get(i) + "</td>");
            if (i == 0)
                list.set(0, "<tr>" + list.get(0));
            if (list.get(i).toString().contains("br") && i < list.size() - 2) {
                list.set(i, "</tr><tr>");
            }
            if (list.get(i).toString().contains("br") && i == list.size() - 1) {
                list.set(i, "</tr>");
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
//        bean.XlsDto xls = null;
        String readXls = "f:/zzz/test.xlsx";
        String writeXls = "f:/zzz/test1.xls";
        String allXls = "f:/zzz/刘梦起好人卡.xls";

        /*List<bean.XlsDto> list = utils.XlsUtils.readXls(readXls);
        // utils.XlsUtils.xlsDto2Excel(list, writeXls);

        for (int i = 0; i < list.size(); i++) {
            xls = (bean.XlsDto) list.get(i);
            System.out.println(xls.getStuNo() + "\t" + xls.getName() + "\t" + xls.getCollege() + "\t" + xls.getCourseName() + "\t" + xls.getScore());
        }*/
        List list = null;
        list = Xls2K3Utils.readXls(allXls);




    }
}
