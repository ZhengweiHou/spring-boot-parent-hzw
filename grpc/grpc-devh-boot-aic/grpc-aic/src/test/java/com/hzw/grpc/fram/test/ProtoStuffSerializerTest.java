package com.hzw.grpc.fram.test;

import com.alibaba.fastjson.JSON;
import com.hzw.grpc.fram.serializer.ProtoStuffSerializer;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class ProtoStuffSerializerTest {

    @Test
    public void protoStuffTest1(){
        ProtoStuffSerializer ps = new ProtoStuffSerializer();
        byte[] b1 = ps.serialize("hello");
        String o1 = ps.deserialize(b1, String.class);
        System.out.println(o1);

        _TA ta = new _TA();
        _TB tb = new _TB();
        ta.name = "ta name";
        tb.name = "tb name";
        ta.tb = tb;
        tb.ta = ta;

        // fastjson OK
        System.out.println("fastjson ta:" + JSON.toJSONString(ta));
        _TA ta2 = JSON.parseObject(JSON.toJSONString(ta), ta.getClass());
        System.out.println("fastjson ta.tb:" + JSON.toJSONString(ta2.tb));

        // gson error
//        System.out.println("gson ta:" + new Gson().toJson(ta));

        byte[] tab1 = ps.serialize(ta);
        _TA ta1 = ps.deserialize(tab1, _TA.class);

        System.out.println("ProtoStuff ta1:" + JSON.toJSONString(ta1));
//        System.out.println(o1);

    }
}

class _TA {
    public String name;
    public _TB tb;
}

class _TB {
    public String name;
    public _TA ta;
}
