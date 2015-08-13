package br.edu.ufabc.android.shootergenerico;

import java.io.IOException;
import java.util.Random;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Enemy {
	
	private Animacao enemyAnimation;
	private Rect pos;
	private boolean ativo;
	private int vida, x, y;
	
	public Enemy(){
		try {
			enemyAnimation=new Animacao(BitmapFactory.decodeStream(Parametros.assetman.open("enemyAnimation.png")), 8);
			ativo=true;
			vida=20;
			Random r=new Random();
			y=r.nextInt((int) Parametros.SCREEN_HEIGHT);
			x=(int) Parametros.SCREEN_WIDTH;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAtivo(){
		return ativo;
	}
	
	public void Dano(int dano){
		vida-=dano;
	}
	
	public int getVida() {
		return vida;
	}
	
	public Rect getPos(){
		return pos;
	}
	
	public void Update(){
		enemyAnimation.Update(x, y);
		pos=enemyAnimation.getPos();
		x-=10;
		if((x<0)||(vida<1))
			ativo=false;
	}
	
	public void Draw(Canvas canvas){
		enemyAnimation.Draw(canvas);
	}


	
}
