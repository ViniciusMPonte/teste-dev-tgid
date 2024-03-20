# Projeto para Teste Dev - TGID

Este projeto é um sistema Java destinado a atender os requisitos de um teste para desenvolvedor, visando criar uma plataforma que permita transações entre clientes e empresas, com validação de CPF para clientes e CNPJ para empresas, gestão de saldos e taxas de administração.

## Endpoints

O projeto possui apenas uma rota, na qual os “Clientes” podem fazer depósitos e saques pelas Empresas.

- **PUT**`/client/transaction`: Envia uma requisição de transição.

### **Request Body**

Para realizar a requisição é necessário enviar 4 valores:

- `cpf` CPF do cliente
- `cnpj` CNPJ da empresa
- `transactionType` Tipo de Transação, que pode ser saque (`withdrawal`) ou depósito  `(deposit`)
- `value` Valor da transação.

```
//Exemplo de Requisição
{
    "cpf": "237.875.775-13",
    "cnpj": "11.163.754/0001-89",
    "transactionType": "deposit",
    "value": 5000.00
}
```

### Possíveis respostas

O retorno pode assumir diferentes valores dependendo das condições de execução:

1. **CPF inválido**:
   - Se o CPF fornecido na requisição não for válido, é definida uma mensagem indicando que o CPF é inválido e retorna o objeto `request` com a mensagem definida.
2. **CNPJ inválido**:
   - Se o CNPJ fornecido na requisição não for válido, é definida uma mensagem indicando que o CNPJ é inválido e retorna o objeto `request` com a mensagem definida.
3. **CPF não encontrado**:
   - Se não for encontrado um cliente associado ao CPF fornecido na requisição, é definida uma mensagem indicando que o CPF não foi encontrado e retorna o objeto `request` com a mensagem definida.
4. **CNPJ não encontrado**:
   - Se não for encontrada uma empresa associada ao CNPJ fornecido na requisição, é definida uma mensagem indicando que o CNPJ não foi encontrado e retorna o objeto `request` com a mensagem definida.
5. **Transação inválida**:
   - Se o tipo de transação fornecido na requisição não for válido (diferente de "withdrawal" ou "deposit"), é definida uma mensagem indicando que a transação é inválida e retorna o objeto `request` com a mensagem definida.
6. **Saldo insuficiente**:
   - Se o saldo da empresa após a transação for menor que zero, é definida uma mensagem indicando que o saldo é insuficiente e retorna o objeto `request` com a mensagem definida.
7. **Transação realizada com sucesso**:
   - Se a transação for válida e o saldo da empresa após a transação for maior ou igual a zero, a transação é realizada com sucesso, uma mensagem indicando isso é definida e retorna o objeto `request` com a mensagem definida.

# Banco de Dados

O banco de dados H2 possui duas tabelas principais: `tb_client` e `tb_company`, que armazenam informações sobre clientes e empresas, respectivamente. Além disso, há uma tabela de relacionamento `tb_client_company` que associa clientes a empresas, indicando quais empresas cada cliente está vinculado.

![tb_company](https://raw.githubusercontent.com/ViniciusMPonte/teste-dev-tgid/main/imgs/tb_company.PNG)
![tb_client](https://raw.githubusercontent.com/ViniciusMPonte/teste-dev-tgid/main/imgs/tb_client.PNG)
![tb_client_company](https://raw.githubusercontent.com/ViniciusMPonte/teste-dev-tgid/main/imgs/tb_client_company.PNG)

# Taxas de Sistema

Cada empresa na tabela `tb_company` possui informações sobre seu saldo atual e suas taxas de saque e depósito. Ao realizar uma transação, o sistema aplica essas taxas de acordo com o tipo de transação.

Elas são aplicadas durante as transações realizadas pelo método `makingTransaction`, podendo variar de empresa para empresa.

‌

```
if (transactionType.equals("withdrawal")) {
    Double fee = company.getWithdrawalFee() * request.getValue();
    balance = balance - request.getValue() - fee;
} else if (transactionType.equals("deposit")) {
    Double fee = company.getDepositFee() * request.getValue();
    balance = balance + request.getValue() - fee;
}
```

## **Validação de CPF e CNPJ**

Neste código, foram implementadas duas classes, `CPFValidator` e `CNPJValidator`, que têm como objetivo validar números de CPF (Cadastro de Pessoas Físicas) e CNPJ (Cadastro Nacional da Pessoa Jurídica), respectivamente.

**CPFValidator:**

- O método `isValid` recebe uma String representando um CPF e executa as seguintes validações:
  1. Remove caracteres especiais e espaços em branco da String do CPF.
  2. Verifica se o CPF possui 11 dígitos.
  3. Verifica se todos os dígitos são iguais, o que invalidaria o CPF.
  4. Calcula e verifica os dígitos verificadores do CPF.

**CNPJValidator:**

- O método `isValid` recebe uma String representando um CNPJ e realiza as seguintes validações:
  1. Remove caracteres não numéricos da String do CNPJ.
  2. Verifica se o CNPJ possui 14 dígitos.
  3. Verifica se todos os dígitos são iguais, o que invalidaria o CNPJ.
  4. Calcula e verifica os dígitos verificadores do CNPJ.

## Como executar o projeto

### Pré-requisitos

Para operar adequadamente, é necessário possuir o JDK 17 e o Apache Maven 3.0.1 devidamente instalados em seu computador.

### Instalação

Para executar o projeto, siga as etapas abaixo:

1. Clone o repositório em sua máquina local;
2. Extraia os arquivos, abra o terminal mesma pasta do projeto e digite os seguites comandos:

```
mvn clean install
mvn spring-boot:run
```
### Contato

- E-mail: vinicius.mponte@gmail.com
