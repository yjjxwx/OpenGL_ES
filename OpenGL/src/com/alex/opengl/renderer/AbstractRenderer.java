package com.alex.opengl.renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

/**
 * This abstract renderer is to handle commom operation
 * when create or change surface view. 
 * You can extends this abstract renderer just implements
 * onDrawFrame method.
 */
public abstract class AbstractRenderer implements Renderer {

	protected float ratio = 0f;
	
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
		gl.glFrustumf(-ratio, ratio, 1.0f, -1.0f, 3, 7);
	}

	@Override
	public abstract void onDrawFrame(GL10 gl);
}
