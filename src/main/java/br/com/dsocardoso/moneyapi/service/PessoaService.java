package br.com.dsocardoso.moneyapi.service;

import br.com.dsocardoso.moneyapi.model.Pessoa;
import br.com.dsocardoso.moneyapi.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long id, Pessoa pessoa) {
        Pessoa pessoaSalva = buscarPessoaPorId(id);

        BeanUtils.copyProperties(pessoa, pessoaSalva, "id");

        return this.pessoaRepository.save(pessoaSalva);
    }

    public void atualizarAtivo(Long id, Boolean ativo) {
        Pessoa pessoaSalva = buscarPessoaPorId(id);
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }

    private Pessoa buscarPessoaPorId(Long id) {
        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(id);
        if(pessoaSalva.isPresent()){
            return pessoaSalva.get();
        }
        throw new EmptyResultDataAccessException(1);
    }
}
