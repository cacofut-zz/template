package br.com.animesnew.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.animesnew.config.RootConfig;
import br.com.animesnew.model.Usuario;
import br.com.animesnew.model.UsuarioService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@ActiveProfiles("development")
public class UsuarioSecurityTest {

	@Autowired
	private UsuarioService service;

	@Before
	public void clearContext() {
		SecurityContextHolder.clearContext();
	}

	@Test
	public void testando() {
		System.out.println("testando");
	}

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void testSecuredMethod_noCredentials() {
		Usuario usuario = new Usuario("limeira2", "123456", true);
		service.save(usuario);
	}

	@Test(expected = AccessDeniedException.class)
	public void testSecuredMethod_insufficentPrivilege() {
		setupUser("ROLE_USER");

		Usuario usuario = new Usuario("lenuca1", "123456", true);
		service.save(usuario);
	}

	@Test
	public void testSecuredMethod_withSufficientPrivilege() {
		setupUser("ROLE_ADMIN");

		Usuario usuario = new Usuario("juraci", "123456", true);
		service.save(usuario);
	}

	@Test(expected = AccessDeniedException.class)
	public void testSecuredMethod_withSufficientPrivilegeButLongText() {
		setupUser("ROLE_USER");
		
		Usuario usuario = new Usuario("juracaraiba", "123456", true);
		service.save(usuario);

	}
	
	
	@Test(expected = AccessDeniedException.class)
	public void testa_update_seguro_sem_privelegio_e_usuario_diferente(){
		setupUser("ROLE_USER");		
		Usuario usuario = new Usuario( 1, "cristiano", "123456", false);
		service.update(usuario);

	}
	
	@Test
	public void testa_update_seguro_com_privelegio_user_e_usuario_igual(){
		setupUser("ROLE_USER");		
		Usuario usuario = new Usuario( 4, "marcio", "123456", true);
		service.update(usuario);

	}
	
	
	
	@Test(expected = AccessDeniedException.class)
	public void testa_find_seguro_com_privelegio_e_usuario_diferente(){
		setupUser("ROLE_USER");		
		service.find(1);

	}
	
	@Test
	public void testa_find_seguro_com_privelegio__admin_e_usuario_diferente(){
		setupUser("ROLE_ADMIN");		
		service.find(1);

	}


	private void setupUser(String... privs) {

		SecurityContext securityContext = SecurityContextHolder.getContext();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String priv : privs) {
			authorities.add(new SimpleGrantedAuthority(priv));
		}
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("marcio",
				"123456", authorities);
		securityContext.setAuthentication(authenticationToken);
	}
}
