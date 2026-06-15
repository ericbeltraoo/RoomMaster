package application;

import entities.Hospede;
import entities.Quarto;
import entities.Reserva;
import util.Faturamento;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void pausar() {
        System.out.println("Pressione enter para continuar... ");
        Scanner pausar = new Scanner (System.in);
        pausar.nextLine();
    }

    public static void verificarVetores() {

    }

    public static Quarto cadastrarQuarto(Scanner input, int numero) {
        boolean passar = true;
        String tipo;
        double precoDiaria;
        Quarto quartoConstruido = null;

        while (passar) {
            System.out.println("Digite o tipo do quarto: (Standard ou Suite)");
            tipo = input.nextLine().toLowerCase();
            if(!(tipo.equals("standard") || tipo.equals("suite"))) {
                System.out.println("ERRO: Digite um tipo de quarto válido.");
                pausar();
            } else {
                System.out.println("Digite o preço da diária: ");
                precoDiaria = input.nextDouble();
                input.nextLine();
                quartoConstruido = new Quarto(numero,tipo,precoDiaria);
                passar = false;
            }
        }
        return quartoConstruido;
    }

    public static Hospede cadastrarHospede(Scanner input) {
        String nomeHospede, cpf, rg;
        Hospede hospedeNovo;

        System.out.println("Digite o nome do hóspede:");
        nomeHospede = input.nextLine();

        System.out.println("Digite o cpf do hóspede: ");
        cpf = input.next();
        input.nextLine();

        System.out.println("Digite o rg do hóspede: ");
        rg = input.next();
        input.nextLine();

        hospedeNovo = new Hospede(nomeHospede,cpf,rg);
        return hospedeNovo;
    }

    public static boolean pagamentoCheckout(Scanner input, Reserva reserva , Quarto[] vectQuarto, int n){

        System.out.println("\n---- PAGAMENTOS ----\n");
        System.out.println("1 - Para realizar o pagamento");
        System.out.println("2 - Para voltar ao menu de interação");
        int opcaoPagamento = input.nextInt();

        if (opcaoPagamento == 1) {
            System.out.println("Pagamento realizado com sucesso!");
            reserva.setStatusReserva("Finalizada");
            vectQuarto[n].setStatusAtual("Disponivel");
            pausar();
            return false;
        } else if (opcaoPagamento == 2) {
            System.out.println("Redirecionando...");
            pausar();
            return true;
        } else {
            System.out.println("ERRO: Digite uma opção válida.");
            return true;
        }
    }

    public static boolean cancelamentoCheckout(Scanner input, Reserva reserva, Quarto[] vectQuarto) {
        if (reserva != null && reserva.getStatusReserva().equals("Ativa")) {
            System.out.println("O cpf cadastrado ja tem uma reserva em andamento.");
            pausar();
            System.out.println("---- MENU INTERATIVO ----");
            System.out.println("1 - Cancelar reserva anterior");
            System.out.println("2 - Voltar ao menu principal");
            System.out.println("Selecione uma das opções acima: ");
            int opcaoMenuInterativo = input.nextInt();

            if (!(opcaoMenuInterativo == 1 || opcaoMenuInterativo == 2)) {
                System.out.println("ERRO: Digite uma opção válida.");
                pausar();
                return true;
            }

            switch (opcaoMenuInterativo) {
                case 1:
                    for(int i = 0; i < vectQuarto.length; i ++) {
                        if (reserva.getQuartoReservado() == 100 + i) {
                            vectQuarto[i].setStatusAtual("Disponivel");
                        }
                    }

                    reserva.setStatusReserva("Cancelada");
                    System.out.println("Cancelamento efetuado com sucesso!");
                    pausar();
                    return true;
                case 2:
                    System.out.println("Redirecionando...");
                    pausar();
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner input = new Scanner(System.in);

        System.out.println("\n---- 1° HOSPEDE ----\n");
        Hospede hospede1 = cadastrarHospede(input);

        System.out.println("\n---- 2° HOSPEDE ----\n");
        Hospede hospede2 = cadastrarHospede(input);

        // VETOR PARA CRIAÇÂO DOS QUARTOS
        Quarto[] vectQuarto = new Quarto[3];
        int numeroInicialQuartos = 100;

        boolean validacaoContinuar = false;

        for(int i = 0; i < vectQuarto.length; i++) {
            int numQuarto = numeroInicialQuartos + i;
            System.out.printf("%n---- %d° QUARTO ----%n",i + 1);
            vectQuarto[i] = cadastrarQuarto(input,numQuarto);
        }


        // AINDA NAO COMENTEI PQ VAI QUEBRA O CODIGO, IR TIRANDO E VERIFICANDO E ARRUMANDO OS ERROS
        // COMENTEI NO SWITCH CASE OS CASOS DE 3 a 5, PARA CONSEGUIR PARA OS ERROS E APRENDER A IR RESOLVENDO UM POR UM.
//        System.out.println("\n---- 1° QUARTO ----\n");
//        tempNumeroQuarto1 = 100;
//        Quarto quarto1 = cadastrarQuarto(input,tempNumeroQuarto1);
//
//        System.out.println("\n---- 2° QUARTO ----\n");
//        tempNumeroQuarto2 = 101;
//        Quarto quarto2 = cadastrarQuarto(input,tempNumeroQuarto2);
//
//        System.out.println("\n---- 3° QUARTO ----\n");
//        tempNumeroQuarto3 = 102;
//        Quarto quarto3 = cadastrarQuarto(input,tempNumeroQuarto3);


        Reserva reserva1 = null;
        Reserva reserva2 = null;

        int menuInterativo = 0;

        while (menuInterativo != 6) {

            System.out.println("---- MENU INTERATIVO ----");
            System.out.println("1 - Visualizar Quartos");
            System.out.println("2 - Criar Reserva");
            System.out.println("3 - Realizar Checkout");
            System.out.println("4 - Cancelar Reserva");
            System.out.println("5 - Visualizar reservas");
            System.out.println("6 - Sair");
            System.out.println("Digite uma das opções acima: ");
            menuInterativo = input.nextInt();

            switch (menuInterativo) {
                case 1:
//                    System.out.println("\n---- QUARTO 100 ----");
//                    System.out.println(quarto1);
//                    System.out.println("\n---- QUARTO 101 ----");
//                    System.out.println(quarto2);
//                    System.out.println("\n---- QUARTO 102 ----");
//                    System.out.println(quarto3);

                    for (int i = 0; i < vectQuarto.length; i++) {
                        System.out.printf("%n---- QUARTO %d ----%n",numeroInicialQuartos + i);
                        System.out.println(vectQuarto[i]);
                    }
                    pausar();
                    break;
                case 2:
                    System.out.println("Digite o seu cpf cadastrado na plataforma: ");
                    String cpfCadastrado = input.next();
                    input.nextLine();

                    String tempHospedeResp;

                    if (cpfCadastrado.equals(hospede1.getCpf())) {
                        tempHospedeResp = hospede1.getNome();

                        if(cancelamentoCheckout(input,reserva1,vectQuarto)) {
                            continue;
                        }

                        System.out.println("Digite o quarto que você deseja reservar: ( 100 / 101 / 102 )");
                        System.out.println("OBS: Verifique a disponibilidade do mesmo digitando 1 no menu iniciar.");
                        int tempQuartoReservado = input.nextInt();
                        input.nextLine();

                        for(int i = 0; i < vectQuarto.length; i++) {
                            if(tempQuartoReservado == numeroInicialQuartos + i) {
                                if(!(vectQuarto[i].getStatusAtual().equals("Ocupado"))) {
                                    vectQuarto[i].setStatusAtual("Ocupado");
                                } else {
                                    System.out.println("ERRO: Esse quarto ja está ocupado");
                                    pausar();
                                    validacaoContinuar = true;
                                    break;
                                }
                            } else {
                                System.out.println("ERRO: Digite um quarto válido.");
                                pausar();
                                validacaoContinuar = true;
                                break;
                            }
                        }

                        if(validacaoContinuar) {
                            continue;
                        }

                        System.out.println("Digite a quantidade de dias que você deseja reservar: ");
                        int tempQtdDiariasAgendadas = input.nextInt();
                        input.nextLine();

                        String tempStatusReserva = "Ativa";

                        reserva1 = new Reserva(tempHospedeResp,tempQuartoReservado,tempQtdDiariasAgendadas,tempStatusReserva);


                    } else if (cpfCadastrado.equals(hospede2.getCpf())) {
                        tempHospedeResp = hospede2.getNome();

                        if (cancelamentoCheckout(input, reserva2, vectQuarto)) {
                            continue;
                        }

                        System.out.println("Digite o quarto que você deseja reservar: ( 100 / 101 / 102 )");
                        System.out.println("OBS: Verifique a disponibilidade do mesmo digitando 1 no menu iniciar.");
                        int tempQuartoReservado = input.nextInt();
                        input.nextLine();

                        for(int i = 0; i < vectQuarto.length; i++) {
                            if(tempQuartoReservado == numeroInicialQuartos + i) {
                                if(!(vectQuarto[i].getStatusAtual().equals("Ocupado"))) {
                                    vectQuarto[i].setStatusAtual("Ocupado");
                                } else {
                                    System.out.println("ERRO: Esse quarto ja está ocupado");
                                    pausar();
                                    validacaoContinuar = true;
                                    break;
                                }
                            } else {
                                System.out.println("ERRO: Digite um quarto válido.");
                                pausar();
                                validacaoContinuar = true;
                                break;
                            }
                        }

                        if(validacaoContinuar) {
                            continue;
                        }

                        System.out.println("Digite a quantidade de dias que você deseja reservar: ");
                        int tempQtdDiariasAgendadas = input.nextInt();
                        input.nextLine();

                        String tempStatusReserva = "Ativa";

                        reserva2 = new Reserva(tempHospedeResp, tempQuartoReservado, tempQtdDiariasAgendadas, tempStatusReserva);

                    } else {
                        System.out.println("ERRO: O cpf inserido não esta em nosso banco de dados.");
                        pausar();
                        continue;
                    }

                    break;
                case 3:
                    System.out.println("Digite o seu cpf cadastrado na plataforma: ");
                    String cpfCadastro = input.next();
                    input.nextLine();

                    if (cpfCadastro.equals(hospede1.getCpf())) {

                        if(reserva1 != null && reserva1.getStatusReserva().equals("Ativa")) {

                            for(int i = 0; i < vectQuarto.length; i++) {
                                if(reserva1.getQuartoReservado() == numeroInicialQuartos + i) {

                                    if(vectQuarto[i].getTipo().equals("suite")) {
                                        Faturamento.calculoTaxa(vectQuarto[i].getPrecoDiaria(), reserva1.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiroTaxa(vectQuarto[i].getPrecoDiaria(), reserva1.getQtdDiariasAgendadas(), vectQuarto[i].getTipo()));

                                        if (pagamentoCheckout(input,reserva1 ,vectQuarto, i)) {
                                            System.out.println("validacaoContinuar");
                                            continue;
                                        } else {
                                            System.out.println("Passou");
                                        }

                                    } else if (vectQuarto[i].getTipo().equals("standard")) {
                                        Faturamento.calculoTotalDiarias(vectQuarto[i].getPrecoDiaria(), reserva1.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiro(vectQuarto[i].getPrecoDiaria(), reserva1.getQtdDiariasAgendadas(), vectQuarto[i].getTipo()));

                                        if (pagamentoCheckout(input,reserva1 ,vectQuarto, i)) {
                                            System.out.println("validacaoContinuar");
                                            continue;
                                        } else {
                                            System.out.println("Passou");
                                        }
                                    }

                                }
                            }
                        } else {
                            System.out.println("ERRO: Não há reservas ativas ligadas a esse CPF.");
                            pausar();
                        }
                    } else if (cpfCadastro.equals(hospede2.getCpf())) {

                        if(reserva2 != null && reserva2.getStatusReserva().equals("Ativa")) {

                            for(int i = 0; i < vectQuarto.length; i++) {
                                if(reserva2.getQuartoReservado() == numeroInicialQuartos + i) {

                                    if(vectQuarto[i].getTipo().equals("suite")) {
                                        Faturamento.calculoTaxa(vectQuarto[i].getPrecoDiaria(), reserva2.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiroTaxa(vectQuarto[i].getPrecoDiaria(), reserva2.getQtdDiariasAgendadas(), vectQuarto[i].getTipo()));

                                        if (pagamentoCheckout(input,reserva2 ,vectQuarto, i)) {
                                            System.out.println("validacaoContinuar");
                                            continue;
                                        } else {
                                            System.out.println("Passou");
                                        }

                                    } else if (vectQuarto[i].getTipo().equals("standard")) {
                                        Faturamento.calculoTotalDiarias(vectQuarto[i].getPrecoDiaria(), reserva2.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiro(vectQuarto[i].getPrecoDiaria(), reserva2.getQtdDiariasAgendadas(), vectQuarto[i].getTipo()));

                                        if (pagamentoCheckout(input,reserva2 ,vectQuarto, i)) {
                                            System.out.println("validacaoContinuar");
                                            continue;
                                        } else {
                                            System.out.println("Passou");
                                        }
                                    }

                                }
                            }
                        } else {
                            System.out.println("ERRO: Não há reservas ativas ligadas a esse CPF.");
                            pausar();
                        }
                    } else {
                        System.out.println("ERRO: O cpf inserido não esta em nosso banco de dados.");
                        pausar();
                        continue;
                    }
                    break;
//                case 4:
//                    System.out.println("Digite o seu cpf cadastrado na plataforma: ");
//                    String cpfCadastroCancelamento = input.next();
//                    input.nextLine();
//
//                    if (cpfCadastroCancelamento.equals(hospede1.getCpf())) {
//
//                        if (reserva1 != null && reserva1.getStatusReserva().equals("Ativa")) {
//
//                            if (reserva1.getQuartoReservado() == 100) {
//                                quarto1.setStatusAtual("Disponivel");
//                            } else if (reserva1.getQuartoReservado() == 101) {
//                                quarto2.setStatusAtual("Disponivel");
//                            } else if (reserva1.getQuartoReservado() == 102) {
//                                quarto3.setStatusAtual("Disponivel");
//                            }
//                            reserva1.setStatusReserva("Cancelada");
//                            System.out.println("Cancelamento efetuado com sucesso!");
//                            pausar();
//                            continue;
//
//                        } else {
//                            System.out.println("ERRO: Não existe uma reserva ativa para esse CPF.");
//                            pausar();
//                            continue;
//                        }
//                    } else if (cpfCadastroCancelamento.equals(hospede2.getCpf())) {
//
//                        if (reserva2 != null && reserva2.getStatusReserva().equals("Ativa")) {
//
//                            if (reserva2.getQuartoReservado() == 100) {
//                                quarto1.setStatusAtual("Disponivel");
//                            } else if (reserva2.getQuartoReservado() == 101) {
//                                quarto2.setStatusAtual("Disponivel");
//                            } else if (reserva2.getQuartoReservado() == 102) {
//                                quarto3.setStatusAtual("Disponivel");
//                            }
//                            reserva2.setStatusReserva("Cancelada");
//                            System.out.println("Cancelamento efetuado com sucesso!");
//                            pausar();
//                            continue;
//
//                        } else {
//                            System.out.println("ERRO: Não existe uma reserva ativa para esse CPF.");
//                            pausar();
//                            continue;
//                        }
//
//                    } else {
//                        System.out.println("ERRO: O cpf inserido não esta em nosso banco de dados.");
//                        pausar();
//                        continue;
//                    }
                case 5:
                    System.out.println("Digite o seu cpf cadastrado na plataforma: ");
                    String cpfCadastroVizuReserva = input.next();
                    input.nextLine();

                    if (cpfCadastroVizuReserva.equals(hospede1.getCpf())) {

                        if (reserva1 != null && !(reserva1.getStatusReserva().isEmpty())) {
                            System.out.println("\n---- RESERVA ----\n");
                            System.out.println(reserva1);
                            pausar();
                        } else {
                            System.out.println("ERRO: Nenhuma reserva foi realizada para esse cpf.");
                            pausar();
                        }

                    } else if (cpfCadastroVizuReserva.equals(hospede2.getCpf())) {

                        if (reserva2 != null && !(reserva2.getStatusReserva().isEmpty())) {
                            System.out.println("\n---- RESERVA ----\n");
                            System.out.println(reserva2);
                            pausar();
                        } else {
                            System.out.println("ERRO: Nenhuma reserva foi realizada para esse cpf.");
                            pausar();
                        }

                    } else {
                        System.out.println("ERRO: O cpf inserido não esta em nosso banco de dados.");
                        pausar();
                        continue;
                    }


                    break;
                case 6:
                    menuInterativo = 6;
                    break;
                default:
                    System.out.println("ERRO: Digite uma opção válida.");
            }
        }


    }
}