package br.edu.ufabc.android.shootergenerico;

import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Tiro {
	
	private Bitmap figura;
	private Rect src, dst;
	private boolean ativo;
	
	public Tiro(int x, int y){
		try {
			figura=BitmapFactory.decodeStream(Parametros.assetman.open("laser.png"));
			src=new Rect(0, 0, figura.getWidth(), figura.getHeight());
			dst=new Rect(x, y, 
					(int) (x+figura.getWidth()*Parametros.Distorcao), (int) (y+figura.getHeight()*Parametros.Distorcao));
			ativo=true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Update(){
		dst.left+=10;
		dst.right=(int) (dst.left+figura.getWidth()*Parametros.Distorcao);
		
		if(dst.left>Parametros.SCREEN_WIDTH)
			ativo=false;
	}
	
	public void Draw(Canvas canvas){
		canvas.drawBitmap(figura, src, dst, null);
	}
	
	public boolean getativo(){
		return ativo;
	}
	
	public Rect getPos(){
		return dst;
	}

	public void setAtivo(boolean b) {
		// TODO Auto-generated method stub
		this.ativo=b;
	}
	
}
