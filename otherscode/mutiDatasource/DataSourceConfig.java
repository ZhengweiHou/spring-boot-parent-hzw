package cn.sccfc.zacps.ops.jdbc.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import cn.sccfc.zacps.ops.jdbc.datasource.DatabaseType;
import cn.sccfc.zacps.ops.jdbc.datasource.DynamicDataSource;

@Configuration
@ConfigurationProperties(prefix = "system.default")
public class DataSourceConfig {
	
    private Map<String, String> ops;
    
    public void setOps(Map<String, String> ops) {
		this.ops = ops;
	}

    @Bean
    public Map<Object, Object> datasourceMap() throws Exception {
    	Map<Object, Object> datasourceMap = new HashMap<>();
    	return datasourceMap;
    }

    @Bean
    public DynamicDataSource dynamicDataSource(Map<Object, Object> datasourceMap) throws Exception {
    	DataSource opsDataSource = DruidDataSourceFactory.createDataSource(ops);
		datasourceMap.put(DatabaseType.OPS.getDbKey(), opsDataSource);
    	
    	DynamicDataSource dynamicDataSource = new DynamicDataSource();
    	dynamicDataSource.setDefaultTargetDataSource(opsDataSource);
    	dynamicDataSource.setTargetDataSources(datasourceMap);
    	return dynamicDataSource;
    }
    
}
