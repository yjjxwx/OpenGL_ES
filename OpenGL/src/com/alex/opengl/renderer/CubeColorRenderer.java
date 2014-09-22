package com.alex.opengl.renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.alex.opengl.util.BufferUtil;

import android.opengl.GLU;

public class CubeColorRenderer extends AbstractRenderer{

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		super.onSurfaceCreated(gl, config);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnable(GL10.GL_DEPTH_TEST);
	}
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		super.onSurfaceChanged(gl, width, height);
		
	}
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
		gl.glColor4f(0f,0f,1f,1f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1, 0);
		
		gl.glRotatef(xRotate, 1, 0, 0);
		gl.glRotatef(yRotate, 0, 1, 0);
		gl.glRotatef(zRotate, 0, 0, 1);
		
		float r = 0.4f;
		float [] coords = new float[]{
				-r, r, r,
				-r, -r, r,
				r, r, r,
				r, -r, r,
				-r, r, -r,
				-r, -r, -r,
				r, r, -r,
				r, -r, -r
		};
		gl.glColor4f(1f,0,0,1);
		byte[] indices = {
				0,1,2,
				2,1,3,
				
				4,5,6,
				6,5,7,
				
				0,1,4,
				4,1,5,
				
				2,3,6,
				6,3,7,
				
				4,0,2,
				4,2,6,
				
				5,1,3,
				5,3,7
		};
		//color
		float [] colors = new float[]{
				0f,1f,1f,1f,
				0f,1f,0f,1f,
				1f,1f,1f,1f,
				1f,1f,0f,1f,
				0f,0f,1f,1f,
				0f,0f,0f,1f,
				1f,0f,1f,1f,
				1f,0f,0f,1f,
		};
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, BufferUtil.arr2FloatBuffer(colors));
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.arr2FloatBuffer(coords));
		gl.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_BYTE, BufferUtil.arr2ByteBuffer(indices));
	}
	

}
