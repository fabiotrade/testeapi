package uol.compass.teste.controller.dto;

import org.springframework.data.domain.Page;
import uol.compass.teste.modelo.Estado;

public class EstadosDto {

	private Long id;
	private String nome;
	private String regiao;
	private String populacao;
	private String Capital;
	private float area;

	public EstadosDto(Long id, String name, String region, String population, String capital, float areaSize) {
		this.id = id;
		this.nome = name;
		this.regiao = region;
		this.populacao = population;
		Capital = capital;
		this.area = areaSize;
	}

	public EstadosDto(Estado state) {

	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public String getPopulacao() {
		return populacao;
	}

	public void setPopulacao(String populacao) {
		this.populacao = populacao;
	}

	public String getCapital() {
		return Capital;
	}

	public void setCapital(String capital) {
		Capital = capital;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public static Page<EstadosDto> convert(Page<Estado> states) {
		return states.map(EstadosDto::new);
	}

}
