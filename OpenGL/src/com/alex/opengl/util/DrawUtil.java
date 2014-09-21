package com.alex.opengl.util;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class DrawUtil {
	public static void  drawSphere(GL10 gl, float r, int stack, int slice){
		List<Float> coords = new ArrayList<Float>();
		float r0, r1, alpha0, alpha1, x0, x1, y0, y1, z0, z1, beta;
		float stackStep = (float)(Math.PI / stack);
		float sliceStep = (float)(Math.PI / slice);
		for(int i = 0; i < stack; ++i){
			alpha0 = (float)( -Math.PI / 2 + i * stackStep);
			alpha1 = alpha0 + stackStep;
			r0 = (float) (r * Math.cos(alpha0));
			r1 = (float) (r * Math.cos(alpha1));
			
			y0 = (float) (r * Math.sin(alpha0));
			y1 = (float) (r * Math.sin(alpha1));
			
			for(int j = 0; j < (slice << 1); ++j){
				beta = j * sliceStep;
				x0 = (float) (r0 * Math.cos(beta));
				x1 = (float) (r1 * Math.cos(beta));
				
				z0 = (float) ( - r0 * Math.sin(beta));
				z1 = (float) (- r1 * Math.sin(beta));
				
				coords.add(x0);
				coords.add(y0);
				coords.add(z0);
				coords.add(x1);
				coords.add(y1);
				coords.add(z1);
			}
			gl.glVertexPointer(3, GL10.GL_FLOAT, 0, BufferUtil.list2ByteBuffer(coords));
			gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, coords.size()/3);
		}
	}
}
