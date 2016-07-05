package com.gb.begin;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.gb.content.AppContext;
import com.gb.content.Svc;
import com.gb.net.web.GameHttpServerBootstrap;
import com.gb.timer.TimerNight;
import com.gb.toolkits.ExpiredLog4j;
import com.gb.toolkits.NameRandom;

public class GBSngBootstrap extends Svc {
	static Log log = LogFactory.getLog(GBSngBootstrap.class);
	public static Date BEGIN = new Date(); // 开服时间

	static String NAME = "gboarpg.game";
	static double VER = 1.0;
	static int TCP_PORT = 1001;
	static int WEB_PORT = 1002;
	static int SHUTDOWNPORT = 8000;

	static final Runtime runtime = Runtime.getRuntime();

	public static void main(String[] args) {

		try {
			long l1 = now();
			String sdate = tFmt(new Date());
			long freeMem1 = runtime.freeMemory();

			// ////////////////////////////////////
			// // 初始化数据
			// NAME = AppContext.getName();
			VER = AppContext.getVer();
			TCP_PORT = AppContext.getGamePortTcp();
			WEB_PORT = AppContext.getGamePortWeb();
			SHUTDOWNPORT = AppContext.getGamePortShutdown();
			// ====end

			loadAll();

			Shutdown shutdown = new Shutdown(SHUTDOWNPORT);
			shutdown.start();
			// =======向入口发送请求，告知已准备就绪
			// GameServerBootstrap.start("", TCP_PORT);
			// Gm2GtBootsLogical.exceTimer();
			GameHttpServerBootstrap.start("", WEB_PORT);
			// =======end
			// ExecutorService executor = Executors.newCachedThreadPool();
			// NioServerBossPool bossPool = new NioServerBossPool(executor, 2);
			// NioWorkerPool workerPool = new NioWorkerPool(executor, 32);
			//
			// ServerBootstrap webBootstrap = new ServerBootstrap(
			// new NioServerSocketChannelFactory(bossPool, workerPool));
			//
			// webBootstrap.setPipelineFactory(new WebPipelineFactory());
			// InetSocketAddress web_addr = new InetSocketAddress(WEB_PORT);
			// webBootstrap.setOption("child.tcpNoDelay", true);
			// webBootstrap.setOption("child.keepAlive", true);
			// webBootstrap.bind(web_addr);
			// 日志清理器
			ExpiredLog4j.beginIt();

			// ////////////////////////////////////
			long freeMem2 = runtime.freeMemory();
			long totalMemory = runtime.totalMemory();

			String sdate2 = tFmt(new Date());
			long l2 = now();
			long df = l2 - l1;

			String strStarUp = "";
			if (df > DateEx.TIME_SECOND) {
				df = (long) Math.ceil((double) df / DateEx.TIME_SECOND);
				strStarUp = df + " 秒(s)";
			} else {
				strStarUp = df + " 毫秒(ms)";
			}

			StringBuffer sb = new StringBuffer();
			sn(sb, "");
			sn(sb, "/////////////////////////////////////////");
			sn(sb, "// Applicatin   :%s v: %.2f", NAME, VER);
			sn(sb, "// WEB_PORT on :%s", WEB_PORT + "");
			sn(sb, "// TCP_PORT on :%s", TCP_PORT + "");
			sn(sb, "// Shutdown  on :%s", SHUTDOWNPORT + "");
			sn(sb, "// GMGT_HOST on :%s", "" + "");
			sn(sb, "// GMGT_TCP_PORT on :%s", "" + "");
			sn(sb, "// used Memory  :%s",
					((freeMem1 - freeMem2) / (1024 * 1024)) + "MB");
			sn(sb, "// free Memory  :%s", ((freeMem2) / (1024 * 1024)) + "MB");
			sn(sb, "// totalMemory  :%s", ((totalMemory) / (1024 * 1024))
					+ "MB");
			sn(sb, "// Start Time   :%s", sdate + "");
			sn(sb, "// startup      :%s", strStarUp);
			sn(sb, "// start time2  :%s", sdate2 + "");
			sn(sb, "/////////////////////////////////////////");
			log.info(sb);

		} catch (Exception e) {
			String sdate = tFmt(new Date());
			StringBuffer sb = new StringBuffer();
			sn(sb, "");
			sn(sb, "/////////////////////////////////////////");
			sn(sb, "// Applicatin   :%s", NAME + "");
			sn(sb, "// Applicatin   :%s v:%.2f", NAME, VER);
			sn(sb, "// WEB_PORT on :%s", WEB_PORT + "");
			sn(sb, "// TCP_PORT on :%s", TCP_PORT + "");
			sn(sb, "// Shutdown  on :%s", SHUTDOWNPORT + "");
			sn(sb, "// GMGT_HOST on :%s", "" + "");
			sn(sb, "// GMGT_TCP_PORT on :%s", "" + "");
			sn(sb, "// Start Time   :%s", sdate + "");
			sn(sb, "// Exception    :%s", e2s(e) + "");
			sn(sb, "/////////////////////////////////////////");
			log.error(sb);
			System.exit(1);
		}
	}

	static void loadAll() throws Exception {
		AppContext.dsData();
		AppContext.loadAll();
		NameRandom.initNames();
		TimerNight.startTimer();
		// TimerNight.getInstance().run();
		// TimerSave.startTimer();
	}
}