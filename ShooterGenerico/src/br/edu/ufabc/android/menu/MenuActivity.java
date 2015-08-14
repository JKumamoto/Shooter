package br.edu.ufabc.android.menu;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import br.edu.ufabc.android.shootergenerico.MainActivity;
import br.edu.ufabc.android.shootergenerico.Parametros;
import br.edu.ufabc.android.shootergenerico.R;

public class MenuActivity extends FragmentActivity{
	
	private PageAdapter mPageAdapter;
	private List<Fragment> fragments;
	
	private EditText nome;
	private SeekBar volume;
	private String spNome;
	private int spVolume;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_viewpager);
		startPages();

	}
	
	
	private void startPages(){
		fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, MenuFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, ScoresFragment.class.getName()));
		fragments.add(Fragment.instantiate(this, ConfigFragment.class.getName()));
		
		mPageAdapter = new PageAdapter(this.getSupportFragmentManager(),fragments);
		
		ViewPager vPager = (ViewPager) findViewById(R.id.viewpager);
		vPager.setAdapter(mPageAdapter);

	}
	

	
	public void btnConfigSave(View v){
		nome = (EditText) findViewById(R.id.editTxtNome);
		volume = (SeekBar) findViewById(R.id.seekBar1);
		
		if(nome.getText() != null) {
			spNome = nome.getText().toString();
			spVolume = volume.getProgress();
			Log.d("Vol", ""+spVolume);
			Parametros.som=spVolume/100;
			
			SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
			SharedPreferences.Editor spEditor = sp.edit();
			
			spEditor.putString("nomePlayer", spNome);
			spEditor.putInt("configVolume", spVolume);
			
			spEditor.commit();
			Toast.makeText(this, "Dados Salvos com Sucesso", Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, "Insira um nome valido!", Toast.LENGTH_SHORT).show();
		}	
		
	}
	public void btnIniciar(View v){
		Intent in = new Intent(this, MainActivity.class);
		startActivity(in);
	}
	
	public void btnSair(View v){
		finish();
	}
}
