<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import com.eship.core.entity.BaseEntity;

/**
 * ${table.tableAlias}
 * <#include "/author.include">
 * <#include "/create.include">
 */
@Repository 
@Table(name="${table.sqlName}")
@Entity
@AttributeOverride(name = "uuid", column = @Column(name = "${table.pkColumn}"))
public class ${className?substring(3)}Entity extends BaseEntity implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    <@generateFields/>
    <@generateProperties/>
}
<#macro generateFields>
    //columns START
    <#list table.columns as column>
    <#if 
    	!column.pk && 
    	column.columnNameLower!="createDate" && 
    	column.columnNameLower!="createBy" && 
    	column.columnNameLower!="updateDate" && 
    	column.columnNameLower!="updateBy" && 
		column.columnNameLower!="isDelete" && 
    	column.columnNameLower!="owner" 
    >
    /**
     * ${column.columnAlias!} db_column: ${column.sqlName} 
     */     
    private ${column.javaType} ${column.columnNameLower};
    </#if>
    </#list>
    //columns END
</#macro>



<#macro generateProperties>
    <#list table.columns as column>
	    <#if 
	    !column.pk && 
    	column.columnNameLower!="createDate" && 
    	column.columnNameLower!="createBy" && 
    	column.columnNameLower!="updateDate" && 
    	column.columnNameLower!="updateBy" && 
		column.columnNameLower!="isDelete" && 
    	column.columnNameLower!="owner"
	>
    /**
	 * @Description: 获取${column.columnAlias}
	 * <#include "/author.include">
	 * @return 
	 */
    @Column(name="${column.constantName}")
    public ${column.javaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }
    
    /**
     * @Description: 设置${column.columnAlias}
     * <#include "/author.include">
     * @param ${column.columnName}
     */
    public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }
    </#if>
    </#list>
</#macro>


