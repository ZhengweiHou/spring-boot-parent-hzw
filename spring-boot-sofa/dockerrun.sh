nacos_host="192.168.104.139:8848"
nacos_namespace="test"
vhost="192.168.104.139"
vport="12200"

docker run --rm -v /home/houzw/document/git-rep/spring-boot-parent-hzw/spring-boot-sofa/target/spring-boot-sofa-0.0.1-SNAPSHOT.jar:/app.jar -p ${vport}:12200 cenots7_jdk8_202:v1 java -jar -Dnacos.host=${nacos_host} -Dnacos.namespace=${nacos_namespace} -Dvhost=${vhost} /app.jar
