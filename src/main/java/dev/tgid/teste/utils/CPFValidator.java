package dev.tgid.teste.utils;

public class CPFValidator {

    public static boolean isValid(String cpf) {
        // Remover caracteres especiais e espaços em branco
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verificar se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcular e verificar o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int resto = soma % 11;
        int digitoVerificador1 = (resto < 2) ? 0 : (11 - resto);
        if (digitoVerificador1 != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        // Calcular e verificar o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        resto = soma % 11;
        int digitoVerificador2 = (resto < 2) ? 0 : (11 - resto);
        if (digitoVerificador2 != Character.getNumericValue(cpf.charAt(10))) {
            return false;
        }

        return true;
    }

}
