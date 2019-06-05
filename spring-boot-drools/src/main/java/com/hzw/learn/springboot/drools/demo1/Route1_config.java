//package com.hzw.learn.springboot.drools.demo1;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//import org.drools.builder.KnowledgeBuilder;
//import org.drools.builder.KnowledgeBuilderFactory;
//import org.drools.builder.ResourceType;
//import org.drools.io.ResourceFactory;
//import org.kie.api.KieBase;
//import org.kie.api.KieServices;
//import org.kie.api.builder.KieBuilder;
//import org.kie.api.builder.KieFileSystem;
//import org.kie.api.builder.KieModule;
//import org.kie.api.builder.KieRepository;
//import org.kie.api.builder.ReleaseId;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//@Configuration
//public class Route1_config {
//	
//	  private static final String RULES_PATH = "drools/";
//	    
//	    @Bean
//	    @ConditionalOnMissingBean(KieFileSystem.class)
//	    public KieFileSystem kieFileSystem() throws IOException {
//	        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
//	        InputStream route1Stream = new PathMatchingResourcePatternResolver().getResource("classpath*:drools/route1.xls").getInputStream();
//	        
//
//	        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//	        kbuilder.add(ResourceFactory.newInputStreamResource(route1Stream), ResourceType.DTABLE);
//	        
//	        kieFileSystem.write(ResourceFactory.newInputStreamResource(route1Stream));
//	        
//	        return kieFileSystem;
//	    }
//
////	    private Resource[] getRuleFiles() throws IOException {
////	        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
////	        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
////	    }
//	    
//	    @Bean
//	    @ConditionalOnMissingBean(KieContainer.class)
//	    public KieContainer kieContainer() throws IOException {
//	        final KieRepository kieRepository = getKieServices().getRepository();
//	        
//	        kieRepository.addKieModule(new KieModule() {
//	            public ReleaseId getReleaseId() {
//	                return kieRepository.getDefaultReleaseId();
//	            }
//	        });
//	        
//	        KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
//	        kieBuilder.buildAll();
//
//	        KieContainer kieContainer=getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
//
//	        return kieContainer;
//	    }
//	    
//	    private KieServices getKieServices() {
//	        return KieServices.Factory.get();
//	    }
//	    
//	    @Bean
//	    @ConditionalOnMissingBean(KieBase.class)
//	    public KieBase kieBase() throws IOException {
//	        return kieContainer().getKieBase();
//	    }
//	    
//	    @Bean
//	    @ConditionalOnMissingBean(KieSession.class)
//	    public KieSession kieSession() throws IOException {
//	        return kieContainer().newKieSession();
//	    }
//
////	    @Bean
////	    @ConditionalOnMissingBean(KModuleBeanFactoryPostProcessor.class)
////	    public KModuleBeanFactoryPostProcessor kiePostProcessor() {
////	        return new KModuleBeanFactoryPostProcessor();
////	    }
//	
//}
