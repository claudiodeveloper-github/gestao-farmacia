package service;

import dao.MedicamentoDAO;
import model.Medicamento;

import java.sql.SQLException;
import java.util.List;

public class MedicamentoService {
    private final MedicamentoDAO dao = new MedicamentoDAO();

    public void cadastrar(Medicamento m) throws Exception {
        if (m.getNome() == null || m.getNome().isBlank())
            throw new Exception("Nome do medicamento é obrigatório.");
        if (m.getLaboratorio() == null || m.getLaboratorio().isBlank())
            throw new Exception("Laboratório é obrigatório.");
        if (m.getPreco() <= 0)
            throw new Exception("O preço deve ser maior que zero.");
        if (m.getQuantidade() < 0)
            throw new Exception("A quantidade não pode ser negativa.");
        if (m.getDataValidade() == null)
            throw new Exception("A data de validade é obrigatória.");
        dao.salvar(m);
    }

    public List<Medicamento> listar(String ordenarPor) throws SQLException {
        return dao.listarTodos(ordenarPor);
    }

    public List<Medicamento> buscar(String nome) throws SQLException {
        return dao.buscarPorNome(nome);
    }

    public List<Medicamento> buscarVencidos() throws SQLException {
        return dao.listarVencidos();
    }

    public List<Medicamento> buscarEstoqueBaixo(int limite) throws SQLException {
        return dao.listarEstoqueBaixo(limite);
    }

    public void remover(int id) throws Exception {
        if (id <= 0) throw new Exception("ID inválido para remoção.");
        dao.deletar(id);
    }

    public void atualizarPreco(int id, double novoPreco) throws Exception {
        if (novoPreco <= 0) throw new Exception("O preço deve ser maior que zero.");
        dao.atualizarPreco(id, novoPreco);
    }

    public void atualizarQuantidade(int id, int novaQtd) throws Exception {
        if (novaQtd < 0) throw new Exception("A quantidade não pode ser negativa.");
        dao.atualizarQuantidade(id, novaQtd);
    }
}