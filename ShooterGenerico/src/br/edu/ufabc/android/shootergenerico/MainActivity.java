package br.edu.ufabc.android.shootergenerico;

import java.io.IOException;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	
	private Game1 game;
	private Thread t;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setVisualParameters();
			game=new Game1(this);
			t=new Thread(game);
			setContentView(game);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
    protected void onPause(){
    	super.onPause();
    	t.interrupt();
    }
    
    protected void onStop(){
    	super.onStop();
    	t.interrupt();
    }
    
    protected void onDestroy(){
    	super.onDestroy();
    	try {
			t.join(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    */
	
	public void setVisualParameters() throws IOException{
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);
		
		Parametros.SCREEN_WIDTH  = size.x;
		Parametros.SCREEN_HEIGHT = size.y;
		Parametros.Distorcao =Parametros.SCREEN_WIDTH / 
				Parametros.IMG_WIDTH;
		Parametros.assetman=getAssets();
		
		Parametros.soundID=new int[2];
		Parametros.streamID=new int[2];
		
		Parametros.efeitos=new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
		Parametros.soundID[0]=Parametros.efeitos.load(Parametros.assetman.openFd("laser.wav"), 1);
		Parametros.soundID[1]=Parametros.efeitos.load(Parametros.assetman.openFd("explosion.wav"), 1);
		/*
		Parametros.media=new MediaPlayer();
		Parametros.media.setAudioStreamType(AudioManager.STREAM_MUSIC);
		AssetFileDescriptor afd=Parametros.assetman.openFd("theme.mp3");
		Parametros.media.setDataSource(afd.getFileDescriptor());
		afd.close();
		Parametros.media.prepare();
		Parametros.media.setLooping(true);*/
		Parametros.score=0;
		Parametros.gameover=false;
	}
}
