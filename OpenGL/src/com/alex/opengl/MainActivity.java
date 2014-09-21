package com.alex.opengl;

import java.lang.reflect.Field;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.alex.opengl.renderer.AbstractRenderer;
import com.alex.opengl.renderer.TextureRenderer;

public class MainActivity extends Activity {
	
	private GLSurfaceView mView = null;
	
	private AbstractRenderer mRenderer = null;
	
	private Dialog dialog = null;
	
	Resources resources = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resources = getResources();
        mView = new GLSurfaceView(this);
        mRenderer = new TextureRenderer(resources);
        mView.setRenderer(mRenderer);
        mView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
        setContentView(mView);
        createDialog();
    }
    private void createDialog() {
		// TODO Auto-generated method stub
		dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
		dialog.setContentView(R.layout.light_setting);
		addDialogEventListener();
	}
	private void addDialogEventListener() {
		// TODO Auto-generated method stub
		Class clazz = R.id.class;
		CheckBox box = null;
		SeekBar bar = null;
		
		CompoundButton.OnCheckedChangeListener boxListener = new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				int id = buttonView.getId();
				String name = resources.getResourceName(id);
				name = name.substring(name.lastIndexOf("/") + 1);
				Class clazz = mRenderer.getClass();
				try {
					Field fd = clazz.getField(name);
					fd.setAccessible(true);
					fd.setBoolean(mRenderer, isChecked);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		SeekBar.OnSeekBarChangeListener barListener = new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				int id = seekBar.getId();
				String name = resources.getResourceName(id);
				name = name.substring(name.lastIndexOf("/") + 1);
				Class clazz = mRenderer.getClass();
				Field fd;
				try {
					fd = clazz.getField(name);
					fd.setAccessible(true);
					float scale = (float)progress/(float)seekBar.getMax();
					fd.setFloat(mRenderer, scale);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		Field [] fs = clazz.getDeclaredFields();
		for(Field f: fs){
			try {
				int id = f.getInt(null);
				View view = dialog.findViewById(id);
				if(view != null){
					if(view instanceof CheckBox){
						box = (CheckBox)view;
						box.setOnCheckedChangeListener(boxListener);
					}else if(view instanceof SeekBar){
						((SeekBar)view).setOnSeekBarChangeListener(barListener);
					}
				}
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
//    	return super.onKeyDown(keyCode, event);
    	return true;
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("Settings");
		return super.onCreateOptionsMenu(menu);
	}
	 @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		 dialog.show();
		return super.onOptionsItemSelected(item);
	}
}
