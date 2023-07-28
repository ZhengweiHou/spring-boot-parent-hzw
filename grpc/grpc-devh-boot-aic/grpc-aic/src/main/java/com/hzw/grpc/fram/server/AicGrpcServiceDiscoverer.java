package com.hzw.grpc.fram.server;

import java.util.Collection;

/**
 * @ClassName AicGrpcServiceDiscoverer
 * @Description TODO
 * @Author houzw
 * @Date 2023/7/26
 **/
@FunctionalInterface
public interface AicGrpcServiceDiscoverer {
    Collection<AicGrpcServiceDefinition> findAicGrpcServiceDefinitions();
}
