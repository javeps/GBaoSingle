package com.gb.proto;

import static com.bowlong.net.proto.gen.B2G.DATA;
import static com.bowlong.net.proto.gen.B2G.SERVER;

import java.util.List;

import com.bowlong.io.FileEx;
import com.bowlong.net.proto.gen.B2Class;
import com.bowlong.net.proto.gen.B2Field;
import com.bowlong.net.proto.gen.B2Method;
import com.bowlong.net.proto.gen.B2Param;
import com.bowlong.net.proto.gen.Bio2GJava4LuaStr;
import com.bowlong.net.proto.gen.Bio2GLuaStr;

@B2Class(namespace = "web_disp")
public class BuilderGBaoSng {
	public static void main(String[] args) throws Exception {
		Class<?> c = BuilderGBaoSng.class;
		boolean src = FileEx.exists("src");
		Bio2GJava4LuaStr.b2g(c, src, false);
		Bio2GLuaStr.b2g(c, src);
	}

	@B2Class(type = DATA, remark = "返回值")
	class ReturnStatus {
		public int succ;
		public String msg;
	}

	@B2Class(type = DATA, remark = "字符串")
	class NStr {
		public String val;
	}

	@B2Class(type = DATA, remark = "int对象")
	class NBool {
		boolean val;
	}

	@B2Class(type = DATA, remark = "bytes对象")
	class NBytes {
		byte[] buff;
	}

	@B2Class(type = DATA, remark = "聊天对象")
	class NChat {
		@B2Field(remark = "聊天类型")
		int type;
		@B2Field(remark = "说话人标识")
		int fpcid;
		@B2Field(remark = "说话人名称")
		String fpname;
		@B2Field(remark = "说话内容")
		String content;
		@B2Field(remark = "创建时间long")
		long creattime;
		@B2Field(remark = "创建时间Str")
		String creattimeStr;
	}

	@B2Class(type = DATA, remark = "聊天对象s")
	class NChats {
		List<NChat> list;
	}

	@B2Class(type = DATA, remark = "邮件")
	class NEmail {
		@B2Field(remark = "标题")
		String title;
		@B2Field(remark = "内容")
		String cont;
		@B2Field(remark = "是否已读")
		boolean isRead;
		@B2Field(remark = "是否已经领取奖励")
		boolean isReceive;
		@B2Field(remark = "奖励[{tpGet,tpId,tpVal}]")
		String awardJson;
		@B2Field(remark = "创建时间long")
		long creattime;
		@B2Field(remark = "有效时间long")
		long validtime;
	}

	@B2Class(type = DATA, remark = "邮件列表")
	class NEmails {
		List<NEmail> list;
	}

	@B2Class(type = DATA, remark = "排行榜")
	class NRank {
		@B2Field(remark = "排名")
		int index;
		@B2Field(remark = "1sword,2wheel")
		int type;
		@B2Field(remark = "登录唯一标识")
		String unqid;
		@B2Field(remark = "角色名")
		String pname;
		@B2Field(remark = "战斗力")
		int sword;
		@B2Field(remark = "无尽循环最大次数")
		int wheel;
	}

	@B2Class(type = DATA, remark = "排行榜列表")
	class NRanks {
		List<NRank> list;
	}

	@B2Class(type = SERVER)
	interface GBSngGmSvI {
		// ======== 服务器，响应客户端请求
		@B2Method(type = SERVER, params = { "unqid", "uuid", "phone" }, remark = "记录电话号码")
		ReturnStatus recordPhone(String unqid, String uuid, String phone);

		// ======== 服务器，响应客户端请求
		@B2Method(type = SERVER, params = { "unqid", "uuid", "nbl", "nname" }, remark = "判断是否初始化")
		ReturnStatus isInitSngByHttp(String unqid, String uuid,
				@B2Param(oType = "NBool", isOut = true) NBool nbl,
				@B2Param(oType = "NStr", isOut = true) NStr nname);

