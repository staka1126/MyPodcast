package com.example.mypodcast;

public class Item {
	private CharSequence mTitle;
	private CharSequence mDescription;
	
	public Item(){
		mTitle = "title";
		mDescription = "test description";
	}
	
	public CharSequence getDescription(){
		return mDescription;
	}
	
	public CharSequence getTitle(){
		return mTitle;
	}
	
	public void setDescription(CharSequence descr){
		mDescription = descr;
	}
	
	public void setTitle(CharSequence title){
		mTitle = title;
	}
}