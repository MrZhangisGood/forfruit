package com.forjob.core.enums;

public enum EBaseEntity{
    
    /**
     * 正常
     */
    USE("正常"),
    
    /**
     * 删除
     */
    DELETE("删除");
    
    private String description;

    private EBaseEntity(String description) {
        this.description = description;
    }
    
    public String getDescription() {
       return this.description;
    }
}
