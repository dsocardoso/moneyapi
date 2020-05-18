package br.com.dsocardoso.moneyapi.repository.lancamento;

import br.com.dsocardoso.moneyapi.repository.filter.LancamentoFilter;
import br.com.dsocardoso.moneyapi.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {
    //Alterado de List para Page o tipo da função
    public Page filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
    public Page <ResumoLancamento> resumir (LancamentoFilter lancamentoFilter, Pageable pageable);

}
