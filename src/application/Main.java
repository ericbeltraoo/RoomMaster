package application;

import entities.Hospede;
import entities.Quarto;
import entities.Reserva;
import util.Faturamento;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void pausar(Scanner pausar) {
        System.out.println("Pressione enter para continuar... ");
        pausar.nextLine();
    }

    public static Quarto cadastrarQuarto(Scanner input, int numero) {
        boolean passar = true;
        String tipo = null;
        double precoDiaria;
        Quarto quartoConstruido = null;

        while (passar) {
            System.out.println("Digite o tipo do quarto: (Standard ou Suite)");
            tipo = input.nextLine().toLowerCase();
            if(!(tipo.equals("standard") || tipo.equals("suite"))) {
                System.out.println("ERRO: Digite um tipo de quarto válido.");
                pausar(input);
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

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner input = new Scanner(System.in);

        String tempNomeHospede1, tempCpf1, tempRg1, tempNomeHospede2, tempCpf2, tempRg2;
        String tempTipoQuarto1, tempTipoQuarto2, tempTipoQuarto3;
        double tempPrecoDiaria1, tempPrecoDiaria2, tempPrecoDiaria3;
        int tempNumeroQuarto1, tempNumeroQuarto2, tempNumeroQuarto3;

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
            Quarto quarto1 = cadastrarQuarto(input,tempNumeroQuarto1);

            System.out.println("\n---- 2° QUARTO ----\n");
            tempNumeroQuarto2 = 101;
            Quarto quarto2 = cadastrarQuarto(input,tempNumeroQuarto2);

            System.out.println("\n---- 3° QUARTO ----\n");
            tempNumeroQuarto3 = 102;
            Quarto quarto3 = cadastrarQuarto(input,tempNumeroQuarto3);


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
                    System.out.println("\n---- QUARTO 100 ----");
                    System.out.println(quarto1);
                    System.out.println("\n---- QUARTO 101 ----");
                    System.out.println(quarto2);
                    System.out.println("\n---- QUARTO 102 ----");
                    System.out.println(quarto3);
                    pausar(input);
                    break;
                case 2:
                    System.out.println("Digite o seu cpf cadastrado na plataforma: ");
                    String cpfCadastrado = input.next();
                    input.nextLine();

                    String tempHospedeResp;

                    if (cpfCadastrado.equals(hospede1.getCpf())) {
                        tempHospedeResp = hospede1.getNome();

                        if (reserva1 != null && reserva1.getStatusReserva().equals("Ativa")) {
                            System.out.println("O cpf cadastrado ja tem uma reserva em andamento.");
                            pausar(input);
                            System.out.println("---- MENU INTERATIVO ----");
                            System.out.println("1 - Cancelar reserva anterior");
                            System.out.println("2 - Voltar ao menu principal");
                            System.out.println("Selecione uma das opções acima: ");
                            int opcaoMenuInterativo = input.nextInt();

                            switch (opcaoMenuInterativo) {
                                case 1:
                                    if (reserva1.getQuartoReservado() == 100) {
                                        quarto1.setStatusAtual("Disponivel");
                                    } else if (reserva1.getQuartoReservado() == 101) {
                                        quarto2.setStatusAtual("Disponivel");
                                    } else if (reserva1.getQuartoReservado() == 102) {
                                        quarto3.setStatusAtual("Disponivel");
                                    }
                                    reserva1.setStatusReserva("Cancelada");
                                    System.out.println("Cancelamento efetuado com sucesso!");
                                    pausar(input);
                                    continue;
                                case 2:
                                    System.out.println("Redirecionando...");
                                    pausar(input);
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
                                pausar(input);
                                continue;
                            }
                        } else if (tempQuartoReservado == 101) {
                            if(!(quarto2.getStatusAtual().equals("Ocupado"))) {
                                quarto2.setStatusAtual("Ocupado");
                            } else {
                                System.out.println("ERRO: Esse quarto ja está ocupado");
                                pausar(input);
                                continue;
                            }
                        } else if (tempQuartoReservado == 102) {
                            if(!(quarto3.getStatusAtual().equals("Ocupado"))) {
                                quarto3.setStatusAtual("Ocupado");
                            } else {
                                System.out.println("ERRO: Esse quarto ja está ocupado");
                                pausar(input);
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

                        if (reserva2 != null && reserva2.getStatusReserva().equals("Ativa")) {
                            System.out.println("O cpf cadastrado ja tem uma reserva em andamento.");
                            pausar(input);
                            System.out.println("---- MENU INTERATIVO ----");
                            System.out.println("1 - Cancelar reserva anterior");
                            System.out.println("2 - Voltar ao menu principal");
                            System.out.println("Selecione uma das opções acima: ");
                            int opcaoMenuInterativo = input.nextInt();

                            switch (opcaoMenuInterativo) {
                                case 1:
                                    if (reserva2.getQuartoReservado() == 100) {
                                        quarto1.setStatusAtual("Disponivel");
                                    } else if (reserva2.getQuartoReservado() == 101) {
                                        quarto2.setStatusAtual("Disponivel");
                                    } else if (reserva2.getQuartoReservado() == 102) {
                                        quarto3.setStatusAtual("Disponivel");
                                    }
                                    reserva2.setStatusReserva("Cancelada");
                                    System.out.println("Cancelamento efetuado com sucesso!");
                                    pausar(input);
                                    continue;
                                case 2:
                                    System.out.println("Redirecionando...");
                                    pausar(input);
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
                                pausar(input);
                                continue;
                            }
                        } else if (tempQuartoReservado == 101) {
                            if(!(quarto2.getStatusAtual().equals("Ocupado"))) {
                                quarto2.setStatusAtual("Ocupado");
                            } else {
                                System.out.println("ERRO: Esse quarto ja está ocupado");
                                pausar(input);
                                continue;
                            }
                        } else if (tempQuartoReservado == 102) {
                            if(!(quarto3.getStatusAtual().equals("Ocupado"))) {
                                quarto3.setStatusAtual("Ocupado");
                            } else {
                                System.out.println("ERRO: Esse quarto ja está ocupado");
                                pausar(input);
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
                        System.out.println("ERRO: O cpf inserido não esta em nosso banco de dados.");
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
                                            reserva1.setStatusReserva("Finalizada");
                                            quarto1.setStatusAtual("Disponivel");
                                            pausar(input);
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar(input);
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
                                            reserva1.setStatusReserva("Finalizada");
                                            quarto1.setStatusAtual("Disponivel");
                                            pausar(input);
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar(input);
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
                                            reserva1.setStatusReserva("Finalizada");
                                            quarto2.setStatusAtual("Disponivel");
                                            pausar(input);
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar(input);
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
                                            reserva1.setStatusReserva("Finalizada");
                                            quarto2.setStatusAtual("Disponivel");
                                            pausar(input);
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar(input);
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
                                            reserva1.setStatusReserva("Finalizada");
                                            quarto3.setStatusAtual("Disponivel");
                                            pausar(input);
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar(input);
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
                                            reserva1.setStatusReserva("Finalizada");
                                            quarto3.setStatusAtual("Disponivel");
                                            pausar(input);
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar(input);
                                            continue;
                                        } else {
                                            System.out.println("ERRO: Digite uma opção válida.");
                                            continue;
                                        }

                                    }

                                }
                            } else {
                                System.out.println("ERRO: Não há reservas ativas ligadas a esse CPF.");
                                pausar(input);
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
                                            reserva2.setStatusReserva("Finalizada");
                                            quarto1.setStatusAtual("Disponivel");
                                            pausar(input);
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar(input);
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
                                            reserva2.setStatusReserva("Finalizada");
                                            quarto1.setStatusAtual("Disponivel");
                                            pausar(input);
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar(input);
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
                                            reserva2.setStatusReserva("Finalizada");
                                            quarto2.setStatusAtual("Disponivel");
                                            pausar(input);
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar(input);
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
                                            reserva2.setStatusReserva("Finalizada");
                                            quarto2.setStatusAtual("Disponivel");
                                            pausar(input);
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar(input);
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
                                            reserva2.setStatusReserva("Finalizada");
                                            quarto3.setStatusAtual("Disponivel");
                                            pausar(input);
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar(input);
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
                                            reserva2.setStatusReserva("Finalizada");
                                            quarto3.setStatusAtual("Disponivel");
                                            pausar(input);
                                        } else if (opcaoPagamento == 2) {
                                            System.out.println("Redirecionando...");
                                            pausar(input);
                                            continue;
                                        } else {
                                            System.out.println("ERRO: Digite uma opção válida.");
                                            pausar(input);
                                            continue;
                                        }

                                    }

                                }
                                reserva2.setStatusReserva("Finalizada");
                            } else {
                                System.out.println("ERRO: Não há reservas ativas ligadas a esse CPF.");
                                pausar(input);
                            }
                        } else {
                            System.out.println("ERRO: O cpf inserido não esta em nosso banco de dados.");
                            pausar(input);
                            continue;
                        }
                    break;
                case 4:
                    System.out.println("Digite o seu cpf cadastrado na plataforma: ");
                    String cpfCadastroCancelamento = input.next();

                    if (cpfCadastroCancelamento.equals(hospede1.getCpf())) {

                        if (reserva1 != null && reserva1.getStatusReserva().equals("Ativa")) {

                            if (reserva1.getQuartoReservado() == 100) {
                                quarto1.setStatusAtual("Disponivel");
                            } else if (reserva1.getQuartoReservado() == 101) {
                                quarto2.setStatusAtual("Disponivel");
                            } else if (reserva1.getQuartoReservado() == 102) {
                                quarto3.setStatusAtual("Disponivel");
                            }
                            reserva1.setStatusReserva("Cancelada");
                            System.out.println("Cancelamento efetuado com sucesso!");
                            pausar(input);
                            continue;

                        } else {
                            System.out.println("ERRO: Não existe uma reserva ativa para esse CPF.");
                            pausar(input);
                            continue;
                        }
                    } else if (cpfCadastroCancelamento.equals(hospede2.getCpf())) {

                        if (reserva2 != null && reserva2.getStatusReserva().equals("Ativa")) {

                            if (reserva2.getQuartoReservado() == 100) {
                                quarto1.setStatusAtual("Disponivel");
                            } else if (reserva2.getQuartoReservado() == 101) {
                                quarto2.setStatusAtual("Disponivel");
                            } else if (reserva2.getQuartoReservado() == 102) {
                                quarto3.setStatusAtual("Disponivel");
                            }
                            reserva2.setStatusReserva("Cancelada");
                            System.out.println("Cancelamento efetuado com sucesso!");
                            pausar(input);
                            continue;

                        } else {
                            System.out.println("ERRO: Não existe uma reserva ativa para esse CPF.");
                            pausar(input);
                            continue;
                        }

                    } else {
                        System.out.println("ERRO: O cpf inserido não esta em nosso banco de dados.");
                        pausar(input);
                        continue;
                    }
                case 5:
                    System.out.println("Digite o seu cpf cadastrado na plataforma: ");
                    String cpfCadastroVizuReserva = input.next();

                    if (cpfCadastroVizuReserva.equals(hospede1.getCpf())) {

                        if (reserva1 != null && !(reserva1.getStatusReserva().isEmpty())) {
                            System.out.println("\n---- RESERVA ----\n");
                            System.out.println(reserva1);
                            pausar(input);
                        } else {
                            System.out.println("ERRO: Nenhuma reserva foi realizada para esse cpf.");
                            pausar(input);
                        }

                    } else if (cpfCadastroVizuReserva.equals(hospede2.getCpf())) {

                        if (reserva2 != null && !(reserva2.getStatusReserva().isEmpty())) {
                            System.out.println("\n---- RESERVA ----\n");
                            System.out.println(reserva2);
                            pausar(input);
                        } else {
                            System.out.println("ERRO: Nenhuma reserva foi realizada para esse cpf.");
                            pausar(input);
                        }

                    } else {
                        System.out.println("ERRO: O cpf inserido não esta em nosso banco de dados.");
                        pausar(input);
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
