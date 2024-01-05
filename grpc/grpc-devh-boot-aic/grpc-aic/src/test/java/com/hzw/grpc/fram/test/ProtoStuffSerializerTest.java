package com.hzw.grpc.fram.test;

import com.alibaba.fastjson.JSON;
import com.hzw.grpc.fram.serializer.protostuff.ProtoStuffSerializer;
import com.hzw.grpc.fram.serializer.protostuff.Wrapper;
import io.protostuff.GraphIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@FixMethodOrder(MethodSorters.JVM)
public class ProtoStuffSerializerTest {

    // 循环引用序列化测试
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



    @Test
    public void protoStuffTest2() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-08-08");
        RuntimeSchema<Date> schema = RuntimeSchema.createFrom(Date.class);
        byte[] value = GraphIOUtil.toByteArray(
                date,
                schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        System.out.println(value.length);

        _TA ta = new _TA();
        ta.name = "hzw";
        ta.date =  date; // date类型序列化测试

//        RuntimeSchema<_TA> schema2 = RuntimeSchema.createFrom(_TA.class);
        Schema<_TA> schema2 = RuntimeSchema.getSchema(_TA.class);
        byte[] value2 = GraphIOUtil.toByteArray(
                ta,
                schema2,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        _TA obj = schema2.newMessage();
        GraphIOUtil.mergeFrom(value2, obj, schema2);

        assert date.getTime() == obj.date.getTime();
        System.out.println(obj.name  + obj.date.toString());
    }

    @Test
    public void protoStuffTest3() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-08-08");

        _TA ta = new _TA();
        ta.name = "hzw";
        ta.date =  date;

        // 序列化A
        RuntimeSchema<_TA> schema = RuntimeSchema.createFrom(_TA.class);
        byte[] value2 = GraphIOUtil.toByteArray(
                ta,
                schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

        // 用B反序列化A
        RuntimeSchema<_TB> schemaB = RuntimeSchema.createFrom(_TB.class);
        _TB obj = schemaB.newMessage();
        GraphIOUtil.mergeFrom(value2, obj, schemaB);
        // 产生异常: io.protostuff.ProtobufException: Protocol message contained an invalid tag (zero).
    }

    @Test
    public void protoStuffTest4() throws ParseException {
        ProtoStuffSerializer ps = new ProtoStuffSerializer();

        // Date类型序列化结果测试
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2000-08-08");
        byte[] value = ps.serialize(date);
        Date date2 = ps.deserialize(value, Date.class);
        System.out.println("date1.time:" + date.getTime() + " date2.time:" + date2.getTime());
        System.out.println(date2.toString());

        // null类型序列化
        byte[] value2 = ps.serialize(null);
        Date date3 = ps.deserialize(value2, Date.class);
        System.out.println(date3);

        // list 类型序列化测试
        ArrayList<String> dataList = new ArrayList<String>();
        dataList.add("hello1");
        dataList.add(null);
        dataList.add("hello2");
        dataList.add(null);
        dataList.add("hello3");
        dataList.add("hello4");

        System.out.println("old size:" + dataList.size());

        byte[] value3 = ps.serialize(dataList);
        ArrayList<Object> dataList2 = ps.deserialize(value3, ArrayList.class);
        System.out.println("size:" + dataList2.size());

        Schema<ArrayList> schema = RuntimeSchema.getSchema(ArrayList.class);
        byte[] value4 = ps.serialize(schema,dataList);
        ArrayList dataList3 = schema.newMessage();
        GraphIOUtil.mergeFrom(value4, dataList3, schema);
        System.out.println("size:" + dataList3.size());

        //
        int a = 2;
        byte[] b = ps.serialize(a); // 此方式最终会按Integer类型进行序列化，应为a.getClass 获取的类型是Integer
        byte[] c = ps.serialize(int.class,a);
//        byte[] b = ps.serialize(int.class, null);
        int s = ps.deserialize(c, int.class);
        System.out.println(s);

    }

    @Test
    public void protoStuffTest5(){

    }
}

class _TA {
    public String name;
    public Date date;
    public _TB tb;
}

class _TB {
    public String name;
    public _TA ta;
}
