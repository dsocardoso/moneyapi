package br.com.dsocardoso.moneyapi.repository;

import br.com.dsocardoso.moneyapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {

    public Optional<Usuario> findByEmail (String email);
}
