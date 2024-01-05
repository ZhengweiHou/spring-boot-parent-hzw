package com.hzw.learn.springboot.javabase.unicode;

import com.hzw.dataspec.ExampleDocument;
import org.apache.tomcat.util.buf.HexUtils;
import org.apache.xmlbeans.XmlException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @ClassName UnicodeTest1
 * @Description TODO
 * @Author houzw
 * @Date 2023/11/30
 **/
public class UnicodeTest1 {
    @Test
    public void test1() throws UnsupportedEncodingException {
        String unitstr = "\uD86E\uDF7C侯\uD862\uDFEF"; //𫭼
        System.out.println(unitstr);

        byte[] strBt = unitstr.getBytes("UTF-8");
        String decodedString = new String(strBt, "UTF-8");
        System.out.println(decodedString);

        System.out.println(HexUtils.toHexString(strBt));


    }

    @Test
    public void test2() throws UnsupportedEncodingException {
//        303032333935
        String oristr = "3c3f786d6c2076657273696f6e3d22312e302220656e636f64696e673d225554462d38223f3e3c5345525649434520786d6c6e733d22687474703a2f2f7777772e616c6c696e66696e616e63652e636f6d2f64617461737065632f223e3c534552564943455f4845414445523e3c534552564943455f534e3e3230323331313238313031343434313438323c2f534552564943455f534e3e3c534552564943455f49443e31303030303c2f534552564943455f49443e3c4f52473e3030303036353834373030303c2f4f52473e3c4348414e4e454c5f49443e30323c2f4348414e4e454c5f49443e3c4f505f49443e43495030303030313c2f4f505f49443e3c5245515553545f54494d453e32303233313132383039333832313c2f5245515553545f54494d453e3c56455253494f4e5f49443e30313c2f56455253494f4e5f49443e3c4d41432f3e3c2f534552564943455f4845414445523e3c534552564943455f424f44593e3c524551554553543e3c4253435f535550505f494e443e423c2f4253435f535550505f494e443e3c50524f445543545f43443e3030313030353c2f50524f445543545f43443e3c4150505f545950453e423c2f4150505f545950453e3c4150505f4e4f3e4232333131323730303033373034333c2f4150505f4e4f3e3c504f535f50494e5f5645524946595f494e443e593c2f504f535f50494e5f5645524946595f494e443e3c50484f544f5f5553455f464c41473e4e3c2f50484f544f5f5553455f464c41473e3c4e414d453ee5bca0f0b19f9bf0ac9aa93c2f4e414d453e3c47454e4445523e4d3c2f47454e4445523e3c42495254484441593e31393837303431323c2f42495254484441593e3c4d41524954414c5f5354415455533e433c2f4d41524954414c5f5354415455533e3c4e4154494f4e414c4954593e3135363c2f4e4154494f4e414c4954593e3c49445f545950453e493c2f49445f545950453e3c49445f4e4f3e3532323132313139383730343132323433";
//        oristr="f0abadbc";
        String decodeStr = new String(HexUtils.fromHexString(oristr), "UTF-8");
        System.out.println(decodeStr);


        String oristr2 = "629830b839618336cf36";
        String decodeStr2 = new String(HexUtils.fromHexString(oristr2), "gb18030");
        System.out.println(decodeStr2);
    }

    @Test
    public void test3() throws UnsupportedEncodingException, XmlException {
        String str="<NAME>\uD86E\uDF7C</NAME>";
        str = ""
                + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<NAME>hello</NAME>";


        byte[] strBt = str.getBytes("UTF-8");
        System.out.println(HexUtils.toHexString(strBt));

//        REQUESTELEDocument.Factory.parse(str);

    }

    @Test
    public void test4() throws UnsupportedEncodingException, XmlException {

        String str= "<example xmlns=\"http://www.hzw.com/dataspec/\">"
                + "<name>\uD862\uDFEF侯正伟\uD86E\uDF7C</name>"
                + "</example>";
        System.out.println(str);

        ExampleDocument cc = ExampleDocument.Factory.parse(str);
        String name = cc.getExample().getName();
        System.out.println(name);

    }

}
