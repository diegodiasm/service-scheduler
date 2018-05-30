package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-14T18:16:27")
@StaticMetamodel(Senha.class)
public class Senha_ { 

    public static volatile SingularAttribute<Senha, Integer> id;
    public static volatile SingularAttribute<Senha, Integer> atendente;
    public static volatile SingularAttribute<Senha, Integer> numeracao;
    public static volatile SingularAttribute<Senha, String> guiche;
    public static volatile SingularAttribute<Senha, Integer> nchamadas;
    public static volatile SingularAttribute<Senha, Character> status;
    public static volatile SingularAttribute<Senha, Boolean> painel;
    public static volatile SingularAttribute<Senha, Date> dtcriacao;
    public static volatile SingularAttribute<Senha, Date> hcriacao;
    public static volatile SingularAttribute<Senha, Date> hchamada;

}