package br.com.animesnew.model;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.mysql.fabric.xmlrpc.base.Value;

public interface Repositorio<T> {

	@Cacheable(value = "usuarioCache")
	public List<T> list();

	@CacheEvict(value="usuarioCache", allEntries = true )
	public T save(T objeto);

	@CacheEvict(value="usuarioCache", allEntries = true)
	public boolean delete(T objeto);

	@CacheEvict(value="usuarioCache", allEntries = true)
	public T update(T objeto);

	@Cacheable(value = "usuarioCache", key="#id")
	public T findOne(long id);

}
