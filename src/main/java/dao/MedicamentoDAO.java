package dao;

import db.Conexao;
import model.Medicamento;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO {

    public void salvar(Medicamento m) throws SQLException {
        String sql = "INSERT INTO medicamento (nome, laboratorio, preco, dataValidade, quantidade) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNome());
            stmt.setString(2, m.getLaboratorio());
            stmt.setDouble(3, m.getPreco());
            stmt.setDate(4, Date.valueOf(m.getDataValidade()));
            stmt.setInt(5, m.getQuantidade());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws Exception {
        String sql = "DELETE FROM medicamento WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0)
                throw new Exception("Nenhum medicamento encontrado com ID: " + id);
        }
    }

    public void atualizarPreco(int id, double novoPreco) throws Exception {
        String sql = "UPDATE medicamento SET preco = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, novoPreco);
            stmt.setInt(2, id);
            if (stmt.executeUpdate() == 0)
                throw new Exception("Nenhum medicamento encontrado com ID: " + id);
        }
    }

    public void atualizarQuantidade(int id, int novaQtd) throws Exception {
        String sql = "UPDATE medicamento SET quantidade = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novaQtd);
            stmt.setInt(2, id);
            if (stmt.executeUpdate() == 0)
                throw new Exception("Nenhum medicamento encontrado com ID: " + id);
        }
    }

    public List<Medicamento> listarTodos(String ordenarPor) throws SQLException {
        String coluna = switch (ordenarPor) {
            case "preco"       -> "preco";
            case "validade"    -> "dataValidade";
            case "quantidade"  -> "quantidade";
            default            -> "nome";
        };
        String sql = "SELECT * FROM medicamento ORDER BY " + coluna;
        List<Medicamento> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        }
        return lista;
    }

    public List<Medicamento> buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM medicamento WHERE nome LIKE ? ORDER BY nome";
        List<Medicamento> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nome + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public List<Medicamento> listarVencidos() throws SQLException {
        String sql = "SELECT * FROM medicamento WHERE dataValidade < ? ORDER BY dataValidade";
        List<Medicamento> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public List<Medicamento> listarEstoqueBaixo(int limite) throws SQLException {
        String sql = "SELECT * FROM medicamento WHERE quantidade <= ? ORDER BY quantidade";
        List<Medicamento> lista = new ArrayList<>();
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, limite);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        }
        return lista;
    }

    private Medicamento mapear(ResultSet rs) throws SQLException {
        Medicamento m = new Medicamento();
        m.setId(rs.getInt("id"));
        m.setNome(rs.getString("nome"));
        m.setLaboratorio(rs.getString("laboratorio"));
        m.setPreco(rs.getDouble("preco"));
        m.setDataValidade(rs.getDate("dataValidade").toLocalDate());
        m.setQuantidade(rs.getInt("quantidade"));
        return m;
    }
}