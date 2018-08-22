package com.example.zhangmengmeng.netty4test;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2018/8/22
 * Email:65489469@qq.com
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 读取客户端发来的数据
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("server received data :" + s);
        channelHandlerContext.writeAndFlush(s);//写回数据
    }

    /**
     * 异常捕获
     */
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {
        cause.printStackTrace();//捕捉异常信息
        ctx.close();//出现异常时关闭channel
    }

}
