package br.com.animesnew.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class NarutoRepositoryImpl implements NarutoRepository{

	public List<Naruto> getList() {
		List<Naruto> narutos = new ArrayList<Naruto>();
		
		Naruto naruto1 = new Naruto( 1, "cristiano", "amaral", "rasegan" );
		Naruto naruto2 = new Naruto( 2, "leonardo", "amaral", "rasegan" );
		Naruto naruto3 = new Naruto( 3, "juliana", "amaral", "rasegan" );
		Naruto naruto4 = new Naruto( 4, "carlos", "amaral", "rasegan" );
		
		narutos.add( naruto1 );
		narutos.add( naruto2 );
		narutos.add( naruto3 );
		narutos.add( naruto4 );
		
		return narutos;
	}

	
}
