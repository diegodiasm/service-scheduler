/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego Dias
 */
@Entity
@Table(name = "agendamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agendamento.findAll", query = "SELECT a FROM Agendamento a"),
    @NamedQuery(name = "Agendamento.findById", query = "SELECT a FROM Agendamento a WHERE a.id = :id"),
    @NamedQuery(name = "Agendamento.findByNome", query = "SELECT a FROM Agendamento a WHERE a.nome = :nome"),
    @NamedQuery(name = "Agendamento.findByCpf", query = "SELECT a FROM Agendamento a WHERE a.cpf = :cpf"),
    @NamedQuery(name = "Agendamento.findByTelefone", query = "SELECT a FROM Agendamento a WHERE a.telefone = :telefone"),
    @NamedQuery(name = "Agendamento.findByHorario", query = "SELECT a FROM Agendamento a WHERE a.horario = :horario"),
    @NamedQuery(name = "Agendamento.findByData", query = "SELECT a FROM Agendamento a WHERE a.data = :data"),
    @NamedQuery(name = "Agendamento.findByServidor", query = "SELECT a FROM Agendamento a WHERE a.servidor = :servidor"),
    @NamedQuery(name = "Agendamento.findByStatus", query = "SELECT a FROM Agendamento a WHERE a.status = :status"),
    @NamedQuery(name = "Agendamento.findByDtmarcacao", query = "SELECT a FROM Agendamento a WHERE a.dtmarcacao = :dtmarcacao"),
    @NamedQuery(name = "Agendamento.findByHmarcacao", query = "SELECT a FROM Agendamento a WHERE a.hmarcacao = :hmarcacao"),
    @NamedQuery(name = "Agendamento.findByInicioatendimento", query = "SELECT a FROM Agendamento a WHERE a.inicioatendimento = :inicioatendimento"),
    @NamedQuery(name = "Agendamento.findByDuracao", query = "SELECT a FROM Agendamento a WHERE a.duracao = :duracao"),
    @NamedQuery(name = "Agendamento.findByOperador", query = "SELECT a FROM Agendamento a WHERE a.operador = :operador"),
    @NamedQuery(name = "Agendamento.deleteById", query = "DELETE FROM Agendamento a WHERE a.id = :id"),
    @NamedQuery(name = "Agendamento.countFindByDataAndServidorAndStatus", query = "SELECT COUNT(a) FROM Agendamento a WHERE a.data = :data AND a.servidor = :servidor AND a.status = :status"),
    @NamedQuery(name = "Agendamento.avgFindByDataAndServidor", query = "SELECT AVG(a.duracao) FROM Agendamento a WHERE a.data = :data AND a.servidor = :servidor AND a.status = 'A'"),
    @NamedQuery(name = "Agendamento.countFindByOperadorAndDtmarcacaoEqualsDataAgendamento", query = "SELECT COUNT(a) FROM Agendamento a WHERE a.operador = :operador AND a.dtmarcacao = :dtmarcacao AND a.data = :dtmarcacao"),
    @NamedQuery(name = "Agendamento.countFindByOperadorAndDtmarcacaoLeqDataAgendamento", query = "SELECT COUNT(a) FROM Agendamento a WHERE a.operador = :operador AND a.dtmarcacao = :dtmarcacao AND a.data > :dtmarcacao"),
    @NamedQuery(name = "Agendamento.countFindByOperadorAndDtmarcacao", query = "SELECT COUNT(a) FROM Agendamento a WHERE a.operador = :operador AND a.dtmarcacao = :dtmarcacao"),
    @NamedQuery(name = "Agendamento.countFindByDtmarcacaoEqualsDataAgendamento", query = "SELECT COUNT(a) FROM Agendamento a WHERE a.dtmarcacao = :dtmarcacao AND a.data = :dtmarcacao"),
    @NamedQuery(name = "Agendamento.countFindDtmarcacaoLeqDataAgendamento", query = "SELECT COUNT(a) FROM Agendamento a WHERE a.dtmarcacao = :dtmarcacao AND a.data > :dtmarcacao"),
    @NamedQuery(name = "Agendamento.countFindByDtmarcacao", query = "SELECT COUNT(a) FROM Agendamento a WHERE a.dtmarcacao = :dtmarcacao"),
    @NamedQuery(name = "Agendamento.countFindByDataAndName", query = "SELECT COUNT(a) FROM Agendamento a, Servidores b WHERE a.data = :data AND a.servidor=b.id AND b.name = :name"),
    @NamedQuery(name = "Agendamento.countFindByDataAndStatusAndName", query = "SELECT COUNT(a) FROM Agendamento a, Servidores b WHERE a.data = :data AND a.status= :status AND a.servidor=b.id AND b.name = :name")})


public class Agendamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "telefone")
    private String telefone;
    @Basic(optional = false)
    @Column(name = "horario")
    private String horario;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "servidor")
    private int servidor;
    @Basic(optional = false)
    @Column(name = "status")
    private char status;
    @Column(name = "dtmarcacao")
    @Temporal(TemporalType.DATE)
    private Date dtmarcacao;
    @Column(name = "hmarcacao")
    @Temporal(TemporalType.TIME)
    private Date hmarcacao;
    @Column(name = "inicioatendimento")
    @Temporal(TemporalType.TIME)
    private Date inicioatendimento;
    @Column(name = "duracao")
    private Integer duracao;
    @Basic(optional = false)
    @Column(name = "operador")
    private String operador;

    public Agendamento() {
    }

    public Agendamento(Integer id) {
        this.id = id;
    }

    public Agendamento(Integer id, String nome, String cpf, String horario, Date data, int servidor, char status, String operador) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.horario = horario;
        this.data = data;
        this.servidor = servidor;
        this.status = status;
        this.operador = operador;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getServidor() {
        return servidor;
    }

    public void setServidor(int servidor) {
        this.servidor = servidor;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Date getDtmarcacao() {
        return dtmarcacao;
    }

    public void setDtmarcacao(Date dtmarcacao) {
        this.dtmarcacao = dtmarcacao;
    }

    public Date getHmarcacao() {
        return hmarcacao;
    }

    public void setHmarcacao(Date hmarcacao) {
        this.hmarcacao = hmarcacao;
    }

    public Date getInicioatendimento() {
        return inicioatendimento;
    }

    public void setInicioatendimento(Date inicioatendimento) {
        this.inicioatendimento = inicioatendimento;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agendamento)) {
            return false;
        }
        Agendamento other = (Agendamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Agendamento[ id=" + id + " ]";
    }
    
}
