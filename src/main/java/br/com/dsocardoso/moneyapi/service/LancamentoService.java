package br.com.dsocardoso.moneyapi.service;

import br.com.dsocardoso.moneyapi.model.Lancamento;
import br.com.dsocardoso.moneyapi.model.Pessoa;
import br.com.dsocardoso.moneyapi.repository.LancamentoRepository;
import br.com.dsocardoso.moneyapi.repository.PessoaRepository;
import br.com.dsocardoso.moneyapi.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.BeanUtils;
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

    public Lancamento atualizar(Long codigo, Lancamento lancamento) {
        Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
        if(!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())){
            validarPessoa(lancamento);
        }

        BeanUtils.copyProperties(lancamento,lancamentoSalvo,"codigo");
        return lancamentoRepository.save(lancamentoSalvo);
    }

    private void validarPessoa(Lancamento lancamento) {
        Optional<Pessoa> pessoa = null;
        if(lancamento.getPessoa().getId() != null) {
            pessoa = pessoaRepository.findById(lancamento.getPessoa().getId());
        }

        if(pessoa == null || pessoa.get().isInativo()){
            throw new PessoaInexistenteOuInativaException();
        }
    }

    private Lancamento buscarLancamentoExistente(Long codigo) {
        Optional<Lancamento> lancamentoSalvo = lancamentoRepository.findById(codigo);
        if(!lancamentoSalvo.isPresent()){
            throw new IllegalArgumentException();
        }
        return lancamentoSalvo.get();
    }

}
