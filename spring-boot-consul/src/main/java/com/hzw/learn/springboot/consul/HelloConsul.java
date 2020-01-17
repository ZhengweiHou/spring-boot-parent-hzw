package com.hzw.learn.springboot.consul;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/helloconsul")
public class HelloConsul {

	@Autowired
	private DiscoveryClient client;

	@Value("${server.port}")
	private Integer port;
	
	@Value("${server.application.name}")
	private String appname;
	@Autowired
	private LoadBalancerClient loadBalancer;

	@RequestMapping("client")
	public String client() {
		ServiceInstance serviceInstance = loadBalancer.choose("spring-boot-consul-demo");
		RestTemplate rest = new RestTemplate();
//    	String uri = "http://" + serviceInstance.getHost() + "/helloconsul/server";
		String uri = serviceInstance.getUri().toString();
		String url = uri + "/helloconsul/server";
		String result = rest.getForObject(url, String.class);
		System.out.println("==============");

		return result;
	}

	@RequestMapping("server")
	public String server() {

		return "hello client, there is server";
	}

//	http://localhost:8081/helloconsul/add?b=1&a=2
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(@RequestParam Integer a, @RequestParam Integer b) {
//		ServiceInstance instance = client.getLocalServiceInstance();
		ServiceInstance instance = client.getInstances(appname).get(0);
		Integer r = a + b;
		String s = "/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + "; result : " + r
				+ "; port:" + port;
//        logger.info(s);
		return s;
	}

	@RequestMapping(value = "/get/{serviceName}", method = RequestMethod.GET)
	public List<ServiceInstance> get(@PathVariable String serviceName) {

		List<ServiceInstance> ls = client.getInstances(serviceName);

		return ls;
	}

	@RequestMapping(value = "/geturl/{serviceName}", method = RequestMethod.GET)
	public String getUrl(@PathVariable String serviceName) {
		ServiceInstance serviceInstance = loadBalancer.choose(serviceName);
		
		return serviceInstance.getUri().toString();
	}
}