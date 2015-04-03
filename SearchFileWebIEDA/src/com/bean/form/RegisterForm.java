package com.bean.form;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class RegisterForm {
    private String username;
    private String password;
    private String password2;
    private String birthday;
    private Map<String, String> errors = new HashMap<String, String>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    // 用户名不能为空，3-8字母
    // 密码不能为空，3-8数字
    // 确认密码不能为空，
    // 邮箱，格式合法
    // 生日可以为空，不为空时，必须要是一个日期
    // 昵称不为空要是汉子
    public boolean validate() {
        boolean isOK = true;

        if (this.username == null || this.username.trim().equals("")) {
            isOK = false;
            errors.put("username", "用户名不能为空！！");
        } else {
            if (!this.username.matches("[A-Za-z]{3,8}")) {
                isOK = false;
                errors.put("username", "用户名长度3-8位字母！！");
            }
        }

        if (this.password == null || this.password.trim().equals("")) {
            isOK = false;
            errors.put("password", "密码不能为空！！");
        } else {
            if (!this.password.matches("\\d{3,8}")) {
                isOK = false;
                errors.put("password", "密码长度3-8位数字！！");
            }
        }

        if (this.password2 == null || this.password2.trim().equals("")) {
            isOK = false;
            errors.put("password2", "确认密码不能为空！！");
        } else {
            if (!this.password2.equals(password)) {
                isOK = false;
                errors.put("password2", "两次密码不一致！！");
            }
        }


        // 生日可以为空，不为空 必须要是一个日期
        if (this.birthday != null && !this.birthday.trim().equals("")) {

            try {
                DateLocaleConverter dic = new DateLocaleConverter();
                dic.convert(this.birthday, "yyyy-MM-dd");
            } catch (Exception e) {
                isOK = false;
                errors.put("birthday", "日期格式不正确");
            }
        }

        // 昵称不能为空 要是汉字

			
			/*
			 if (this.email == null || this.email.trim().equals("")) {
				isOK = false;
				errors.put("email", "邮箱不能为空！！");
			} else {
				if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
					isOK = false;
					errors.put("email", "邮箱格式不对！！");
				}
			}
			 if (this.nickname == null || this.nickname.trim().equals("")) {
				isOK = false;
				errors.put("nickname", "昵称不能为空");
			}else {
				if(!this.nickname.matches("^([\u4e00-\u9fa5]+)$")){
					isOK = false;
					errors.put("nickname", "昵称必须为汉字");
				}
			}*/

        return isOK;
    }

}
