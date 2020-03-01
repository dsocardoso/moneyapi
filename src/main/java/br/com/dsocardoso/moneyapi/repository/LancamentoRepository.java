package br.com.dsocardoso.moneyapi.repository;

import br.com.dsocardoso.moneyapi.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
