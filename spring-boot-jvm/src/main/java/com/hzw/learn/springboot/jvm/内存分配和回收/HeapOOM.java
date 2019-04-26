package com.hzw.learn.springboot.jvm.内存分配和回收;

import java.util.ArrayList;
import java.util.List;

//-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
public class HeapOOM {
 static class OOMObject {}
 public static void main(String[] args){
     List<OOMObject> list =  new ArrayList<OOMObject>();
     while (true) {
         list.add(new OOMObject());
     }
 }
}

