package UI;

import java.util.List;
import java.util.Scanner;

import Fachada.FinanceiroFachada;
import Negocios.Categoria;
import Negocios.Conta;
import Negocios.Transacao;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        FinanceiroFachada fachada = new FinanceiroFachada();

        int idTemp;
        int idTempDois;
        double valorTemp;
        String descTemp;
        Categoria  categoriaTemp;
        Transacao transacaoTemp;

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
                                        try{ 
                                        System.out.print("Digite o ID que deseja editar: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();
                                        }catch(InputMismatchException e){
                                            System.out.println("Erro: digite um numero inteiro valido para o ID!");
                                            scanner.nextLine();
                                            break;
                                        }
                                        System.out.print("Digite o novo nome para a conta: ");
                                        String novoNomeConta = scanner.nextLine();

                                        fachada.editarNomeConta(idTemp, novoNomeConta);

                                        System.out.println("Conta editada com sucesso!");
                                        break;

                                    case "3":
                                        try{
                                        System.out.print("Digite o ID da conta à ser removida: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();
                                        fachada.removerConta(idTemp);
                                        System.out.println("Conta removida com sucesso!");
                                        }catch(InputMismatchException e){
                                            System.out.println("Erro: digite um numero inteiro valido para o ID!");
                                            scanner.nextLine();
                                            break;
                                        }catch(NegocioException e){
                                            System.out.println("Erro: "+e.getMessage());
                                        }
                                        break;
                                        
                                    case "4":
                                        System.out.print("Digite o ID da conta: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();

                                        Conta encontradoConta = fachada.buscarContaPorId(idTemp);

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

                                        try{
                                        fachada.criarCategoria(nomeCat);
                                        System.out.println("Categoria criada com sucesso!");
                                        }catch(NegocioException e){
                                            System.out.println("Erro: " +e.getMessage());
                                        }
                                        break;

                                    case "2":
                                        System.out.print("Digite o ID que deseja editar: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Digite o novo nome para a categoria: ");
                                        String novoNomeCat = scanner.nextLine();
                                        try{
                                        fachada.editarNomeCategoria(idTemp, novoNomeCat);
                                        System.out.println("Categoria editada com sucesso!");
                                        }catch(NegocioException e){
                                            System.out.println("Erro: " +e.getMessage());
                                        }
                                        break;                                       

                                    case "3":
                                        System.out.print("Digite o ID da categoria à ser removida: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();
                                        try{
                                        fachada.removerCategoria(idTemp);
                                        System.out.println("Categoria removida com sucesso!");
                                        }catch(NegocioException e){
                                            System.out.println("Erro :"+e.getMessage());
                                        }
                                        break;

                                    case "4":
                                        System.out.print("Digite o ID da categoria: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();
                                        try{
                                        categoriaTemp = fachada.buscarCategoriaPorId(idTemp);
                                        System.out.println("Categoria encontrada:");
                                        System.out.println(categoriaTemp);
                                        }catch(NegocioException e){
                                            System.out.println("Erro: "+e.getMessage());
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
                                        System.out.print("Digite o id da conta: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Digite o valor da receita: ");
                                        valorTemp = scanner.nextDouble();
                                        scanner.nextLine();

                                        System.out.print("Digite a descrição da receita: ");
                                        descTemp = scanner.nextLine();

                                        System.out.print("Digite o id da categoria: ");
                                        idTempDois = scanner.nextInt();
                                        scanner.nextLine();
                                        categoriaTemp = fachada.buscarCategoriaPorId(idTempDois);

                                        fachada.adicionarReceita(idTemp, valorTemp, descTemp, categoriaTemp);

                                        System.out.println("Receita adicionada com sucesso. ");
                                        break;

                                    case "2":
                                        System.out.print("Digite o id da conta: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Digite o valor da despesa: ");
                                        valorTemp = scanner.nextDouble();
                                        scanner.nextLine();

                                        System.out.print("Digite a descrição da despesa: ");
                                        descTemp = scanner.nextLine();

                                        System.out.print("Digite o id da categoria: ");
                                        idTempDois = scanner.nextInt();
                                        scanner.nextLine();

                                        fachada.adicionarDespesa(idTemp, valorTemp, descTemp, idTempDois);

                                        System.out.println("Despesa adicionada com sucesso. ");

                                        break;
                                    case "3":
                                        System.out.print("Digite o id da transação: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();

                                        fachada.removerTransacao(idTemp);

                                        System.out.println("Transação excluída com sucesso. ");

                                        break;

                                    case "4":
                                        System.out.print("Digite o id da transação: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();

                                        transacaoTemp = fachada.buscarTransacaoPorId(idTemp);

                                        if (transacaoTemp != null) {
                                            System.out.println("Transação encontrada:");
                                            System.out.println(transacaoTemp);
                                        }
                                        break;

                                    case "5":
                                        System.out.print("Lista de Transações: ");
                                        List<Transacao> listaTrans = fachada.listarTransacoes();

                                        if (listaTrans.isEmpty()) {
                                            System.out.println("Nenhuma transação cadastrada.");
                                        } else {
                                            for (Transacao t : listaTrans) {
                                                System.out.println(t);
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
