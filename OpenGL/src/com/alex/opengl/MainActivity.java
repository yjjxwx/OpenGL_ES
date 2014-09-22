package com.alex.opengl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;

import com.alex.opengl.renderer.AbstractRenderer;
import com.alex.opengl.renderer.CubeColorRenderer;

public class MainActivity extends Activity {
	
	private GLSurfaceView mView = null;
	
	private AbstractRenderer mRenderer = new CubeColorRenderer();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new GLSurfaceView(this);
        mView.setEGLConfigChooser(5, 6, 5, 0, 16, 4);
        mView.setRenderer(mRenderer);
        mView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setContentView(mView);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	// TODO Auto-generated method stub
    	float step = 5f;
    	switch(keyCode){
    	case KeyEvent.KEYCODE_VOLUME_UP:
    		mRenderer.xRotate += step;
    		mView.requestRender();
    		return true;
    	case KeyEvent.KEYCODE_VOLUME_DOWN:
    		mRenderer.yRotate -= step;
    		mView.requestRender();
    		return true;
    	}
    	return super.onKeyDown(keyCode, event);
    }
}
