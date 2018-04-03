package com.imooc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author ChenXb
 *               程序的入口  负责启动应用
 * 2018年4月3日
 */
public class Main {
	public static void main(String[] args) {
		EventLoopGroup bossGroup=new NioEventLoopGroup();
		EventLoopGroup workGroup=new NioEventLoopGroup();
		try {
			ServerBootstrap b=new ServerBootstrap();
			b.group(bossGroup,workGroup);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(new MyWebSocketChannelHandler());
			System.out.println("服务端开启等待客户端链接...");
			Channel channel = b.bind(8888).sync().channel();
			channel.closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//优雅的退出程序
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
}
