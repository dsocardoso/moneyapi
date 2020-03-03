package br.com.dsocardoso.moneyapi.service;

import br.com.dsocardoso.moneyapi.model.Lancamento;
import br.com.dsocardoso.moneyapi.model.Pessoa;
import br.com.dsocardoso.moneyapi.repository.LancamentoRepository;
import br.com.dsocardoso.moneyapi.repository.PessoaRepository;
import br.com.dsocardoso.moneyapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    public Lancamento salvar(Lancamento lancamento)  {
        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(lancamento.getPessoa().getId());
        if(!pessoaSalva.isPresent()){
             throw  new PessoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }
}
