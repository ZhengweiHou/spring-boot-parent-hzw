package com.hzw.learn.springboot.mybatis.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HZWSimpleUtil {
	
	/**
	 * 解析sql获取表名（sql中没有scheme和表别名）
	 * @param sql
	 * @return
	 */
	public static String extractTableName(String sql){
		if(null == sql){
			return null;
		}
		
        Matcher matcher = null;
        //SELECT 列名称 FROM 表名称
        //SELECT * FROM 表名称
        if( sql.toLowerCase().startsWith("select")){
            matcher = Pattern.compile("\\s*(select|SELECT)\\s+\\S+\\s+from\\s+(\\S+).*").matcher(sql);
            if(matcher.find()){
                return matcher.group(2);
            }
        }
        //INSERT INTO 表名称 VALUES (值1, 值2,....)
        //INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
        if( sql.toLowerCase().startsWith("insert") ){
            matcher = Pattern.compile("\\s*(insert|INSERT)\\s+(into|INTO)\\s+(\\S+).*").matcher(sql);
            if(matcher.find()){
                return matcher.group(3);
            }
        }
        //UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
        if( sql.toLowerCase().startsWith("update") ){
            matcher = Pattern.compile("\\s*(update|UPDATE)\\s+(\\S+)\\s+set\\s+.*").matcher(sql);
            if(matcher.find()){
                return matcher.group(2);
            }
        }
        //DELETE FROM 表名称 WHERE 列名称 = 值
        if( sql.toLowerCase().startsWith("delete") ){
            matcher = Pattern.compile("\\s*(delete|DELETE)\\s+from\\s+(\\S+).*").matcher(sql);
            if(matcher.find()){
                return matcher.group(2);
            }
        }
        return null;
    }
	
	/** 下滑线命名转驼峰命名，首字母大写
	 * @param para
	 * @return
	 */
	public static String UnderlineToHumpFirstUpper(String para){
        StringBuilder result=new StringBuilder();
        String a[]=para.split("_");
        for(String s:a){
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
        }
        return result.toString();
	}
	
	/** 测试
	 * @param args
	 */
	public static void main(String[] args) {
		 //SELECT 列名称（*所有列） FROM 表名称
       //SELECT 列名称 FROM 表名称 where 条件
       System.out.println( extractTableName( "SELECT  * from aaa ") );
       System.out.println( extractTableName( "select id,name,password from bbb where id = 1 ") );
       //INSERT INTO 表名称 VALUES (值1, 值2,....)
       //INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)
       System.out.println( extractTableName("insert into ccc valuse(1,'neo','password')") );
       System.out.println( extractTableName("insert into DDD (id,name,password) valuses(1,'neo','password')") );
       //UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
       System.out.println( extractTableName("update eee set name = 'neo' where id = 1 ") );
       System.out.println( extractTableName("UPDATE fff set name = 'neo'") );
       //DELETE FROM 表名称 WHERE 列名称 = 值
       System.out.println( extractTableName("DELETE from ggg where id = 1 ") );
       System.out.println( extractTableName(null) );
	}
	
}
