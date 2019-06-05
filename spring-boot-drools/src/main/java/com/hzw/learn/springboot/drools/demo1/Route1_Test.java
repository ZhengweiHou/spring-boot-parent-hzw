package com.hzw.learn.springboot.drools.demo1;

import java.io.InputStream;
import java.util.HashMap;

import org.drools.core.impl.InternalKnowledgeBase;
import org.drools.core.impl.KnowledgeBaseFactory;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.hzw.learn.springboot.drools.Message;

@Component
public class Route1_Test {
	public void testExcel() throws Exception {
		System.out.println("---------------begin------------------------");

		InputStream route1Stream = new PathMatchingResourcePatternResolver().getResource("classpath:drools/route1.xls")
				.getInputStream();

		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

//		kbuilder.add(ResourceFactory.newInputStreamResource(route1Stream, "UTF-8"), ResourceType.DTABLE);

		///////////
		DecisionTableConfiguration dtconf = KnowledgeBuilderFactory.newDecisionTableConfiguration();
		dtconf.setInputType(DecisionTableInputType.XLS);
		dtconf.setWorksheetName("S1");
		
		kbuilder.add(ResourceFactory.newInputStreamResource(route1Stream, "UTF-8"), ResourceType.DTABLE,dtconf);

		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors().toString());
		}

		///////////////
		InternalKnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

		kbase.addPackages(kbuilder.getKnowledgePackages());

		StatelessKieSession ksession = kbase.newStatelessKieSession();

		Message keys = new Message();
//		HashMap<String, String> keys = new HashMap<String, String>();
		keys.put("key1", "a");
		keys.put("key2", "b");

		ksession.execute(keys);
		System.out.println(keys);

		System.out.println("---------------end------------------------");
	}

}
