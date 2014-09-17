package com.alex.opengl.renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import com.alex.opengl.util.BufferUtil;

import android.opengl.GLU;

public class PointSizeRenderer extends AbstractRenderer {

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
		float x = 0f, y = 0f, z = 1.5f;
		float zstep = 0.005f;
		float pointSize = 1.0f;
		for(float alpha = 0f; alpha < Math.PI * 6; alpha += Math.PI/16){
			x = (float)(r * Math.cos(alpha));
			y = (float) (r * Math.sin(alpha));
			z -= zstep;
			gl.glPointSize(pointSize);
			pointSize += 0.3;
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.arr2ByteBuffer(new float[]{x,y,z}));
			gl.glDrawArrays(GL10.GL_POINTS, 0, 1);
		}
		
	}
}
