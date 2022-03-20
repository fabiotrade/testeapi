package uol.compass.teste.controller.form;

import org.springframework.beans.factory.annotation.Autowired;
import uol.compass.teste.modelo.Regiao;
import uol.compass.teste.modelo.Estado;
import uol.compass.teste.repository.StateRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizacaoEstadosForm {

	@Autowired
	private StateRepository stateRepository;
	
	@NotNull @NotEmpty
	private String name;
	
	private Regiao region;

	@NotNull @NotEmpty
	private String population;

	@NotNull @NotEmpty
	private String capital;

	@NotNull @NotEmpty
	private Long areaSize;

	public void setName(String name) {
		this.name = name;
	}

	public void setRegion(Regiao region) {
		this.region = region;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public void setAreaSize(Long areaSize) {
		this.areaSize = areaSize;
	}

	public <stateRepository> Estado update(Long id) {
		Estado state = stateRepository.getById(id);

		state.setName(this.name);
		state.setRegion(this.region);
		state.setPopulation(this.population);
		state.setCapital(this.capital);
		state.setAreaSize(this.areaSize);
		
		return state;
	}
	
}
