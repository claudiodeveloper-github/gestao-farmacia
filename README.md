<h1 align="center">
  <img src="https://img.shields.io/badge/-%F0%9F%92%8A%20Gest%C3%A3o%20Farm%C3%A1cia-0d9e6e?style=for-the-badge&labelColor=0a0a0a" alt="Gestão Farmácia"/>
</h1>

<p align="center">
  Sistema de gerenciamento de medicamentos via linha de comando, desenvolvido em Java com MySQL.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white"/>
  <img src="https://img.shields.io/badge/IntelliJ_IDEA-2026.1-000000?style=for-the-badge&logo=intellijidea&logoColor=white"/>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/status-conclu%C3%ADdo-brightgreen?style=flat-square"/>
  <img src="https://img.shields.io/badge/licen%C3%A7a-MIT-blue?style=flat-square"/>
  <img src="https://img.shields.io/badge/vers%C3%A3o-1.0.0-orange?style=flat-square"/>
</p>

---

## 📋 Sobre o Projeto

O **Gestão Farmácia** é uma aplicação console desenvolvida em Java puro com conexão a banco de dados MySQL. O projeto aplica boas práticas de arquitetura em camadas (DAO, Service, Model), separação de responsabilidades e tratamento de exceções, consolidando fundamentos essenciais do desenvolvimento backend.

> Projeto desenvolvido para praticar Java com banco de dados relacional, arquitetura em camadas e boas práticas de código.

---

##  Funcionalidades

| # | Funcionalidade | Descrição |
|---|---|---|
| 1 | 📦 **Cadastrar** | Registra novos medicamentos com validação completa |
| 2 | 📋 **Listar** | Exibe todos os medicamentos com ordenação escolhida |
| 3 | 🔍 **Buscar** | Busca por nome com correspondência parcial |
| 4 | ⚠️ **Vencidos** | Lista medicamentos com validade expirada |
| 5 | 🗑️ **Remover** | Remove medicamento pelo ID |
| 6 | ✏️ **Atualizar** | Edita preço ou quantidade de um medicamento |
| 7 | 🚨 **Estoque baixo** | Alerta de medicamentos abaixo de um limite |
| 8 | 📄 **Exportar** | Gera relatório em `.txt` com data e hora |

---

## Estrutura do Projeto

```
gestao-farmacia/
├── src/
│   └── main/
│       ├── java/
│       │   ├── com/farmacia/
│       │   │   └── Main.java              # Ponto de entrada e menu
│       │   ├── dao/
│       │   │   └── MedicamentoDAO.java    # Acesso ao banco de dados
│       │   ├── db/
│       │   │   └── Conexao.java           # Gerenciamento de conexão
│       │   ├── model/
│       │   │   └── Medicamento.java       # Entidade principal
│       │   ├── service/
│       │   │   └── MedicamentoService.java # Regras de negócio
│       │   └── util/
│       │       └── Relatorio.java         # Exportação de relatórios
│       └── resources/
│           └── db.properties              # Configurações do banco (não versionado)
├── .gitignore
└── pom.xml
```

---

## Arquitetura

```
Main (View/CLI)
     │
     ▼
MedicamentoService (Regras de negócio + validações)
     │
     ▼
MedicamentoDAO (Queries SQL com PreparedStatement)
     │
     ▼
MySQL (banco: farmacia / tabela: medicamento)
```

A aplicação segue o padrão **DAO (Data Access Object)** com separação clara entre as camadas de apresentação, negócio e persistência.

---

## 🛠️ Tecnologias

