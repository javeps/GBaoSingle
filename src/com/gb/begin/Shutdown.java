package com.gb.begin;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.sql.freemarker.JedisTookits;
import com.gb.content.AppContext;
import com.gb.net.web.GameHttpServerBootstrap;
import com.gb.toolkits.UtileTools;

//SHUTDOWN
public class Shutdown extends Thread {
	static Log log = LogFactory.getLog(Shutdown.class);

	public ServerSocket ssocket = null;

	public Shutdown(int port) throws Exception {
		InetAddress addr = InetAddress.getByName("127.0.0.1");

		try { // 关闭其他进程
			new Socket(addr, port);
			Thread.sleep(1000);
		} catch (Exception e) {
		}

		ssocket = new ServerSocket(port, 2, addr);
	}

	@Override
	public void run() {
		try {
			// accept默认会阻塞进程，直到有一个客户连接建立后返回，它返回的是一个新可用的套接字，这个套接字是连接套接字
			ssocket.accept();
			beforeShutDown();
			System.exit(1);
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
	}

	// 在下线之前
	void beforeShutDown() {
		try {
			GameHttpServerBootstrap.shutDownNet();
			// DbSave.saveAll();
			AppContext.closeJedisPool();
			JedisTookits.closeJedisPool();
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
	}
}