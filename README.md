# Agenda de Contatos

## Objetivo
Fazer um CRUD em uma agenda de contatos.

## Sobre o Projeto
O projeto consiste em um grid principal que exibe os contatos cadastrados. Acima do grid, há um botão "Adicionar Contato", que abre um modal contendo os campos para preenchimento dos dados. A última coluna do grid é dedicada às ações e contém dois botões: Editar e Excluir.
Ao clicar em "Editar", o sistema abre o mesmo modal de cadastro, desta vez com os dados do contato preenchidos para edição.

O botão Excluir remove o contato selecionado da lista.
## Tecnologias Utilizadas
### Backend
* Java 17
* Spring Boot
* Spring Data JPA
* Flyway

### Frontend
* VueJS 3 (Composition API)
* HTML5
* CSS3

### Banco de Dados
Postgres 16

### Versionamento 
* Git / GitHub

## Como Executar
Para executar o sistema, primeiro certifique-se de que você tem instalado:
* Java JDK 17
* Node.js e npm 

1. Faça um clone do repositório na sua máquina
2. Navegue até o diretório raiz do projeto e abra o Prompt de Comando ou Terminal
3. Execute:   
   ```
   cd agenda-de-contatos-api
   mvnw.cmd spring-boot:run
   ```
    **OBS:** Não feche o Prompt de Comando ou Terminal
4. Abra um novo Prompt de Comando no diretório raiz do projeto
5. Execute:
   ```
   cd agenda-contatos-frontend
   npm install
   npm run dev 
   ```
6. O sistema estará disponível em: http://localhost:5173

## Considerações
* O Banco de dados está rodando um núvem, então pode haver uma leve lentidão devido à latência. Caso seja de interesse fazer testes com um banco de dados local basta seguir o diretório "/agenda-contatos-api/src/main/resources" e abrir o arquivo "application.properties",   dentro do arquivo, basta trocar as credenciais. Lembrando que para testes locais, deve-se criar um banco de dados chamado "db_agenda_contatos".
  
* Para acessar o script de criação da tabela, acesse: "/agenda-contatos-api/src/main/resources/db/migration", dentro desse diretório estão as versões do banco de dados.
  
* Criei um arquivo Dockerfile para rodar a API na núvem, porém houve uma instabilidade no serviço que parava depois de um tempo de inatividade e não voltava mais, então abandonei a ideia.
