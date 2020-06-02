package br.com.dsocardoso.moneyapi.repository;

import br.com.dsocardoso.moneyapi.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    public Page<Pessoa> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
