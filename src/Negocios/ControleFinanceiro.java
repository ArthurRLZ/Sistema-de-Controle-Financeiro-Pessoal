package Negocios;

import Dados.RepositorioCategoria;
import Dados.RepositorioConta;
import Dados.RepositorioTransacao;
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

    // Gerenciamento de Categorias
    public void adicionarCategoria(String nome) {
        Categoria c = new Categoria(UUID.randomUUID().toString(), nome);
        repoCategoria.adicionar(c);
    }

    public boolean removerCategoria(String id) {
        return repoCategoria.remover(id);
    }

    public boolean editarCategoria(String id, String novoNome) {
        Categoria c = repoCategoria.buscarPorId(id);
        if (c != null) {
            c.setNome(novoNome);
            return true;
        }
        return false;
    }

    public ArrayList<Categoria> getCategorias() {
        return repoCategoria.getTodos();
    }

    // Gerenciamento de Contas
    public void adicionarConta(String nome) {
        Conta c = new Conta(UUID.randomUUID().toString(), nome);
        repoConta.adicionar(c);
    }

    public boolean removerConta(String id) {
        return repoConta.remover(id);
    }

    public boolean editarConta(String id, String novoNome) {
        Conta c = repoConta.buscarPorId(id);
        if (c != null) {
            c.setNome(novoNome);
            return true;
        }
        return false;
    }

    public ArrayList<Conta> getContas() {
        return repoConta.getTodos();
    }

    // Gerenciamento de Transações (Receita e Despesa)
    public void adicionarReceita(String descricao, double valor, String categoriaId, String contaId) {
        Categoria categoria = repoCategoria.buscarPorId(categoriaId);
        Conta conta = repoConta.buscarPorId(contaId);
        if (categoria != null && conta != null && valor > 0) {
            Receita receita = new Receita(UUID.randomUUID().toString(), descricao, valor, LocalDate.now(), categoria, conta);
            repoTransacao.adicionar(receita);
        }
    }

    public void adicionarDespesa(String descricao, double valor, String categoriaId, String contaId) {
        Categoria categoria = repoCategoria.buscarPorId(categoriaId);
        Conta conta = repoConta.buscarPorId(contaId);
        if (categoria != null && conta != null && valor > 0) {
            Despesa despesa = new Despesa(UUID.randomUUID().toString(), descricao, valor, LocalDate.now(), categoria, conta);
            repoTransacao.adicionar(despesa);
        }
    }

    public void adicionarTransacao(Transacao t) {
        if (t != null) {
            repoTransacao.adicionar(t);
        }
    }

    public boolean removerTransacao(String id) {
        return repoTransacao.remover(id);
    }

    public ArrayList<Transacao> getTransacoes() {
        return repoTransacao.getTodos();
    }

    // Métodos para editar Transações
    public boolean editarDescricaoTransacao(String id, String novaDescricao) {
        Transacao t = repoTransacao.buscarPorId(id);
        if (t != null) {
            t.setDescricao(novaDescricao);
            return true;
        }
        return false;
    }

    public boolean editarValorTransacao(String id, double novoValor) {
        Transacao t = repoTransacao.buscarPorId(id);
        if (t != null) {
            t.setValor(novoValor);
            return true;
        }
        return false;
    }

    public boolean editarDataTransacao(String id, LocalDate novaData) {
        Transacao t = repoTransacao.buscarPorId(id);
        if (t != null) {
            t.setData(novaData);
            return true;
        }
        return false;
    }

    public boolean editarCategoriaTransacao(String id, String novaCategoriaId) {
        Transacao t = repoTransacao.buscarPorId(id);
        Categoria novaCategoria = repoCategoria.buscarPorId(novaCategoriaId);
        if (t != null && novaCategoria != null) {
            t.setCategoria(novaCategoria);
            return true;
        }
        return false;
    }

    public boolean editarContaTransacao(String id, String novaContaId) {
        Transacao t = repoTransacao.buscarPorId(id);
        Conta novaConta = repoConta.buscarPorId(novaContaId);
        if (t != null && novaConta != null) {
            t.setConta(novaConta);
            return true;
        }
        return false;
    }
    
    // Transferência entre Contas
    public void adicionarTransferencia(String idOrigem, String idDestino, double valor, String descricao) {
        Conta origem = repoConta.buscarPorId(idOrigem);
        Conta destino = repoConta.buscarPorId(idDestino);
        Categoria categoriaTransferencia = new Categoria(UUID.randomUUID().toString(), "Transferencia");

        if (origem != null && destino != null && valor > 0) {
            Despesa despesa = new Despesa(UUID.randomUUID().toString(), "Transferencia para " + destino.getNome() + ": " + descricao, valor, LocalDate.now(), categoriaTransferencia, origem);
            Receita receita = new Receita(UUID.randomUUID().toString(), "Transferencia de " + origem.getNome() + ": " + descricao, valor, LocalDate.now(), categoriaTransferencia, destino);
            
            repoTransacao.adicionar(despesa);
            repoTransacao.adicionar(receita);
        }
    }

    // Despesas Recorrentes
    public void adicionarDespesaRecorrente(String descricao, double valor, String categoriaId, String contaId, String frequencia, int numeroDeParcelas) {
        Categoria categoria = repoCategoria.buscarPorId(categoriaId);
        Conta conta = repoConta.buscarPorId(contaId);
        if (categoria != null && conta != null && valor > 0) {
            DespesaRecorrente dr = new DespesaRecorrente(UUID.randomUUID().toString(), descricao, valor, LocalDate.now(), categoria, conta, frequencia, numeroDeParcelas);
            this.despesasRecorrentes.add(dr);
        }
    }
    
    public void processarDespesasRecorrentes() {
        LocalDate hoje = LocalDate.now();
        ArrayList<DespesaRecorrente> despesasParaRemover = new ArrayList<>();

        for (DespesaRecorrente dr : this.despesasRecorrentes) {
            if (dr.getNumeroDeParcelas() > 0 && !hoje.isBefore(dr.calcularProximaData())) {
                Despesa novaDespesa = new Despesa(
                    UUID.randomUUID().toString(),
                    dr.getDescricao(),
                    dr.getValor(),
                    hoje,
                    dr.getCategoria(),
                    dr.getConta()
                );
                
                repoTransacao.adicionar(novaDespesa);
                dr.setDataUltimaCobranca(hoje);
                dr.setNumeroDeParcelas(dr.getNumeroDeParcelas() - 1);

                if (dr.getNumeroDeParcelas() == 0) {
                    despesasParaRemover.add(dr);
                }
            }
        }
        this.despesasRecorrentes.removeAll(despesasParaRemover);
    }
}
