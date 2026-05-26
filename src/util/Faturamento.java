package util;

public class Faturamento {
    public static final double TAXA_SUITE = 0.15;

    public static double calculoTotalDiarias(double precoDiaria, int qtdDiasAgendados) {
        return precoDiaria * qtdDiasAgendados;
    }
    public static double calculoTaxa(String tipoQuarto, double precoDiaria, int qtdDiasAgendados) {
        if (tipoQuarto == "suite") {
            double tempCalculoTaxa = calculoTotalDiarias(precoDiaria, qtdDiasAgendados) * TAXA_SUITE;
            return calculoTotalDiarias(precoDiaria, qtdDiasAgendados) + tempCalculoTaxa;
        }
        }
    }
}

// falta somente fazer a parte dos calculos na main e aqui
