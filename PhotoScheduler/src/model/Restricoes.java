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
@Table(name = "restricoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restricoes.findAll", query = "SELECT r FROM Restricoes r"),
    @NamedQuery(name = "Restricoes.findById", query = "SELECT r FROM Restricoes r WHERE r.id = :id"),
    @NamedQuery(name = "Restricoes.findByData", query = "SELECT r FROM Restricoes r WHERE r.data = :data"),
    @NamedQuery(name = "Restricoes.findByInicioexp", query = "SELECT r FROM Restricoes r WHERE r.inicioexp = :inicioexp"),
    @NamedQuery(name = "Restricoes.findByFinalexp", query = "SELECT r FROM Restricoes r WHERE r.finalexp = :finalexp"),
    @NamedQuery(name = "Restricoes.findByMotivo", query = "SELECT r FROM Restricoes r WHERE r.motivo = :motivo"),
    @NamedQuery(name = "Restricoes.findByServerid", query = "SELECT r FROM Restricoes r WHERE r.serverid = :serverid"),
    @NamedQuery(name = "Restricoes.findByInicioalmoco", query = "SELECT r FROM Restricoes r WHERE r.inicioalmoco = :inicioalmoco"),
    @NamedQuery(name = "Restricoes.findByFinalalmoco", query = "SELECT r FROM Restricoes r WHERE r.finalalmoco = :finalalmoco")})
public class Restricoes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @Column(name = "inicioexp")
    private String inicioexp;
    @Basic(optional = false)
    @Column(name = "finalexp")
    private String finalexp;
    @Column(name = "motivo")
    private String motivo;
    @Basic(optional = false)
    @Column(name = "serverid")
    private int serverid;
    @Column(name = "inicioalmoco")
    private String inicioalmoco;
    @Column(name = "finalalmoco")
    private String finalalmoco;

    public Restricoes() {
    }

    public Restricoes(Integer id) {
        this.id = id;
    }

    public Restricoes(Integer id, Date data, String inicioexp, String finalexp, int serverid) {
        this.id = id;
        this.data = data;
        this.inicioexp = inicioexp;
        this.finalexp = finalexp;
        this.serverid = serverid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getInicioexp() {
        return inicioexp;
    }

    public void setInicioexp(String inicioexp) {
        this.inicioexp = inicioexp;
    }

    public String getFinalexp() {
        return finalexp;
    }

    public void setFinalexp(String finalexp) {
        this.finalexp = finalexp;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getServerid() {
        return serverid;
    }

    public void setServerid(int serverid) {
        this.serverid = serverid;
    }

    public String getInicioalmoco() {
        return inicioalmoco;
    }

    public void setInicioalmoco(String inicioalmoco) {
        this.inicioalmoco = inicioalmoco;
    }

    public String getFinalalmoco() {
        return finalalmoco;
    }

    public void setFinalalmoco(String finalalmoco) {
        this.finalalmoco = finalalmoco;
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
        if (!(object instanceof Restricoes)) {
            return false;
        }
        Restricoes other = (Restricoes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Restricoes[ id=" + id + " ]";
    }
    
}
