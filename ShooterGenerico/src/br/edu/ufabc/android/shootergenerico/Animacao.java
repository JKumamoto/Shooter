package br.edu.ufabc.android.shootergenerico;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Animacao {
	
	private Bitmap figura;
	private int FrameHeight, FrameWidth, FrameCount, currentFrame;
	private Rect src, dst;
	
	public Animacao(Bitmap figura, int FrameCount){
		this.figura=figura;
		this.FrameCount=FrameCount;
		FrameHeight=figura.getHeight();
		FrameWidth=figura.getWidth()/FrameCount;
		currentFrame=0;
		src=new Rect();
		dst=new Rect();
	}
	
	public void Update(int X, int Y){
		src.left=currentFrame*FrameWidth;
		src.right=src.left+FrameWidth;
		src.top=0;
		src.bottom=FrameHeight;
		
		dst.left=(int) X; //(X-FrameWidth*Parametros.Distorcao);
		dst.right=(int) (dst.left + FrameWidth*Parametros.Distorcao);
		dst.top=(int) Y; //(Y-FrameHeight*Parametros.Distorcao);
		dst.bottom= (int) (dst.top + FrameHeight*Parametros.Distorcao);
		
		currentFrame=(currentFrame+1)%FrameCount;
	}
	
	public void Draw(Canvas canvas){
		canvas.drawBitmap(figura, src, dst, null);
	}
	
	public Rect getPos(){
		return dst;
	}
}
