package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-03-07T09:15:18")
@StaticMetamodel(Agendamento.class)
public class Agendamento_ { 

    public static volatile SingularAttribute<Agendamento, String> horario;
    public static volatile SingularAttribute<Agendamento, Character> status;
    public static volatile SingularAttribute<Agendamento, Date> inicioatendimento;
    public static volatile SingularAttribute<Agendamento, Date> data;
    public static volatile SingularAttribute<Agendamento, Date> dtmarcacao;
    public static volatile SingularAttribute<Agendamento, String> operador;
    public static volatile SingularAttribute<Agendamento, Integer> id;
    public static volatile SingularAttribute<Agendamento, Integer> duracao;
    public static volatile SingularAttribute<Agendamento, Integer> servidor;
    public static volatile SingularAttribute<Agendamento, String> telefone;
    public static volatile SingularAttribute<Agendamento, String> nome;
    public static volatile SingularAttribute<Agendamento, String> cpf;
    public static volatile SingularAttribute<Agendamento, Date> hmarcacao;

}