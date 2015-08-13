package br.edu.ufabc.android.menu;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {
	private List<Fragment> frag;
	
	public PageAdapter(FragmentManager fm, List<Fragment> fragments){
		super(fm);
		this.frag = fragments;
	}
	
	@Override
	public Fragment getItem(int arg0) {
		return this.frag.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.frag.size();
	}

}
