package com.alex.opengl.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;

public class BufferUtil {
	public static ByteBuffer arr2ByteBuffer(float [] arr){
		ByteBuffer ibb = ByteBuffer.allocateDirect(arr.length << 2);
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
		fbb.put(arr);
		ibb.position(0);
		return ibb;
	}
	
	public static ByteBuffer arr2ByteBuffer(byte [] arr){
		ByteBuffer ibb = ByteBuffer.allocateDirect(arr.length << 2);
		ibb.order(ByteOrder.nativeOrder());
		ibb.put(arr);
		ibb.position(0);
		return ibb;
	}
	public static FloatBuffer arr2FloatBuffer(float [] arr){
		ByteBuffer ibb = ByteBuffer.allocateDirect(arr.length << 2);
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
		fbb.put(arr);
		fbb.position(0);
		return fbb;
	}
	
	public static ByteBuffer list2ByteBuffer(List<Float> list){
		ByteBuffer ibb = ByteBuffer.allocateDirect(list.size() << 2);
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
		for(float f: list){
			fbb.put(f);
		}
		ibb.position(0);
		return ibb;
	}
	
	public static FloatBuffer list2FloatBuffer(List<Float> list){
		ByteBuffer ibb = ByteBuffer.allocateDirect(list.size() << 2);
		ibb.order(ByteOrder.nativeOrder());
		FloatBuffer fbb = ibb.asFloatBuffer();
		for(float f: list){
			fbb.put(f);
		}
		fbb.position(0);
		return fbb;
	}
}
