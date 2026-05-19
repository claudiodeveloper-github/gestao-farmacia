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