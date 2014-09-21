package com.alex.opengl.renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import com.alex.opengl.util.BufferUtil;
import com.alex.opengl.util.DrawUtil;

public class LightRenderer extends AbstractRenderer {

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		super.onSurfaceCreated(gl, config);
//		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
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
		
		//Use ambient lighting start
		if(enableAmibentLighting){
			gl.glEnable(GL10.GL_LIGHTING);
		}else{
			gl.glDisable(GL10.GL_LIGHTING);
		}
		float [] globaldAmbients = {
				globalAmbientR, 
				globalAmbientG,
				globalAmbientB,
				globalAmbientA};
		gl.glLightModelfv(GL10.GL_LIGHT_MODEL_AMBIENT, BufferUtil.arr2FloatBuffer(globaldAmbients));
		//Set material use first method
		float [] materials = {
				materialR,
				materialG,
				materialB,
				materialA
		};
		gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT_AND_DIFFUSE, BufferUtil.arr2FloatBuffer(materials));
		
		//Set material use second method
		if(enableColorTrack){
			gl.glEnable(GL10.GL_COLOR_MATERIAL);
		}else{
			gl.glDisable(GL10.GL_COLOR_MATERIAL);
		}
		//Use ambient light end
		
		gl.glColor4f(colorTrackR, colorTrackG,
				colorTrackB, colorTrackA);
		DrawUtil.drawSphere(gl, 0.5f, 8, 12);
	}
}
