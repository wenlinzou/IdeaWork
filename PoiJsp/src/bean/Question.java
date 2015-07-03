package bean;

import java.util.Date;
import java.util.List;


/**
 * database : em_question 
 * Description : 试题 
 * Company : Wicresoft
 * @author wuheng
 * 
 */
public class Question {
	private String id; // 编号
	private String org_id; // 归属机构
	private String classify_id; // 分类
	private String qdb_id; // 题库ID
	private Integer qtype_id; // 试题类型id
	private String type; // 题型 单选 多选 判断
	private String content; // 题干内容
	private String aswer; // 正确答案
	private String qlevel; // 难易级别 容易 一般 较难 非常难
	private Date cdate; // 创建时间
	private String cuser;//创建人员
	private String remark; // 备注
	private Integer score;//本题分值
	
	private String index;//题目在集合中的下标
	
	
	
	private String isadd;//是否已经添加
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}




	public String getIsadd() {
		return isadd;
	}

	public void setIsadd(String isadd) {
		this.isadd = isadd;
	}


	


	
	public String getCuser() {
		return cuser;
	}

	public void setCuser(String cuser) {
		this.cuser = cuser;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public String getClassify_id() {
		return classify_id;
	}

	public void setClassify_id(String classify_id) {
		this.classify_id = classify_id;
	}

	public String getQdb_id() {
		return qdb_id;
	}

	public void setQdb_id(String qdb_id) {
		this.qdb_id = qdb_id;
	}

	public Integer getQtype_id() {
		return qtype_id;
	}

	public void setQtype_id(Integer qtype_id) {
		this.qtype_id = qtype_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAswer() {
		return aswer;
	}

	public void setAswer(String aswer) {
		this.aswer = aswer;
	}

	public String getQlevel() {
		return qlevel;
	}

	public void setQlevel(String qlevel) {
		this.qlevel = qlevel;
	}

	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
