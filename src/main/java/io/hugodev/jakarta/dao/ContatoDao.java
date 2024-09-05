package io.hugodev.jakarta.dao;

import io.hugodev.jakarta.model.Contato;
import io.hugodev.jakarta.util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The type Contato dao.
 */
public class ContatoDao {

    /**
     * Salvar.
     *
     * @param contato the contato
     */
    public void salvar(Contato contato){
        try(Connection connection = DbUtil.getConnection()){
            String sql = "insert into contatos (id, nome, email, fone) values (?, ?, ?, ?)";

            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setObject(1, UUID.randomUUID());
                ps.setString(2, contato.getNome());
                ps.setString(3, contato.getEmail());
                ps.setString(4, contato.getFone());

                ps.execute();
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Listar contatos list.
     *
     * @return the list
     */
    public List<Contato> listarContatos(){

        List<Contato> contatos = new ArrayList<>();

        try(Connection connection = DbUtil.getConnection()){
            String sql = "select * from contatos";

            try(Statement stmt = connection.createStatement()){
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()){
                    contatos.add(new Contato(UUID.fromString(rs.getString("id")), rs.getString("nome"), rs.getString("email"),rs.getString("fone")));
                }
            }

            return contatos;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Selecionar contato contato.
     *
     * @param id the id
     * @return the contato
     */
    public Contato selecionarContato(String id){
        Contato contato = new Contato();
        try(Connection connection = DbUtil.getConnection()){
            String sql = "select * from contatos where id = ? ";

            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setObject(1, UUID.fromString(id));

                ResultSet rs = ps.executeQuery();

                while (rs.next()){
                    contato.setId(UUID.fromString(rs.getString("id")));
                    contato.setNome(rs.getString("nome"));
                    contato.setEmail(rs.getString("email"));
                    contato.setFone(rs.getString("fone"));
                }
            }

            return contato;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Atualizar contato.
     *
     * @param contato the contato
     */
    public void atualizarContato(Contato contato){
        try(Connection connection = DbUtil.getConnection()){
            String sql = "update contatos set nome = ? , email = ? , fone = ? where id = ? ";

            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, contato.getNome());
                ps.setString(2, contato.getEmail());
                ps.setString(3, contato.getFone());
                ps.setObject(4, UUID.fromString(contato.getId().toString()));

                ps.execute();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletar contato.
     *
     * @param id the id
     */
    public void deletarContato(String id){
        try(Connection connection = DbUtil.getConnection()){
            String sql = "delete from contatos where id = ? ";

            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setObject(1, UUID.fromString(id));

                ps.execute();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
