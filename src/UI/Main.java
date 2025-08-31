package UI;

import java.util.List;
import java.util.Scanner;

import Fachada.FinanceiroFachada;
import Negocios.Categoria;
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
                                System.out.println("4. Buscar Conta por ID");
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
                                        int idNomeConta = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Digite o novo nome para a conta: ");
                                        String novoNomeConta = scanner.nextLine();

                                        fachada.editarNomeConta(idNomeConta, novoNomeConta);

                                        System.out.println("Conta editada com sucesso!");
                                        break;

                                    case "3":
                                        System.out.print("Digite o ID da conta à ser removida: ");
                                        int idExcluirConta = scanner.nextInt();
                                        scanner.nextLine();

                                        fachada.removerConta(idExcluirConta);

                                        System.out.println("Conta removida com sucesso!");
                                        break;

                                    case "4":
                                        System.out.print("Digite o ID da conta: ");
                                        int idBuscaConta = scanner.nextInt();
                                        scanner.nextLine();

                                        Conta encontradoConta = fachada.buscarContaPorId(idBuscaConta);

                                        if (encontradoConta != null) {
                                            System.out.println("Conta encontrada:");
                                            System.out.println(encontradoConta);
                                        }

                                        break;

                                    case "5":
                                        System.out.print("Lista de Contas: ");
                                        List<Conta> listaContas = fachada.listarContas();

                                        if (listaContas.isEmpty()) {
                                            System.out.println("Nenhuma conta cadastrada.");
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
                        while (true) {
                            try {
                                System.out.println("\n==== Gerenciar Categorias ====");
                                System.out.println("1. Criar Categoria");
                                System.out.println("2. Editar Nome da Categoria");
                                System.out.println("3. Remover Categoria");
                                System.out.println("4. Buscar Categoria por ID");
                                System.out.println("5. Listar Categorias");
                                System.out.println("0. Voltar");
                                System.out.print("Opção: ");
                                String opcaoCat = scanner.nextLine();

                                switch (opcaoCat) {
                                    case "1":
                                        System.out.print("Digite o nome da nova categoria: ");
                                        String nomeCat = scanner.nextLine();

                                        fachada.criarCategoria(nomeCat);

                                        System.out.println("Categoria criada com sucesso!");
                                        break;

                                    case "2":
                                        System.out.print("Digite o ID que deseja editar: ");
                                        int idNomeCat = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Digite o novo nome para a categoria: ");
                                        String novoNomeCat = scanner.nextLine();

                                        fachada.editarNomeCategoria(idNomeCat, novoNomeCat);

                                        System.out.println("Categoria editada com sucesso!");
                                        break;

                                    case "3":
                                        System.out.print("Digite o ID da categoria à ser removida: ");
                                        int idExcluirCat = scanner.nextInt();
                                        scanner.nextLine();

                                        fachada.removerCategoria(idExcluirCat);

                                        System.out.println("Categoria removida com sucesso!");
                                        break;

                                    case "4":
                                        System.out.print("Digite o ID da categoria: ");
                                        int idBuscaCat = scanner.nextInt();
                                        scanner.nextLine();

                                        Categoria encontradoCat = fachada.buscarCategoriaPorId(idBuscaCat);

                                        if (encontradoCat != null) {
                                            System.out.println("Categoria encontrada:");
                                            System.out.println(encontradoCat);
                                        }

                                        break;

                                    case "5":
                                        System.out.print("Lista de Categorias: ");
                                        List<Categoria> listaCats = fachada.listarCategorias();

                                        if (listaCats.isEmpty()) {
                                            System.out.println("Nenhuma categoria cadastrada.");
                                        } else {
                                            for (Categoria c : listaCats) {
                                                System.out.println(c);
                                            }
                                        }

                                        break;

                                    case "0":
                                        System.out.println("Voltando ao menu principal...");
                                        break;

                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
                                }

                                if (opcaoCat.equals("0")) break;

                            } catch (Exception e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }
                        break;

                    case "3":
                        while (true) {
                            try {
                                System.out.println("\n==== Gerenciar Transações ====");
                                System.out.println("1. Adicionar Receita");
                                System.out.println("2. Adicionar Despesa");
                                System.out.println("3. Remover Transação");
                                System.out.println("4. Buscar Transação por ID");
                                System.out.println("5. Listar Transações");
                                System.out.println("0. Voltar");
                                System.out.print("Opção: ");
                                String opcaoTrans = scanner.nextLine();

                                switch (opcaoTrans) {
                                    case "1":

                                        break;

                                    case "2":

                                        break;

                                    case "0":
                                        System.out.println("Voltando ao menu principal...");
                                        break;

                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
                                }

                                if (opcaoTrans.equals("0")) break;

                            } catch (Exception e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }
                        break;

                    case "4":
                        while (true) {
                            try {
                                System.out.println("\n==== Gerenciar Despesas Recorrentes ====");
                                System.out.println("1. Adicionar Despesa Recorrente");
                                System.out.println("2. Processar Despesa Recorrente");
                                System.out.println("2. Listar Despesas Recorrentes");
                                System.out.println("0. Voltar");
                                System.out.print("Opção: ");
                                String opcaoDesp = scanner.nextLine();

                                switch (opcaoDesp) {
                                    case "1":

                                        break;

                                    case "2":

                                        break;

                                    case "0":
                                        System.out.println("Voltando ao menu principal...");
                                        break;

                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
                                }

                                if (opcaoDesp.equals("0")) break;

                            } catch (Exception e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }
                        break;

                    case "5":
                        while (true) {
                            try {
                                System.out.println("\n==== Gerar Relatórios ====");
                                System.out.println("1. Gerar Balanço");
                                System.out.println("2. Gerar Relatório por Categoria");
                                System.out.println("3. Gerar Relatório Mensal");
                                System.out.println("4. Gerar Relatório Trimestral");
                                System.out.println("5. Gerar Relatório Semestral");
                                System.out.println("6. Exibir Relatório Anual");
                                System.out.println("0. Voltar");
                                System.out.print("Opção: ");
                                String opcaoRel = scanner.nextLine();

                                switch (opcaoRel) {
                                    case "1":

                                        break;

                                    case "2":

                                        break;

                                    case "0":
                                        System.out.println("Voltando ao menu principal...");
                                        break;

                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
                                }

                                if (opcaoRel.equals("0")) break;

                            } catch (Exception e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }
                        break;

                    case "6":
                        while (true) {
                            try {
                                System.out.println("\n==== Persistência ====");
                                System.out.println("1. Salvar Dados");
                                System.out.println("2. Carregar Dados");
                                System.out.println("0. Voltar");
                                System.out.print("Opção: ");
                                String opcaoPers = scanner.nextLine();

                                switch (opcaoPers) {
                                    case "1":
                                        System.out.println("Salvando dados...");
                                        // fachada.salvarDados();
                                        break;

                                    case "2":
                                        System.out.println("Carregando dados...");
                                        // fachada.carregarDados();
                                        break;

                                    case "0":
                                        System.out.println("Voltando ao menu principal...");
                                        break;

                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
                                }

                                if (opcaoPers.equals("0")) break;

                            } catch (Exception e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                        }
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
