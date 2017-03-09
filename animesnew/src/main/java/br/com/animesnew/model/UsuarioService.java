package br.com.animesnew.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PreAuthorize("(hasRole('ROLE_ADMIN'))")
	public Usuario save(Usuario usuario) {
		usuario.setSenha(encrypt(usuario.getSenha()));
		return usuarioRepositorio.save(usuario);
	}

	@PreAuthorize("(hasRole('ROLE_ADMIN'))")
	public List<Usuario> list() {
		return usuarioRepositorio.list();
	}

	@PreAuthorize( "(hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and #usuario.usuario == authentication.name))" )
	public Usuario update(Usuario usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setSenha(encrypt(usuario.getSenha()));
		return usuarioRepositorio.update(usuario);
	}

	
	/*tenho que ver a propriedade returnObject*/
	@PostAuthorize( "(hasRole('ROLE_ADMIN') or (hasRole('ROLE_USER') and returnObject.usuario == authentication.name))" )
	public Usuario find(long id) {
		return usuarioRepositorio.findOne(id);
	}

	
	public boolean delete(Usuario usuario) {
		return usuarioRepositorio.delete(usuario);
	}

	public Usuario findByUserName(String username) {
		return usuarioRepositorio.findByUserName(username);
	}

	private String encrypt(String senha) {
		return passwordEncoder.encode(senha);
	}
}
