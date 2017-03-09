package br.com.animesnew.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.animesnew.config.RootConfig;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { RootConfig.class } )
public class UsuarioSecurityTest {

	@Autowired
	private UsuarioService service;
	
	@Before
	public void clearContext(){
		SecurityContextHolder.clearContext();
	}
	
	@Test
	public void test(){
		System.out.println( "sdf fsdfsd" );
	}
	
}
