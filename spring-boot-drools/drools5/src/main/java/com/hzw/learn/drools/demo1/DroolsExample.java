package com.hzw.learn.drools.demo1;

/**
 * @ClassName DroolsExample
 * @Description TODO
 * @Author houzw
 * @Date 2024/11/18
 **/
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class DroolsExample {
    public static void main(String[] args) {
        try {
            // 创建规则构建器
            KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
            kbuilder.add(ResourceFactory.newClassPathResource("drools/T1.drl"), org.drools.builder.ResourceType.DRL);

            // 检查规则文件中的语法错误
            KnowledgeBuilderErrors errors = kbuilder.getErrors();
            if (errors.size() > 0) {
                for (KnowledgeBuilderError error : errors) {
                    System.err.println("Error in rule: " + error.getMessage());
                }
                throw new IllegalArgumentException("Could not parse knowledge.");
            }

            // 创建知识库
            KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
            kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

            // 创建会话
            StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

            // 插入数据
            Order order = new Order(150);
            ksession.insert(order);

            // 执行规则
            ksession.fireAllRules();

            System.out.println("Final order: " + order);

            // 关闭会话
            ksession.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
