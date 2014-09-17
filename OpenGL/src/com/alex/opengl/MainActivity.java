package com.alex.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	private GLSurfaceView mView = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new GLSurfaceView(this);
        mView.setRenderer(new MyRenderer());
        setContentView(mView);
    }
    class MyRenderer implements Renderer{

    	private float radio = 0.0f;
		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			// TODO Auto-generated method stub
			gl.glClearColor(0, 0, 0, 0);
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			// TODO Auto-generated method stub
			gl.glViewport(0, 0, width, height);
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();
			radio = (float)width/(float)height;
			gl.glFrustumf(-1f, 1f, -radio, radio, 3, 7);
		}

		@Override
		public void onDrawFrame(GL10 gl) {
			// TODO Auto-generated method stub
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			GLU.gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1, 0);
			float [] coords = new float[]{
					0f,radio,2f,
					-1f,-radio,2f,
					1, -radio, 2f
			};
			gl.glColor4f(1.0f, 0, 0, 1.0f);
			ByteBuffer ibb = ByteBuffer.allocateDirect(coords.length << 2);
			ibb.order(ByteOrder.nativeOrder());
			FloatBuffer fbb = ibb.asFloatBuffer();
			fbb.put(coords);
			ibb.position(0);
			gl.glVertexPointer(3,GL10.GL_FLOAT, 0, ibb);
			gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		}
    }
}
