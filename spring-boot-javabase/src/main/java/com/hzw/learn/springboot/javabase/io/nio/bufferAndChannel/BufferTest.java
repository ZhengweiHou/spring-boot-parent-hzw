package com.hzw.learn.springboot.javabase.io.nio.bufferAndChannel;

import java.nio.CharBuffer;

public class BufferTest {
	public static void main(String[] args) {
		CharBuffer buffer = CharBuffer.allocate(10);
		System.out.println(buffer.capacity());
		System.out.println(buffer.limit());
		System.out.println(buffer.position());

	}
}
