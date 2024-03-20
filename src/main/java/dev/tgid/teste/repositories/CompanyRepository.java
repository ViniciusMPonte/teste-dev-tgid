package dev.tgid.teste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.tgid.teste.model.Company;

public interface CompanyRepository extends JpaRepository<Company, String>{

    
}