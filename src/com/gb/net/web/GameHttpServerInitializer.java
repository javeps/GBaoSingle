package com.gb.net.web;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class GameHttpServerInitializer extends
		ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel chn) throws Exception {
		ChannelPipeline pipeline = chn.pipeline();
		// 以("\n")为结尾分割的 解码器
		// pipeline.addLast("framer", new
		// DelimiterBasedFrameDecoder(8192,Delimiters.lineDelimiter()));

		// 字符串解码 和 编码
		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("encoder", new HttpResponseEncoder());

		// 压缩
		pipeline.addLast("deflater", new HttpContentCompressor());

		// HttpObjectAggregator会把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse。
		int maxContentLength = 1024 * 1024 * 3;
		pipeline.addLast("aggregator", new HttpObjectAggregator(maxContentLength));

		// 自己的逻辑Handler
		pipeline.addLast("handler", new GameHttpServerHandler());
	}
}
