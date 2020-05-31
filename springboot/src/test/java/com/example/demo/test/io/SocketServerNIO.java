package com.example.demo.test.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class SocketServerNIO {

	public static void main(String[] args) throws IOException {

//		创建选择器
		Selector selector = Selector.open();

//		将通道注册到选择器上
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
//		通道配置为非阻塞模式
		ssChannel.configureBlocking(false);
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);

		ServerSocket serverSocket = ssChannel.socket();
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
		serverSocket.bind(address);

//		 事件循环
		while (true) {
//			使用 select() 来监听到达的事件，它会一直阻塞直到有至少一个事件到达。
			selector.select();

//			获取到达的事件
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = keys.iterator();

			while (keyIterator.hasNext()) {

				SelectionKey key = keyIterator.next();

				if (key.isAcceptable()) {

					ServerSocketChannel ssChannel1 = (ServerSocketChannel) key.channel();

					// 服务器会为每个新连接创建一个 SocketChannel
					SocketChannel sChannel = ssChannel1.accept();
					sChannel.configureBlocking(false);

					// 这个新连接主要用于从客户端读取数据
					sChannel.register(selector, SelectionKey.OP_READ);

				} else if (key.isReadable()) {

					SocketChannel sChannel = (SocketChannel) key.channel();
					System.out.println(readDataFromSocketChannel(sChannel));
					sChannel.close();
				}

				keyIterator.remove();
			}
		}
	}

	// ByteBuffer直接分配大小
	private static String readDataFromSocketChannel(SocketChannel sChannel) throws IOException {
		String result = "";
		ByteBuffer buffer = ByteBuffer.allocate(200);
		ArrayList<Byte> byteList = new ArrayList<Byte>();

		while (true) {
			buffer.clear();
			int n = sChannel.read(buffer);
			System.out.println("n:" + n);
			if (n <= 0) {
				break;
			}
//			将Buffer从写模式切换到读模式（必须调用这个方法）
			buffer.flip();

			int limit = buffer.limit();
			for (int i = 0; i < limit; i++) {
				// 读取一个byte
				byteList.add(buffer.get());
			}
		}
		buffer.clear();

		byte[] byteArray = new byte[byteList.size()];
		for (int i = 0; i < byteList.size(); i++) {
			byteArray[i] = byteList.get(i).byteValue();
		}
		result = new String(byteArray, "UTF-8");
		return result;
	}

	// ByteBuffer指定后面的byte数组
	private static String readDataFromSocketChannel2(SocketChannel sChannel) throws IOException {
		StringBuffer result = new StringBuffer("");
		byte[] wrap = new byte[200];
		ByteBuffer buffer = ByteBuffer.wrap(wrap);

		while (true) {
			// This method does not actually erase the data in the buffer
			buffer.clear();
			int n = sChannel.read(buffer);
			System.out.println("n:" + n);
			if (n <= 0) {
				break;
			}

			String str = new String(wrap, 0, n, "UTF-8");
			System.out.println("str:" + str);
			result.append(str);
		}
		buffer.clear();

		return result.toString();
	}
}
