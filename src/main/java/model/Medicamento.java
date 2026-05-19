package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Medicamento {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int id;
    private String nome;
    private String laboratorio;
    private double preco;
    private LocalDate dataValidade;
    private int quantidade;

    public Medicamento() {}

    public Medicamento(String nome, String laboratorio, double preco,
                       LocalDate dataValidade, int quantidade) {
        this.nome = nome;
        this.laboratorio = laboratorio;
        this.preco = preco;
        this.dataValidade = dataValidade;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getLaboratorio() { return laboratorio; }
    public void setLaboratorio(String laboratorio) { this.laboratorio = laboratorio; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public LocalDate getDataValidade() { return dataValidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    /** Indica se o medicamento já venceu */
    public boolean isVencido() {
        return dataValidade != null && dataValidade.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        String status = isVencido() ? " [VENCIDO]" : "";
        return String.format(
                "ID: %3d | %-20s | Lab: %-12s | R$ %7.2f | Val: %s | Estoque: %d%s",
                id, nome, laboratorio, preco,
                dataValidade != null ? dataValidade.format(FMT) : "N/A",
                quantidade, status
        );
    }
}