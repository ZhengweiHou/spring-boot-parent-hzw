package com.hzw.learn.springboot.springbase.DI.DIHello;

import java.util.*;

/**
 * @ClassName ArrayListMapDITestBean
 * @Description 演示 注入 集合、数组、字典、properties等方式
 * @Author houzw
 * @Date 2020/7/1
 **/

public class ListSetMapDITestBean {
    private List<String> listValues;

    private Set<String> setValues;

    private Map<String,String> mapValues;

    private String[] array1v;

    private String[][] array2v;

    private Properties properties1;

    private Properties properties2;


    public ListSetMapDITestBean() {
    }


    //    Setter
    public void setListValues(List<String> listValues) {
        this.listValues = listValues;
    }

    public void setSetValues(Set<String> setValues) {
        this.setValues = setValues;
    }

    public void setMapValues(Map<String, String> mapValues) {
        this.mapValues = mapValues;
    }

    public void setArray1v(String[] array1v) {
        this.array1v = array1v;
    }

    public void setArray2v(String[][] array2v) {
        this.array2v = array2v;
    }

    public void setProperties1(Properties properties1) {
        this.properties1 = properties1;
    }

    public void setProperties2(Properties properties2) {
        this.properties2 = properties2;
    }
}
