package UI;

import java.util.List;
import java.util.Scanner;

import Fachada.FinanceiroFachada;
import Negocios.Conta;

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
                                System.out.println("3. Remover Conta");
                                System.out.println("4. Buscar Conta Por ID");
                                System.out.println("5. Listar Contas");
                                System.out.println("0. Voltar");
                                System.out.print("Opção: ");
                                String opcaoConta = scanner.nextLine();

                                switch (opcaoConta) {
                                    case "1":
                                        System.out.print("Digite o nome da nova conta: ");
                                        String nomeConta = scanner.nextLine();

                                        fachada.criarConta(nomeConta);

                                        System.out.println("Conta criada com sucesso!");
                                        break;

                                    case "2":
                                        System.out.print("Digite o ID que deseja editar: ");
                                        int idNomeAntigo = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Digite o novo nome para a conta: ");
                                        String novoNome = scanner.nextLine();

                                        fachada.editarNomeConta(idNomeAntigo, novoNome);

                                        System.out.println("Conta editada com sucesso!");
                                        break;

                                    case "3":
                                        System.out.print("Digite o ID da conta à ser removida: ");
                                        int idExcluir = scanner.nextInt();
                                        scanner.nextLine();

                                        fachada.removerConta(idExcluir);

                                        System.out.println("Conta removida com sucesso!");
                                        break;

                                    case "4":
                                        System.out.print("Digite o ID da conta: ");
                                        int idBusca = scanner.nextInt();
                                        scanner.nextLine();

                                        Conta encontrado = fachada.buscarContaPorId(idBusca);

                                        if (encontrado != null) {
                                            System.out.println("Conta encontrada:");
                                            System.out.println(encontrado);
                                        }

                                        break;

                                    case "5":
                                        System.out.print("Lista de Contas: ");
                                        List<Conta> listaContas = fachada.listarContas();

                                        if (listaContas.isEmpty()) {
                                            System.out.println("Nenhum médico cadastrado.");
                                        } else {
                                            for (Conta c : listaContas) {
                                                System.out.println(c);
                                            }
                                        }

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
