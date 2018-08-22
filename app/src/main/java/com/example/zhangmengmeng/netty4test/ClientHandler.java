package com.example.zhangmengmeng.netty4test;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/8/22
 * Email:65489469@qq.com
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 接收服务器发来的数据
     **/
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("client received data "+s);
    }

    /**
     * 连接服务器时调用
     **/
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    /**
     * 捕捉到异常
     **/
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
