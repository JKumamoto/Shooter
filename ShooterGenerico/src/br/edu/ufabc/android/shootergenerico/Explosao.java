package br.edu.ufabc.android.shootergenerico;

import java.io.IOException;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Explosao {
	
	private Animacao animation;
	private Rect pos;
	private boolean ativo;
	private int cont;
	
	public Explosao(Rect r){
		try{
			animation=new Animacao(BitmapFactory.decodeStream(Parametros.assetman.open("explosion.png")), 12);
			this.pos=r;
			ativo=true;
			cont=0;
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void Update(){
		animation.Update(pos.left, pos.top);
	}
	
	public void Draw(Canvas canvas){
		animation.Draw(canvas);
		cont++;
		if(cont==12)
			ativo=false;
	}

	public boolean isAtivo() {
		return ativo;
	}

}
