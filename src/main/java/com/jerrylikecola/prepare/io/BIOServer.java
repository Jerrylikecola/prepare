package com.jerrylikecola.prepare.io;

import ch.qos.logback.core.net.server.ServerRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author xiaxiang
 * @date 2021/3/30 09:53
 * @description
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                new Thread(new ServerHandle(socket)).start();
            }
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
                serverSocket = null;
            }
        }
    }

    static class ServerHandle implements Runnable {

        private Socket socket;

        public ServerHandle(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                while (true) {
                    String body = reader.readLine();
                    if (body == null) {
                        break;
                    }
                    System.out.println(body);
                    writer.println(new Date());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}