		@B2Method(type = SERVER, params = { "nrndName" }, remark = "随机取聊天名")
		ReturnStatus rndPnameByHttp(
				@B2Param(oType = "NStr", isOut = true) NStr nrndName);

		@B2Method(type = SERVER, params = { "unqid", "newName" }, remark = "设置聊天名")
		ReturnStatus setPnameByHttp(String unqid, String newName);

		@B2Method(type = SERVER, params = { "unqid", "cont", "nchats" }, remark = "发送聊天")
		ReturnStatus sendChatByHttp(String unqid, String cont,
				@B2Param(oType = "NChats", isOut = true) NChats nchats);

		@B2Method(type = SERVER, params = { "unqid", "nchats" }, remark = "取得聊天内容集合")
		ReturnStatus getChatsByHttp(String unqid,
				@B2Param(oType = "NChats", isOut = true) NChats nchats);

		@B2Method(type = SERVER, params = { "unqid", "uuid", "btPl", "btHero",
				"btPart", "btProp", "btNpc", "btEmail", "chnStr", "chnSub",
				"fight4hero", "fight4part" }, remark = "同步数据到服务器")
		ReturnStatus sync2Game(String unqid, String uuid, byte[] btPl,
				byte[] btHero, byte[] btPart, byte[] btProp, byte[] btNpc,
				byte[] btEmail, String chnStr, String chnSub, int fight4hero,
				int fight4part);

		@B2Method(type = SERVER, params = { "unqid", "uuid", "nbtPl",
				"nbtHero", "nbtPart", "nbtProp", "nbtNpc", "nbtEmail" }, remark = "同步数据到本地")
		ReturnStatus sync2Local(String unqid, String uuid,
				@B2Param(oType = "NBytes", isOut = true) NBytes nbtPl,
				@B2Param(oType = "NBytes", isOut = true) NBytes nbtHero,
				@B2Param(oType = "NBytes", isOut = true) NBytes nbtPart,
				@B2Param(oType = "NBytes", isOut = true) NBytes nbtProp,
				@B2Param(oType = "NBytes", isOut = true) NBytes nbtNpc,
				@B2Param(oType = "NBytes", isOut = true) NBytes nbtEmail);

		@B2Method(type = SERVER, params = { "unqid", "lasttime", "nemails" }, remark = "取得邮件列表")
		ReturnStatus getNEmals(String unqid, long lasttime,
				@B2Param(oType = "NEmails", isOut = true) NEmails nemails);

		@B2Method(type = SERVER, params = { "unqid", "type", "nrnkSelf",
				"nrnks" }, remark = "取得排行榜列表")
		ReturnStatus getNRanks(String unqid, int type,
				@B2Param(oType = "NRank", isOut = true) NRank nrnkSelf,
				@B2Param(oType = "NRanks", isOut = true) NRanks nrnks);

		@B2Method(type = SERVER, params = { "uuid", "device", "error" }, remark = "提交错误问题")
		ReturnStatus error4Lua(String uuid, String device, String error);

		@B2Method(type = SERVER, params = { "timeStr", "nbl" }, remark = "判断是否是当天")
		ReturnStatus verifySytTime(String timeStr,
				@B2Param(oType = "NBool", isOut = true) NBool nbl);

		@B2Method(type = SERVER, params = { "dayIn", "nbl" }, remark = "国庆7天签到的验证")
		ReturnStatus verifySign4GuoQin(int dayIn,
				@B2Param(oType = "NBool", isOut = true) NBool nbl);

		// ======== 服务器，推送给客户端信息
		// @B2Method(type = CLIENT, params = {}, remark = "主动下行状态信息")
		// ReturnStatus notifyStatus();
		//
		// @B2Method(type = CLIENT, params = { "npl" }, remark = "主动下行角色信息")
		// ReturnStatus notifyPlayer(
		// @B2Param(oType = "NPlayer", isOut = true) NPlayer npl);
	}
}
