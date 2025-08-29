package Negocios;

import Dados.RepositorioCategoria;	
import Dados.RepositorioTransacao;
import Dados.RepositorioConta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ControleFinanceiro {
    private RepositorioTransacao repoTransacao;
    private RepositorioCategoria repoCategoria;
    private RepositorioConta repoConta;
    private ArrayList<DespesaRecorrente> despesasRecorrentes;

    public ControleFinanceiro(RepositorioTransacao repoTransacao, RepositorioCategoria repoCategoria, RepositorioConta repoConta) {
        this.repoTransacao = repoTransacao;
        this.repoCategoria = repoCategoria;
        this.repoConta = repoConta;
        this.despesasRecorrentes = new ArrayList<>();
    }
    
    // ===== Persistencia =====
    
    // ===== Contas =====
    public void adicionarTransferencia(Conta origem, Conta destino, double valor,Categoria categoria, String descricao) { // transferencia interna entre contas
        Despesa despesa = new Despesa(UUID.randomUUID().toString(), descricao, valor,LocalDate.now(), categoria, origem);
        Receita receita = new Receita(UUID.randomUUID().toString(), descricao, valor, LocalDate.now(), categoria,destino);

        repoTransacao.adicionar(despesa);
        repoTransacao.adicionar(receita);
        
        origem.debitar(valor);
        destino.creditar(valor);
    }
    
    public Conta buscarContaPorId(String id) {
        return repoConta.buscarPorId(id);
    }
    
    // ===== Transações =====
    public void adicionarTransacao(Transacao t, Categoria c) {
    	repoTransacao.adicionar(t);          
    }
    
    public boolean removerTransacao(String id) {
        return repoTransacao.remover(id);
    }


    public ArrayList<Transacao> getTransacoes() {
        return repoTransacao.getTodos();
    }
    
    public boolean editarDescricaoTransacao(String id, String novaDescricao) {
        for (Transacao t : repoTransacao.getTodos()) {
            if (t.getId().equals(id)) {
                t.setDescricao(novaDescricao);
                return true;
            }
        }
        return false;
    }

    public boolean editarValorTransacao(String id, double novoValor) {
        for (Transacao t : repoTransacao.getTodos()) {
            if (t.getId().equals(id)) {
                t.setValor(novoValor);
                return true;
            }
        }
        return false;
    }
 
    public boolean editarDataTransacao(String id, LocalDate novaData) {
        for (Transacao t : repoTransacao.getTodos()) {
            if (t.getId().equals(id)) {
                t.setData(novaData);
                return true;
            }
        }
        return false;
    }
    
    public boolean editarCategoriaTransacao(String id, Categoria novaCat) {
        for (Transacao t : repoTransacao.getTodos()) {
            if (t.getId().equals(id)) {
                t.setCategoria(novaCat);
                return true;
            }
        }
        return false;
    }
    
    
    

    // ===== Categorias =====
    public void adicionarCategoria(Categoria c) {
        repoCategoria.adicionar(c);
    }
    
    public boolean editarCategoria(String antigoNome, String novoNome) {
        for (Categoria c : repoCategoria.getTodos()) {
            if (c.getNome().equals(antigoNome)) {
                c.setNome(novoNome);
                return true;
            }
        }
        return false;
    }
   

    public boolean removerCategoria(String id) {
        return repoCategoria.remover(id);
    }
    
    public ArrayList<Categoria> getCategorias() {
        return repoCategoria.getTodos();
    }
    
    public Categoria buscarCategoriaPorId(String Id) {
    	return repoCategoria.buscarPorId(Id);
  
    }
    
    // ===== Despesas Recorrentes =====
    public void adicionarDespesaRecorrente(String descricao, double valor, Categoria categoria, Conta conta, String frequencia, int numeroDeParcelas) {
        if (valor > 0) {
            DespesaRecorrente dr = new DespesaRecorrente(UUID.randomUUID().toString(),descricao,valor,LocalDate.now(),categoria,conta,frequencia,numeroDeParcelas);
            this.despesasRecorrentes.add(dr);
        }
    }
    
    public void processarDespesasRecorrentes() {
        LocalDate hoje = LocalDate.now();
        ArrayList<DespesaRecorrente> despesasParaRemover = new ArrayList<>();

        for (DespesaRecorrente dr : this.despesasRecorrentes) {
            // checa se ainda tem parcelas e se a data de hoje nao eh antes da proxima geracao
            if (dr.getNumeroDeParcelas() > 0 && !hoje.isBefore(dr.calcularProximaData())) {
                
                // cria uma nova despesa
                Despesa novaDespesa = new Despesa(
                    UUID.randomUUID().toString(),
                    dr.getDescricao(),
                    dr.getValor(),
                    hoje,
                    dr.getCategoria(),
                    dr.getConta()
                );
                
                // adiciona a nova despesa a lista de transacoes
                repoTransacao.adicionar(novaDespesa);
                
                // atualiza a despesa recorrente
                dr.setDataUltimaCobranca(hoje);
                dr.setNumeroDeParcelas(dr.getNumeroDeParcelas() - 1);

                // se nao houver mais parcelas, marca para remocao
                if (dr.getNumeroDeParcelas() == 0) {
                    despesasParaRemover.add(dr);
                }
            }
        }
        // remove as despesas recorrentes que ja terminaram
        this.despesasRecorrentes.removeAll(despesasParaRemover);
    }
}
