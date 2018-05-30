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
@Table(name = "confguiche")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Confguiche.findAll", query = "SELECT c FROM Confguiche c"),
    @NamedQuery(name = "Confguiche.findById", query = "SELECT c FROM Confguiche c WHERE c.id = :id"),
    @NamedQuery(name = "Confguiche.findByAviso", query = "SELECT c FROM Confguiche c WHERE c.aviso = :aviso"),
    @NamedQuery(name = "Confguiche.findByNtentativas", query = "SELECT c FROM Confguiche c WHERE c.ntentativas = :ntentativas"),
    @NamedQuery(name = "Confguiche.findByTolerancia", query = "SELECT c FROM Confguiche c WHERE c.tolerancia = :tolerancia"),
    @NamedQuery(name = "Confguiche.findByIntervalo", query = "SELECT c FROM Confguiche c WHERE c.intervalo = :intervalo"),
    @NamedQuery(name = "Confguiche.findByNultimas", query = "SELECT c FROM Confguiche c WHERE c.nultimas = :nultimas"),
    @NamedQuery(name = "Confguiche.findByTexibicao", query = "SELECT c FROM Confguiche c WHERE c.texibicao = :texibicao"),
    @NamedQuery(name = "Confguiche.findByTfreqconsultasenha", query = "SELECT c FROM Confguiche c WHERE c.tfreqconsultasenha = :tfreqconsultasenha"),
    @NamedQuery(name = "Confguiche.findByTfreqconsultaaviso", query = "SELECT c FROM Confguiche c WHERE c.tfreqconsultaaviso = :tfreqconsultaaviso"),
    @NamedQuery(name = "Confguiche.findByVelocidadeaviso", query = "SELECT c FROM Confguiche c WHERE c.velocidadeaviso = :velocidadeaviso"),
    @NamedQuery(name = "Confguiche.findByFontsenha", query = "SELECT c FROM Confguiche c WHERE c.fontsenha = :fontsenha"),
    @NamedQuery(name = "Confguiche.findByFontaviso", query = "SELECT c FROM Confguiche c WHERE c.fontaviso = :fontaviso"),
    @NamedQuery(name = "Confguiche.findBySound", query = "SELECT c FROM Confguiche c WHERE c.sound = :sound")})
public class Confguiche implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "aviso")
    private String aviso;
    @Column(name = "ntentativas")
    private Integer ntentativas;
    @Column(name = "tolerancia")
    private Integer tolerancia;
    @Column(name = "intervalo")
    private Integer intervalo;
    @Column(name = "nultimas")
    private Integer nultimas;
    @Column(name = "texibicao")
    private Integer texibicao;
    @Column(name = "tfreqconsultasenha")
    private Integer tfreqconsultasenha;
    @Column(name = "tfreqconsultaaviso")
    private Integer tfreqconsultaaviso;
    @Column(name = "velocidadeaviso")
    private Integer velocidadeaviso;
    @Column(name = "fontsenha")
    private Integer fontsenha;
    @Column(name = "fontaviso")
    private Integer fontaviso;
    @Column(name = "sound")
    private String sound;

    public Confguiche() {
    }

    public Confguiche(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public Integer getNtentativas() {
        return ntentativas;
    }

    public void setNtentativas(Integer ntentativas) {
        this.ntentativas = ntentativas;
    }

    public Integer getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(Integer tolerancia) {
        this.tolerancia = tolerancia;
    }

    public Integer getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(Integer intervalo) {
        this.intervalo = intervalo;
    }

    public Integer getNultimas() {
        return nultimas;
    }

    public void setNultimas(Integer nultimas) {
        this.nultimas = nultimas;
    }

    public Integer getTexibicao() {
        return texibicao;
    }

    public void setTexibicao(Integer texibicao) {
        this.texibicao = texibicao;
    }

    public Integer getTfreqconsultasenha() {
        return tfreqconsultasenha;
    }

    public void setTfreqconsultasenha(Integer tfreqconsultasenha) {
        this.tfreqconsultasenha = tfreqconsultasenha;
    }

    public Integer getTfreqconsultaaviso() {
        return tfreqconsultaaviso;
    }

    public void setTfreqconsultaaviso(Integer tfreqconsultaaviso) {
        this.tfreqconsultaaviso = tfreqconsultaaviso;
    }

    public Integer getVelocidadeaviso() {
        return velocidadeaviso;
    }

    public void setVelocidadeaviso(Integer velocidadeaviso) {
        this.velocidadeaviso = velocidadeaviso;
    }

    public Integer getFontsenha() {
        return fontsenha;
    }

    public void setFontsenha(Integer fontsenha) {
        this.fontsenha = fontsenha;
    }

    public Integer getFontaviso() {
        return fontaviso;
    }

    public void setFontaviso(Integer fontaviso) {
        this.fontaviso = fontaviso;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
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
        if (!(object instanceof Confguiche)) {
            return false;
        }
        Confguiche other = (Confguiche) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Confguiche[ id=" + id + " ]";
    }
    
}
