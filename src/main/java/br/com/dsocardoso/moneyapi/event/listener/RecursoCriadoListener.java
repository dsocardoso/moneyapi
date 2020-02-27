package br.com.dsocardoso.moneyapi.event.listener;

import br.com.dsocardoso.moneyapi.event.RecursoCriadoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {
    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {

        HttpServletResponse response = recursoCriadoEvent.getResponse();
        Long id = recursoCriadoEvent.getId();

        adicionarLocation(response, id);

    }
    private void adicionarLocation(HttpServletResponse response, Long id){

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{/id")
                .buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
