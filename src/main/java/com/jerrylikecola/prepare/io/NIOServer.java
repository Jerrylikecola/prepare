package com.jerrylikecola.prepare.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xiaxiang
 * @date 2021/3/30 11:14
 * @description
 */
public class NIOServer {
    public static void main(String[] args) {
        new Thread().start();
    }

    static class ServerHandle implements Runnable{
        private Selector selector;
        private ServerSocketChannel serverSocketChannel;
        private volatile boolean stop;

        public ServerHandle() {
            try {
                selector=Selector.open();
                serverSocketChannel = ServerSocketChannel.open();
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.socket().bind(new InetSocketAddress(8888),1024);
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("server is ok");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void stop() {
            this.stop = true;
        }

        @Override
        public void run() {
            while (!stop){
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> it = selectionKeys.iterator();
                    SelectionKey key = null;
                    while (it.hasNext()){
                        key = it.next();
                        it.remove();
                        handleInput(key);
                        if (key!=null){
                            key.cancel();
                        }
                    }
                }catch (IOException e){

                }
            }
        }

        private void handleInput(SelectionKey key) throws IOException {
            if (key.isValid()){
                if (key.isAcceptable()){
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                }
                if (key.isReadable()){
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int readBytes = sc.read(byteBuffer);
                    byteBuffer.flip();
                    doWrite(sc,new Date().toString());
                }
            }
        }

        private void doWrite(SocketChannel socketChannel,String resp) throws IOException {
            byte[] bytes = resp.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
        }

    }
}
