package com.spokenmessenger.spoken.models;

import com.parse.LogInCallback;
import com.parse.ParseClassName;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class User{

	public ParseUser pUser;
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
	
	public static void logInBackground(String username, String password, LogInCallback logInCallback){
		ParseUser.logInInBackground(username, password, logInCallback);
	}
	
	public static User getCurrentUser(){
		User u = new User();
		u.pUser = ParseUser.getCurrentUser();
		return u;
	}
}
