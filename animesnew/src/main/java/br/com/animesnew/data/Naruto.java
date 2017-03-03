package br.com.animesnew.data;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Naruto implements Serializable {

	private static final long serialVersionUID = -8348495955941068927L;
	private long id;
	
	@NotNull
	@Size( min=5, max=16 )
	private String nome;
	@NotNull
	@Size( min=5, max=16 )
	private String sobrenome;
	@NotNull
	@Size( min=5, max=16 )
	private String golpe;

	public Naruto(String nome, String sobrenome, String golpe) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.golpe = golpe;
	}

	public Naruto(long id, String nome, String sobrenome, String golpe) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.golpe = golpe;
	}

	public Naruto() {

	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getGolpe() {
		return golpe;
	}

	public void setGolpe(String golpe) {
		this.golpe = golpe;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((golpe == null) ? 0 : golpe.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Naruto other = (Naruto) obj;
		if (golpe == null) {
			if (other.golpe != null)
				return false;
		} else if (!golpe.equals(other.golpe))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sobrenome == null) {
			if (other.sobrenome != null)
				return false;
		} else if (!sobrenome.equals(other.sobrenome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Naruto [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", golpe=" + golpe + "]";
	}

}
