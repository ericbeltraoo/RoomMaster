package application;

import entities.Hospede;
import entities.Quarto;
import entities.Reserva;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void pausar() {
        System.out.println("Pressione enter para continuar... ");
        Scanner pausar = new Scanner(System.in);
        pausar.nextLine();
    }

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner input = new Scanner(System.in);

        System.out.println("\n---- 1° HOSPEDE ----\n");
        System.out.println("Digite o nome do 1° hóspede:");
        String tempNomeHospede1 = input.nextLine();
        System.out.println("Digite o cpf do 1° hóspede: ");
        int tempCpf1 = input.nextInt();
        System.out.println("Digite o rg do 1° hóspede: ");
        int tempRg1 = input.nextInt();

        Hospede hospede1 = new Hospede(tempNomeHospede1,tempCpf1,tempRg1);

        System.out.println("\n---- 2° HOSPEDE ----\n");
        System.out.println("Digite o nome do 2° hóspede:");
        String tempNomeHospede2 = input.nextLine();
        System.out.println("Digite o cpf do 2° hóspede: ");
        int tempCpf2 = input.nextInt();
        System.out.println("Digite o rg do 2° hóspede: ");
        int tempRg2 = input.nextInt();

        Hospede hospede2 = new Hospede(tempNomeHospede2,tempCpf2,tempRg2);

        System.out.println("\n---- 1° QUARTO ----\n");
        int tempNumeroQuarto1 = 100;
        System.out.println("Digite o tipo do quarto: (Standard ou Suite)");
        String tempTipoQuarto1 = input.nextLine().toLowerCase();
        if(!(tempTipoQuarto1.equals("standard") || tempTipoQuarto1.equals("suite"))) {
            System.out.println("ERRO: Digite um tipo de quarto válido.");
        }
        System.out.println("Digite o preço da diária: ");
        double tempPrecoDiaria1 = input.nextDouble();

        Quarto quarto1 = new Quarto(tempNumeroQuarto1,tempTipoQuarto1,tempPrecoDiaria1);

        System.out.println("\n---- 2° QUARTO ----\n");
        int tempNumeroQuarto2 = 101;
        System.out.println("Digite o tipo do quarto: (Standard ou Suite)");
        String tempTipoQuarto2 = input.nextLine().toLowerCase();
        if(!(tempTipoQuarto2.equals("standard") || tempTipoQuarto2.equals("suite"))) {
            System.out.println("ERRO: Digite um tipo de quarto válido.");
        }
        System.out.println("Digite o preço da diária: ");
        double tempPrecoDiaria2 = input.nextDouble();

        Quarto quarto2 = new Quarto(tempNumeroQuarto2,tempTipoQuarto2,tempPrecoDiaria2);

        System.out.println("\n---- 3° QUARTO ----\n");
        int tempNumeroQuarto3 = 102;
        System.out.println("Digite o tipo do quarto: (Standard ou Suite)");
        String tempTipoQuarto3 = input.nextLine().toLowerCase();
        if(!(tempTipoQuarto3.equals("standard") || tempTipoQuarto3.equals("suite"))) {
            System.out.println("ERRO: Digite um tipo de quarto válido.");
        }
        System.out.println("Digite o preço da diária: ");
        double tempPrecoDiaria3 = input.nextDouble();

        Quarto quarto3 = new Quarto(tempNumeroQuarto3,tempTipoQuarto3,tempPrecoDiaria3);


        int menuInterativo = 0;
        Reserva reserva1 = null;
        Reserva reserva2 = null;

        while (menuInterativo != 4) {

            System.out.println("---- MENU INTERATIVO ----");
            System.out.println("1 - Visualizar Quartos");
            System.out.println("2 - Criar Reserva");
            System.out.println("3 - Realizar Checkout");
            System.out.println("4 - Sair");
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
                    break;
                case 2:
                    System.out.println("Digite o seu cpf cadastrado na plataforma: ");
                    int cpfCadastrado = input.nextInt();

                    String tempHospedeResp;

                    if (cpfCadastrado == hospede1.getCpf()) {
                        tempHospedeResp = hospede1.getNome();

                        System.out.println("Digite o quarto que você deseja reservar: ( 100 / 101 / 102 )");
                        System.out.println("OBS: Verifique a disponibilidade do mesmo digitando 1 no menu iniciar.");
                        int tempQuartoReservado = input.nextInt();

                        if (tempQuartoReservado == 100) {
                            quarto1.setStatusAtual("Ocupado");
                        } else if (tempQuartoReservado == 101) {
                            quarto2.setStatusAtual("Ocupado");
                        } else if (tempQuartoReservado == 102) {
                            quarto3.setStatusAtual("Ocupado");
                        } else {
                            System.out.println("ERRO: Digite um quarto válido.");
                            continue;
                        }

                        System.out.println("Digite a quantidade de dias que você deseja reservar: ");
                        int tempQtdDiariasAgendadas = input.nextInt();

                        String tempStatusReserva = "Ativa";

                        reserva1 = new Reserva(tempHospedeResp,tempQuartoReservado,tempQtdDiariasAgendadas,tempStatusReserva);

                    } else if (cpfCadastrado == hospede2.getCpf()) {
                        tempHospedeResp = hospede2.getNome();

                        System.out.println("Digite o quarto que você deseja reservar: ( 100 / 101 / 102 )");
                        System.out.println("OBS: Verifique a disponibilidade do mesmo digitando 1 no menu iniciar.");
                        int tempQuartoReservado = input.nextInt();

                        if (tempQuartoReservado == 100) {
                            quarto1.setStatusAtual("Ocupado");
                        } else if (tempQuartoReservado == 101) {
                            quarto2.setStatusAtual("Ocupado");
                        } else if (tempQuartoReservado == 102) {
                            quarto3.setStatusAtual("Ocupado");
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
                    int cpfCadastro = input.nextInt();

                    if (cpfCadastro == hospede1.getCpf()) {
                        if(reserva1.getStatusReserva() == "Ativo") {

                            // calculos

                        } else {
                            System.out.println("ERRO: Não à reservas ativas ligadas a esse CPF.");
                        }
                    } else if (cpfCadastro == hospede2.getCpf()) {
                        if(reserva2.getStatusReserva() == "Ativo") {

                            // calculos

                        } else {
                            System.out.println("ERRO: Não à reservas ativas ligadas a esse CPF.");
                        }
                    }
                    break;
                case 4:
                    menuInterativo = 4;
                    break;
                default:
                    System.out.println("ERRO: Digite uma opção válida.");
            }
        }


    }
}
