package com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest;
import org.apache.dubbo.common.extension.ExtensionLoader;
public class A1$Adaptive implements com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A1 {
public void sayHello2(org.apache.dubbo.common.URL arg0)  {
if (arg0 == null) throw new IllegalArgumentException("url == null");
org.apache.dubbo.common.URL url = arg0;
String extName = url.getParameter("a1", "hzw");
if(extName == null) throw new IllegalStateException("Failed to get extension (com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A1) name from url (" + url.toString() + ") use keys([a1])");
com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A1 extension = (com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A1)ExtensionLoader.getExtensionLoader(com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A1.class).getExtension(extName);
extension.sayHello2(url);
}
public void sayHello(org.apache.dubbo.common.URL arg0, org.apache.dubbo.rpc.Invocation arg1, java.lang.String arg2)  {
if (arg0 == null) throw new IllegalArgumentException("url == null");
org.apache.dubbo.common.URL url = arg0;
if (arg1 == null) throw new IllegalArgumentException("invocation == null"); String methodName = arg1.getMethodName();
String extName = url.getMethodParameter(methodName, "a1", "hzw");
if(extName == null) throw new IllegalStateException("Failed to get extension (com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A1) name from url (" + url.toString() + ") use keys([a1])");
com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A1 extension = (com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A1)ExtensionLoader.getExtensionLoader(com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A1.class).getExtension(extName);
extension.sayHello(url, invocation, str1);
}
public void sayHello3(org.apache.dubbo.common.URL arg0)  {
if (arg0 == null) throw new IllegalArgumentException("url == null");
org.apache.dubbo.common.URL url = arg0;
String extName = url.getParameter("hhh", url.getParameter("zzz", url.getParameter("www", "hzw")));
if(extName == null) throw new IllegalStateException("Failed to get extension (com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A1) name from url (" + url.toString() + ") use keys([hhh, zzz, www])");
com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A1 extension = (com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A1)ExtensionLoader.getExtensionLoader(com.hzw.learn.springboot.dubbo.AdaptiveExtension.AdaptiveClassCodeGeneratorTest.A1.class).getExtension(extName);
extension.sayHello3(url);
}
}