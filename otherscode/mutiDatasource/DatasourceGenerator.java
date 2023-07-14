package cn.sccfc.zacps.ops.jdbc.datasource;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import cn.sccfc.front.common.utils.json.JsonUtil;
import cn.sccfc.zacps.ops.jdbc.mapper.ops.OpsDatasourceMapper;
import cn.sccfc.zacps.ops.jdbc.model.ops.OpsDatasource;

@Component
public class DatasourceGenerator {

	@Autowired
	OpsDatasourceMapper mapper;
	@Resource(name="datasourceMap")
	Map<Object, Object> datasourceMap;
	@Autowired
	DynamicDataSource dynamicDataSource;

	@PostConstruct
	public void initDatasource() throws Exception {
		List<OpsDatasource> datasourceList = mapper.selectList(null);
		for (OpsDatasource datasource : datasourceList) {
			refreshDatasource(datasource);
		}
	}

	public void refreshAll() throws Exception {
		initDatasource();
	}

	public void refreshDatasource(String name) throws Exception {
		OpsDatasource datasource = new OpsDatasource();
		datasource.setName(name);
		QueryWrapper<OpsDatasource> queryWrapper = Wrappers.query(datasource);
		datasource = mapper.selectOne(queryWrapper);
		refreshDatasource(datasource);
	}

	public void refreshDatasource(OpsDatasource datasource) throws Exception {
		if (datasource != null) {
			Map<String, Object> datasourceConfig = JsonUtil.obj2Map(datasource);
			datasourceConfig.put("validationQuery", "select 1");
			datasourceConfig.put("testWhileIdle", "true");
			datasourceConfig.put("testOnBorrow", "false");
			DataSource datasourceObj = DruidDataSourceFactory.createDataSource(datasourceConfig);
			datasourceMap.put(datasource.getName(), datasourceObj);
			dynamicDataSource.afterPropertiesSet();
		}
	}
	
}
