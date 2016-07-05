package com.gb.logic.impl;

import gen_b2g.web_disp.GBSngGmSvI;
import gen_b2g.web_disp.bean.NBool;
import gen_b2g.web_disp.bean.NBytes;
import gen_b2g.web_disp.bean.NChats;
import gen_b2g.web_disp.bean.NEmails;
import gen_b2g.web_disp.bean.NRank;
import gen_b2g.web_disp.bean.NRanks;
import gen_b2g.web_disp.bean.NStr;
import gen_b2g.web_disp.bean.ReturnStatus;

import com.bowlong.net.TcpChannel;
import com.gb.logic.opt.Logical;
import com.gb.logic.opt.LogicalEmail;
import com.gb.logic.opt.LogicalRank;

public class GBSngGmImpl extends GBSngGmSvI {
	@Override
	public void onExcept(TcpChannel chn, String method, Exception e,
			ReturnStatus ret) throws Exception {
		Logical.onExcept(chn, method, e, ret);
	}

	@Override
	public void onIsInitSngByHttp(TcpChannel chn, String unqid, String uuid,
			NBool nbl, NStr nname, ReturnStatus ret) throws Exception {
		Logical.onIsInitSngByHttp(chn, unqid, uuid, nbl, nname, ret);
	}

	@Override
	public void onRndPnameByHttp(TcpChannel chn, NStr nrndName, ReturnStatus ret)
			throws Exception {
		Logical.onRndPnameByHttp(chn, nrndName, ret);
	}

	@Override
	public void onSetPnameByHttp(TcpChannel chn, String unqid, String newName,
			ReturnStatus ret) throws Exception {
		Logical.onSetPnameByHttp(chn, unqid, newName, ret);
	}

	@Override
	public void onSendChatByHttp(TcpChannel chn, String unqid, String cont,
			NChats nchats, ReturnStatus ret) throws Exception {
		Logical.onSendChatByHttp(chn, unqid, cont, nchats, ret);
	}

	@Override
	public void onGetChatsByHttp(TcpChannel chn, String unqid, NChats nchats,
			ReturnStatus ret) throws Exception {
		Logical.onGetChatsByHttp(chn, unqid, nchats, ret);
	}

	@Override
	public void onSync2Game(TcpChannel chn, String unqid, String uuid,
			byte[] btPl, byte[] btHero, byte[] btPart, byte[] btProp,
			byte[] btNpc, byte[] btEmail, String chnStr, String chnSub,
			int fight4hero, int fight4part, ReturnStatus ret) throws Exception {
		Logical.onSync2Game(chn, unqid, uuid, btPl, btHero, btPart, btProp,
				btNpc, btEmail, chnStr, chnSub, fight4hero, fight4part, ret);
	}

	@Override
	public void onSync2Local(TcpChannel chn, String unqid, String uuid,
			NBytes nbtPl, NBytes nbtHero, NBytes nbtPart, NBytes nbtProp,
			NBytes nbtNpc, NBytes nbtEmail, ReturnStatus ret) throws Exception {
		Logical.onSync2Local(chn, unqid, uuid, nbtPl, nbtHero, nbtPart,
				nbtProp, nbtNpc, nbtEmail, ret);
	}

	@Override
	public void onGetNEmals(TcpChannel chn, String unqid, long lasttime,
			NEmails nemails, ReturnStatus ret) throws Exception {
		LogicalEmail.onGetNEmals(chn, unqid, lasttime, nemails, ret);
	}

	@Override
	public void onGetNRanks(TcpChannel chn, String unqid, int type,
			NRank nrnkSelf, NRanks nrnks, ReturnStatus ret) throws Exception {
		LogicalRank.onGetNRanks(chn, unqid, type, nrnkSelf, nrnks, ret);
	}

	@Override
	public void onRecordPhone(TcpChannel chn, String unqid, String uuid,
			String phone, ReturnStatus ret) throws Exception {
		Logical.onRecordPhone(chn, unqid, uuid, phone, ret);
	}

	@Override
	public void onError4Lua(TcpChannel chn, String uuid, String device,
			String error, ReturnStatus ret) throws Exception {
		Logical.onError4Lua(chn, uuid, device, error, ret);
	}

	@Override
	public void onVerifySytTime(TcpChannel chn, String timeStr, NBool nbl,
			ReturnStatus ret) throws Exception {
		Logical.onVerifySytTime(chn, timeStr, nbl, ret);
	}

	@Override
	public void onVerifySign4GuoQin(TcpChannel chn, int dayIn, NBool nbl,
			ReturnStatus ret) throws Exception {
		Logical.onVerifySign4GuoQin(chn, dayIn, nbl, ret);
	}

}
