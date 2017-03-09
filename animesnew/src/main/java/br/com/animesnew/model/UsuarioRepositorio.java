package br.com.animesnew.model;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

public interface UsuarioRepositorio extends Repositorio<Usuario>{

	@Cacheable(value = "usuarioCache", key="#username")
	public Usuario findByUserName( String username );
	
	public boolean desativaUsuario( long id );
	public boolean ativaUsuario( long id );
	
}
