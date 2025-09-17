Sistema de Controle Financeiro Pessoal
Descrição do Projeto
Este projeto é um sistema de controle financeiro pessoal, desenvolvido como parte da disciplina de Programação Orientada a Objetos (POO). Ele permite ao usuário gerenciar seu fluxo de caixa, registrando receitas e despesas, categorizando transações e visualizando relatórios detalhados. O sistema foi construído seguindo uma arquitetura modular e utilizando princípios de design como Herança, Polimorfismo e o padrão de projeto Fachada.

Requisitos Atendidos
O projeto foi desenvolvido para cumprir os seguintes requisitos da disciplina:

Persistência de Dados: O sistema suporta persistência em arquivo, utilizando serialização de objetos e arquivos CSV (com o auxílio de uma biblioteca de terceiros).

Ocultação da Informação: Todos os atributos das classes são privados, com acesso controlado através de métodos Getters e Setters.

Padrão de Projeto: A arquitetura do projeto é dividida em camadas, utilizando o padrão Fachada para simplificar a interface de usuário.

Tratamento de Exceções: O sistema lida com situações de erro usando exceções personalizadas, como NegocioException e SaldoInsuficienteException.

Herança e Polimorfismo: Conceitos como herança (Transacao sendo a classe-mãe de Receita e Despesa) e polimorfismo (instanceof nos relatórios) são aplicados em todo o projeto.

Tecnologias Utilizadas
Java: Linguagem de programação principal.

Maven: Ferramenta de gerenciamento de dependências.

Apache Commons CSV: Biblioteca de terceiros para manipulação de arquivos CSV.

Arquitetura do Sistema
O projeto é organizado em camadas para garantir a separação de responsabilidades:

UI: A interface de usuário (neste caso, uma interface de linha de comando) interage diretamente com a Fachada.

Fachada: A classe FinanceiroFachada atua como um ponto de entrada simplificado para o sistema.

Negocios: A camada de negócios, onde a classe ControleFinanceiro gerencia a lógica principal e as entidades do sistema.

Dados: A camada de dados, que contém as classes de repositório e a lógica de persistência (PersistenciaDados).

Funcionalidades Principais
Gerenciamento de Contas: Adicionar, editar, remover e listar contas (Conta).

Gerenciamento de Categorias: Adicionar, editar, remover e listar categorias (Categoria).

Gerenciamento de Transações: Adicionar receitas (Receita) e despesas (Despesa), remover transações, e listar o histórico.

Despesas Recorrentes: Adicionar e processar despesas recorrentes (DespesaRecorrente).

Transferências: Realizar transferências de valores entre contas.

Relatórios: Gerar relatórios de balanço, gastos por categoria e relatórios por período (mensal, trimestral, semestral e anual).

Persistência: Salvar e carregar o estado completo do sistema usando serialização de objetos ou arquivos CSV.

Instalação e Execução
Para rodar este projeto, certifique-se de ter o Java 17 ou superior e o Maven instalados.

Clone o repositório:

Bash

git clone (https://github.com/ArthurRLZ/Sistema-de-Controle-Financeiro-Pessoal.git)

Abra o projeto em sua IDE (IntelliJ, Eclipse, VS Code).

O Maven deve baixar automaticamente as dependências listadas no pom.xml. Se não, clique com o botão direito no projeto e selecione Maven > Update Project....

Execute a classe Main.java no pacote UI para iniciar a interface de linha de comando.

Autores
Joaci Laurindo 

Euclides Laurindo

Heitor Calado

Arthur Ricardo
