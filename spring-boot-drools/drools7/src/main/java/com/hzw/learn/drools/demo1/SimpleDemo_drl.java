package com.hzw.learn.drools.demo1;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.conf.MBeansOption;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import java.io.IOException;
import java.util.HashMap;

//@Component
public class SimpleDemo_drl {
	public static void main(String[] args) throws IOException {
		// kbuilder
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		// 规则文件
		//		InputStream route1Stream = new PathMatchingResourcePatternResolver().getResource("classpath:drools/SimpleDemo.xls").getInputStream();
//		Resource resourceFile = ResourceFactory.newInputStreamResource(route1Stream, "UTF-8");
		Resource resourceFile = ResourceFactory.newClassPathResource("drools/SimpleDemo.drl","UTF-8");


		kbuilder.add(resourceFile, ResourceType.DRL);

		// 检查KnowledgeBuilder构建结果
		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors().toString());
		}

		// 创建kbase实例
//		InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		// kbase 额外的选项配置
		KieBaseConfiguration kbaseConf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
		kbaseConf.setOption(MBeansOption.ENABLED);
		kbaseConf.setOption(EventProcessingOption.STREAM);
		InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kbaseConf);

		// kbase添加规则
		kbase.addPackages(kbuilder.getKnowledgePackages());

		// 通过kbase获取session
		StatelessKieSession ksession = kbase.newStatelessKieSession();

		// 传入参数执行规则
		HashMap<String, String> keys = new HashMap<String, String>();
		keys.put("key1", "a");
		keys.put("key2", "b");
		ksession.execute(keys); // 只有一个参数
		System.out.println(keys);

		System.out.println("---------------end------------------------");
	}

}
