package br.edu.ufabc.android.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import br.edu.ufabc.android.shootergenerico.R;

public class ConfigFragment extends Fragment {
	
	private EditText nome;
	private SeekBar volume;
	private String spNome;
	private int spVolume;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		if(container == null)
			return null;
		
		return inflater.inflate(R.layout.fragment_config, container, false);	
			}
	


}
