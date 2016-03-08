package cn.org.rapid_framework.generator;


/**
 * 
 * @author badqiu
 * @email badqiu(a)gmail.com
 */

public class GeneratorMain {
    
    private static String tableName = "WMS_INV_ITEM_HIS";
    
    private static String templateSrc = "for-webrapidgen/template";
    
	/**
	 * 请直接修改以下代码调用不同的方法以执行相关生成任务.
	 */
	public static void  main(String[] args) throws Exception {
        GeneratorFacade g = new GeneratorFacade();
        //打印数据库中的表名称
        //g.printAllTableNames();
        //删除生成器的输出目录
        g.deleteOutRootDir();
        //通过数据库表生成文件,template为模板的根目录
        g.generateByTable(tableName, templateSrc);
        //自动搜索数据库中的所有表并生成文件,template为模板的根目录
        //g.generateByAllTable("template");
        //g.generateByClass(Blog.class,"template_clazz");
        //删除生成的文件
        //g.deleteByTable("table_name", "template");
        
        //创建服务层
        //CreateService.create(tableName, templateSrc, GeneratorProperties.getRequiredProperty("outRoot"));
        
        System.out.println("+++CREATE SUCCESS [END] ...............+++++++++++++++++++++++++++");
        
        //打开文件夹
        Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
        
	}
}
