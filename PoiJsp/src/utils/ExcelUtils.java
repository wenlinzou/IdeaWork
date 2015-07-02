package utils;

import java.util.ArrayList;
import java.util.Date;

import bean.Question;

/**
 * Created by Pet on 2015-07-02.
 */
public class ExcelUtils {

    public static String getSequence() {
        return String.valueOf(new Date().getTime());
    }

    //    public String createFExcel(String filePath, String qdb_id,TrainEmpVO account) {
    public String createFExcel(String filePath, String qdb_id) {

        PoiExcelHelper helper = getPoiExcelHelper(filePath);

        // 读取excel文件数据
        ArrayList<ArrayList<String>> dataList = helper.readExcel(filePath, 0, "9-", new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m"});


        // 把excel中的数据插入数据库
        Question question = new Question();
        for (ArrayList<String> data : dataList) {

            String str = this.getSequence();// 手动生成试题的id
            question.setId(str);
            question.setQdb_id(qdb_id);

            String type = data.get(1);
            String qlevel = data.get(2);
            String score = data.get(3);
            String content = data.get(4);
            String reamrk = data.get(5);
            String aswer = data.get(6);
            String optiona = data.get(7);
            String optionb = data.get(8);
            String optionc = data.get(9);
            String optiond = data.get(10);
            String optione = data.get(11);
            String optionf = data.get(12);

            question.setType(type);
            question.setContent(content);
            question.setAswer(aswer);
            question.setQlevel(qlevel);
            question.setCdate(new Date());
            question.setRemark(reamrk);
            if (!score.isEmpty()) {
                question.setScore(Integer.parseInt(score));
            }

            // 题干不为空，
            /*if(!question.getContent().isEmpty()){
                this.createQuestion(question);

                if (!optiona.isEmpty()) {
                    questionOption.setOptions_id("A");
                    questionOption.setOptions(optiona);
                    questionOptionService.createQuestionOption(questionOption);
                }

                if (!optionb.isEmpty()) {
                    questionOption.setOptions_id("B");
                    questionOption.setOptions(optionb);
                    questionOptionService.createQuestionOption(questionOption);
                }

                if (!optionc.isEmpty()) {
                    questionOption.setOptions_id("C");
                    questionOption.setOptions(optionc);
                    questionOptionService.createQuestionOption(questionOption);
                }

                if (!optiond.isEmpty()) {
                    questionOption.setOptions_id("D");
                    questionOption.setOptions(optiond);
                    questionOptionService.createQuestionOption(questionOption);
                }

                if (!optione.isEmpty()) {
                    questionOption.setOptions_id("E");
                    questionOption.setOptions(optione);
                    questionOptionService.createQuestionOption(questionOption);
                }

                if (!optionf.isEmpty()) {
                    questionOption.setOptions_id("F");
                    questionOption.setOptions(optionf);
                    questionOptionService.createQuestionOption(questionOption);
                }

            }*/

        }
        String s = "3";// 3表示从excel中导入数据成功
        return s;
    }

    // 获取Excel处理类
    private PoiExcelHelper getPoiExcelHelper(String filePath) {
        PoiExcelHelper helper;
        if (filePath.indexOf(".xlsx") != -1) {
            helper = new PoiExcel2k7Helper();
        } else {
            helper = new PoiExcel2k3Helper();
        }
        return helper;
    }
}
