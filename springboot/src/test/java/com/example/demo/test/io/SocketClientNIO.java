package com.example.demo.test.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

public class SocketClientNIO {
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("127.0.0.1", 8888);
		OutputStream out = socket.getOutputStream();
		String s = "hello world 速度阿迪斯法第三啊速度发送给一个通道的所有数据都必须首先放到缓冲区中，同样地，从通道中读取的任何数据\r\n"
				+ "都要先读到缓冲区中。也就是说，不会直接对通道进行读写数据，而是要先经过缓冲区。\r\n"
				+ "缓冲区实质上是一个数组，但它不仅仅是一个数组。缓冲区提供了对数据的结构化访问，而且还可以跟踪系统的读/写进程。\r\n";
		out.write(s.getBytes("UTF-8"));
		out.close();
	}
}
