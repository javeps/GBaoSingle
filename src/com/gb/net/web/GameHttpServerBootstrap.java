package com.gb.net.web;

import io.netty.channel.EventLoopGroup;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.third.netty4.socket.N4BootstrapSocketServer;

@SuppressWarnings({ "rawtypes" })
public class GameHttpServerBootstrap {

	static Log log = LogFactory.getLog(GameHttpServerBootstrap.class);

	static Map map = null;

	static public void start(String host, int port) throws Exception {
		boolean nodelay = true;
		boolean alive = false;
		GameHttpServerInitializer childHandler = new GameHttpServerInitializer();
		map = N4BootstrapSocketServer.startSync(host, port, nodelay, alive,
				childHandler);
	}

	static public void shutDownNet() {
		if (map != null) {
			EventLoopGroup parentGroup = (EventLoopGroup) map
					.get("parentGroup");
			EventLoopGroup childGroup = (EventLoopGroup) map.get("childGroup");
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
		}
	}
}