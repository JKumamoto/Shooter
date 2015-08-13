package br.edu.ufabc.android.support;

public class Score {
	private String name;
	private int sco;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSco() {
		return sco;
	}

	public void setSco(int sco) {
		this.sco = sco;
	}

	public Score(){
		this.name = "";
		sco = 0;
	}
	
	public Score(String n, int s){
		this.name = n;
		this.sco = s;
	}
}
