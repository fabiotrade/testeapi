package uol.compass.teste.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uol.compass.teste.modelo.Estado;

public interface StateRepository extends JpaRepository<Estado, Long> {

	Page<Estado> findByName(String name, Pageable pageable);

	Object findByName(String name);
}
