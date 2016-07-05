package com.gb.logic.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.bowlong.net.TcpChannel;
import com.bowlong.util.DateEx;
import com.gb.content.Svc;

public class Session implements Serializable {

	private static final long serialVersionUID = 1L;
	static private Log log = LogFactory.getLog(Session.class);

	static final long liveTime = 20 * DateEx.TIME_MINUTE;

	private long sessionId;
	private int pcid; // unid唯一标识
	private Map<Integer, String> notifyData;
	
	// session 过期时间
	private long timeOverdue;
	// 每个session 与 chn之间绑定一一对应关系
	private TcpChannel chn;

	public long getSessionId() {
		return sessionId;
	}

	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}

	public int getPcid() {
		return pcid;
	}

	public void setPcid(int unid) {
		this.pcid = unid;
	}

	public Map<Integer, String> getNotifyData() {
		return notifyData;
	}

	public void setNotifyData(Map<Integer, String> notifyData) {
		this.notifyData = notifyData;
	}
	
	public long getTimeOverdue() {
		return timeOverdue;
	}

	public void setTimeOverdue(long timeOverdue) {
		this.timeOverdue = timeOverdue;
	}

	public void changeTimeOverdue() {
		long now_time = System.currentTimeMillis();
		this.timeOverdue = now_time + liveTime;
	}

	public TcpChannel getChn() {
		return chn;
	}

	public void setChn(TcpChannel chn) {
		this.chn = chn;
	}

//	public Session(long sessionId, Player pl, TcpChannel rs) {
//		super();
//		this.sessionId = sessionId;
//		this.pl = pl;
//		this.notifyData = new HashMap<Integer, String>();
//		if (this.pl != null) {
//			this.pcid = this.pl.getPcid();
//		}
//		long now_time = System.currentTimeMillis();
//		this.timeOverdue = now_time + liveTime;
//		this.chn = rs;
//	}
//
//	public Session(long sessionId, int unid, TcpChannel rs) {
//		super();
//		this.sessionId = sessionId;
//		this.pcid = unid;
//		this.notifyData = new HashMap<Integer, String>();
//		if (this.sessionId != 0 && this.pcid != 0) {
//			this.pl = PlayerEntity.getByKey(this.pcid);
//		}
//		long now_time = System.currentTimeMillis();
//		this.timeOverdue = now_time + liveTime;
//		this.chn = rs;
//	}

	/**
	 * 取得该消息类型的列表标识集合 这个用于等待通知，不是及时通知
	 * 
	 * @param code
	 *            消息类型(聊天，邮件...)
	 * @return
	 */
	public List<Integer> getNotifyList(int code) {
		try {
			String data = this.notifyData.get(code);
			if (Svc.isEmpty(data))
				return null;
			List<Integer> ids = new ArrayList<Integer>();
			if (data != null) {
				ids = JSON.parseArray(data, Integer.class);
			}
			return ids;
		} catch (Exception e) {
			log.info(Svc.e2s(e));
		}
		return null;
	}

	/**
	 * 添加通知消息 这个用于等待通知，不是及时通知
	 * 
	 * @param code
	 *            消息类型(聊天，邮件...)
	 * @param unid
	 *            该类型下对象的唯一标识
	 */
	public void addNotify(int code, int unid) {
		if (unid == 0)
			return;
		try {
			List<Integer> ids = getNotifyList(code);
			if (Svc.isEmpty(ids))
				ids = new ArrayList<Integer>();
			boolean isHas = ids.contains(unid);
			if (isHas)
				return;
			ids.add(unid);
			String data = JSON.toJSONString(ids);
			this.notifyData.put(code, data);
		} catch (Exception e) {
			log.info(Svc.e2s(e));
		}
	}

	public void emptyNotify(int code) {
		this.notifyData.put(code, "");
	}
}
