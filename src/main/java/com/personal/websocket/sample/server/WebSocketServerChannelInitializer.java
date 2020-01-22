package com.personal.websocket.sample.server;

import com.personal.websocket.sample.server.handler.MessageToTextFrameHandler;
import com.personal.websocket.sample.server.handler.TextFrameToMessageHandler;
import com.personal.websocket.sample.server.handler.WebSocketHandler;
import com.personal.websocket.sample.test.handler.ProcessMessageHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class WebSocketServerChannelInitializer extends ChannelInitializer<SocketChannel> {
	
	private WebSocketServer server;
	
	public WebSocketServerChannelInitializer(WebSocketServer server) {
		this.server = server;
	}

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		if (server.getSslContext() != null) {
            pipeline.addLast(server.getSslContext().newHandler(ch.alloc()));
        }
		pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new WebSocketHandler(server));
        pipeline.addLast(new MessageToTextFrameHandler());
        pipeline.addLast(new TextFrameToMessageHandler());
        pipeline.addLast(new ProcessMessageHandler());
	}

}
