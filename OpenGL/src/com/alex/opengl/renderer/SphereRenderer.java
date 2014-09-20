package com.alex.opengl.renderer;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

import com.alex.opengl.util.BufferUtil;

/**
 * This renderer is to draw a sphere with line strip.
 * @author alex
 *
 */
public class SphereRenderer extends AbstractRenderer {
	
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1, 0);
		
		gl.glRotatef(xRotate, 1, 0, 0);
		gl.glRotatef(yRotate, 0, 1, 0);
		gl.glRotatef(zRotate, 0, 0, 1);
		gl.glColor4f(1.0f, 0f, 0f, 1.0f);
		float R = 0.7f;
		int stack = 12;
		float stackStep = (float) (Math.PI / stack);
		
		int slice = 16;
		float sliceStep = (float)(Math.PI / slice);
		float r0 = 0, r1 = 0, x0 = 0.5f, x1 = 0.2f, y0 = 0.1f, y1 = 0.6f, z0 = 1f, z1 = -1f;
		float alpha0 = 0, alpha1=0, beta=0;
		
		List<Float> coords = new ArrayList<Float>();
		for(int i = 0; i < stack; ++i){
			alpha0 = (float)( -Math.PI / 2 + (i * stackStep));
			alpha1 = (float)( -Math.PI / 2 + ((i+1) * stackStep));
			y0 = (float)(R * Math.sin(alpha0));
			r0 = (float)(R * Math.cos(alpha0));		
			y1 = (float)(R * Math.sin(alpha1));
			r1 = (float)(R * Math.cos(alpha1));
			for(int j = 0; j <= (slice * 2); ++j){
				beta = j * sliceStep;
				x0 = (float)(r0 * Math.cos(beta));
				z0 = -(float)(r0 * Math.sin(beta));
				x1 = (float)(r1 * Math.cos(beta));
				z1 = -(float)(r1 * Math.sin(beta));
				coords.add(x0);
				coords.add(y0);
				coords.add(z0);
				coords.add(x1);
				coords.add(y1);
				coords.add(z1);
			}
		}
		FloatBuffer fbb = BufferUtil.list2FloatBuffer(coords);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, fbb);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, coords.size()/3);
	}	
}
