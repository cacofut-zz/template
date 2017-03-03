package br.com.animesnew.test;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceView;


import br.com.animesnew.config.Webconfig;
import br.com.animesnew.controller.FileUploadController;
import br.com.animesnew.controller.HomeController;
import br.com.animesnew.data.Naruto;
import br.com.animesnew.data.NarutoRepository;
import br.com.animesnew.util.Upload;


@WebAppConfiguration
@ContextConfiguration(classes = Webconfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class HomeControllerTest {

	@Autowired
	WebApplicationContext webApplicationContext;
	
	@Test
	public void testHomePage() throws Exception {

		
		List<Naruto> narutos = createNarutos();
		NarutoRepository mockRepository = mock( NarutoRepository.class );
		when( mockRepository.getList() ).thenReturn( narutos );
		
		
		HomeController controller = new HomeController( mockRepository );
		MockMvc mockMvc = standaloneSetup( controller )
				.setSingleView( new InternalResourceView( "/WEB-INF/views/home.jsp" ) )
				.build();
		
		mockMvc.perform( get( "/" ) )
		.andExpect( view().name( "home" ) )
		.andExpect( model().attributeExists( "narutos" ) )
		.andExpect( model().attribute( "narutos", hasItems( narutos.toArray()) ) );
		
		System.out.println( hasItems( narutos.toArray()) );
		

	}
	
	

	private List<Naruto> createNarutos() {
		
		List<Naruto> narutos = new ArrayList<Naruto>();

		Naruto naruto1 = new Naruto(1, "cristiano", "amaral", "rasegan");
		Naruto naruto2 = new Naruto(2, "leonardo", "amaral", "rasegan");
		Naruto naruto3 = new Naruto(3, "juliana", "amaral", "rasegan");
		Naruto naruto4 = new Naruto(4, "carlos", "amaral", "rasegan");

		narutos.add(naruto1);
		narutos.add(naruto2);
		narutos.add(naruto3);
		narutos.add(naruto4);

		return narutos;
		
	}
	
}
