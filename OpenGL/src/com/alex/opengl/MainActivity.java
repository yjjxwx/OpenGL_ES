package com.alex.opengl;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;

import com.alex.opengl.renderer.AbstractRenderer;
import com.alex.opengl.renderer.LineRenderer;

public class MainActivity extends Activity {
	
	private GLSurfaceView mView = null;
	
	private AbstractRenderer mRenderer = new LineRenderer();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = new GLSurfaceView(this);
        mView.setRenderer(mRenderer);
        mView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
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
