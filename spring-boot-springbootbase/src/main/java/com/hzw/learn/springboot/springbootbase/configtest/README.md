# Spring Boot配置详解
## 配置文件
    `src/main/resources`目录是Spring Boot的配置目录，Spring Boot的默认配置文件位置为
`src/main/resources/application.properties`。Spring Boot应用的配置内容都可以集中在
该文件中，根据pom引入不同的Starter模块，可以在这里定义不同的配置信息。如：web模块的服务端口
`server.port=8080`;应用名称`spring.application.name=zhw-springboot-demo`<br/>
    