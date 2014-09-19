package com.alex.opengl.renderer;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.util.Log;

import com.alex.opengl.util.BufferUtil;

public class StencilRenderer extends AbstractRenderer {

	public float left = -ratio, top = 1f, width = 0.3f;
	boolean xadd = false, yadd = false;
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		super.onSurfaceCreated(gl, config);
//		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glClearStencil(0);
		gl.glEnable(GL10.GL_STENCIL_TEST);
//		Log.d("yjjxwx","onCreate end");
	}
	
	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		super.onSurfaceChanged(gl, width, height);
		left = -ratio;
		Log.d("yjjxwx","onChanged end");
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT|GL10.GL_STENCIL_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glColor4f(1.0f, 0f, 0f, 1.0f);
		GLU.gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1, 0);
		gl.glPushMatrix();
		gl.glRotatef(xRotate, 1, 0, 0);
		gl.glRotatef(yRotate, 0, 1, 0);
		gl.glRotatef(zRotate, 0, 0, 1);
		
		float r = 0.5f;
		float x = 0f, y =  ratio, z = 1.5f;
		float zstep = 0.01f;
		List<Float> coords = new ArrayList<Float>();
		for(float alpha = 0f; alpha < Math.PI * 12; alpha += Math.PI/16){
			x = (float)(r * Math.cos(alpha));
			z = (float) (r * Math.sin(alpha));
			y -= zstep;
			coords.add(x);
			coords.add(y);
			coords.add(z);
		}
		gl.glColor4f(1, 1, 1, 1);
		gl.glStencilFunc(GL10.GL_NEVER, 1, 0);
		gl.glStencilOp(GL10.GL_INCR, GL10.GL_INCR, GL10.GL_INCR);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.list2ByteBuffer(coords));
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, coords.size()/3);
		
		gl.glPopMatrix();
		
		if(xadd){
			left += 0.01f;
		}else{
			left -= 0.01f;
		}
		if(left <= (-ratio)){
			xadd = true;
		}
		if(left >= (ratio - width)){
			xadd = false;
		}
		if(yadd){
			top += 0.01f;
		}else{
			top -= 0.01f;
		}
		if(top >= 1.0f){
			yadd = false;
		}
		if(top <= -1 + width){
			yadd = true;
		}
		float [] rectVertexs = new float[]{
				left,top, 2f,
				left, top-width, 2f,
				left + width, top - width, 2f,
				left + width, top,2f
		};
		gl.glStencilFunc(GL10.GL_EQUAL, 1, 1);
		gl.glStencilOp(GL10.GL_KEEP,GL10.GL_KEEP,GL10.GL_KEEP);
		
		gl.glColor4f(1f, 0, 0, 1f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.arr2ByteBuffer(rectVertexs));
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
	}
}
