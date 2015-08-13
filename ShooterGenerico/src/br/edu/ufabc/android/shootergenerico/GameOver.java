package br.edu.ufabc.android.shootergenerico;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GameOver extends Activity{
	
	protected void onCreate(Bundle savedInstanceState){
		Log.d("oncreate", "GameOver");
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.gameover);
		TextView te=(TextView) findViewById(R.id.textView2);
		te.setText("Pontuacao: "+Parametros.score);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finish();
	}

}
