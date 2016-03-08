package com.forjob.server.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;

import com.forjob.core.entity.BaseEntity;

/**
 * 客商用户信息
 * @author zhanglm@joyplus.com.cn
 * @version date  2014.11.24 10:48:54
 */
@Repository 
@Table(name="CUS_USER")
@Entity
@AttributeOverride(name = "uuid", column = @Column(name = "USER_ID"))
@Inheritance(strategy = InheritanceType.JOINED)
public class UserEntity extends BaseEntity implements Serializable{
    
    private static final long serialVersionUID = 3476820084878684591L;
    
    //columns START
    /**
     * 用户编码 db_column: USER_NO 
     */     
    private java.lang.String userNo;
    /**
     * 登陆名 db_column: LOGIN_NAME 
     */     
    private java.lang.String loginName;
    /**
     * 密码 db_column: PASSWORD 
     */     
    private java.lang.String password;
    /**
     * 中文名称 db_column: USER_NAME 
     */     
    private java.lang.String userName;
    /**
     * 性别 db_column: SEX 
     */     
    private int sex;
    /**
     * 头像 db_column: HEAD_IMAGE_URL 
     */     
    private java.lang.String headImageUrl;
    /**
     * 移动电话 db_column: PHONE 
     */     
    private java.lang.String phone;
    /**
     * 移动电话是否验证 db_column: PHONE_VERIFY 
     */     
    private int phoneVerify;
    /**
     * 电子信箱 db_column: EMAIL 
     */     
    private java.lang.String email;
    /**
     * 电子信箱是否验证 db_column: EMAIL_VERIFY 
     */     
    private int emailVerify;
    /**
     * 积分 db_column: INTEGRAL 
     */     
    private java.lang.Long integral;
    /**
     * 备注 db_column: REMARK 
     */     
    private java.lang.String remark;
    //columns END
    
    /**
     * @Description: 获取用户编码
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    @Column(name="USER_NO")
    public java.lang.String getUserNo() {
        return this.userNo;
    }
    
    /**
     * @Description: 设置用户编码
     * @author zhanglm@joyplus.com.cn
     * @param UserNo
     */
    public void setUserNo(java.lang.String userNo) {
        this.userNo = userNo;
    }
    /**
     * @Description: 获取登陆名
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    @Column(name="LOGIN_NAME")
    public java.lang.String getLoginName() {
        return this.loginName;
    }
    
    /**
     * @Description: 设置登陆名
     * @author zhanglm@joyplus.com.cn
     * @param LoginName
     */
    public void setLoginName(java.lang.String loginName) {
        this.loginName = loginName;
    }
    /**
     * @Description: 获取密码
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    @Column(name="PASSWORD")
    public java.lang.String getPassword() {
        return this.password;
    }
    
    /**
     * @Description: 设置密码
     * @author zhanglm@joyplus.com.cn
     * @param Password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }
    /**
     * @Description: 获取中文名称
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    @Column(name="USER_NAME")
    public java.lang.String getUserName() {
        return this.userName;
    }
    
    /**
     * @Description: 设置中文名称
     * @author zhanglm@joyplus.com.cn
     * @param userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }
    /**
     * @Description: 获取性别
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    @Column(name="SEX")
    public int getSex() {
        return this.sex;
    }
    
    /**
     * @Description: 设置性别
     * @author zhanglm@joyplus.com.cn
     * @param Sex
     */
    public void setSex(int sex) {
        this.sex = sex;
    }
    /**
     * @Description: 获取头像
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    @Column(name="HEAD_IMAGE_URL")
    public java.lang.String getHeadImageUrl() {
        return this.headImageUrl;
    }
    
    /**
     * @Description: 设置头像
     * @author zhanglm@joyplus.com.cn
     * @param HeadImageUrl
     */
    public void setHeadImageUrl(java.lang.String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }
    /**
     * @Description: 获取移动电话
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    @Column(name="PHONE")
    public java.lang.String getPhone() {
        return this.phone;
    }
    
    /**
     * @Description: 设置移动电话
     * @author zhanglm@joyplus.com.cn
     * @param Phone
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }
    /**
     * @Description: 获取电子信箱
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    @Column(name="EMAIL")
    public java.lang.String getEmail() {
        return this.email;
    }
    
    /**
     * @Description: 设置电子信箱
     * @author zhanglm@joyplus.com.cn
     * @param Email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }
    
    /**
	 * @Description: 获取备注
	 * @author zhanglm@joyplus.com.cn
	 * @return 
	 */
    @Column(name="REMARK")
    public java.lang.String getRemark() {
        return this.remark;
    }
    
    /**
     * @Description: 设置备注
     * @author zhanglm@joyplus.com.cn
     * @param Remark
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }
    /**
	 * @Description: 获取积分
	 * @author zhanglm@joyplus.com.cn
	 * @return 
	 */
    @Column(name="INTEGRAL")
    public java.lang.Long getIntegral() {
        return this.integral;
    }
    
    /**
     * @Description: 设置积分
     * @author zhanglm@joyplus.com.cn
     * @param Integral
     */
    public void setIntegral(java.lang.Long integral) {
        this.integral = integral;
    }
    /**
     * @Description: 获取移动电话是否验证
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    @Column(name="PHONE_VERIFY")
    public int getPhoneVerify() {
        return this.phoneVerify;
    }
    
    /**
     * @Description: 设置移动电话是否验证
     * @author zhanglm@joyplus.com.cn
     * @param PhoneVerify
     */
    public void setPhoneVerify(int phoneVerify) {
        this.phoneVerify = phoneVerify;
    }
    /**
     * @Description: 获取电子信箱是否验证
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    @Column(name="EMAIL_VERIFY")
    public int getEmailVerify() {
        return this.emailVerify;
    }
    
    /**
     * @Description: 设置电子信箱是否验证
     * @author zhanglm@joyplus.com.cn
     * @param EmailVerify
     */
    public void setEmailVerify(int emailVerify) {
        this.emailVerify = emailVerify;
    }
}
