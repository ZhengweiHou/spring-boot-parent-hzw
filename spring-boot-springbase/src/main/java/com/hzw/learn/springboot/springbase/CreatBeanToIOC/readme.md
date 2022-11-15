### 往spring容器中创建Bean的方式
- xml <bean>标签
- @ComponentScan + @Service、@Controller、@Repository、@Compont
- **@Import(XXXX.class)**
- **ImportSelector** 接口 selectImports方法返回一个类名数组
- **ImportBeanDefinitionRegistrar** 接口
> ImportSelector和ImportBeanDefinitionRegistrar一般搭配@Import一起使用

- @Bean
- FactoryBean 接口
- SingletonBeanRegistry.registerSingleton() API






