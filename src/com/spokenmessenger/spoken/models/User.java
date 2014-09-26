package com.spokenmessenger.spoken.models;

import com.parse.LogInCallback;
import com.parse.ParseClassName;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class User{

	private ParseUser pUser;
	private String userId;
	private String objectId;
	private String username;
	private String password;
	private String displayName;
	private Integer invitesSent;
	
	public User() {
		pUser = new ParseUser();
	}
	
	public void signUpInBackground(SignUpCallback signUpCallback){
		pUser.signUpInBackground(signUpCallback);	
	}
	
	public void logInBackground(String username, String password, LogInCallback logInCallback){
		pUser.logInInBackground(username, password, logInCallback);
	}
	
	public static User getCurrentUser(){
		User u = new User();
		u.pUser = ParseUser.getCurrentUser();
		return u;
	}
}

//User currentUser = User.getCurrent();
//currentUser.invitesSent++;
//currentUser.save();

/*
 * 
 * 

String username;
String password;

User user = new User();
user.signUpInBackground(new SignUpCallBack() {
	publc void done() {
		
	}
})*/