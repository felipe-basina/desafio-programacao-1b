# Desafio de programação 1B :: descrição

A idéia deste desafio é nos permitir avaliar melhor as habilidades de candidatos à vagas de programador, de vários níveis.

Este desafio deve ser feito por você em sua casa. Gaste o tempo que você quiser, porém normalmente você não deve precisar de mais do que algumas horas.

## Pré requisitos

1. Versão JAVA >= 7.0
2. Versão MAVEN >= 3.0

Observação: a base de dados utilizada é a **h2 (com.h2database)**

## Procedimentos

Uma vez efetuado o download do projeto deve-se executar o seguinte comando no terminal/console (dentro do diretório onde se encontra o mesmo):

**mvn clean install spring-boot:run**

OBS: Adicionar o seguinte parâmetro caso não se deseja executar os testes de unidade **-DskipTests=true**

Finalizado o processo de build/deploy, abrir o navegador de preferência e acessar o seguinte endereço:

**http://localhost:8080/**

Uma vez carregada a página, selecionar o arquivo exemplo 'dados.txt' e pressionar o botão 'Carregar'. 

O resultado será exibido na próxima página com as informações do arquivo estruturadas em uma tabela e no final da mesma o valor
referente à 'Receita bruta' é contabilizada

## Eclipse

Para realizar a importação do projeto na IDE eclipse deve-se, primeiro, executar o seguinte comando no diretório onde se encontra o projeto:

**mvn eclipse:clean eclipse:eclipse**
