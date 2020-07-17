package com.hzw.learn.springboot.dubbo.AdaptiveExtension.dubboDemo;
import org.apache.dubbo.common.extension.ExtensionLoader;
public class RefillMaker$Adaptive implements com.hzw.learn.springboot.dubbo.AdaptiveExtension.dubboDemo.RefillMaker {
public com.hzw.learn.springboot.dubbo.AdaptiveExtension.handDemo.Refill makeRefill(org.apache.dubbo.common.URL arg0)  {
if (arg0 == null) throw new IllegalArgumentException("url == null");
org.apache.dubbo.common.URL url = arg0;
String extName = url.getParameter("refill.maker");
if(extName == null) throw new IllegalStateException("Failed to get extension (com.hzw.learn.springboot.dubbo.AdaptiveExtension.dubboDemo.RefillMaker) name from url (" + url.toString() + ") use keys([refill.maker])");
com.hzw.learn.springboot.dubbo.AdaptiveExtension.dubboDemo.RefillMaker extension = (com.hzw.learn.springboot.dubbo.AdaptiveExtension.dubboDemo.RefillMaker)ExtensionLoader.getExtensionLoader(com.hzw.learn.springboot.dubbo.AdaptiveExtension.dubboDemo.RefillMaker.class).getExtension(extName);
return extension.makeRefill(url);
}
public com.hzw.learn.springboot.dubbo.AdaptiveExtension.handDemo.Refill makeRefillLight(org.apache.dubbo.common.URL arg0)  {
if (arg0 == null) throw new IllegalArgumentException("url == null");
org.apache.dubbo.common.URL url = arg0;
String extName = url.getParameter("makerkey", url.getParameter("makername"));
if(extName == null) throw new IllegalStateException("Failed to get extension (com.hzw.learn.springboot.dubbo.AdaptiveExtension.dubboDemo.RefillMaker) name from url (" + url.toString() + ") use keys([makerkey, makername])");
com.hzw.learn.springboot.dubbo.AdaptiveExtension.dubboDemo.RefillMaker extension = (com.hzw.learn.springboot.dubbo.AdaptiveExtension.dubboDemo.RefillMaker)ExtensionLoader.getExtensionLoader(com.hzw.learn.springboot.dubbo.AdaptiveExtension.dubboDemo.RefillMaker.class).getExtension(extName);
return extension.makeRefillLight(url);
}
}