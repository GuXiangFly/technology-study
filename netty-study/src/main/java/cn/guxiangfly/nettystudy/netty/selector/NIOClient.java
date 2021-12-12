package cn.guxiangfly.nettystudy.netty.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author guxiang02
 * @Date 2021/6/16
 **/
public class  NIOClient {
    public static void main(String[] args) throws IOException {
        //得到一个网路通道
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("因为连接需要事件，客户端不会阻塞，可以做其他工作");
            }
        }
        String str = "hello,guxiangfly";

        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        socketChannel.write(buffer);
        System.in.read();
    }
}
