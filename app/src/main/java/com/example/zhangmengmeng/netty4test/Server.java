package com.example.zhangmengmeng.netty4test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/8/22
 * Email:65489469@qq.com
 */
public class Server {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel ch) {

                    ch.pipeline().addLast("decoder",new StringDecoder());//解码器
                    ch.pipeline().addLast("encoder", new StringEncoder());//编码器
                    ch.pipeline().addLast("handler",new ServerHandler());//处理逻辑业务
                }
            });

            // 服务器绑定端口监听
            b.bind(8989).sync().channel().closeFuture().sync();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
