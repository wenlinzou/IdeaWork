package service;

import bean.XlsDto;
import utils.Xls2K7Utils;
import utils.XlsUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by Pet on 2015-06-30.
 */
public class XlsService {
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
            iList = XlsUtils.arrangeList(XlsUtils.getTitlesAndRowIndex(xlsPath), xlsPath);
        }
        String[] temptitles = (String[]) iList.get(0);
        for (int i = 0; i < temptitles.length; i++) {
            System.out.print(temptitles[i] + "\t");
        }
        System.out.println();

        for (int i = 0; i < iList.size(); i++) {
            if (iList.get(i) instanceof XlsDto) {
                XlsDto xls = (XlsDto) iList.get(i);
                System.out.println(xls.getStuNo() + "\t" + xls.getScore() + "\t" + xls.getName() + "\t" + xls.getCollege() + "\t" + xls.getCourseName());
            }
        }

        return iList;
    }


    public static void main(String[] args) throws IOException {
//        bean.XlsDto xls = null;
        String readXls = "f:/zzz/test.xls";
        String writeXls = "f:/zzz/test1.xls";

        /*List<bean.XlsDto> list = utils.XlsUtils.readXls(readXls);
        // utils.XlsUtils.xlsDto2Excel(list, writeXls);

        for (int i = 0; i < list.size(); i++) {
            xls = (bean.XlsDto) list.get(i);
            System.out.println(xls.getStuNo() + "\t" + xls.getName() + "\t" + xls.getCollege() + "\t" + xls.getCourseName() + "\t" + xls.getScore());
        }*/

        List iList = XlsUtils.arrangeList(XlsUtils.getTitlesAndRowIndex(readXls), readXls);
        String[] temptitles = (String[]) iList.get(0);
        for (int i = 0; i < temptitles.length; i++) {
            System.out.print(temptitles[i] + "\t");
        }
        System.out.println();

        for (int i = 0; i < iList.size(); i++) {
            if (iList.get(i) instanceof XlsDto) {
                XlsDto xls = (XlsDto) iList.get(i);
                System.out.println(xls.getStuNo() + "\t" + xls.getScore() + "\t" + xls.getName() + "\t" + xls.getCollege() + "\t" + xls.getCourseName());
            }
        }
    }
}
