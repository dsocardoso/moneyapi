package br.com.dsocardoso.moneyapi.repository;

import br.com.dsocardoso.moneyapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
