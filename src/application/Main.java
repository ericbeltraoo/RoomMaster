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
        Scanner pausar = new Scanner(System.in);
        pausar.nextLine();
    }

    public static String cadastrarTipoQuarto(Scanner input) {
        boolean passar = true;
        String tipo = null;

        while (passar) {
            System.out.println("Digite o tipo do quarto: (Standard ou Suite)");
            tipo = input.nextLine().toLowerCase();
            if(!(tipo.equals("standard") || tipo.equals("suite"))) {
                System.out.println("ERRO: Digite um tipo de quarto válido.");
                pausar();
            } else {
                passar = false;
            }
        }
        return tipo;
    }

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner input = new Scanner(System.in);

        String tempNomeHospede1, tempCpf1, tempRg1, tempNomeHospede2, tempCpf2, tempRg2;
        String tempTipoQuarto1, tempTipoQuarto2, tempTipoQuarto3;
        double tempPrecoDiaria1, tempPrecoDiaria2, tempPrecoDiaria3;
        int tempNumeroQuarto1, tempNumeroQuarto2, tempNumeroQuarto3;

        Quarto quarto1;
        Quarto quarto2;
        Quarto quarto3;
        Hospede hospede1;
        Hospede hospede2;

        System.out.println("\n---- 1° HOSPEDE ----\n");
        System.out.println("Digite o nome do 1° hóspede:");
        tempNomeHospede1 = input.nextLine();
        System.out.println("Digite o cpf do 1° hóspede: ");
        tempCpf1 = input.next();
        input.nextLine();
        System.out.println("Digite o rg do 1° hóspede: ");
        tempRg1 = input.next();
        input.nextLine();

        hospede1 = new Hospede(tempNomeHospede1,tempCpf1,tempRg1);

        System.out.println("\n---- 2° HOSPEDE ----\n");
        System.out.println("Digite o nome do 2° hóspede:");
        tempNomeHospede2 = input.nextLine();
        System.out.println("Digite o cpf do 2° hóspede: ");
        tempCpf2 = input.next();
        input.nextLine();
        System.out.println("Digite o rg do 2° hóspede: ");
        tempRg2 = input.next();
        input.nextLine();

        hospede2 = new Hospede(tempNomeHospede2,tempCpf2,tempRg2);

            System.out.println("\n---- 1° QUARTO ----\n");
            tempNumeroQuarto1 = 100;

            tempTipoQuarto1 = cadastrarTipoQuarto(input);

            System.out.println("Digite o preço da diária: ");
            tempPrecoDiaria1 = input.nextDouble();
            input.nextLine();

            quarto1 = new Quarto(tempNumeroQuarto1,tempTipoQuarto1,tempPrecoDiaria1);

            System.out.println("\n---- 2° QUARTO ----\n");
            tempNumeroQuarto2 = 101;

            tempTipoQuarto2 = cadastrarTipoQuarto(input);

            System.out.println("Digite o preço da diária: ");
            tempPrecoDiaria2 = input.nextDouble();
            input.nextLine();

            quarto2 = new Quarto(tempNumeroQuarto2,tempTipoQuarto2,tempPrecoDiaria2);

            System.out.println("\n---- 3° QUARTO ----\n");
            tempNumeroQuarto3 = 102;

            tempTipoQuarto3 = cadastrarTipoQuarto(input);

            System.out.println("Digite o preço da diária: ");
            tempPrecoDiaria3 = input.nextDouble();

            quarto3 = new Quarto(tempNumeroQuarto3,tempTipoQuarto3,tempPrecoDiaria3);

        Reserva reserva1 = null;
        Reserva reserva2 = null;

        int menuInterativo = 0;

        while (menuInterativo != 5) {

            System.out.println("---- MENU INTERATIVO ----");
            System.out.println("1 - Visualizar Quartos");
            System.out.println("2 - Criar Reserva");
            System.out.println("3 - Realizar Checkout");
            System.out.println("4 - Cancelar Reserva");
            System.out.println("5 - Sair");
            System.out.println("Digite uma das opções acima: ");
            menuInterativo = input.nextInt();

            switch (menuInterativo) {
                case 1:
                    System.out.println("\n---- QUARTO 100 ----");
                    System.out.println(quarto1);
                    System.out.println("\n---- QUARTO 101 ----");
                    System.out.println(quarto2);
                    System.out.println("\n---- QUARTO 102 ----");
                    System.out.println(quarto3);
                    pausar();
                    break;
                case 2:
                    System.out.println("Digite o seu cpf cadastrado na plataforma: ");
                    String cpfCadastrado = input.next();
                    input.nextLine();

                    String tempHospedeResp;

                    if (cpfCadastrado.equals(hospede1.getCpf())) {
                        tempHospedeResp = hospede1.getNome();

                        if (reserva1 != null && hospede1.getNome().equals(reserva1.getHospedeResponsavel())) {
                            System.out.println("O cpf cadastrado ja tem uma reserva em andamento.");
                            pausar();
                            System.out.println("---- MENU INTERATIVO ----");
                            System.out.println("1 - Cancelar reserva anterior");
                            System.out.println("2 - Voltar ao menu principal");
                            System.out.println("Selecione uma das opções acima: ");
                            int opcaoMenuInterativo = input.nextInt();

                            switch (opcaoMenuInterativo) {
                                case 1:
                                    if (reserva1.getQuartoReservado() == 100) {
                                        quarto1.setStatusAtual("Disponível");
                                    } else if (reserva1.getQuartoReservado() == 101) {
                                        quarto2.setStatusAtual("Disponível");
                                    } else if (reserva1.getQuartoReservado() == 102) {
                                        quarto3.setStatusAtual("Disponível");
                                    }
                                    reserva1 = null;
                                    System.out.println("Cancelamento efetuado com sucesso...");
                                    pausar();
                                    continue;
                                case 2:
                                    System.out.println("Redirecionando...");
                                    pausar();
                                    continue;
                            }
                        }

                        System.out.println("Digite o quarto que você deseja reservar: ( 100 / 101 / 102 )");
                        System.out.println("OBS: Verifique a disponibilidade do mesmo digitando 1 no menu iniciar.");
                        int tempQuartoReservado = input.nextInt();
                        input.nextLine();

                        if (tempQuartoReservado == 100) {
                            if(!(quarto1.getStatusAtual().equals("Ocupado"))) {
                                quarto1.setStatusAtual("Ocupado");
                            } else {
                                System.out.println("ERRO: Esse quarto ja está ocupado");
                                pausar();
                                continue;
                            }
                        } else if (tempQuartoReservado == 101) {
                            if(!(quarto2.getStatusAtual().equals("Ocupado"))) {
                                quarto2.setStatusAtual("Ocupado");
                            } else {
                                System.out.println("ERRO: Esse quarto ja está ocupado");
                                pausar();
                                continue;
                            }
                        } else if (tempQuartoReservado == 102) {
                            if(!(quarto3.getStatusAtual().equals("Ocupado"))) {
                                quarto3.setStatusAtual("Ocupado");
                            } else {
                                System.out.println("ERRO: Esse quarto ja está ocupado");
                                pausar();
                                continue;
                            }
                        } else {
                            System.out.println("ERRO: Digite um quarto válido.");
                            continue;
                        }

                        System.out.println("Digite a quantidade de dias que você deseja reservar: ");
                        int tempQtdDiariasAgendadas = input.nextInt();
                        input.nextLine();

                        String tempStatusReserva = "Ativa";

                        reserva1 = new Reserva(tempHospedeResp,tempQuartoReservado,tempQtdDiariasAgendadas,tempStatusReserva);


                    } else if (cpfCadastrado.equals(hospede2.getCpf())) {
                        tempHospedeResp = hospede2.getNome();

                        if (reserva2 != null && hospede2.getNome().equals(reserva2.getHospedeResponsavel())) {
                            System.out.println("O cpf cadastrado ja tem uma reserva em andamento.");
                            pausar();
                            System.out.println("---- MENU INTERATIVO ----");
                            System.out.println("1 - Cancelar reserva anterior");
                            System.out.println("2 - Voltar ao menu principal");
                            System.out.println("Selecione uma das opções acima: ");
                            int opcaoMenuInterativo = input.nextInt();

                            switch (opcaoMenuInterativo) {
                                case 1:
                                    if (reserva2.getQuartoReservado() == 100) {
                                        quarto1.setStatusAtual("Disponível");
                                    } else if (reserva2.getQuartoReservado() == 101) {
                                        quarto2.setStatusAtual("Disponível");
                                    } else if (reserva2.getQuartoReservado() == 102) {
                                        quarto3.setStatusAtual("Disponível");
                                    }
                                    reserva2 = null;
                                    System.out.println("Cancelamento efetuado com sucesso...");
                                    pausar();
                                    continue;
                                case 2:
                                    System.out.println("Redirecionando...");
                                    pausar();
                                    continue;
                            }
                        }

                        System.out.println("Digite o quarto que você deseja reservar: ( 100 / 101 / 102 )");
                        System.out.println("OBS: Verifique a disponibilidade do mesmo digitando 1 no menu iniciar.");
                        int tempQuartoReservado = input.nextInt();

                        if (tempQuartoReservado == 100) {
                            if(!(quarto1.getStatusAtual().equals("Ocupado"))) {
                                quarto1.setStatusAtual("Ocupado");
                            } else {
                                System.out.println("ERRO: Esse quarto ja está ocupado");
                                pausar();
                                continue;
                            }
                        } else if (tempQuartoReservado == 101) {
                            if(!(quarto2.getStatusAtual().equals("Ocupado"))) {
                                quarto2.setStatusAtual("Ocupado");
                            } else {
                                System.out.println("ERRO: Esse quarto ja está ocupado");
                                pausar();
                                continue;
                            }
                        } else if (tempQuartoReservado == 102) {
                            if(!(quarto3.getStatusAtual().equals("Ocupado"))) {
                                quarto3.setStatusAtual("Ocupado");
                            } else {
                                System.out.println("ERRO: Esse quarto ja está ocupado");
                                pausar();
                                continue;
                            }
                        } else {
                            System.out.println("ERRO: Digite um quarto válido.");
                            continue;
                        }

                        System.out.println("Digite a quantidade de dias que você deseja reservar: ");
                        int tempQtdDiariasAgendadas = input.nextInt();

                        String tempStatusReserva = "Ativa";

                        reserva2 = new Reserva(tempHospedeResp,tempQuartoReservado,tempQtdDiariasAgendadas,tempStatusReserva);

                    } else {
                        System.out.println("ERRO: Digite um CPF válido.");
                        continue;
                    }

                    break;
                case 3:
                        System.out.println("Digite o seu cpf cadastrado na plataforma: ");
                        String cpfCadastro = input.next();

                        if (cpfCadastro.equals(hospede1.getCpf())) {

                            if(reserva1 != null && reserva1.getStatusReserva().equals("Ativa")) {

                                if (reserva1.getQuartoReservado() == 100) {

                                    if (quarto1.getTipo().equals("suite")) {
                                        Faturamento.calculoTaxa(quarto1.getPrecoDiaria(), reserva1.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiroTaxa(quarto1.getPrecoDiaria(), reserva1.getQtdDiariasAgendadas(), quarto1.getTipo()));

                                        System.out.println("\n---- PAGAMENTOS ----\n");
                                        System.out.println("1 - Para realizar o pagamento");
                                        System.out.println("2 - Para voltar ao menu de interação");
                                        int opcaoPagamento = input.nextInt();

                                        if (opcaoPagamento == 1) {
                                            System.out.println("Pagamento realizado com sucesso!");
                                            quarto1.setStatusAtual("Disponivel");
                                            pausar();
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar();
                                            continue;
                                        } else {
                                            System.out.println("ERRO: Digite uma opção válida.");
                                            continue;
                                        }

                                    } else if (quarto1.getTipo().equals("standard")) {
                                        Faturamento.calculoTotalDiarias(quarto1.getPrecoDiaria(), reserva1.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiro(quarto1.getPrecoDiaria(), reserva1.getQtdDiariasAgendadas(), quarto1.getTipo()));

                                        System.out.println("\n---- PAGAMENTOS ----\n");
                                        System.out.println("1 - Para realizar o pagamento");
                                        System.out.println("2 - Para voltar ao menu de interação");
                                        int opcaoPagamento = input.nextInt();

                                        if (opcaoPagamento == 1) {
                                            System.out.println("Pagamento realizado com sucesso!");
                                            quarto1.setStatusAtual("Disponivel");
                                            pausar();
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar();
                                            continue;
                                        } else {
                                            System.out.println("ERRO: Digite uma opção válida.");
                                            continue;
                                        }

                                    }

                                } else if (reserva1.getQuartoReservado() == 101) {

                                    if (quarto2.getTipo().equals("suite")) {
                                        Faturamento.calculoTaxa(quarto2.getPrecoDiaria(), reserva1.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiroTaxa(quarto2.getPrecoDiaria(), reserva1.getQtdDiariasAgendadas(), quarto2.getTipo()));

                                        System.out.println("\n---- PAGAMENTOS ----\n");
                                        System.out.println("1 - Para realizar o pagamento");
                                        System.out.println("2 - Para voltar ao menu de interação");
                                        int opcaoPagamento = input.nextInt();

                                        if (opcaoPagamento == 1) {
                                            System.out.println("Pagamento realizado com sucesso!");
                                            quarto2.setStatusAtual("Disponivel");
                                            pausar();
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar();
                                            continue;
                                        } else {
                                            System.out.println("ERRO: Digite uma opção válida.");
                                            continue;
                                        }
                                    } else if (quarto2.getTipo().equals("standard")) {
                                        Faturamento.calculoTotalDiarias(quarto2.getPrecoDiaria(), reserva1.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiro(quarto2.getPrecoDiaria(), reserva1.getQtdDiariasAgendadas(), quarto2.getTipo()));

                                        System.out.println("\n---- PAGAMENTOS ----\n");
                                        System.out.println("1 - Para realizar o pagamento");
                                        System.out.println("2 - Para voltar ao menu de interação");
                                        int opcaoPagamento = input.nextInt();

                                        if (opcaoPagamento == 1) {
                                            System.out.println("Pagamento realizado com sucesso!");
                                            quarto2.setStatusAtual("Disponivel");
                                            pausar();
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar();
                                            continue;
                                        } else {
                                            System.out.println("ERRO: Digite uma opção válida.");
                                            continue;
                                        }

                                    }

                                } else if (reserva1.getQuartoReservado() == 102) {

                                    if (quarto3.getTipo().equals("suite")) {
                                        Faturamento.calculoTaxa(quarto3.getPrecoDiaria(), reserva1.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiroTaxa(quarto3.getPrecoDiaria(), reserva1.getQtdDiariasAgendadas(), quarto3.getTipo()));

                                        System.out.println("\n---- PAGAMENTOS ----\n");
                                        System.out.println("1 - Para realizar o pagamento");
                                        System.out.println("2 - Para voltar ao menu de interação");
                                        int opcaoPagamento = input.nextInt();

                                        if (opcaoPagamento == 1) {
                                            System.out.println("Pagamento realizado com sucesso!");
                                            quarto3.setStatusAtual("Disponivel");
                                            pausar();
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar();
                                            continue;
                                        } else {
                                            System.out.println("ERRO: Digite uma opção válida.");
                                            continue;
                                        }
                                    } else if (quarto3.getTipo().equals("standard")) {
                                        Faturamento.calculoTotalDiarias(quarto3.getPrecoDiaria(), reserva1.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiro(quarto3.getPrecoDiaria(), reserva1.getQtdDiariasAgendadas(), quarto3.getTipo()));

                                        System.out.println("\n---- PAGAMENTOS ----\n");
                                        System.out.println("1 - Para realizar o pagamento");
                                        System.out.println("2 - Para voltar ao menu de interação");
                                        int opcaoPagamento = input.nextInt();

                                        if (opcaoPagamento == 1) {
                                            System.out.println("Pagamento realizado com sucesso!");
                                            quarto3.setStatusAtual("Disponivel");
                                            pausar();
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar();
                                            continue;
                                        } else {
                                            System.out.println("ERRO: Digite uma opção válida.");
                                            continue;
                                        }

                                    }

                                }
                                reserva1.setStatusReserva("Finalizada");
                            } else {
                                System.out.println("ERRO: Não à reservas ativas ligadas a esse CPF.");
                                pausar();
                            }
                        } else if (cpfCadastro.equals(hospede2.getCpf())) {
                            if(reserva2 != null && reserva2.getStatusReserva().equals("Ativa")) {
                                if (reserva2.getQuartoReservado() == 100) {

                                    if (quarto1.getTipo().equals("suite")) {
                                        Faturamento.calculoTaxa(quarto1.getPrecoDiaria(), reserva2.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiroTaxa(quarto1.getPrecoDiaria(), reserva2.getQtdDiariasAgendadas(), quarto1.getTipo()));

                                        System.out.println("\n---- PAGAMENTOS ----\n");
                                        System.out.println("1 - Para realizar o pagamento");
                                        System.out.println("2 - Para voltar ao menu de interação");
                                        int opcaoPagamento = input.nextInt();

                                        if (opcaoPagamento == 1) {
                                            System.out.println("Pagamento realizado com sucesso!");
                                            quarto1.setStatusAtual("Disponivel");
                                            pausar();
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar();
                                            continue;
                                        } else {
                                            System.out.println("ERRO: Digite uma opção válida.");
                                            continue;
                                        }
                                    } else if (quarto1.getTipo().equals("standard")) {
                                        Faturamento.calculoTotalDiarias(quarto1.getPrecoDiaria(), reserva2.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiro(quarto1.getPrecoDiaria(), reserva2.getQtdDiariasAgendadas(), quarto1.getTipo()));

                                        System.out.println("\n---- PAGAMENTOS ----\n");
                                        System.out.println("1 - Para realizar o pagamento");
                                        System.out.println("2 - Para voltar ao menu de interação");
                                        int opcaoPagamento = input.nextInt();

                                        if (opcaoPagamento == 1) {
                                            System.out.println("Pagamento realizado com sucesso!");
                                            quarto1.setStatusAtual("Disponivel");
                                            pausar();
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar();
                                            continue;
                                        } else {
                                            System.out.println("ERRO: Digite uma opção válida.");
                                            continue;
                                        }

                                    }

                                } else if (reserva2.getQuartoReservado() == 101) {

                                    if (quarto2.getTipo().equals("suite")) {
                                        Faturamento.calculoTaxa(quarto2.getPrecoDiaria(), reserva2.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiroTaxa(quarto2.getPrecoDiaria(), reserva2.getQtdDiariasAgendadas(), quarto2.getTipo()));

                                        System.out.println("\n---- PAGAMENTOS ----\n");
                                        System.out.println("1 - Para realizar o pagamento");
                                        System.out.println("2 - Para voltar ao menu de interação");
                                        int opcaoPagamento = input.nextInt();

                                        if (opcaoPagamento == 1) {
                                            System.out.println("Pagamento realizado com sucesso!");
                                            quarto2.setStatusAtual("Disponivel");
                                            pausar();
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar();
                                            continue;
                                        } else {
                                            System.out.println("ERRO: Digite uma opção válida.");
                                            continue;
                                        }
                                    } else if (quarto2.getTipo().equals("standard")) {
                                        Faturamento.calculoTotalDiarias(quarto2.getPrecoDiaria(), reserva2.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiro(quarto2.getPrecoDiaria(), reserva2.getQtdDiariasAgendadas(), quarto2.getTipo()));

                                        System.out.println("\n---- PAGAMENTOS ----\n");
                                        System.out.println("1 - Para realizar o pagamento");
                                        System.out.println("2 - Para voltar ao menu de interação");
                                        int opcaoPagamento = input.nextInt();

                                        if (opcaoPagamento == 1) {
                                            System.out.println("Pagamento realizado com sucesso!");
                                            quarto2.setStatusAtual("Disponivel");
                                            pausar();
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar();
                                            continue;
                                        } else {
                                            System.out.println("ERRO: Digite uma opção válida.");
                                            continue;
                                        }

                                    }

                                } else if (reserva2.getQuartoReservado() == 102) {

                                    if (quarto3.getTipo().equals("suite")) {
                                        Faturamento.calculoTaxa(quarto3.getPrecoDiaria(), reserva2.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiroTaxa(quarto3.getPrecoDiaria(), reserva2.getQtdDiariasAgendadas(), quarto3.getTipo()));

                                        System.out.println("\n---- PAGAMENTOS ----\n");
                                        System.out.println("1 - Para realizar o pagamento");
                                        System.out.println("2 - Para voltar ao menu de interação");
                                        int opcaoPagamento = input.nextInt();

                                        if (opcaoPagamento == 1) {
                                            System.out.println("Pagamento realizado com sucesso!");
                                            quarto3.setStatusAtual("Disponivel");
                                            pausar();
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar();
                                            continue;
                                        } else {
                                            System.out.println("ERRO: Digite uma opção válida.");
                                            continue;
                                        }
                                    } else if (quarto3.getTipo().equals("standard")) {
                                        Faturamento.calculoTotalDiarias(quarto3.getPrecoDiaria(), reserva2.getQtdDiariasAgendadas());
                                        System.out.println(Faturamento.relatorioFinanceiro(quarto3.getPrecoDiaria(), reserva2.getQtdDiariasAgendadas(), quarto3.getTipo()));

                                        System.out.println("\n---- PAGAMENTOS ----\n");
                                        System.out.println("1 - Para realizar o pagamento");
                                        System.out.println("2 - Para voltar ao menu de interação");
                                        int opcaoPagamento = input.nextInt();

                                        if (opcaoPagamento == 1) {
                                            System.out.println("Pagamento realizado com sucesso!");
                                            quarto3.setStatusAtual("Disponivel");
                                            pausar();
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar();
                                            continue;
                                        } else {
                                            System.out.println("ERRO: Digite uma opção válida.");
                                            pausar();
                                            continue;
                                        }

                                    }

                                }
                                reserva2.setStatusReserva("Finalizada");
                            } else {
                                System.out.println("ERRO: Não à reservas ativas ligadas a esse CPF.");
                                pausar();
                            }
                        } else {
                            System.out.println("ERRO: Digite um cpf válido.");
                            pausar();
                            continue;
                        }
                    break;
                case 4:
                    System.out.println("Digite o seu cpf cadastrado na plataforma: ");
                    String cpfCadastroCancelamento = input.next();

                    if (cpfCadastroCancelamento.equals(hospede1.getCpf())) {

                        if (reserva1 != null && reserva1.getStatusReserva().equals("Ativa")) {

                            if (reserva1.getQuartoReservado() == 100) {
                                quarto1.setStatusAtual("Disponível");
                            } else if (reserva1.getQuartoReservado() == 101) {
                                quarto2.setStatusAtual("Disponível");
                            } else if (reserva1.getQuartoReservado() == 102) {
                                quarto3.setStatusAtual("Disponível");
                            }
                            reserva1 = null;
                            System.out.println("Cancelamento efetuado com sucesso...");
                            pausar();
                            continue;

                        } else {
                            System.out.println("ERRO: Não existe uma reserva ativa para esse CPF.");
                            pausar();
                            continue;
                        }
                    } else if (cpfCadastroCancelamento.equals(hospede2.getCpf())) {

                        if (reserva2 != null && reserva2.getStatusReserva().equals("Ativa")) {

                            if (reserva2.getQuartoReservado() == 100) {
                                quarto1.setStatusAtual("Disponível");
                            } else if (reserva2.getQuartoReservado() == 101) {
                                quarto2.setStatusAtual("Disponível");
                            } else if (reserva2.getQuartoReservado() == 102) {
                                quarto3.setStatusAtual("Disponível");
                            }
                            reserva2 = null;
                            System.out.println("Cancelamento efetuado com sucesso...");
                            pausar();
                            continue;

                        } else {
                            System.out.println("ERRO: Não existe uma reserva ativa para esse CPF.");
                            pausar();
                            continue;
                        }

                    } else {
                        System.out.println("ERRO: O cpf inserido não esta em nosso banco de dados.");
                        pausar();
                        continue;
                    }
                case 5:
                    menuInterativo = 5;
                    break;
                default:
                    System.out.println("ERRO: Digite uma opção válida.");
            }
        }


    }
}
