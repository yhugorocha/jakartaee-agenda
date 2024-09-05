package io.hugodev.jakarta.model;

import java.util.UUID;

/**
 * The type Contato.
 */
public class Contato {

    private UUID id;
    private String nome;
    private String email;
    private String fone;

    /**
     * Instantiates a new Contato.
     *
     * @param id    the id
     * @param nome  the nome
     * @param email the email
     * @param fone  the fone
     */
    public Contato(UUID id, String nome, String email, String fone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.fone = fone;
    }

    /**
     * Instantiates a new Contato.
     */
    public Contato() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets fone.
     *
     * @return the fone
     */
    public String getFone() {
        return fone;
    }

    /**
     * Sets fone.
     *
     * @param fone the fone
     */
    public void setFone(String fone) {
        this.fone = fone;
    }

    @Override
    public String toString() {
        return "JavaBeans{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", fone='" + fone + '\'' +
                '}';
    }
}
