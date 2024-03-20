package dev.tgid.teste.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_company")
public class Company {

    @Id
    private String cnpj;
    private String name;
    private Double balance;
    private Double withdrawalFee;
    private Double depositFee;

    @JsonIgnore
    @ManyToMany(mappedBy = "companies")
    private List<Client> clients;

    public Company() {

    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Double getWithdrawalFee() {
        return withdrawalFee;
    }

    public void setWithdrawalFee(Double withdrawalFee) {
        this.withdrawalFee = withdrawalFee;
    }

    public Double getDepositFee() {
        return depositFee;
    }

    public void setDepositFee(Double depositFee) {
        this.depositFee = depositFee;
    }

}
