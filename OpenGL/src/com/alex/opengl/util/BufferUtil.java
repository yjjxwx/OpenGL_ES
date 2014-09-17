package com.alex.opengl.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class BufferUtil {
	public static ByteBuffer arr2ByteBuffer(float [] arr){
		ByteBuffer ibb = ByteBuffer.allocateDirect(arr.length << 2);
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
		fbb.put(arr);
		ibb.position(0);
		return ibb;
	}
}
