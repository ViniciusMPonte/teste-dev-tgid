package dev.tgid.teste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.tgid.teste.model.Client;
import dev.tgid.teste.repositories.ClientRepository;

import dev.tgid.teste.model.Company;
import dev.tgid.teste.repositories.CompanyRepository;
import dev.tgid.teste.utils.CPFValidator;
import dev.tgid.teste.utils.CNPJValidator;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @PutMapping(value = "/transaction")
    public Object makingTransaction(@RequestBody TransactionRequest request) {


        if (!CPFValidator.isValid((request.getCpf()))) {
            request.setMessage("CPF inválido.");
            return request;
        } else if (!CNPJValidator.isValid((request.getCnpj()))) {
            request.setMessage("CNPJ inválido.");
            return request;
        }

        @SuppressWarnings("null")
        Optional<Client> clientOptional = clientRepository.findById(request.getCpf());

        if (clientOptional.isEmpty()) {
            request.setMessage("Transação cancelada. CPF não encontrado.");
            return request;
        }

        Client client = clientOptional.get();

        Company company = TransactionRequest.findCompanyByCnpj(request.getCnpj(), client.getCompanies());

        if (company == null) {
            request.setMessage("Transação cancelada. CNPJ não encontrado.");
            return request;
        }

        Double balance = company.getBalance();
        String transactionType = request.getTransactionType();

        if (transactionType.equals("withdrawal")) {

            Double fee = company.getWithdrawalFee() * request.getValue();
            balance = balance - request.getValue() - fee;

        } else if (transactionType.equals("deposit")) {

            Double fee = company.getDepositFee() * request.getValue();
            balance = balance + request.getValue() - fee;

        } else {

            request.setMessage("Transação cancelada. Transação inválida.");
            return request;
            
        }

        if (balance < 0) {
            request.setMessage("Transação cancelada. Saldo Insuficiente.");
            return request;
        }

        company.setBalance(balance);
        companyRepository.save(company);

        request.setMessage("Transação realizada com sucesso.");
        return request;
    }
}

class TransactionRequest {

    private String cpf;
    private String cnpj;
    private String transactionType;
    private Double value;
    private String message = "";

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Company findCompanyByCnpj(String cnpj, List<Company> companies) {
        for (Company company : companies) {
            if (company.getCnpj().equals(cnpj)) {
                return company;
            }
        }
        return null;
    }

}
