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
@Table(name = "senha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Senha.findAll", query = "SELECT s FROM Senha s"),
    @NamedQuery(name = "Senha.findById", query = "SELECT s FROM Senha s WHERE s.id = :id"),
    @NamedQuery(name = "Senha.findByGuiche", query = "SELECT s FROM Senha s WHERE s.guiche = :guiche"),
    @NamedQuery(name = "Senha.findByStatus", query = "SELECT s FROM Senha s WHERE s.status = :status"),
    @NamedQuery(name = "Senha.findByHchamada", query = "SELECT s FROM Senha s WHERE s.hchamada = :hchamada"),
    @NamedQuery(name = "Senha.findByAtendente", query = "SELECT s FROM Senha s WHERE s.atendente = :atendente"),
    @NamedQuery(name = "Senha.findByHcriacao", query = "SELECT s FROM Senha s WHERE s.hcriacao = :hcriacao"),
    @NamedQuery(name = "Senha.findByDtcriacao", query = "SELECT s FROM Senha s WHERE s.dtcriacao = :dtcriacao"),
    @NamedQuery(name = "Senha.findByNumeracao", query = "SELECT s FROM Senha s WHERE s.numeracao = :numeracao"),
    @NamedQuery(name = "Senha.findByNchamadas", query = "SELECT s FROM Senha s WHERE s.nchamadas = :nchamadas"),
    @NamedQuery(name = "Senha.findByPainel", query = "SELECT s FROM Senha s WHERE s.painel = :painel"),
    @NamedQuery(name = "Senha.findByStatusAndPainel", query = "SELECT s FROM Senha s WHERE s.status = :status AND s.painel = :painel"),
    @NamedQuery(name = "Senha.findMaxNumeracaoByDtcriacao", query = "SELECT MAX(s.numeracao) FROM Senha s WHERE s.dtcriacao = :dtcriacao"),
    @NamedQuery(name = "Senha.findByStatusAndHchamadaAndHcriacao", query = "SELECT s FROM Senha s WHERE s.status = :status AND s.hchamada <= :hchamada AND s.hcriacao >= :hcriacao"),
    @NamedQuery(name = "Senha.findCountByGuicheAndDtCriacaoAndStatus", query = "SELECT COUNT(s) FROM Senha s WHERE s.status = :status AND s.dtcriacao = :dtcriacao AND s.guiche = :guiche"),
    @NamedQuery(name = "Senha.findCountByGuicheAndDtCriacaoAndStatusAndAtendente", query = "SELECT COUNT(s) FROM Senha s WHERE s.status = :status AND s.dtcriacao = :dtcriacao AND s.guiche = :guiche AND s.atendente = :atendente")})
public class Senha implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "guiche")
    private String guiche;
    @Column(name = "status")
    private Character status;
    @Column(name = "hchamada")
    @Temporal(TemporalType.TIME)
    private Date hchamada;
    @Column(name = "atendente")
    private Integer atendente;
    @Column(name = "hcriacao")
    @Temporal(TemporalType.TIME)
    private Date hcriacao;
    @Column(name = "dtcriacao")
    @Temporal(TemporalType.DATE)
    private Date dtcriacao;
    @Column(name = "numeracao")
    private Integer numeracao;
    @Column(name = "nchamadas")
    private Integer nchamadas;
    @Column(name = "painel")
    private Boolean painel;

    public Senha() {
    }

    public Senha(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuiche() {
        return guiche;
    }

    public void setGuiche(String guiche) {
        this.guiche = guiche;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Date getHchamada() {
        return hchamada;
    }

    public void setHchamada(Date hchamada) {
        this.hchamada = hchamada;
    }

    public Integer getAtendente() {
        return atendente;
    }

    public void setAtendente(Integer atendente) {
        this.atendente = atendente;
    }

    public Date getHcriacao() {
        return hcriacao;
    }

    public void setHcriacao(Date hcriacao) {
        this.hcriacao = hcriacao;
    }

    public Date getDtcriacao() {
        return dtcriacao;
    }

    public void setDtcriacao(Date dtcriacao) {
        this.dtcriacao = dtcriacao;
    }

    public Integer getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(Integer numeracao) {
        this.numeracao = numeracao;
    }

    public Integer getNchamadas() {
        return nchamadas;
    }

    public void setNchamadas(Integer nchamadas) {
        this.nchamadas = nchamadas;
    }

    public Boolean getPainel() {
        return painel;
    }

    public void setPainel(Boolean painel) {
        this.painel = painel;
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
        if (!(object instanceof Senha)) {
            return false;
        }
        Senha other = (Senha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Senha[ id=" + id + " ]";
    }
    
}
