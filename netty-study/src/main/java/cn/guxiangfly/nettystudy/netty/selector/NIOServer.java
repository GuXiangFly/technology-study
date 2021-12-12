package cn.guxiangfly.nettystudy.netty.selector;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author guxiang02
 * @Date 2020/10/12
 **/
public class NIOServer {
    public static void main(String[] args) throws Exception{

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        Selector selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            //selector等待1秒，如果没有连接事件，返回
            if (selector.select(1000) == 0 ){
                System.out.println("服务器等待了1s，无连接");
                continue;
            }
            //如果返回的>0,表示已经获取到关注的事件了
            //selector.selectedKeys()代表是关注的事件的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()){  //如果是OP_ACCEPT,代表有新客户端连接
                    //给连接的客户端分配一个channel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("客户端连接成功 生成了一个socketChannel");
                    //给分配的channel注册一个读事件，到selector上，同时关联一个buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()){

                    /**此处可以开启一个线程单独处理*/
                    SocketChannel channel =(SocketChannel) key.channel();
                    //获取到该channel关联的buffer
                    ByteBuffer buffer =(ByteBuffer) key.attachment();
                    int read = channel.read(buffer);
                    System.out.println("form客户端 "+new String(buffer.array()));

                }
                //防止多线程情况下的重复操作
                keyIterator.remove();
            }
        }
    }

}
