package br.com.animesnew.model;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.mysql.fabric.xmlrpc.base.Value;

public interface Repositorio<T> {

	@Cacheable( value = "usuarioCache" )
	public List<T> list();
	public T save( T objeto );
	public boolean delete( T objeto );
	public T update( T objeto );
	public T findOne( long id );
	
}
