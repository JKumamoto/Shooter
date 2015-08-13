package br.edu.ufabc.android.shootergenerico;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import br.edu.ufabc.android.menu.MenuActivity;

public class GameOver extends Activity{
	
	protected void onCreate(Bundle savedInstanceState){
		Log.d("oncreate", "GameOver");
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		this.setContentView(R.layout.gameover);
		TextView te=(TextView) findViewById(R.id.textView2);
		te.setText("Pontuacao: "+Parametros.score);
	}
	
	public void btnVoltar(View v){
		Intent i=new Intent(this, MenuActivity.class);
		finish();
		this.startActivity(i);
	}

}
