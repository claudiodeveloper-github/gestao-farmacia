package com.farmacia;

import model.Medicamento;
import service.MedicamentoService;
import util.Relatorio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final MedicamentoService service = new MedicamentoService();
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n========= SISTEMA DE FARMÁCIA =========");
            System.out.println("1. Cadastrar Medicamento");
            System.out.println("2. Listar Todos");
            System.out.println("3. Buscar por Nome");
            System.out.println("4. Ver Vencidos");
            System.out.println("5. Remover Medicamento");
            System.out.println("6. Atualizar Preço ou Quantidade");
            System.out.println("7. Alerta de Estoque Baixo");
            System.out.println("8. Exportar Relatório (.txt)");
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            try {
                String linha = sc.nextLine().trim();
                if (linha.isEmpty()) continue;
                opcao = Integer.parseInt(linha);

                switch (opcao) {
                    case 1 -> cadastrar();
                    case 2 -> listar();
                    case 3 -> buscar();
                    case 4 -> listarVencidos();
                    case 5 -> remover();
                    case 6 -> atualizar();
                    case 7 -> estoqueBaixo();
                    case 8 -> exportar();
                    case 0 -> System.out.println("Saindo... até logo!");
                    default -> System.out.println("⚠ Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠ Digite apenas números no menu.");
            } catch (Exception e) {
                System.err.println("Erro: " + e.getMessage());
            }
        }
        sc.close();
    }

    private static void cadastrar() throws Exception {
        System.out.println("\n--- Cadastrar Medicamento ---");

        System.out.print("Nome: ");
        String nome = sc.nextLine().trim();
        if (nome.isEmpty()) throw new Exception("Nome não pode ser vazio.");

        System.out.print("Laboratório: ");
        String lab = sc.nextLine().trim();
        if (lab.isEmpty()) throw new Exception("Laboratório não pode ser vazio.");

        System.out.print("Preço (ex: 12.50): ");
        double preco;
        try {
            preco = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            throw new Exception("Preço inválido.");
        }

        System.out.print("Data de Validade (dd/MM/yyyy): ");
        LocalDate data;
        try {
            data = LocalDate.parse(sc.nextLine().trim(), fmt);
        } catch (DateTimeParseException e) {
            throw new Exception("Data inválida. Use dd/MM/yyyy.");
        }

        System.out.print("Quantidade em estoque: ");
        int qtd;
        try {
            qtd = Integer.parseInt(sc.nextLine().trim());
            if (qtd < 0) throw new Exception("Quantidade não pode ser negativa.");
        } catch (NumberFormatException e) {
            throw new Exception("Quantidade inválida.");
        }

        service.cadastrar(new Medicamento(nome, lab, preco, data, qtd));
        System.out.println("✔ Medicamento cadastrado com sucesso!");
    }

    private static void listar() throws Exception {
        System.out.println("\nOrdenar por: 1-Nome  2-Preço  3-Validade  4-Quantidade");
        System.out.print("Escolha: ");
        String ordem = switch (sc.nextLine().trim()) {
            case "2" -> "preco";
            case "3" -> "validade";
            case "4" -> "quantidade";
            default  -> "nome";
        };

        List<Medicamento> lista = service.listar(ordem);
        System.out.println("\n--- Lista de Medicamentos (ordenado por " + ordem + ") ---");
        if (lista.isEmpty()) {
            System.out.println("Nenhum medicamento cadastrado.");
            return;
        }
        lista.forEach(System.out::println);
        System.out.println("Total: " + lista.size() + " medicamento(s).");
    }

    private static void buscar() throws Exception {
        System.out.print("\nDigite o nome para busca: ");
        String nome = sc.nextLine().trim();
        if (nome.isEmpty()) throw new Exception("Digite ao menos um caractere.");

        List<Medicamento> lista = service.buscar(nome);
        if (lista.isEmpty()) {
            System.out.println("Nenhum medicamento encontrado para: \"" + nome + "\"");
            return;
        }
        lista.forEach(System.out::println);
    }

    private static void listarVencidos() throws Exception {
        List<Medicamento> lista = service.buscarVencidos();
        System.out.println("\n--- Medicamentos Vencidos ---");
        if (lista.isEmpty()) {
            System.out.println("Nenhum medicamento vencido. ✔");
            return;
        }
        lista.forEach(System.out::println);
        System.out.println("Total vencidos: " + lista.size());
    }

    private static void remover() throws Exception {
        System.out.print("\nID do medicamento a remover: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new Exception("ID inválido.");
        }
        service.remover(id);
        System.out.println("✔ Medicamento removido com sucesso!");
    }

    private static void atualizar() throws Exception {
        System.out.print("\nID do medicamento a atualizar: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new Exception("ID inválido.");
        }

        System.out.println("O que deseja atualizar?  1-Preço  2-Quantidade");
        System.out.print("Escolha: ");
        String escolha = sc.nextLine().trim();

        if (escolha.equals("1")) {
            System.out.print("Novo preço: ");
            double preco;
            try {
                preco = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                throw new Exception("Preço inválido.");
            }
            service.atualizarPreco(id, preco);
            System.out.println("✔ Preço atualizado com sucesso!");

        } else if (escolha.equals("2")) {
            System.out.print("Nova quantidade: ");
            int qtd;
            try {
                qtd = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                throw new Exception("Quantidade inválida.");
            }
            service.atualizarQuantidade(id, qtd);
            System.out.println("✔ Quantidade atualizada com sucesso!");

        } else {
            System.out.println("⚠ Opção inválida.");
        }
    }

    private static void estoqueBaixo() throws Exception {
        System.out.print("\nMostrar medicamentos com estoque abaixo de quantas unidades? ");
        int limite;
        try {
            limite = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new Exception("Valor inválido.");
        }

        List<Medicamento> lista = service.buscarEstoqueBaixo(limite);
        System.out.println("\n--- Alerta: Estoque ≤ " + limite + " unidades ---");
        if (lista.isEmpty()) {
            System.out.println("Nenhum medicamento abaixo do limite. ✔");
            return;
        }
        lista.forEach(System.out::println);
        System.out.println("Total em alerta: " + lista.size() + " medicamento(s).");
    }

    private static void exportar() throws Exception {
        System.out.println("\nExportar qual relatório?");
        System.out.println("1. Todos os medicamentos");
        System.out.println("2. Medicamentos vencidos");
        System.out.println("3. Estoque baixo");
        System.out.print("Escolha: ");

        String escolha = sc.nextLine().trim();
        List<Medicamento> lista;
        String titulo;

        switch (escolha) {
            case "2" -> {
                lista  = service.buscarVencidos();
                titulo = "Medicamentos Vencidos";
            }
            case "3" -> {
                System.out.print("Limite de estoque: ");
                int lim;
                try {
                    lim = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    throw new Exception("Valor inválido.");
                }
                lista  = service.buscarEstoqueBaixo(lim);
                titulo = "Estoque Baixo (≤ " + lim + " unidades)";
            }
            default -> {
                System.out.println("Ordenar por: 1-Nome  2-Preço  3-Validade  4-Quantidade");
                System.out.print("Escolha: ");
                String ordem = switch (sc.nextLine().trim()) {
                    case "2" -> "preco";
                    case "3" -> "validade";
                    case "4" -> "quantidade";
                    default  -> "nome";
                };
                lista  = service.listar(ordem);
                titulo = "Todos os Medicamentos";
            }
        }

        String arquivo = Relatorio.exportarTxt(lista, titulo);
        System.out.println("✔ Relatório exportado: " + arquivo);
        System.out.println("  (salvo na pasta raiz do projeto)");
    }
}