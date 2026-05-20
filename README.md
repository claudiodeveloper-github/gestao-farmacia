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

## 💬 Conversa Técnica (FAQ do Projeto)

Para ajudar a entender o propósito do projeto e as minhas decisões de design, imagine um bate-papo sincero entre um **Recrutador/Avaliador Técnico (💬)** e o **Desenvolvedor (💻)**:

---

### 💬 1. O que é este projeto e qual problema ele resolve?
💻 O **Gestão Farmácia** é um sistema para o gerenciamento de estoque de medicamentos operado via linha de comando (CLI). Ele permite cadastrar itens, listar com ordenação personalizada, buscar por nome, alertar sobre estoque baixo, verificar produtos vencidos e exportar um relatório consolidado em formato `.txt`. 

O grande objetivo aqui não foi criar uma interface visual complexa, mas sim consolidar na prática os fundamentos de **programação orientada a objetos (POO)**, boas práticas de **arquitetura em camadas** e a persistência de dados em um banco real.

---

### 💬 2. Como você organizou a estrutura do projeto e por quê?
💻 O projeto segue o padrão de arquitetura em camadas para garantir a **separação de responsabilidades**. A estrutura ficou dividida assim:

* **`Main.java`**: Atua como a nossa **View (Apresentação)**. É o ponto de entrada que interage diretamente com o usuário no console e renderiza o menu.
* **`MedicamentoService.java`**: Concentra as **Regras de Negócio e Validações**. É aqui que o sistema valida se os dados estão corretos (como preços e quantidades válidas) antes de permitir o envio para o banco.
* **`MedicamentoDAO.java`**: Nossa camada de **Persistência**. Nenhuma regra de negócio fica aqui; o DAO foca exclusivamente em executar os comandos SQL e conversar com o banco de dados.
* **`Medicamento.java`**: Nosso **Model (Entidade)**, que representa a estrutura do medicamento exatamente como ele existe no mundo real e na tabela do banco.
* **`Relatorio.java` (`util/`)**: Uma classe utilitária focada isoladamente na lógica de escrita, organização e formatação do arquivo de exportação.

Fazer dessa forma impede o famoso "código espaguete". Se amanhã eu decidir trocar a interface de console por uma tela Web ou Mobile, eu mudo apenas a View; toda a lógica de negócios e as consultas ao banco continuam intactas e reaproveitáveis.

---

### 💬 3. O que significa o padrão DAO que você usou e qual a sua importância?
💻 **DAO** significa *Data Access Object* (Objeto de Acesso a Dados). É um padrão de projeto que serve para isolar completamente a lógica de acesso ao banco de dados do restante da aplicação. 

No código, o `MedicamentoDAO` encapsula todas as queries SQL (`SELECT`, `INSERT`, `UPDATE`, `DELETE`). As outras camadas do sistema não sabem (e não precisam saber) se estou salvando os dados em um arquivo de texto, em um banco MySQL ou em um banco PostgreSQL; elas apenas chamam os métodos do DAO e recebem os objetos Java prontos. Isso gera um forte **desacoplamento**, facilitando muito a manutenção.

---

### 💬 4. Por que você escolheu o MySQL e como foi feita a integração?
💻 Escolhi o **MySQL 8.0** por ser um banco de dados relacional robusto, amplamente utilizado no mercado e ideal para exercitar a modelagem de tabelas com chaves primárias e tipos de dados específicos (como `DECIMAL` para preços e `DATE` para validades).

A integração foi feita de forma nativa usando **JDBC (Java Database Connectivity)** com o driver oficial do MySQL. Para evitar expor dados sensíveis no código (como o usuário e a senha locais do banco), utilizei um arquivo isolado chamado `db.properties` dentro de `src/main/resources/`. Esse arquivo foi adicionado ao `.gitignore`, garantindo que credenciais de acesso nunca sejam enviadas para o GitHub público.

---

### 💬 5. Notei que você usou PreparedStatement. Por que não concatenou as strings diretamente no SQL?
💻 Usar concatenação direta (como ` "WHERE nome = '" + nome + "'" `) abre uma vulnerabilidade gravíssima de segurança chamada **SQL Injection**, onde um usuário mal-intencionado poderia digitar comandos SQL maliciosos no console para burlar o sistema, apagar ou roubar dados do banco.

O **`PreparedStatement`** pré-compila a query SQL com marcadores de posição (`?`). Quando passamos os parâmetros, o driver JDBC trata esses dados estritamente como valores textuais ou numéricos, neutralizando qualquer tentativa de injeção de código. Além da segurança, ele também traz um ganho de performance em consultas repetitivas.

---

### 💬 6. Como funciona a exportação de relatórios no projeto?
💻 Quando o usuário escolhe a opção de exportar no menu, a interface aciona a classe utilitária `Relatorio.java`. Essa classe busca a lista atualizada de medicamentos do banco de dados passando pela Service e pelo DAO. 

Utilizando as classes nativas do Java para manipulação de arquivos (`BufferedWriter` e `FileWriter`), o sistema gera um arquivo `.txt` na raiz do projeto. O nome do arquivo é gerado dinamicamente incluindo a data e a hora exatas do momento da exportação (no formato `relatorio_AAAAMMDD_HHMMSS.txt`), garantindo que um relatório novo nunca sobrescreva o anterior e mantendo um histórico limpo para o usuário.

---

### 💬 7. O que você aplicou de gerenciamento de recursos e tratamento de erros?
💻 No acesso ao banco de dados, apliquei o recurso do **`try-with-resources`** do Java. Conexões com bancos de dados são recursos caros e finitos do sistema; se deixadas abertas, podem travar a aplicação ou estourar o limite do servidor. O `try-with-resources` garante que objetos como `Connection`, `PreparedStatement` e `ResultSet` sejam fechados automaticamente assim que o bloco termina, ocorrendo um erro ou não.

Também implementei um fluxo de **tratamento de exceções** para capturar falhas de banco de dados (erros de SQL) ou erros de validação de forma elegante. Isso impede que o programa quebre bruscamente mostrando aquela tela cheia de linhas vermelhas (*stack trace*) para o usuário, exibindo em vez disso uma mensagem amigável e clara no console.

---

### 💬 8. Como você garantiu que o sistema não tentasse alterar ou deletar um registro que não existe?
💻 Em métodos de alteração e deleção (`UPDATE` e `DELETE`), o método `stmt.executeUpdate()` do JDBC retorna um número inteiro que representa exatamente quantas linhas foram afetadas no banco de dados. 

No `MedicamentoDAO`, eu capturo esse retorno. Se o valor retornado for `0`, significa que o ID passado pelo usuário não existia na tabela. Sabendo disso, o sistema lança imediatamente uma exceção detalhada, impedindo que a operação termine silenciosamente e garantindo que o usuário seja alertado de que nada foi alterado.


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
