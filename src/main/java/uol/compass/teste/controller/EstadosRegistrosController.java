package uol.compass.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import uol.compass.teste.controller.dto.EstadosDto;
import uol.compass.teste.controller.form.EstadosForm;
import uol.compass.teste.controller.form.AtualizacaoEstadosForm;
import uol.compass.teste.modelo.Regiao;
import uol.compass.teste.modelo.Estado;
import uol.compass.teste.repository.StateRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EstadosRegistrosController {
	
	@Autowired
	private StateRepository stateRepository;
	
	@GetMapping("/states")
	@Cacheable(value = "statesList")
	public Page<EstadosDto> list(@RequestParam(required = false) Regiao region,
								 @RequestParam(required = false) String population,
								 @RequestParam(required = false) String areaSize,
								 @PageableDefault(sort = "id", direction = Direction.ASC) Pageable pageable) {

		Page<Estado> state;
		if (region != null) {
			state = stateRepository.findByName(String.valueOf(region), pageable);
			return EstadosDto.convert(state);
		} else if (population != null){
		state = stateRepository.findByName(population, pageable);
		return EstadosDto.convert(state);
		} else if (areaSize != null) {
		state = stateRepository.findByName(areaSize, pageable);
		return EstadosDto.convert(state);
		} else {
			state = stateRepository.findAll(pageable);
			return EstadosDto.convert(state);
		}
	}
	
	@PostMapping("/states")
	@Transactional
	@CacheEvict(value = "stateList", allEntries = true)
	public ResponseEntity<EstadosDto> register(@RequestBody @Valid EstadosForm form, UriComponentsBuilder uriBuilder) {
		Estado state = form.converter();
		stateRepository.save(state);
		
		URI uri = uriBuilder.path("/api/states/{id}").buildAndExpand(state.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstadosDto(state));
	}
	
	@PutMapping("/states/{id}")
	@Transactional
	@CacheEvict(value = "stateList", allEntries = true)
	public ResponseEntity<EstadosDto> update(@PathVariable Long id, @RequestBody @Valid AtualizacaoEstadosForm form) {
		Optional<Estado> optional = stateRepository.findById(id);
		if (optional.isPresent()) {
			Estado state = form.update(id);
			return ResponseEntity.ok(new EstadosDto(state));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/states/{id}")
	@Transactional
	@CacheEvict(value = "stateList", allEntries = true)
	public ResponseEntity<?> remove(@PathVariable Long id) {
		Optional<Estado> optional = stateRepository.findById(id);
		if (optional.isPresent()) {
			stateRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
