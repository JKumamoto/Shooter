package br.edu.ufabc.android.shootergenerico;

import java.io.IOException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public class Background {
	
	private Bitmap main;
	private Matrix mainmat;
	
	private Bitmap aux1;
	private Rect aux1src;
	private Rect aux1first;
	private Rect aux1second;
	
	private Bitmap aux2;
	private Rect aux2src;
	private Rect aux2first;
	private Rect aux2second;
	
	private int cont;

	public Background(){
		try{
			main=BitmapFactory.decodeStream(Parametros.assetman.open("mainbackground.png"));
			mainmat=new Matrix();
			mainmat.setScale(Parametros.Distorcao, Parametros.Distorcao);
			
			aux1=BitmapFactory.decodeStream(Parametros.assetman.open("bgLayer1.png"));
			aux1src=new Rect(0, 0, (int) (Parametros.IMG_WIDTH), (int) (Parametros.IMG_HEIGHT));
			aux1first=new Rect();
			aux1second=new Rect();
			
			aux2=BitmapFactory.decodeStream(Parametros.assetman.open("bgLayer2.png"));
			aux2src=new Rect(0, 0, (int) Parametros.IMG_WIDTH, (int) Parametros.IMG_HEIGHT);
			aux2first=new Rect();
			aux2second=new Rect();

			cont=0;
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void Update(){
		aux1first.left=-cont;
		aux1first.right=(int) (Parametros.IMG_WIDTH*Parametros.Distorcao)+aux1first.left;
		aux1first.top=0;
		aux1first.bottom=(int) (Parametros.IMG_HEIGHT*Parametros.Distorcao);
		
		aux1second.left=aux1first.right;
		aux1second.right=(int) (aux1second.left+Parametros.IMG_WIDTH*Parametros.Distorcao);
		aux1second.top=0;
		aux1second.bottom=(int) (Parametros.IMG_HEIGHT*Parametros.Distorcao);
		
		aux2first.left=-cont;
		aux2first.right=(int) (Parametros.IMG_WIDTH*Parametros.Distorcao)+aux1first.left;
		aux2first.top=0;
		aux2first.bottom=(int) (Parametros.IMG_HEIGHT*Parametros.Distorcao);
		
		aux2second.left=aux1first.right;
		aux2second.right=(int) (aux1second.left+Parametros.IMG_WIDTH*Parametros.Distorcao);
		aux2second.top=0;
		aux2second.bottom=(int) (Parametros.IMG_HEIGHT*Parametros.Distorcao);

		cont+=Parametros.SCREEN_WIDTH/10;
		if(cont>=Parametros.SCREEN_WIDTH)
			cont=0;
	}
	
	
	public void Draw(Canvas canvas){
		canvas.drawBitmap(main, mainmat, null);
		canvas.drawBitmap(aux1, aux1src, aux1first, null);
		canvas.drawBitmap(aux1, aux1src, aux1second, null);
		canvas.drawBitmap(aux2, aux2src, aux2first, null);
		canvas.drawBitmap(aux2, aux2src, aux2second, null);
	}
}
