package br.edu.ufabc.android.shootergenerico;

import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Parametros {
	
	public static float SCREEN_WIDTH;
	public static float SCREEN_HEIGHT;
	public static float Distorcao;
	
	public static final float IMG_WIDTH  = 800;
	public static final float IMG_HEIGHT = 480;
	
	public static AssetManager assetman;
	public static MediaPlayer media;
	public static SoundPool efeitos;
	
	public static int[] soundID;
	public static int[] streamID;
	
	public static float som;
	
	public static int score;
	public static boolean gameover;
}
