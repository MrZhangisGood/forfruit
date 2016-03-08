/**
 * Copyright 中海信息  Co.Ltd
 * @Title: BaseEntity.java
 * @Package entity
 * @author guozj@joyplus.com.cn
 * @date 2013年8月19日 下午4:22:31
 * @version V1.0
 */
package com.forjob.core.entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * BaseEntity
 * @author zhanglm@joyplus.com.cn
 * @version date  2014.11.10 13:07:02
 */
@MappedSuperclass
public abstract class BaseEntity {
    //columns START
    private String uuid;
    /**
     * DELETE db_column: IS_DELETE 
     */     
    private int isDelete;
    /**
     * createDate db_column: CREATE_DATE 
     */     
    private java.util.Date createDate;
    /**
     * updateDate db_column: UPDATE_DATE 
     */     
    private java.util.Date updateDate;
    //columns END
    
    @Id
    @Column(name = "UUID", length = 32)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    /**
     * @Description: 获取createDate
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    @Column(name="CREATE_DATE")
    public java.util.Date getCreateDate() {
        return this.createDate;
    }
    
    /**
     * @Description: 设置createDate
     * @author zhanglm@joyplus.com.cn
     * @param CreateDate
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }
    /**
     * @Description: 获取updateDate
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    @Column(name="UPDATE_DATE")
    public java.util.Date getUpdateDate() {
        return this.updateDate;
    }
    
    /**
     * @Description: 设置updateDate
     * @author zhanglm@joyplus.com.cn
     * @param UpdateDate
     */
    public void setUpdateDate(java.util.Date updateDate) {
        this.updateDate = updateDate;
    }
    /**
     * @Description: 获取DELETE
     * @author zhanglm@joyplus.com.cn
     * @return 
     */
    @Column(name="IS_DELETE")
    public int getIsDelete() {
        return this.isDelete;
    }
    
    /**
     * @Description: 设置DELETE
     * @author zhanglm@joyplus.com.cn
     * @param IsDelete
     */
    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取主键
     * @return
     */
    @Transient
    public String loadPKey() {
        String pKey = "";
        Method method = getMethod();
        try {
            if (method != null) {
                Object object = method.invoke(this, new Object[] {});
                if (object != null) {
                    pKey = object.toString();
                }
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return pKey;
    };

    @Transient
    public String loadPkName() {
        String pkName = "uuid";
        return pkName;
    }

    @Transient
    private Method getMethod() {
        Method m = null;
        Class<?> c = this.getClass();
        try {
            Method[] methods = c.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Id.class)) {
                    m = method;
                    break;
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return m;
    }
}
