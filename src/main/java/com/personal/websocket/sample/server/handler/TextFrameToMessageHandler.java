package com.personal.websocket.sample.server.handler;

import com.google.gson.Gson;
import com.personal.websocket.sample.test.handler.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class TextFrameToMessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {
		Gson gson = new Gson();
		Message msg = gson.fromJson(frame.text(), Message.class);
		ctx.fireChannelRead(msg);
	}

}
