package com.gb.net.web;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.PStr;
import com.bowlong.util.DateEx;
import com.gb.content.Svc;

public class GameHttpServerHandler extends ChannelInboundHandlerAdapter {

	static Log log = LogFactory.getLog(GameHttpServerHandler.class);

	static ExecutorService pool = Executors.newCachedThreadPool();

	static final GameHttpServerReps gmHttpSReps = GameHttpServerReps
			.getInstance();

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		final Channel chn = ctx.channel();
		final Object obj = msg;
		synchronized (chn) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					execute(chn, obj);
				}
			});
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		super.exceptionCaught(ctx, cause);
	}

	// 执行 函数
	void execute(final Channel chn, final Object msg) {
		try {
			long t1 = System.currentTimeMillis();

			String pv = gmHttpSReps.dispIssue(chn, msg);

			long t2 = System.currentTimeMillis();
			long dt = t2 - t1;
			String strStarUp = "";
			if (dt > DateEx.TIME_SECOND) {
				dt = (long) Math.ceil((double) dt / DateEx.TIME_SECOND);
				strStarUp = PStr.str(dt, " 秒(s)");
			} else {
				strStarUp = PStr.str(dt, " 毫秒(ms)");
			}

			String info = PStr.str("GameHttpServerHandler.execute===time : ", strStarUp, ",pv : ", pv);
			System.out.println(info);
			// log.info(info);
		} catch (Exception e) {
			log.error(Svc.e2s(e));
		}
	}
}
