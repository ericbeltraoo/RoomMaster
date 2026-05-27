package util;

public class Faturamento {
    public static final double TAXA_SUITE = 0.15;

    public static double calculoTotalDiarias(double precoDiaria, int qtdDiasAgendados) {
        return precoDiaria * qtdDiasAgendados;
    }
    public static double calculoTaxa(double precoDiaria, int qtdDiasAgendados) {
            double tempCalculoTaxa = calculoTotalDiarias(precoDiaria, qtdDiasAgendados) * TAXA_SUITE;
            return calculoTotalDiarias(precoDiaria, qtdDiasAgendados) + tempCalculoTaxa;
    }
    public static String relatorioFinanceiroTaxa(double precoDiaria, int qtdDiasAgendados, String tipoQuarto) {
        return "---- RELATÓRIO FINANCEIRO ----"
                +"\nTipo Quarto: " + tipoQuarto
                + "\n" + String.format("Preço da diária: R$ %.2f", precoDiaria)
                + "\nQuantidade de dias reservados: " + qtdDiasAgendados
                + "\n" + String.format("Valor a pagar: %.2f", calculoTaxa(precoDiaria, qtdDiasAgendados));
    }

    public static String relatorioFinanceiro(double precoDiaria, int qtdDiasAgendados, String tipoQuarto) {
        return "---- RELATÓRIO FINANCEIRO ----"
                +"\nTipo Quarto: " + tipoQuarto
                + "\n" + String.format("Preço da diária: R$ %.2f", precoDiaria)
                + "\nQuantidade de dias reservados: " + qtdDiasAgendados
                + "\n" + String.format("Valor a pagar: %.2f", calculoTotalDiarias(precoDiaria, qtdDiasAgendados));
    }
}

// colocar para escrever novamente apos erros com else fora do while e verificar os de dentro do while