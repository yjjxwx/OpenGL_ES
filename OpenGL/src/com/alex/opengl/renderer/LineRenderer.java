package com.alex.opengl.renderer;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import com.alex.opengl.util.BufferUtil;

public class LineRenderer extends AbstractRenderer {

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
		float x = 0f, y = 0f, z = 0f;
		List<Float> coords = new ArrayList<Float>();
		for(float alpha = 0f; alpha < Math.PI * 6; alpha += Math.PI/16){
			x = (float)(r * Math.cos(alpha));
			y = (float) (r * Math.sin(alpha));
			
			coords.add(0f);
			coords.add(0f);
			coords.add(0f);
			
			coords.add(x);
			coords.add(y);
			coords.add(z);
		}
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.list2ByteBuffer(coords));
		gl.glDrawArrays(GL10.GL_LINES, 0, coords.size()/6);
	}
}
