package dev.tgid.teste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.tgid.teste.model.Client;

public interface ClientRepository extends JpaRepository<Client, String>{

    
}