package com.gb.net.web;

import io.netty.channel.Channel;

import java.io.Serializable;
import java.util.Map;

import com.bowlong.lang.StrEx;
import com.bowlong.net.TcpChannel;
import com.bowlong.third.netty4.httphand.N4HttpResponse;

@SuppressWarnings({ "rawtypes" })
public class GameHttpServerRepsChn implements Serializable, TcpChannel {

	private static final long serialVersionUID = 1L;

	public final Channel chn;
	public Map params;
	public String hostIP;

	public GameHttpServerRepsChn(Channel chn, Map params) {
		super();
		this.chn = chn;
		resetParmets(params);
	}

	public void resetParmets(Map parms) {
		this.params = parms;
		boolean isNullHost = StrEx.isEmpty(this.hostIP);
		if (isNullHost && this.chn != null && this.chn.remoteAddress() != null)
			this.hostIP = this.chn.remoteAddress().toString();
	}

	@Override
	public void close() {
	}

	@Override
	public void send(Map map) throws Exception {
		N4HttpResponse.sendByChunked(chn, map);
	}

	@Override
	public void send(byte[] buf) throws Exception {
		N4HttpResponse.sendByChunked(chn, buf);
	}

}
