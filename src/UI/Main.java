package UI;

import java.util.List;
import java.util.Scanner;
import Negocios.exceptions.NegocioException;
import Negocios.exceptions.SaldoInsuficienteException;

import Fachada.FinanceiroFachada;
import Negocios.*;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        FinanceiroFachada fachada = new FinanceiroFachada();

        int idTemp;
        int idTempDois;
        double valorTemp;
        int valorTempDois;
        String descTemp;
        Categoria  categoriaTemp;
        Transacao transacaoTemp;
        String opcao;
        Periodicidade periodicidadeTemp;


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
                opcao = scanner.nextLine();

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
                                opcao = scanner.nextLine();

                                switch (opcao) {
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
                                        }catch(NegocioException e){
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
                                        }catch(NegocioException e){
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

                                if (opcao.equals("0")) {
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
                                opcao = scanner.nextLine();

                                switch (opcao) {
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

                                if (opcao.equals("0")) break;

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
                                opcao = scanner.nextLine();

                                switch (opcao) {
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
                                        try{
                                        fachada.adicionarReceita(idTemp, valorTemp, descTemp, categoriaTemp);
                                        System.out.println("Receita adicionada com sucesso. ");
                                        }catch(NegocioException e){
                                            System.out.println("Erro: "+e,getMessage());
                                        }

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
                                        try{
                                        fachada.adicionarDespesa(idTemp, valorTemp, descTemp, idTempDois);
                                        System.out.println("Despesa adicionada com sucesso. ");
                                        }catch(NegocioException e){
                                            System.out.println("Erro: "+e.getMessage());
                                        }catch(SaldoInsuficienteException e){
                                            System.out.println("Erro: "+e.getMessage());
                                        }
                                        break;

                                    case "3":
                                        System.out.print("Digite o id da transação: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();
                                        try{
                                        fachada.removerTransacao(idTemp);
                                        System.out.println("Transação excluída com sucesso. ");
                                        }catch(NegocioException e){
                                            throw new NegocioException("Erro: "+e.getMessage());
                                        }catch(SaldoInsuficienteException e){
                                            throw new SaldoInsuficienteException("Erro: "+e.getMessage());
                                        }
                                        break;

                                    case "4":
                                        System.out.print("Digite o id da transação: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();
                                        try{
                                        transacaoTemp = fachada.buscarTransacaoPorId(idTemp);
                                        System.out.println("Transação encontrada:");
                                        System.out.println(transacaoTemp);
                                        }catch(NegocioException e){
                                            System.out.println("Erro: "+e.getMessage());
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

                                if (opcao.equals("0")) break;

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
                                System.out.println("3. Listar Despesas Recorrentes");
                                System.out.println("4. Remover Despesas Recorrentes");
                                System.out.println("5. Buscar Despesas Recorrentes");
                                System.out.println("0. Voltar");
                                System.out.print("Opção: ");
                                opcao = scanner.nextLine();

                                switch (opcao) {
                                    case "1":
                                        System.out.print("Digite o valor da despesa recorrente: ");
                                        valorTemp = scanner.nextDouble();
                                        scanner.nextLine();

                                        System.out.print("Digite a descrição da despesa: ");
                                        descTemp = scanner.nextLine();

                                        System.out.print("Digite o id da conta: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Digite o id da categoria: ");
                                        idTempDois = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.println("Escolha a periodicidade:");
                                        System.out.println("1 - Diária");
                                        System.out.println("2 - Semanal");
                                        System.out.println("3 - Mensal");
                                        System.out.println("4 - Anual");
                                        System.out.print("Opção: ");

                                        int escolha = scanner.nextInt();
                                        scanner.nextLine(); // consome ENTER



                                        switch (escolha) {
                                            case 1:
                                                periodicidadeTemp = Periodicidade.DIARIA;
                                                break;
                                            case 2:
                                                periodicidadeTemp = Periodicidade.SEMANAL;
                                                break;
                                            case 3:
                                                periodicidadeTemp = Periodicidade.MENSAL;
                                                break;
                                            case 4:
                                                periodicidadeTemp = Periodicidade.ANUAL;
                                                break;
                                            default:
                                                System.out.println("Opção inválida, assumindo MENSAL como padrão.");
                                                periodicidadeTemp = Periodicidade.MENSAL;
                                                break;
                                        }

                                        System.out.print("Digite o número de parcelas: ");
                                        valorTempDois = scanner.nextInt();
                                        scanner.nextLine();

                                        fachada.adicionarDespesaRecorrente(valorTemp, descTemp, idTemp, idTempDois, periodicidadeTemp, valorTempDois);

                                        System.out.println("Despesa recorrente adicionada com sucesso. ");
                                        break;

                                    case "2":
                                        System.out.println("Processando despesas recorrentes");

                                        fachada.processarDespesasRecorrentes();
                                        break;

                                    case "3":
                                        System.out.print("Lista de despesas recorrentes: ");
                                        List<DespesaRecorrente> listaDespRec = fachada.listarDespesasRecorrentes();

                                        if (listaDespRec.isEmpty()) {
                                            System.out.println("Nenhuma transação cadastrada.");
                                        } else {
                                            for (DespesaRecorrente dr : listaDespRec) {
                                                System.out.println(dr);
                                            }
                                        }

                                        break;

                                    case "4":
                                        System.out.print("Digite o id da despesa recorrente: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();

                                        fachada.removerDespesaRecorrente(idTemp);

                                        System.out.println("Despesa excluída com sucesso. ");
                                        break;

                                    case "5":
                                        System.out.print("Digite o id da despesa recorrenye: ");
                                        idTemp = scanner.nextInt();
                                        scanner.nextLine();

                                        DespesaRecorrente despesaRecorrenteTemp = fachada.buscarDespesaRecorrentePorId(idTemp);

                                        if (despesaRecorrenteTemp != null) {
                                            System.out.println("despesa encontrada:");
                                            System.out.println(despesaRecorrenteTemp);
                                        }
                                        break;
                                    case "0":
                                        System.out.println("Voltando ao menu principal...");
                                        break;

                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
                                }

                                if (opcao.equals("0")) break;

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
                                opcao = scanner.nextLine();

                                switch (opcao) {
                                    case "1":
                                        System.out.println(fachada.gerarBalanco());
                                        break;

                                    case "2":
                                        System.out.println(fachada.gerarBalanco());
                                        break;

                                    case "3":
                                        System.out.print("Digite o ano: ");
                                        int anoMensal = scanner.nextInt();
                                        System.out.print("Digite o mês (1-12): ");
                                        int mes = scanner.nextInt();
                                        scanner.nextLine(); // consome ENTER
                                        System.out.println(fachada.gerarRelatorioMensal(anoMensal, mes));
                                        break;

                                    case "4":
                                        System.out.print("Digite o ano: ");
                                        int anoTrim = scanner.nextInt();
                                        System.out.print("Digite o trimestre (1-4): ");
                                        int trimestre = scanner.nextInt();
                                        scanner.nextLine();
                                        System.out.println(fachada.gerarRelatorioTrimestral(anoTrim, trimestre));
                                        break;

                                    case "5":
                                        System.out.print("Digite o ano: ");
                                        int anoSem = scanner.nextInt();
                                        System.out.print("Digite o semestre (1-2): ");
                                        int semestre = scanner.nextInt();
                                        scanner.nextLine();
                                        System.out.println(fachada.gerarRelatorioSemestral(anoSem, semestre));
                                        break;

                                    case "6":
                                        System.out.print("Digite o ano: ");
                                        int anoAnual = scanner.nextInt();
                                        scanner.nextLine();
                                        System.out.println(fachada.exibirRelatorioAnual(anoAnual));
                                        break;

                                    case "0":
                                        System.out.println("Voltando ao menu principal...");
                                        break;

                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
                                }

                                if (opcao.equals("0")) break;

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
                                opcao = scanner.nextLine();

                                switch (opcao) {
                                    case "1":
                                        // Menu para salvar
                                        System.out.println("\nEscolha o formato para salvar:");
                                        System.out.println("1. Serialização");
                                        System.out.println("2. CSV");
                                        System.out.print("Opção: ");
                                        String formatoS = scanner.nextLine();
                                        
                                        if (formatoS.equals("1") || formatoS.equals("2")) {
                                            System.out.print("Nome do arquivo (pressione Enter para usar o padrão): ");
                                            String nomeArquivoSalvar = scanner.nextLine();
                                            if (nomeArquivoSalvar.isEmpty()) {
                                                nomeArquivoSalvar = "dados.dat";
                                            }
                                            
                                            if (formatoS.equals("1")) {
                                                fachada.salvarDados("serializacao", nomeArquivoSalvar);
                                                System.out.println("Dados salvos em serialização.");
                                            } else {
                                                fachada.salvarDados("csv", nomeArquivoSalvar);
                                                System.out.println("Dados salvos em CSV.");
                                            }
                                        } else {
                                            System.out.println("Opção inválida.");
                                        }
                                        break;

                                    case "2":
                                        // Menu para carregar
                                        System.out.println("\nEscolha o formato para carregar:");
                                        System.out.println("1. Serialização");
                                        System.out.println("2. CSV");
                                        System.out.print("Opção: ");
                                        String formatoC = scanner.nextLine();
                                        
                                        if (formatoC.equals("1") || formatoC.equals("2")) {
                                            System.out.print("Nome do arquivo (pressione Enter para usar o padrão): ");
                                            String nomeArquivoCarregar = scanner.nextLine();
                                            if (nomeArquivoCarregar.isEmpty()) {
                                                nomeArquivoCarregar = "dados.dat";
                                            }

                                            if (formatoC.equals("1")) {
                                                fachada.carregarDados("serializacao", nomeArquivoCarregar);
                                                System.out.println("Dados carregados da serialização.");
                                            } else {
                                                fachada.carregarDados("csv", nomeArquivoCarregar);
                                                System.out.println("Dados carregados do CSV.");
                                            }
                                        } else {
                                            System.out.println("Opção inválida.");
                                        }
                                        break;

                                    case "0":
                                        System.out.println("Voltando ao menu principal...");
                                        break;

                                    default:
                                        System.out.println("Opção inválida.");
                                        break;
                                }

                                if (opcao.equals("0")) break;

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
