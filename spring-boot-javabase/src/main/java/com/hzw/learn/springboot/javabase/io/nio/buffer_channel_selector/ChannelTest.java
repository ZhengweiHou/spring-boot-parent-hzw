package com.hzw.learn.springboot.javabase.io.nio.buffer_channel_selector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;


public class ChannelTest {
	public static void main(String[] args) throws IOException {
		test1();
//		test2();

	}
	
	/**
	 * 使用Channel.map()方法直接将Channel中的指定部分内容映射到buffer中
	 * @throws IOException
	 */
	public static void test1() throws IOException {
		File f = new File("src/main/java/com/hzw/learn/springboot/javabase/io/nio/buffer_channel_selector/test.txt");
		File fout = new File("src/main/java/com/hzw/learn/springboot/javabase/io/nio/buffer_channel_selector/test.out");
		
		FileChannel inChannel = new FileInputStream(f).getChannel();
		MappedByteBuffer buffer = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
		
		FileChannel outChannel = new FileOutputStream(fout).getChannel();
		outChannel.write(buffer);
		
		buffer.clear();
		Charset charset = Charset.forName("GBK");
		CharsetDecoder decoder = charset.newDecoder();
		CharBuffer charbuffer = decoder.decode(buffer);
		
		System.out.println(charbuffer);
	}
	
	/**
	 * 使用ByteBuffer从Channel中依次取出数据
	 * @throws IOException
	 */
	public static void test2() throws IOException {
		File f = new File("src/main/java/com/hzw/learn/springboot/javabase/io/nio/buffer_channel_selector/test.txt");
		
		CharsetDecoder decoder = Charset.forName("GBK").newDecoder();
	
		FileChannel inChannel = new FileInputStream(f).getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(16);
		
		while (inChannel.read(buffer) != -1) {
			buffer.flip();
			CharBuffer cbuffer = decoder.decode(buffer);
			System.out.print(cbuffer);
			buffer.clear();
			
		}

	}
}
