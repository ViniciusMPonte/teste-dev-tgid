package dev.tgid.teste.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    private String cpf;
    private String name;

    @ManyToMany
        @JoinTable(
        name = "tb_client_company",
        joinColumns = @JoinColumn(name = "client_cpf"),
        inverseJoinColumns = @JoinColumn(name = "company_cnpj")
    )
    private List<Company> companies;

    public Client() {

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
}