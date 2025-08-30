package UI;

import java.util.Scanner;

import Fachada.FinanceiroFachada;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        FinanceiroFachada fachada = new FinanceiroFachada();

        while (true) {
            try {
                System.out.println("\n==== Seu Controle Financeiro Pessoal ====");
                System.out.println("1. Contas");
                System.out.println("2. Categorias");
                System.out.println("3. Transações");
                System.out.println("4. Despesas Recorrentes");
                System.out.println("5. Relatórios");
                System.out.println("6. Persistência");
                System.out.println("0. Sair");
                System.out.print("Opção: ");
                String opcao = scanner.nextLine();

                switch (opcao) {
                    case "1":
                        while (true) {
                            try {
                                System.out.println("\n==== Gerenciar Contas ====");
                                System.out.println("1. Criar Conta");
                                System.out.println("2. Editar Nome da Conta");
                                System.out.println("0. Voltar");
                                System.out.print("Opção: ");
                                String opcaoConta = scanner.nextLine();

                                switch (opcaoConta) {
                                    case "1":
                                        System.out.print("Digite o nome da nova conta: ");
                                        String nomeConta = scanner.nextLine();
                                        // aqui vai a lógica de criar a conta
                                        System.out.println("Conta criada com sucesso!");
                                        break;

                                    case "2":
                                        System.out.print("Digite o ID que deseja editar: ");
                                        String nomeAntigo = scanner.nextLine();
                                        // aqui vai a lógica de editar o nome
                                        System.out.println("Conta editada com sucesso!");
                                        break;

                                    case "0":
                                        System.out.println("Voltando ao menu principal...");
                                        break;

                                    default:
                                        System.out.println("Opção inválida, tente novamente.");
                                        break;
                                }

                                if (opcaoConta.equals("0")) {
                                    break; // sai do submenu de contas
                                }

                            } catch (Exception e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }
                        break;

                    case "2":
                        System.out.println(">> Gerenciar Categorias");
                        // exemplo: controle.adicionarCategoria(...)
                        break;

                    case "3":
                        System.out.println(">> Gerenciar Transações");
                        // exemplo: controle.adicionarTransacao(...)
                        break;

                    case "4":
                        System.out.println(">> Gerenciar Despesas Recorrentes");
                        // exemplo: cadastrar recorrências
                        break;

                    case "5":
                        System.out.println(">> Relatórios");
                        // exemplo: Relatorio.gerarBalanco(controle.getTransacoes())
                        break;

                    case "6":
                        System.out.println(">> Persistência");
                        // salvar/carregar dados do sistema
                        break;

                    case "0":
                        System.out.println("Saindo do sistema...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Opção inválida, tente novamente.");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