- **[Java 17](https://openjdk.org/)** — Linguagem principal
- **[MySQL 8.0](https://www.mysql.com/)** — Banco de dados relacional
- **[MySQL Connector/J 8.0.33](https://dev.mysql.com/downloads/connector/j/)** — Driver JDBC
- **[Apache Maven](https://maven.apache.org/)** — Gerenciamento de dependências
- **[IntelliJ IDEA](https://www.jetbrains.com/idea/)** — IDE

---

## Como Executar

### Pré-requisitos

- Java 17+
- MySQL 8.0+
- Maven 3.x
- IntelliJ IDEA (recomendado)

### 1. Clone o repositório

```bash
git clone https://github.com/claudiodeveloper-github/gestao-farmacia.git
cd gestao-farmacia
```

### 2. Crie o banco de dados

```sql
CREATE DATABASE farmacia;
USE farmacia;

CREATE TABLE medicamento (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    nome         VARCHAR(100)   NOT NULL,
    laboratorio  VARCHAR(100)   NOT NULL,
    preco        DECIMAL(10, 2) NOT NULL,
    dataValidade DATE           NOT NULL,
    quantidade   INT            NOT NULL
);
```

### 3. Configure as credenciais

Crie o arquivo `src/main/resources/db.properties`:

```properties
db.url=jdbc:mysql://localhost:3306/farmacia
db.user=seu_usuario
db.password=sua_senha
```

>  Este arquivo está no `.gitignore` e **não deve ser versionado**.

### 4. Execute o projeto

Abra no IntelliJ IDEA e rode a classe `Main.java`, ou via Maven:

```bash
mvn compile exec:java -Dexec.mainClass="com.farmacia.Main"
```

---

## Demonstração

```
========= SISTEMA DE FARMÁCIA =========
1. Cadastrar Medicamento
2. Listar Todos
3. Buscar por Nome
4. Ver Vencidos
5. Remover Medicamento
6. Atualizar Preço ou Quantidade
7. Alerta de Estoque Baixo
8. Exportar Relatório (.txt)
0. Sair
Opção: 2

--- Lista de Medicamentos (ordenado por nome) ---
ID:   1 | Amoxicilina          | Lab: Medley       | R$   25,90 | Val: 30/12/2026 | Estoque: 10
ID:   2 | Dipirona 500mg       | Lab: EMS          | R$   12,50 | Val: 20/12/2027 | Estoque: 100
ID:   3 | Paracetamol Antigo   | Lab: Farmalab     | R$    8,90 | Val: 01/01/2023 | Estoque: 5 [VENCIDO]
ID:   4 | Vitamina C + Zinco   | Lab: Neo Química  | R$   35,00 | Val: 10/10/2028 | Estoque: 45
Total: 4 medicamento(s).
```

---

## Exportação de Relatório

Ao escolher a opção **8**, o sistema gera um arquivo `.txt` na raiz do projeto com nome no formato `relatorio_AAAAMMDD_HHMMSS.txt`:

```
===========================================================================
  FARMÁCIA — TODOS OS MEDICAMENTOS
  Gerado em: 18/05/2026 21:48:06
===========================================================================

ID:   1 | Amoxicilina          | Lab: Medley      | R$   25,90 | Val: 30/12/2026 | Estoque: 10
...

  Total: 4 medicamento(s).
===========================================================================
```

---

## Conceitos Aplicados

- ✅ Arquitetura em camadas (Model / DAO / Service / View)
- ✅ JDBC com `PreparedStatement` (prevenção de SQL Injection)
- ✅ Tratamento de exceções customizadas
- ✅ Uso de `try-with-resources` para fechar conexões
- ✅ Credenciais protegidas via `db.properties` (fora do código)
- ✅ Padrão DAO para desacoplamento do banco
- ✅ Validações na camada de serviço

---

## 💬 FAQ de Arquitetura:

## 💬 Conversa Técnica (FAQ do Projeto)

Para ajudar a entender o propósito do projeto e as decisões de design, imagine uma conversa entre um **Recrutador/Avaliador Técnico (💬)** e o **Desenvolvedor (💻)**:

---

### 💬 1. O que é este projeto e qual problema ele resolve?
[cite_start]💻 O **Gestão Farmácia** é um sistema para o gerenciamento de estoque de medicamentos operado via linha de comando (CLI)[cite: 34, 92]. [cite_start]Ele permite cadastrar itens, listar com ordenação, buscar por nome, alertar sobre estoque baixo, verificar produtos vencidos e exportar um relatório consolidado em formato `.txt`[cite: 49]. 

[cite_start]O grande objetivo dele não foi criar uma interface visual complexa, mas sim consolidar os fundamentos de **programação orientada a objetos (POO)**, boas práticas de **arquitetura em camadas** e persistência de dados em um banco real[cite: 46, 47].

---

### 💬 2. Como você organizou a estrutura do projeto e por quê?
[cite_start]💻 O projeto segue o padrão de arquitetura em camadas para garantir a **separação de responsabilidades**[cite: 46, 96]. [cite_start]A estrutura foi dividida assim[cite: 65]:
* `Main.java`: Atua como a nossa **View (Apresentação)**. [cite_start]É o ponto de entrada que interage diretamente com o usuário no console[cite: 71, 72, 92].
* [cite_start]`MedicamentoService.java`: Concentra as **Regras de Negócio e Validações**[cite: 83, 93]. [cite_start]É aqui que o sistema valida se um dado é correto antes de enviar para o banco[cite: 193].
* [cite_start]`MedicamentoDAO.java`: Camada de **Persistência**[cite: 74, 96]. [cite_start]Nenhuma regra de negócio fica aqui; o DAO apenas executa os comandos SQL no banco de dados[cite: 75, 94].
* [cite_start]`Medicamento.java`: Nosso **Model (Entidade)**, que representa a estrutura do dado como ele existe no mundo real e no banco[cite: 81, 95].
* [cite_start]`Relatorio.java` (`util/`): Uma classe utilitária focada exclusivamente na lógica de escrita e formatação do arquivo de exportação[cite: 84, 85, 88].

Fazer dessa forma impede o "código espaguete". [cite_start]Se amanhã eu decidir trocar a interface de console por uma tela Web, eu mudo apenas a View; a lógica de negócios e o banco continuam intactos[cite: 96].

---

### 💬 3. O que significa o padrão DAO que você usou e qual a sua importância?
[cite_start]💻 **DAO** significa *Data Access Object* (Objeto de Acesso a Dados)[cite: 96]. [cite_start]É um padrão de projeto que serve para isolar completamente a lógica de acesso ao banco de dados do restante da aplicação[cite: 46, 96]. 

[cite_start]No código, o `MedicamentoDAO` encapsula todas as queries SQL (`SELECT`, `INSERT`, `UPDATE`, `DELETE`)[cite: 49, 74, 94]. [cite_start]As outras camadas não sabem se estou salvando os dados em um arquivo, em um banco MySQL ou em um banco PostgreSQL; elas apenas chamam os métodos do DAO e recebem os objetos prontos[cite: 94, 95, 192]. [cite_start]Isso gera um forte **desacoplamento**[cite: 192].

---

### 💬 4. Por que você escolheu o MySQL e como foi feita a integração?
[cite_start]💻 Escolhi o **MySQL 8.0** por ser um banco de dados relacional extremamente robusto, amplamente utilizado no mercado e ideal para exercitar a modelagem de tabelas com chaves primárias e tipos de dados específicos (como `DECIMAL` para preços e `DATE` para validades)[cite: 99, 100, 116, 124, 125].

[cite_start]A integração foi feita de forma nativa usando **JDBC (Java Database Connectivity)** com o driver oficial do MySQL[cite: 101, 185]. [cite_start]Para evitar expor dados sensíveis no código (como o usuário e a senha do banco), utilizei um arquivo isolado chamado `db.properties` dentro de `src/main/resources/`[cite: 86, 87, 131]. [cite_start]Esse arquivo fica listado no `.gitignore`, garantindo que credenciais de produção nunca sejam enviadas para o GitHub público[cite: 90, 135, 191].

---

### 💬 5. Notei que você usou PreparedStatement. Por que não concatenou as strings diretamente no SQL?
💻 Usar concatenação direta (ex: ` "WHERE nome = '" + nome + "'" `) abre uma vulnerabilidade gravíssima de segurança chamada **SQL Injection**, onde um usuário mal-intencionado poderia digitar comandos SQL maliciosos no console para apagar ou roubar dados do banco.

[cite_start]O **`PreparedStatement`** pré-compila a query SQL com marcadores de posição (`?`)[cite: 94, 185]. [cite_start]Quando passamos os parâmetros, o driver JDBC trata esses dados estritamente como valores textuais ou numéricos, neutralizando qualquer tentativa de injeção de código[cite: 185]. Além disso, ele melhora a performance em consultas repetitivas.

---

### 💬 6. Como funciona a exportação de relatórios no projeto?
[cite_start]💻 Quando o usuário escolhe a opção de exportar, a interface aciona a classe utilitária `Relatorio.java`[cite: 49, 85]. [cite_start]Essa classe busca a lista atualizada de medicamentos do banco de dados (via Service e DAO)[cite: 93, 94]. 

[cite_start]Utilizando as classes nativas do Java para manipulação de arquivos, o sistema gera um arquivo `.txt` na raiz do projeto[cite: 49, 174]. [cite_start]O nome do arquivo é gerado dinamicamente incluindo a data e a hora exatas do momento da exportação (no formato `relatorio_AAAAMMDD_HHMMSS.txt`), garantindo que um relatório nunca sobrescreva o outro e mantendo um histórico limpo[cite: 49, 174, 175].

---

### 💬 7. O que você aplicou de gerenciamento de recursos e tratamento de erros?
[cite_start]💻 No acesso ao banco de dados, apliquei o **`try-with-resources`** do Java[cite: 190]. Conexões com bancos de dados são recursos caros do sistema; se deixadas abertas, podem travar a aplicação ou estourar o limite do servidor. [cite_start]O `try-with-resources` garante que objetos como `Connection`, `PreparedStatement` e `ResultSet` sejam fechados automaticamente assim que o bloco termina, ocorrendo um erro ou não[cite: 190].

[cite_start]Também foram implementados **tratamentos de exceções customizadas** para capturar falhas de banco de dados (erros de SQL) ou erros de validação de forma elegante, impedindo que o programa quebre bruscamente na tela do usuário e exibindo mensagens amigáveis[cite: 46, 186].

---

### 💬 8. Como você garantiu que o sistema não tentasse alterar ou deletar um registro que não existe?
💻 Em métodos de alteração e deleção (`UPDATE` e `DELETE`), o método `stmt.executeUpdate()` retorna um número inteiro que representa quantas linhas foram afetadas no banco de dados. No `MedicamentoDAO`, eu capturo esse retorno. Se o valor for `0`, significa que o ID passado pelo usuário não existia na tabela. Sabendo disso, o sistema lança imediatamente uma exceção impendindo que a operação termine silenciosamente sem que o usuário saiba que nada foi alterado.



## Autor

<table>
  <tr>
    <td align="center">
      <b>Cláudio G. S. Castro</b><br/>
      <sub>Java Backend Developer em formação</sub><br/>
      <a href="https://github.com/claudiodeveloper-github">GitHub</a>
    </td>
  </tr>
</table>

---

## Licença

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

<p align="center">
  Feito com ☕ e Java por <a href="https://github.com/claudiodeveloper-github">Cláudio G. S. Castro</a>
</p>

---
