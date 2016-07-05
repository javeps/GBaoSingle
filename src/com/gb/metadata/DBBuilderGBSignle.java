package com.gb.metadata;

import javax.sql.DataSource;

import com.bowlong.sql.freemarker.SK_Generate;
import com.bowlong.sql.freemarker.decode.SK_MysqlType;
import com.gb.content.AppContext;

@SuppressWarnings("rawtypes")
public class DBBuilderGBSignle {
	public static void main(String[] args) throws Exception {
		buildEntityByDesign();
		// buildEntityByCfg();
		System.exit(1);
	}

	static void buildEntityByDesign() throws Exception {
		String path = "src/com/gb/db2";// 实体保存的位置
		String cfgPath = ""; // "src/com/monster/metadata/template/"
		DataSource ds = AppContext.dsDesign();
		Class clazz = AppContext.class;// 取得连接的getConnection
		SK_Generate.runByMySql(ds, clazz, path, cfgPath, false);
		SK_Generate.runJedisCacheClear(ds, clazz, path, cfgPath,
				new SK_MysqlType(), "player");
	}

	static void buildEntityByCfg() throws Exception {
		DataSource dsCfg = AppContext.dsDesign();
		Class clazz = AppContext.class;// 取得连接的getConnection
		SK_Generate.runByMySql(dsCfg, clazz, "src/com/gb/db", "", true);
	}
}
