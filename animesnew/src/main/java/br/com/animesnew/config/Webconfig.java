package br.com.animesnew.config;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.expression.common.TemplateAwareExpressionParser;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import br.com.animesnew.data.NarutoRepository;
import br.com.animesnew.data.NarutoRepositoryImpl;

@Configuration
@EnableWebMvc
@ComponentScan( "br.com" )
public class Webconfig extends WebMvcConfigurerAdapter implements ApplicationContextAware{
	
	private ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext ) throws BeansException {
		this.applicationContext = applicationContext; 
	}
	
	/*@Bean
	public ViewResolver internalViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix( "/WEB-INF/views/" );
		resolver.setSuffix( ".jsp" );
		resolver.setExposeContextBeansAsAttributes( true );
		return resolver;
	}*/
	
	
	@Bean
	@Primary
	public ViewResolver thymeleafViewResolver(){
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine( templateEngine() );
		viewResolver.setCharacterEncoding( "UTF-8" );
		return viewResolver;
	}	
	
	@Bean
	public TemplateEngine templateEngine(){
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setEnableSpringELCompiler( true );
		engine.setTemplateResolver( templateResolver() );
		engine.addDialect( new SpringSecurityDialect() );
		return engine;
	}
	
	@Bean
	public ITemplateResolver templateResolver(){
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext( applicationContext );
		resolver.setPrefix( "/WEB-INF/templates/" );
		resolver.setSuffix( ".html" );
		resolver.setTemplateMode( TemplateMode.HTML );
		return resolver;
	}
	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public MultipartResolver multipartResolver() throws IOException{
		return new StandardServletMultipartResolver();
	}
	
	
	
}
