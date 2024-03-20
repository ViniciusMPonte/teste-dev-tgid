package dev.tgid.teste.utils;

public class CNPJValidator {
    
    public static boolean isValid(String cnpj) {
        // Remove caracteres não numéricos
        cnpj = cnpj.replaceAll("[^0-9]", "");
        
        // Verifica se a string tem tamanho igual a 14
        if (cnpj.length() != 14) {
            return false;
        }
        
        // Verifica se todos os dígitos são iguais, o que invalidaria o CNPJ
        if (cnpj.matches("(\\d)\\1*")) {
            return false;
        }
        
        // Calcula o primeiro dígito verificador
        int soma = 0;
        int peso = 5;
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * peso;
            peso--;
            if (peso < 2) {
                peso = 9;
            }
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }
        
        // Calcula o segundo dígito verificador
        soma = 0;
        peso = 6;
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * peso;
            peso--;
            if (peso < 2) {
                peso = 9;
            }
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }
        
        // Verifica se os dígitos calculados são iguais aos dígitos informados
        return (cnpj.charAt(12) - '0') == digito1 && (cnpj.charAt(13) - '0') == digito2;
    }
}