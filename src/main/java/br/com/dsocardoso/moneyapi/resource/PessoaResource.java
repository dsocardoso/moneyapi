package br.com.dsocardoso.moneyapi.resource;

import br.com.dsocardoso.moneyapi.event.RecursoCriadoEvent;
import br.com.dsocardoso.moneyapi.model.Pessoa;
import br.com.dsocardoso.moneyapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping
    public ResponseEntity<Pessoa> criar (@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPeloId(@PathVariable Long id){
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.isPresent() ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> listar(){
        List<Pessoa> listPessoas = pessoaRepository.findAll();
        return !listPessoas.isEmpty() ? ResponseEntity.ok((listPessoas)) : ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover (@PathVariable Long id){
        pessoaRepository.deleteById(id);
    }
}
