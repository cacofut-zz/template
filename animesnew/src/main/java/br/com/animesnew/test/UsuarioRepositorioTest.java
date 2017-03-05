package br.com.animesnew.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.animesnew.config.DataConfig;
import br.com.animesnew.config.RootConfig;
import br.com.animesnew.model.Usuario;
import br.com.animesnew.model.UsuarioRepositorio;
import br.com.animesnew.model.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@ActiveProfiles("development")
public class UsuarioRepositorioTest {

	@Autowired
	private UsuarioService usarioService;

	// @Test
	@Transactional
	public void teste() {

		Usuario teste = new Usuario("juquinha", "123456", true);
		usarioService.save(teste);
	}

	@Test
	// @Transactional
	public void listarTodos() {

		List<Usuario> lista = usarioService.list();
		for (Usuario usuario : lista) {
			System.out.println(usuario);

		}
		System.out.println();

		List<Usuario> lista2 = usarioService.list();
		for (Usuario usuario : lista2) {
			System.out.println(usuario);

		}
		System.out.println();

	}

	// @Test
	// @Transactional
	public void testeUpdate() {
		Usuario usuario = new Usuario(10, "juquinha", "123456", true);
		usarioService.update(usuario);
	}

	// @Test
	public void listarUsuario() {
		Usuario usuario = usarioService.find(10);
		System.out.println(usuario);
	}
}
