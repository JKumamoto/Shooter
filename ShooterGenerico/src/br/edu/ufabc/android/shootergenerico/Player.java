package br.edu.ufabc.android.shootergenerico;

import java.io.IOException;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player{
	
	public Animacao playerAnimation;
	private Rect pos;
	private boolean ativo;
	private int vida;
	
	public Player(){
		try{
			playerAnimation=new Animacao(
					BitmapFactory.decodeStream(Parametros.assetman.open("shipAnimation.png")), 8);
			ativo=true;
			vida=100;
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public int getVida() {
		return vida;
	}

	public void Dano() {
		vida = vida-10;
	}
	
	public Rect getPos(){
		return pos;
	}
	
	public void Update(int x, int y){
			playerAnimation.Update(x, y);
			pos=playerAnimation.getPos();
		if(vida<1)
			ativo = false;
	}
	
	public void Draw(Canvas canvas){
		playerAnimation.Draw(canvas);
	}
}
