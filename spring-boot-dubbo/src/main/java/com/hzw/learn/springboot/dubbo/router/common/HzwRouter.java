package com.hzw.learn.springboot.dubbo.router.common;

import java.util.List;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.Router;

public class HzwRouter implements Router{

	@Override
	public URL getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRuntime() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isForce() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

}
