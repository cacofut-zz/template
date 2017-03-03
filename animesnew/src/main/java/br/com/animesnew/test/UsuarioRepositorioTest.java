package br.com.animesnew.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.animesnew.config.DataConfig;
import br.com.animesnew.model.UsuarioRepositorio;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes={ DataConfig.class} )
public class UsuarioRepositorioTest {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Test
	public void teste(){
		
		System.out.println( usuarioRepositorio );
	}
}
