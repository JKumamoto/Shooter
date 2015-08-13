package br.edu.ufabc.android.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.edu.ufabc.android.shootergenerico.MainActivity;
import br.edu.ufabc.android.shootergenerico.R;

public class MenuFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		if(container == null)
			return null;
		
		return inflater.inflate(R.layout.fragment_menu, container, false);	
	}
	
	public void btnIniciar(View v){
		Intent in = new Intent(getActivity(), MainActivity.class);
		startActivity(in);
	}
	
	public void btnSair(View v){
		getActivity().finish();
	}

}
