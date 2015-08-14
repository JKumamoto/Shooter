package br.edu.ufabc.android.shootergenerico;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.AssetFileDescriptor;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private Game1 game;
	private Thread t;
	private Handler handler;
	
	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setUpParameters();
			handler=new Handler(){
				public void handleMessage(Message msg){
					super.handleMessage(msg);
					if(msg.what==100){
						Context com=getBaseContext();
						Intent i=new Intent(com, GameOver.class);
						finish();
						startActivity(i);
					}
				}
			};
			game=new Game1(this);
			game.setHandler(handler);
			t=new Thread(game);
			setContentView(game);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUpParameters() throws IOException{
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
		
		Parametros.media=new MediaPlayer();
		Parametros.media.reset();
		try{
			AssetFileDescriptor afd=Parametros.assetman.openFd("theme.mp3");
			Parametros.media.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			afd.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		Parametros.media.prepare();
		Parametros.media.setLooping(true);
		SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
		Parametros.som=1;
		Parametros.Nome="AAA";
		try{
			Parametros.som=(sp.getInt("configVolume", 100)/100);
			Parametros.Nome=sp.getString("nomePlayer", "AAA");
			EditText nome = (EditText) findViewById(R.id.editTxtNome);
			nome.setText(Parametros.Nome);
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
}
