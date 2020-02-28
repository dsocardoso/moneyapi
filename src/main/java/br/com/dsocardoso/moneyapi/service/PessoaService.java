package br.com.dsocardoso.moneyapi.service;

import br.com.dsocardoso.moneyapi.model.Pessoa;
import br.com.dsocardoso.moneyapi.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualziar(Long id, Pessoa pessoa) {
        Pessoa pessoaSalva = this.pessoaRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        BeanUtils.copyProperties(pessoa, pessoaSalva, "id");

        return this.pessoaRepository.save(pessoaSalva);
    }
}
