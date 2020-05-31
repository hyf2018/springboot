package com.example.demo.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;

public class FileChannelTest {

	public static void main(String[] args) throws IOException {
		String src = "E:\\test\\11.xml";
		String dist = "E:\\test\\11_copy.xml";
//		fastCopy(src, dist);
		fastCopy2(src, dist);
	}

	/* 通过FileChannel的 transferTo 实现 */
	public static void fastCopy2(String src, String dist) throws IOException {
		FileChannel srcfc = FileChannel.open(Paths.get(src));
		
		HashSet<OpenOption> op = new HashSet<OpenOption>(2);
		op.add(StandardOpenOption.WRITE);
		// 如果文件已经存在，并且打开 WRITE访问，则其长度将截断为0。
		op.add(StandardOpenOption.TRUNCATE_EXISTING);
		FileChannel distfc = FileChannel.open(Paths.get(dist), op);

		/*
		 * transferTo(long position, long count, WritableByteChannel target)
		 * 将该通道文件的字节传输到给定的可写字节通道。
		 */
		long size = srcfc.transferTo(0, srcfc.size(), distfc);
		System.out.println("size:" + size);
	}

	/* 通过FileChannel的 read、write 实现 */
	public static void fastCopy(String src, String dist) throws IOException {
		/* 获得源文件的输入字节流 */
		FileInputStream fin = new FileInputStream(src);

		/* 获取输入字节流的文件通道 */
		FileChannel fcin = fin.getChannel();

		/* 获取目标文件的输出字节流 */
		FileOutputStream fout = new FileOutputStream(dist);

		/* 获取输出字节流的文件通道 */
		FileChannel fcout = fout.getChannel();

		/* 为缓冲区分配 1024 个字节 */
		ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

		while (true) {
			/* 从输入通道中读取数据到缓冲区中 */
			int r = fcin.read(buffer);

			/* read() 返回 -1 表示 EOF */
			if (r <= 0) {
				break;
			}

			/* 切换读写 */
			buffer.flip();

			/* 把缓冲区的内容写入输出文件中 */
			fcout.write(buffer);

			/* 清空缓冲区 */
			buffer.clear();
		}
		System.out.println("copy success!");
	}
}
