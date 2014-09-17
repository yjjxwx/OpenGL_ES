package com.alex.opengl.renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

public class PointRenderer implements Renderer {

	private float ratio = 0f;
	
	public float xRotate = 0f;
	
	public float yRotate = 0f;
	
	public float zRotate = 0f;
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		gl.glClearColor(0f, 0f, 0f, 1);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		ratio = (float)width/(float)height;
		gl.glFrustumf(-ratio, ratio,1.0f, -1.0f, 3, 7);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glColor4f(1.0f, 0f, 0f, 1.0f);
		GLU.gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1, 0);
		
		gl.glRotatef(xRotate, 1, 0, 0);
		gl.glRotatef(yRotate, 0, 1, 0);
		gl.glRotatef(zRotate, 0, 0, 1);
		
		float r = 0.5f;
		List<Float> coords = new ArrayList<Float>();
		float x = 0f, y = 0f, z = 1.5f;
		float zstep = 0.005f;
		for(float alpha = 0f; alpha < Math.PI * 6; alpha += Math.PI/16){
			x = (float)(r * Math.cos(alpha));
			y = (float) (r * Math.sin(alpha));
			z -= zstep;
			coords.add(x);
			coords.add(y);
			coords.add(z);
		}
		
		ByteBuffer ibb = ByteBuffer.allocateDirect(coords.size() << 2);
		ibb.order(ByteOrder.nativeOrder());
		
		FloatBuffer fbb = ibb.asFloatBuffer();
		
		for(float f: coords){
			fbb.put(f);
		}
		ibb.position(0);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, ibb);
		gl.glDrawArrays(GL10.GL_POINTS, 0, coords.size()/3);
	}
}
