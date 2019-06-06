package com.hzw.learn.springboot.javabase.io.nio.bufferAndChannel;

import java.nio.CharBuffer;

public class BufferTest {
	public static void main(String[] args) {
		CharBuffer buffer = CharBuffer.allocate(10);
		buffer.limit(5);
		System.out.println(buffer.capacity());
		System.out.println(buffer.limit());
		System.out.println(buffer.position());
		buffer.mark();
		buffer.put(new char[] {'a','b'});
		System.out.println(buffer.capacity());
		System.out.println(buffer.limit());
		System.out.println(buffer.position());
		buffer.mark();
		buffer.put(new char[] {'c','d'});
		System.out.println(buffer.capacity());
		System.out.println(buffer.limit());
		System.out.println(buffer.position());
		buffer.reset();
		System.out.println(buffer.capacity());
		System.out.println(buffer.limit());
		System.out.println(buffer.position());
		System.out.println(buffer.hasRemaining());
		buffer.flip();
		System.out.println(buffer.capacity());
		System.out.println(buffer.limit());
		System.out.println(buffer.position());
		

		
	}
}
