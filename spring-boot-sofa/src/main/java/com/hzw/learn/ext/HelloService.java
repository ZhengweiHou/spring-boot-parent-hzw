package com.hzw.learn.ext;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Resource
@Path("rest")
@Consumes
public interface HelloService {
    @GET
    @Path(value = "/hello/{code}")
    String hello(@PathParam("code") String mesg);

    @GET
    @Path(value = "/hello2")
    String hello2();

    // 参数是对象
    W hellow(W w);

    /**
     * 测试sofarpc，在服务端抛出异常时的处理
     * @param msg
     * @return
     */
    String helloException(String msg) throws HzwException;
}
