package br.com.dsocardoso.moneyapi.repository.lancamento;

import br.com.dsocardoso.moneyapi.model.Lancamento;
import br.com.dsocardoso.moneyapi.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar (LancamentoFilter lancamentoFilter);

}
