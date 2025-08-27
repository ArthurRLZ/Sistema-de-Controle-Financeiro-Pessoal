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

    // pra achar pelo id
    private Categoria buscarCategoriaPorId(String id) {
        for (Categoria c : repoCategoria.getTodos()) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    private Conta buscarContaPorId(String id) {
        for (Conta c : repoConta.getTodos()) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    // categorias aq 
    public void adicionarCategoria(String nome) {
        Categoria c = new Categoria(UUID.randomUUID().toString(), nome);
        repoCategoria.adicionar(c);
    }
    
    public boolean removerCategoria(String id) {
        return repoCategoria.remover(id);
    }
    
    public boolean editarCategoria(String id, String novoNome) {
        Categoria c = buscarCategoriaPorId(id);
        if (c != null) {
            c.setNome(novoNome);
            return true;
        }
        return false;
    }
    
    // gerencia as contas
    public void adicionarConta(String nome) {
        Conta c = new Conta(UUID.randomUUID().toString(), nome);
        repoConta.adicionar(c);
    }
    
    public boolean removerConta(String id) {
        return repoConta.remover(id);
    }
    
    public boolean editarConta(String id, String novoNome) {
        Conta c = buscarContaPorId(id);
        if (c != null) {
            c.setNome(novoNome);
            return true;
        }
        return false;
    }
    
    // gerencia as transacoes (receita e despesa) 
    public void adicionarReceita(String descricao, double valor, String categoriaId, String contaId) {
        Categoria categoria = buscarCategoriaPorId(categoriaId);
        Conta conta = buscarContaPorId(contaId);
        if (categoria != null && conta != null && valor > 0) {
            Receita receita = new Receita(UUID.randomUUID().toString(), descricao, valor, LocalDate.now(), categoria, conta);
            repoTransacao.adicionar(receita);
        }
    }
    
    public void adicionarDespesa(String descricao, double valor, String categoriaId, String contaId) {
        Categoria categoria = buscarCategoriaPorId(categoriaId);
        Conta conta = buscarContaPorId(contaId);
        if (categoria != null && conta != null && valor > 0) {
            Despesa despesa = new Despesa(UUID.randomUUID().toString(), descricao, valor, LocalDate.now(), categoria, conta);
            repoTransacao.adicionar(despesa);
        }
    }
    
    public boolean removerTransacao(String id) {
        return repoTransacao.remover(id);
    }
    
    // transferencia entre contas 
    public void adicionarTransferencia(String idOrigem, String idDestino, double valor, String descricao) {
        Conta origem = buscarContaPorId(idOrigem);
        Conta destino = buscarContaPorId(idDestino);
        Categoria categoriaTransferencia = new Categoria("transferencia", "Transferencia");

        if (origem != null && destino != null && valor > 0) {
            Despesa despesa = new Despesa(UUID.randomUUID().toString(), "Transferencia para " + destino.getNome() + ": " + descricao, valor, LocalDate.now(), categoriaTransferencia, origem);
            Receita receita = new Receita(UUID.randomUUID().toString(), "Transferencia de " + origem.getNome() + ": " + descricao, valor, LocalDate.now(), categoriaTransferencia, destino);
            
            repoTransacao.adicionar(despesa);
            repoTransacao.adicionar(receita);
        }
    }

    // gerencia as despesas recorrentes
    public void adicionarDespesaRecorrente(String descricao, double valor, String categoriaId, String contaId, String frequencia, int numeroDeParcelas) {
        Categoria categoria = buscarCategoriaPorId(categoriaId);
        Conta conta = buscarContaPorId(contaId);
        if (categoria != null && conta != null && valor > 0) {
            DespesaRecorrente dr = new DespesaRecorrente(UUID.randomUUID().toString(), descricao, valor, LocalDate.now(), categoria, conta, frequencia, numeroDeParcelas);
            despesasRecorrentes.add(dr);
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
