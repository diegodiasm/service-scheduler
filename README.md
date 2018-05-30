# service-scheduler
Service scheduler 

A service-scheduler with two kinds of user: a service-agent, and admin.
This system allows the admin to set the number of agents for a fixed
service, and the duration of each time-slot available for appointment,
and collect statistics of the service.

The system also implements a display module, that can be used to call
service-users when they are ready to be dispatched, and display 
relevant information for them in the form of a scrolling text.

Developed using Java 7 + Swing + PostgreSQL + JPA


------------------- Agendamento de Atendimento --------------------

[Instalação]

A instalação completa (incluindo o banco de dados PostgreSQL) deve
ser realizada apenas em um computador. Este computador será usado
como servidor e precisará ficar ligado durante a utilização do
sistema. Nos demais computadores que utilizarão o programa para
cadastro ou acompanhamento, deve-se optar por realizar a instalação
mínima ou customizada (caso precise instalar a JVM, maquina virtual
java, durante a instalação).


----------------------------- Manual do Usuário -------------------

[Cadastrar]

Para agendar um atendimento, o usuário deve escolher a data,
horário, e inserir dados: nome, cpf e telefone. Caso seja inserido
um número de CPF, este será aceito apenas se passar na validação. 
Ao clicar em Salvar os dados são enviados ao banco, e ficam
disponíveis na aba de acompanhamento. 


[Acompanhar]

Para acompanhar o agendamento, selecione o atendente que deseja
monitorar. As atualizações dos agendamentos são automáticas,
ocorrendo a cada 1/4 (um quarto) do intervalo do tempo de atendimento
do atendente. Desta forma, para um atendente cujo tempo de
atendimento é 10 minutos, por exemplo, ocorrem 4 atualizações da
tabela de agendamentos durante o transcorrer do tempo de 1
atendimento.


[Configurar]

Permite confirar detalhes referentes a conexão com o banco de
dados, atendentes, usuáriose e restrições de expediente. Também é
possível visualizar os atendimentos realizados e excluir os
registros de atendimentos realizados do banco de dados.




Contato:
Diego Machado Dias (diegodias.m@gmail.com)
