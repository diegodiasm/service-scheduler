/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego Dias
 */
@Entity
@Table(name = "servidores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servidores.findAll", query = "SELECT s FROM Servidores s"),
    @NamedQuery(name = "Servidores.findAllOrderByName", query = "SELECT s FROM Servidores s ORDER by s.name"),
    @NamedQuery(name = "Servidores.findById", query = "SELECT s FROM Servidores s WHERE s.id = :id"),
    @NamedQuery(name = "Servidores.findByName", query = "SELECT s FROM Servidores s WHERE s.name = :name"),
    @NamedQuery(name = "Servidores.findByInicioexp", query = "SELECT s FROM Servidores s WHERE s.inicioexp = :inicioexp"),
    @NamedQuery(name = "Servidores.findByFinalexp", query = "SELECT s FROM Servidores s WHERE s.finalexp = :finalexp"),
    @NamedQuery(name = "Servidores.findByInicioalmoco", query = "SELECT s FROM Servidores s WHERE s.inicioalmoco = :inicioalmoco"),
    @NamedQuery(name = "Servidores.findByFinalalmoco", query = "SELECT s FROM Servidores s WHERE s.finalalmoco = :finalalmoco"),
    @NamedQuery(name = "Servidores.findByServico", query = "SELECT s FROM Servidores s WHERE s.servico = :servico"),
    @NamedQuery(name = "Servidores.findByStatus", query = "SELECT s FROM Servidores s WHERE s.status = :status")})
public class Servidores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "inicioexp")
    private String inicioexp;
    @Basic(optional = false)
    @Column(name = "finalexp")
    private String finalexp;
    @Basic(optional = false)
    @Column(name = "inicioalmoco")
    private String inicioalmoco;
    @Basic(optional = false)
    @Column(name = "finalalmoco")
    private String finalalmoco;
    @Column(name = "servico")
    private String servico;
    @Column(name = "status")
    private Character status;

    public Servidores() {
    }

    public Servidores(Integer id) {
        this.id = id;
    }

    public Servidores(Integer id, String name, String inicioexp, String finalexp, String inicioalmoco, String finalalmoco) {
        this.id = id;
        this.name = name;
        this.inicioexp = inicioexp;
        this.finalexp = finalexp;
        this.inicioalmoco = inicioalmoco;
        this.finalalmoco = finalalmoco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
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
        if (!(object instanceof Servidores)) {
            return false;
        }
        Servidores other = (Servidores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Servidores[ id=" + id + " ]";
    }
    
}
