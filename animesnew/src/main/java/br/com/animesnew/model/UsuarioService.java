package br.com.animesnew.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Usuario save(Usuario usuario) {
		usuario.setSenha(encrypt(usuario.getSenha()));
		return usuarioRepositorio.save(usuario);
	}

	
	public List<Usuario> list() {
		return usuarioRepositorio.list();
	}

	public Usuario update(Usuario usuario) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		usuario.setSenha(encrypt(usuario.getSenha()));
		return usuarioRepositorio.update(usuario);
	}

	public Usuario find(long id) {
		return usuarioRepositorio.findOne(id);
	}

	private String encrypt(String senha) {
		return passwordEncoder.encode(senha);
	}
}
