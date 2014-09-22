package com.alex.opengl.renderer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.alex.opengl.R;
import com.alex.opengl.util.BufferUtil;
import com.alex.opengl.util.DrawUtil;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLU;
import android.opengl.GLUtils;

public class TextureRenderer extends AbstractRenderer{

	private Resources mResources = null;
	public TextureRenderer(Resources resources){
		mResources = resources;
	}
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		super.onSurfaceCreated(gl, config);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glEnable(GL10.GL_DEPTH_TEST);
	}
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT
				);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
//		gl.glColor4f(1.0f, 0f, 0f, 1.0f);
		GLU.gluLookAt(gl, 0, 0, 5, 0, 0, 0, 0, 1, 0);
		gl.glShadeModel(GL10.GL_FLAT);
		gl.glRotatef(xRotate, 1, 0, 0);
		gl.glRotatef(yRotate, 0, 1, 0);
		gl.glRotatef(zRotate, 0, 0, 1);
		
		gl.glEnable(GL10.GL_TEXTURE_2D);
		int[] texIds = new int[1];
		gl.glGenTextures(1, texIds, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texIds[0]);
		Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.girl);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);
		
		float [] texCoords = {
				0f,0f,
				1f,0f,
				0f,1f,
				1f,1f
		};
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, BufferUtil.arr2ByteBuffer(texCoords));
		
		float [] rectCoords = {
			-1f, -1f, 0f,
			1f, -1f, 0f,
			-1f, 1f, 0f,
			1f, 1f, 0f
		};
		DrawUtil.drawRect(gl, rectCoords);
	}
}
