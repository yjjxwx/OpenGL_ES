package com.alex.opengl.renderer;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import com.alex.opengl.util.BufferUtil;

public class TriangleConeRenderer extends AbstractRenderer {

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		super.onSurfaceCreated(gl, config);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glFrontFace(GL10.GL_CCW);
		gl.glCullFace(GL10.GL_BACK);
		gl.glEnable(GL10.GL_DEPTH_TEST);
	}
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glColor4f(1.0f, 0f, 0f, 1.0f);
		GLU.gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1, 0);
		gl.glShadeModel(GL10.GL_FLAT);
		gl.glRotatef(xRotate, 1, 0, 0);
		gl.glRotatef(yRotate, 0, 1, 0);
		gl.glRotatef(zRotate, 0, 0, 1);
		
		float r = 0.5f;
		float x = 0f, y = 0f, z = 0.5f;
		
		
		List<Float> coords = new ArrayList<Float>();
		coords.add(x);
		coords.add(y);
		coords.add(z);
		
		List<Float> colorList = new ArrayList<Float>();
		colorList.add(1.0f);
		colorList.add(0.0f);
		colorList.add(0.0f);
		colorList.add(1.0f);
		
		List<Float> bottomList = new ArrayList<Float>();
		bottomList.add(x);
		bottomList.add(y);
		bottomList.add(-0.5f);
		
		
		boolean flag = true;
		for(float alpha = 0; alpha < Math.PI * 6; alpha += Math.PI/16){
			x = (float)(r * Math.cos(alpha));
			y = (float) (r * Math.sin(alpha));
			z = -0.5f;
			coords.add(x);
			coords.add(y);
			coords.add(z);
			
			bottomList.add(x);
			bottomList.add(y);
			bottomList.add(z);
			
			if(flag = !flag){
				colorList.add(1.0f);
				colorList.add(0.0f);
				colorList.add(0.0f);
				colorList.add(1.0f);
			}else{
				colorList.add(1.0f);
				colorList.add(1.0f);
				colorList.add(0.0f);
				colorList.add(1.0f);
			}
		}
		
		ByteBuffer cbb = BufferUtil.list2ByteBuffer(colorList);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.list2ByteBuffer(coords));
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, cbb);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, coords.size()/3);
		
		
		
		cbb.position(4);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.list2ByteBuffer(bottomList));
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, cbb);
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, bottomList.size()/3);
	}
}
