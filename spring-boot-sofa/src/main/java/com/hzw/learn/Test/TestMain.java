package com.hzw.learn.Test;

import com.hzw.learn.ext.NetworkAddressUtil;

/**
 * @ClassName TestMain
 * @Description TODO
 * @Author houzw
 * @Date 2023/3/1
 **/

public class TestMain {
    public static void main(String[] args) {

//        String ipRange = System.getProperty("ipRange", "");
//        NetworkAddressUtil.caculate(ipRange, null);
//        String ip = NetworkAddressUtil.getLocalIP();
//        System.out.println(ip);

        String nacosaddress = "127.0.0.1:8148,127.0.0.1:8248,127.0.0.1:8348/test";
        if (args.length < 1){
            System.out.println("请给个配置!!\n" +
                    "type[c/s] [nacosAddress] [sofaserverport]\n" +
                    "eg:c 127.0.0.1:8248,127.0.0.1:8348/test\n" +
                    "eg:s 127.0.0.1:8248,127.0.0.1:8348/test 12200\n");
            System.exit(1);
        }
        String type = args[0];

        String[] args2 = new String[args.length-1];
        for (int i=1; i<args.length; i++){
            args2[i-1] = args[i];
        }

//        if (args.length > 1){
//            nacosaddress = args[1];
//        }

        switch (type){
            case "c":
                SofaRegistryClient.main(args2);
                break;
            case "s":
                SofaRegistryServer.main(args2);
                break;
        }
    }
}
