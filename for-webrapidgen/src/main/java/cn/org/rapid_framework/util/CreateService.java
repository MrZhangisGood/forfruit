package cn.org.rapid_framework.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** 
 * @author zhanglm@joyplus.com.cn 
 * @date 2015年2月4日 上午10:07:16   
 * @version 1.0
 */
public class CreateService {
    
    private static String modelPath = ".service";
    private static String className_ = "${className}";
    private static String varName_ = "${varName}";
    private static String createDate_ = "${createDate}";
    private static String basepackage_ = "${basepackage}";
    private static String projectName_ = "${projectName}";
    
    @SuppressWarnings("serial")
    private static Map<String, String> projectMap = new HashMap<String, String>(){{
        this.put("bsd", "basedata");
        this.put("cus", "customer");
        this.put("ord", "order");
        this.put("sys", "system");
        this.put("wms", "wms");
    }};
    
    private static String projectPath = "E:/workspace.self/forfruit/E-SHIP/${projectName}/src/main/java/com/eship/${projectName}";
    
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    
    public static void create(String tableName, String templateSrc, String outPath) throws Exception{
        String[] tables = tableName.split("_");
        
        String createDate = dateFormat.format(new Date());
        String basepackage = "com.eship." + projectMap.get(tables[0].toLowerCase());
        String projectName = projectMap.get(tables[0].toLowerCase());
        String className = "";
        String varName = "";
        int fromIndex = 1;
        for (int i = fromIndex; i < tables.length; i++) {
            String lower = tables[i].toLowerCase();
            String first = lower.substring(0, 1);
            className += lower.replaceAll(first, first.toUpperCase());
            if(i == fromIndex){
                varName = lower;
            }else {
                varName += lower.replaceAll(first, first.toUpperCase());
            }
        }
        
        //生成controller , service , dao 层
        File filePath = new File(templateSrc + modelPath);
        File[] files = filePath.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                File file = files[i];
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuffer content = new StringBuffer();
                String temp = "";
                while ((temp = reader.readLine()) != null) {
                    temp = temp.replace(className_, className);
                    temp = temp.replace(varName_, varName);
                    temp = temp.replace(createDate_, createDate);
                    temp = temp.replace(basepackage_, basepackage);
                    content.append(temp + "\r\n");
                }
                reader.close();
                String sourcePath = projectPath.replace(projectName_, projectName) + "/" + file.getName().replace(className_, "").replace(".java", "").toLowerCase();
                writeToFile(sourcePath, content.toString(), file.getName().replace(className_, className));
            }
        }
        
        //复制到entity 层
        String entityPath = projectPath.replace(projectName_, projectName) + "/entity";
        File resultEntityPath = new File(outPath + "/java_src");
        File[] entitys = resultEntityPath.listFiles();
        copyFile(entitys[0], new File(entityPath + "/" + entitys[0].getName()));
    }
    

    // 写入文件
    public static void writeToFile(String outPath, String content, String fileName) throws IOException {
        File file = new File(outPath + "/" + fileName);
        if(!file.exists()){
            FileWriter fWriter = new FileWriter(file);
            fWriter.write(content);
            fWriter.flush();
            fWriter.close();
        }
    }
    

    // 复制文件
    public static void copyFile(File sourceFile, File targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

}
