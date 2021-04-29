package com.jerrylikecola.prepare.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author xiaxiang
 * @date 2021/3/30 10:08
 * @description
 */
public class BIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8080);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
        writer.println("hello");
        String resp = reader.readLine();
        System.out.println(resp);
    }
}
