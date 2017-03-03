package br.com.animesnew.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestPasswordEncoder {
	
	
	public static void main( String args[] ){
		
		int i = 0;
		
		while( i < 10 ){
			
			String password = "12345689";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hash = passwordEncoder.encode( password );
			System.out.println( hash );
			i++;
		}
	}

}
