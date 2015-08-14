package br.edu.ufabc.android.shootergenerico;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

public class Game1 extends View implements Runnable{
	
	private Player jogador;
	private Background back;
	private Paint paint;
	private ArrayList<Tiro> tiros;
	private ArrayList<Enemy> inimigos;
	private ArrayList<Explosao> explosao;
	private int cont;
	private Handler handler;
	
	private int x, y;
	
	public Game1(Context ctx){
		super(ctx);
		paint=new Paint(Color.BLUE);
		back=new Background();
		jogador=new Player();
		tiros=new ArrayList<Tiro>();
		inimigos=new ArrayList<Enemy>();
		explosao=new ArrayList<Explosao>();
		cont=0;
		Parametros.media.setVolume(Parametros.som, Parametros.som);
		Parametros.media.start();
	}
	
	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouchEvent(MotionEvent evt){
		if(evt.getAction()==MotionEvent.ACTION_DOWN){
			x=(int) evt.getX();
			y=(int) evt.getY();
		}
		return true;
	}
	
	public void setHandler(Handler handler){
		this.handler=handler;
	}
	
	public void Update(){
		back.Update();
		UpdateEnemy();
		jogador.Update(x, y);
		Parametros.gameover=!jogador.isAtivo();
		cont++;
		if(cont%20==0)
			AddTiros();
		UpdateTiros();
		UpdateCollision();
		UpdateExplosion();
	}
	
	public void UpdateExplosion() {
		for(int i=0; i<explosao.size(); i++){
			explosao.get(i).Update();
			if(!explosao.get(i).isAtivo())
				explosao.remove(i);
		}
	}
	
	public void AddExplosion(Rect r){
		Explosao e=new Explosao(r);
		explosao.add(e);
	}

	public void UpdateCollision(){
		Rect rect1, rect2;
		for (int i = 0; i < inimigos.size(); i++){
        	//colisao jogador vs inimigo
			rect1=jogador.getPos();
            rect2=inimigos.get(i).getPos();
            // determina se os dois colidirao
            if (rect1.intersect(rect2)){
                // diminui a vida do jogador de acordo com a forca do inimigo
                jogador.Dano();
                inimigos.get(i).Dano(20);
                
                if(!jogador.isAtivo()){
                	//Parametros.gameover=true;
                	return ;
                }
            }
            // tiro vs inimigo
            for (int j = 0; j < tiros.size(); j++){
            	rect1=tiros.get(j).getPos();
            	//verifica se colidiu, inimigo toma dano e desativa tiro;
            	if(rect2.intersect(rect1)){
            		inimigos.get(i).Dano(10);
            		tiros.get(j).setAtivo(false);
            	}
            }
        }
		
	}
	
	public void AddTiros(){
		Rect r=jogador.getPos();
		Tiro tiro=new Tiro((r.left+r.right)/2, (r.top+r.bottom)/2);
		Parametros.efeitos.play(Parametros.soundID[0], Parametros.som, Parametros.som, 0, 0, 1);
		tiros.add(tiro);
	}
	
	public void UpdateTiros(){
		for(int i=0; i<tiros.size(); i++){
			tiros.get(i).Update();
			if(!tiros.get(i).getativo()){
				tiros.remove(i);
			}
		}
	}
	
	public void UpdateEnemy(){
		if((cont+5)%50==0)
			AddEnemy();
		
		for(int i=0; i<inimigos.size(); i++){
			inimigos.get(i).Update();
			if(!inimigos.get(i).isAtivo()){
				if(inimigos.get(i).getVida()<1){
					AddExplosion(inimigos.get(i).getPos());
					Parametros.efeitos.play(Parametros.soundID[1], Parametros.som, Parametros.som, 0, 0, 1);
					Parametros.score+=10;
				}
				inimigos.remove(i);
			}
		}
	}
	
	private void AddEnemy() {
		Enemy inimigo=new Enemy();
		inimigos.add(inimigo);
	}

	protected void onDraw(Canvas canvas){
		back.Draw(canvas);
		for(int i=0; i<tiros.size(); i++)
			tiros.get(i).Draw(canvas);
		for(int i=0; i<inimigos.size(); i++)
			inimigos.get(i).Draw(canvas);
		for(int i=0; i<explosao.size(); i++){
			explosao.get(i).Draw(canvas);
		}
		jogador.Draw(canvas);
		canvas.drawText("Vida: "+jogador.getVida(), 10, 10, paint);
		canvas.drawText("Pontuacao: "+Parametros.score, Parametros.SCREEN_WIDTH-80, 10, paint);
	}
	
	public void run(){
		try{
			while(!Parametros.gameover){
    			this.Update();
    			this.postInvalidate();
    			Thread.sleep(50);
			}
			this.postInvalidate();
			Message msg=new Message();
			msg.what=100;
			handler.sendMessage(msg);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	protected void onWindowVisibilityChanged(int visibility){
		if(visibility==View.GONE){
			try{
				Parametros.media.stop();
				Parametros.media.prepare();
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(visibility==View.INVISIBLE){
			Parametros.media.pause();
		}
	}
}
