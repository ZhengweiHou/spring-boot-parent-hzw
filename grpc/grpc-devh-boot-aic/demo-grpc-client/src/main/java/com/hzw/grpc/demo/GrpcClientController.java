/*
 * Copyright (c) 2016-2021 Michael Zhang <yidongnan@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.hzw.grpc.demo;

import com.hzw.grpc.demo.api.HzwApi;
import com.hzw.grpc.fram.client.AicGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Optional demo controller making the grpc service accessible via browser requests.
 */
@RestController
public class GrpcClientController {

    @AicGrpc("ccc")
    private HzwApi hzwApi_inject_by_BPP;

    @Autowired
    private HzwApi hzwApi;

    @RequestMapping("/1")
    public String printMessage(@RequestParam(defaultValue = "sirius") final String name) {
        try {
            return hzwApi_inject_by_BPP.sayHello(name);
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @RequestMapping("/2")
    public String printMessage2(@RequestParam(defaultValue = "sirius") final String name) {
        try {
            return hzwApi.sayHello(name);
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
