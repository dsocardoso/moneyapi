package br.com.dsocardoso.moneyapi.resource;

import br.com.dsocardoso.moneyapi.event.RecursoCriadoEvent;
import br.com.dsocardoso.moneyapi.model.Lancamento;
import br.com.dsocardoso.moneyapi.repository.LancamentoRepository;
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
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<?> listarLancamentos(){
        List<Lancamento> lancamentos = lancamentoRepository.findAll();
        return !lancamentos.isEmpty() ? ResponseEntity.ok(lancamentos) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> buscarPeloId(@PathVariable Long id){
        Optional<Lancamento> lancamento = lancamentoRepository.findById(id);
        return lancamento.isPresent()? ResponseEntity.ok(lancamento.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Lancamento> incluirLancamento(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this,response,lancamentoSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);

    }

}
