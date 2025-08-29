package Fachada;

import Dados.PersistenciaDados;
import Dados.RepositorioCategoria;
import Dados.RepositorioConta;
import Dados.RepositorioTransacao;
import Negocios.Categoria;
import Negocios.ControleFinanceiro;
import Negocios.Relatorio;
import Negocios.Transacao;
import Negocios.Receita;
import Negocios.Despesa;
import Negocios.Conta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class FinanceiroFachada {
    private ControleFinanceiro controle;
    private PersistenciaDados persistencia;
    private Relatorio relatorio;

    public FinanceiroFachada() {
    	RepositorioTransacao repoTransacao = new RepositorioTransacao();
        RepositorioCategoria repoCategoria = new RepositorioCategoria();
        RepositorioConta repoConta = new RepositorioConta();
        
    	this.controle = new ControleFinanceiro(repoTransacao, repoCategoria, repoConta);
        this.persistencia = new PersistenciaDados();
        this.relatorio = new Relatorio();
    }
//TUDO: falta a persistencia e os relatorios, toda a outra parte das transacoes e categoria ja foram feitas
    
    // ===== Persistência =====
  

    // ===== Conta =====
    public void adicionarTransferencia(Conta origem, Conta destino, double valor,Categoria categoria, String descricao) {
        controle.adicionarTransferencia(origem, destino, valor,categoria,descricao);
    }

    // ===== Transações =====
    public void adicionarTransacao(String tipo, String descricao, double valor, LocalDate data, Categoria categoria, Conta conta) { // Agora com o argumento Conta
        if ("receita".equalsIgnoreCase(tipo)) {
            Receita novaReceita = new Receita(UUID.randomUUID().toString(), descricao, valor, data, categoria, conta);
            controle.adicionarTransacao(novaReceita, categoria);
        } else if ("despesa".equalsIgnoreCase(tipo)) {
            Despesa novaDespesa = new Despesa(UUID.randomUUID().toString(), descricao, valor, data, categoria, conta);
            controle.adicionarTransacao(novaDespesa, categoria);
        }
    }

     public void removerTransacao(String id) {
         controle.removerTransacao(id);
     }
    
    public ArrayList<Transacao> listarTransacoes() {
    	return controle.getTransacoes();
    }

    //dividi o metodo de editar transacao, para poder editar cada atributo individualmente
    
    public boolean editarDescricaoTransacao(String id, String novaDescricao) {
        return controle.editarDescricaoTransacao(id, novaDescricao);
    }

    public boolean editarValorTransacao(String id, double novoValor) {
        return controle.editarValorTransacao(id, novoValor);
    }
    
    public boolean editarDataTransacao(String id, LocalDate novaData) {
        return controle.editarDataTransacao(id, novaData);
    }
    
    public boolean editarCategoriaTransacao(String id, Categoria novaCat) {
        return controle.editarCategoriaTransacao(id, novaCat);
    }
    
    
    // ===== Categorias =====
    public void adicionarCategoria(String nome) {
    	Categoria novaCategoria = new Categoria(UUID.randomUUID().toString(), nome);
        controle.adicionarCategoria(novaCategoria);
    }

    public void editarCategoria(String antigoNome, String novoNome) {
        controle.editarCategoria(antigoNome, novoNome);
    }

    public void removerCategoria(String nome) {
        for (Categoria c : controle.getCategorias()) {
            if (c.getNome().equals(nome)) {
                controle.removerCategoria(c.getId());
                return;
            }
        }
    }
    
    public ArrayList<Categoria> listarCategorias() {
    	return controle.getCategorias();
    }
    // ===== Despesas Recorrentes =====
    public void adicionarDespesaRecorrente(String descricao, double valor, String categoriaId, String contaId, String frequencia, int numeroDeParcelas) {
        Categoria categoria = controle.buscarCategoriaPorId(categoriaId);
        Conta conta = controle.buscarContaPorId(contaId);
        
        if (categoria != null && conta != null && valor > 0) {
        controle.adicionarDespesaRecorrente(descricao, valor, categoria, conta, frequencia, numeroDeParcelas);
        }
    }
       
    
    public void processarDespesasRecorrentes() {
        controle.processarDespesasRecorrentes();
    }


    // ===== Relatórios =====
    public String exibirBalanco() {
        return relatorio.gerarBalanco(controle.getTransacoes());
    }

    public String exibirRelatorioGastos() {
        return relatorio.gerarRelatorioGastoPorCategoria(controle.getTransacoes());
    }

    public String exibirRelatorioMensal(int ano, int mes) {
        return relatorio.gerarRelatorioMensal(controle.getTransacoes(), ano, mes);
    }

    public String exibirRelatorioTrimestral(int ano, int trimestre) {
        return relatorio.gerarRelatorioTrimestral(controle.getTransacoes(), ano, trimestre);
    }

    public String exibirRelatorioSemestral(int ano, int semestre) {
        return relatorio.gerarRelatorioSemestral(controle.getTransacoes(), ano, semestre);
    }

    public String exibirRelatorioAnual(int ano) {
        return relatorio.gerarRelatorioAnual(controle.getTransacoes(), ano);
    }
}

