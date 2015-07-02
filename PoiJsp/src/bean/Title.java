package bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pet on 2015-07-01.
 */
public class Title {

    public static final String[] dataTitles = {"序号", "得分", "学生姓名", "学院名称", "课程名称"};
    public static final Map map = new HashMap() {
        {
            put("序号", "setStuNo");
            put("得分", "setScore");
            put("学生姓名", "setName");
            put("学院名称", "setCollege");
            put("课程名称", "setCourseName");
        }
    };


}
