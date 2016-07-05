package com.gb.proto;

import static com.bowlong.net.proto.gen.B2G.DATA;

import com.bowlong.io.FileEx;
import com.bowlong.net.proto.gen.B2Class;
import com.bowlong.net.proto.gen.B2Field;
import com.bowlong.net.proto.gen.Bio2GJava4Lua;
import com.bowlong.net.proto.gen.Bio2GLua;

/**
 * 游戏静态常量
 * 
 * @author canyon
 * @date 2014年10月14日
 */
@B2Class(namespace = "result")
public class GBaoSngFixed {
	// ///////////////////////////////////////////////////
	@B2Class(type = DATA, remark = "所有状态返回值", constant = true)
	class ResultStatus {
		@B2Field(remark = "成功", def = "0")
		int R_Success = 0;
		@B2Field(remark = "异常！", def = "-999")
		int R_Error = 0;
		@B2Field(remark = "名字长度应为2~8个字符! ", def = "-199")
		int R_NameLenthError = 0;
		@B2Field(remark = "角色名已存在! ", def = "-89")
		int R_NameIsHas = 0;
	}

	@B2Class(type = DATA, remark = "公共属性", constant = true)
	class PubAttr {
		@B2Field(remark = "密匙key", def = "gbarpg")
		String signAppKey = "";
	}

	// ///////////////////////////////////////////////////

	public static void main(String[] args) throws Exception {
		Class<?> c = GBaoSngFixed.class;
		boolean src = FileEx.exists("src");
		Bio2GJava4Lua.b2g(c, src);
		// Bio2GCSharp.b2g(c, src);
		Bio2GLua.b2g_globle(c, src);
	}

}
