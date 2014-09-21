package com.alex.opengl.renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;

public abstract class AbstractRenderer implements Renderer {

	protected float ratio = 0f;
	
	public float xRotate = 0f;
	
	public float yRotate = 0f;
	
	public float zRotate = 0f;
	
	public boolean enableAmibentLighting = false;
	
	public float globalAmbientR = 0.0f;
	public float globalAmbientG = 0.0f;
	public float globalAmbientB = 0.0f;
	public float globalAmbientA = 1.0f;
	
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
	public abstract void onDrawFrame(GL10 gl);
}
