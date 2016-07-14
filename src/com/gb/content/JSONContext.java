package com.gb.content;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;

import redis.clients.jedis.JedisPool;

import com.alibaba.druid.pool.DruidDataSource;
import com.bowlong.json.MyJson;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class JSONContext extends Svc {
	static Log log = getLog(JSONContext.class);
	protected static final File CONFIG = new File("json/app.json");

	static Map<String, Object> ctx = null;

	public static Map<String, Object> ctx() {
		try {
			if (ctx == null)
				ctx = MyJson.parseGeneric(CONFIG);
		} catch (Exception e) {
			log.error(e2s(e));
		}
		return ctx;
	}

	static Map<String, Object> APP = null;

	public static Map<String, Object> APP() {
		if (APP == null)
			APP = (Map<String, Object>) ctx().get("APP");
		return APP;
	}

	// ============= sql DataSource ==============
	static Map<String, Map<String, Object>> dsMap = null;

	static Map<String, Map<String, Object>> getDSMap() {
		if (dsMap == null) {
			dsMap = (Map<String, Map<String, Object>>) ctx().get("DATASOURCE");
		}
		return dsMap;
	}

	static Map<String, DataSource> dataSourceDesign = null;

	public static Map<String, DataSource> dataSourceDesign() {
		if (dataSourceDesign == null) {
			dataSourceDesign = newMap();
			Map<String, Map<String, Object>> dss = getDSMap();
			Set<Entry<String, Map<String, Object>>> entrys = dss.entrySet();
			for (Entry<String, Map<String, Object>> entry : entrys) {
				if (!(entry.getKey() instanceof String)
						|| !(entry.getValue() instanceof Map))
					continue;
				String key = entry.getKey();
				Map<String, Object> map = entry.getValue();
				if ("ds".equalsIgnoreCase(key) || "log".equalsIgnoreCase(key))
					continue;
				DruidDataSource ds = dataSource(map);
				dataSourceDesign.put(key, ds);
			}
		}
		return dataSourceDesign;
	}

	static Map<String, DataSource> dataSource = null;

	public static Map<String, DataSource> dataSource() {
		if (dataSource == null) {
			dataSource = newMap();
			Map<String, Map<String, Object>> dss = getDSMap();
			Set<Entry<String, Map<String, Object>>> entrys = dss.entrySet();
			for (Entry<String, Map<String, Object>> entry : entrys) {
				if (!(entry.getKey() instanceof String)
						|| !(entry.getValue() instanceof Map))
					continue;
				String key = entry.getKey();
				Map<String, Object> map = entry.getValue();
				if ("design".equalsIgnoreCase(key)
						|| "cfg".equalsIgnoreCase(key))
					continue;
				DruidDataSource ds = dataSource(map);
				dataSource.put(key, ds);
			}
		}
		return dataSource;
	}

	@SuppressWarnings("deprecation")
	public static final DruidDataSource dataSource(Map map) {
		DruidDataSource ds = new DruidDataSource();
		String driverClassName = getString(map, "driverClassName");
		String url = getString(map, "url");
		String username = getString(map, "username");
		String password = getString(map, "password");
		int maxActive = getInt(map, "maxActive");
		int maxIdle = getInt(map, "maxIdle");
		int initialSize = getInt(map, "initialSize");
		int maxWait = getInt(map, "maxWait");
		boolean removeAbandoned = getBool(map, "removeAbandoned");
		int removeAbandonedTimeout = getInt(map, "removeAbandonedTimeout");

		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setMaxActive(maxActive);
		ds.setMaxIdle(maxIdle);
		ds.setInitialSize(initialSize);
		ds.setMaxWait(maxWait);
		ds.setRemoveAbandoned(removeAbandoned);
		ds.setRemoveAbandonedTimeout(removeAbandonedTimeout);
		// try {
		// ds.getConnection().getMetaData();
		// // System.out.println(ds.getNumActive() + "-" + ds.getNumIdle());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return ds;
	}

	public static JedisPool getJedisPool() {
		return getJedisPool((Map<String, Object>) (ctx().get("REDIS")));
	}

	// ////////////////////////////////////////////////////////

}
