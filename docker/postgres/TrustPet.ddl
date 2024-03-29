CREATE TABLE Dono (UtilizadorEmail varchar(255) NOT NULL, PRIMARY KEY (UtilizadorEmail));
CREATE TABLE Petsitter (UtilizadorEmail varchar(255) NOT NULL, HorarioId int4 NOT NULL, PRIMARY KEY (UtilizadorEmail));
CREATE TABLE Pedido (Id SERIAL NOT NULL, PetsitterUtilizadorEmail varchar(255), DonoUtilizadorEmail varchar(255) NOT NULL, Preco float8 NOT NULL, Ativo bool NOT NULL, DataInicio varchar(255), DataFim varchar(255), PRIMARY KEY (Id));
CREATE TABLE Servico (Id SERIAL NOT NULL, Designacao varchar(255), PRIMARY KEY (Id));
CREATE TABLE Animal (Id SERIAL NOT NULL, TipoAnimalId int4 NOT NULL, DonoUtilizadorEmail varchar(255) NOT NULL, Nome varchar(255), Avatar varchar(255), Idade varchar(255) NOT NULL, Porte varchar(255), Sexo varchar(255), Alergias varchar(255), Doencas varchar(255), Comportamento varchar(255), Vacinas bool NOT NULL, Desparasitacao bool NOT NULL, Esterilizacao bool NOT NULL, Raca varchar(255), Ativo bool NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Review (Id SERIAL NOT NULL, PetsitterUtilizadorEmail varchar(255) NOT NULL, DonoUtilizadorEmail varchar(255) NOT NULL, Pontuacao int4 NOT NULL, Comentario varchar(255), Alvo varchar(255), Data varchar(255), PRIMARY KEY (Id));
CREATE TABLE TipoAnimal (Id SERIAL NOT NULL, Tipo varchar(255), PRIMARY KEY (Id));
CREATE TABLE Horario (Id SERIAL NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Hora (Hora SERIAL NOT NULL, PRIMARY KEY (Hora));
CREATE TABLE Administrador (Email varchar(255) NOT NULL, Password varchar(255), PRIMARY KEY (Email));
CREATE TABLE Utilizador (Email varchar(255) NOT NULL, Password varchar(255), Nome varchar(255), Avatar varchar(255), DataNasc varchar(255), Contacto varchar(255), Jardim bool NOT NULL, Morada varchar(255), Ativo bool NOT NULL, Concelho varchar(255), Distrito varchar(255), AvaliacaoMedia float4 NOT NULL, NrAvaliacoes int4 NOT NULL, Token varchar(255), PRIMARY KEY (Email));
CREATE TABLE PrecoPetsitterServico (Id SERIAL NOT NULL, ServicoId int4 NOT NULL, PetsitterUtilizadorEmail varchar(255) NOT NULL, Preco float8 NOT NULL, PRIMARY KEY (Id));
CREATE TABLE AnimalServico (Id SERIAL NOT NULL, PedidoId int4 NOT NULL, ServicoId int4 NOT NULL, AnimalId int4 NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Dia (Id SERIAL NOT NULL, Dia int4 NOT NULL, PRIMARY KEY (Id));
CREATE TABLE Petsitter_TipoAnimal (PetsitterUtilizadorEmail varchar(255) NOT NULL, TipoAnimalId int4 NOT NULL, PRIMARY KEY (PetsitterUtilizadorEmail, TipoAnimalId));
CREATE TABLE Servico_TipoAnimal (ServicoId int4 NOT NULL, TipoAnimalId int4 NOT NULL, PRIMARY KEY (ServicoId, TipoAnimalId));
CREATE TABLE Horario_Dia (HorarioId int4 NOT NULL, DiaId int4 NOT NULL, PRIMARY KEY (HorarioId, DiaId));
CREATE TABLE Dia_Hora (DiaId int4 NOT NULL, HoraHora int4 NOT NULL, PRIMARY KEY (DiaId, HoraHora));
ALTER TABLE Dono ADD CONSTRAINT FKDono837672 FOREIGN KEY (UtilizadorEmail) REFERENCES Utilizador (Email);
ALTER TABLE Petsitter ADD CONSTRAINT FKPetsitter554888 FOREIGN KEY (UtilizadorEmail) REFERENCES Utilizador (Email);
ALTER TABLE Animal ADD CONSTRAINT FKAnimal697886 FOREIGN KEY (DonoUtilizadorEmail) REFERENCES Dono (UtilizadorEmail);
ALTER TABLE Animal ADD CONSTRAINT FKAnimal573623 FOREIGN KEY (TipoAnimalId) REFERENCES TipoAnimal (Id);
ALTER TABLE Petsitter_TipoAnimal ADD CONSTRAINT FKPetsitter_450423 FOREIGN KEY (PetsitterUtilizadorEmail) REFERENCES Petsitter (UtilizadorEmail);
ALTER TABLE Petsitter_TipoAnimal ADD CONSTRAINT FKPetsitter_451944 FOREIGN KEY (TipoAnimalId) REFERENCES TipoAnimal (Id);
ALTER TABLE Pedido ADD CONSTRAINT FKPedido671180 FOREIGN KEY (DonoUtilizadorEmail) REFERENCES Dono (UtilizadorEmail);
ALTER TABLE Review ADD CONSTRAINT FKReview465817 FOREIGN KEY (DonoUtilizadorEmail) REFERENCES Dono (UtilizadorEmail);
ALTER TABLE Review ADD CONSTRAINT FKReview263530 FOREIGN KEY (PetsitterUtilizadorEmail) REFERENCES Petsitter (UtilizadorEmail);
ALTER TABLE Petsitter ADD CONSTRAINT FKPetsitter770932 FOREIGN KEY (HorarioId) REFERENCES Horario (Id);
ALTER TABLE Pedido ADD CONSTRAINT FKPedido468893 FOREIGN KEY (PetsitterUtilizadorEmail) REFERENCES Petsitter (UtilizadorEmail);
ALTER TABLE PrecoPetsitterServico ADD CONSTRAINT FKPrecoPetsi477254 FOREIGN KEY (PetsitterUtilizadorEmail) REFERENCES Petsitter (UtilizadorEmail);
ALTER TABLE PrecoPetsitterServico ADD CONSTRAINT FKPrecoPetsi635110 FOREIGN KEY (ServicoId) REFERENCES Servico (Id);
ALTER TABLE AnimalServico ADD CONSTRAINT FKAnimalServ927533 FOREIGN KEY (AnimalId) REFERENCES Animal (Id);
ALTER TABLE AnimalServico ADD CONSTRAINT FKAnimalServ408300 FOREIGN KEY (ServicoId) REFERENCES Servico (Id);
ALTER TABLE AnimalServico ADD CONSTRAINT FKAnimalServ906784 FOREIGN KEY (PedidoId) REFERENCES Pedido (Id);
ALTER TABLE Servico_TipoAnimal ADD CONSTRAINT FKServico_Ti898353 FOREIGN KEY (ServicoId) REFERENCES Servico (Id);
ALTER TABLE Servico_TipoAnimal ADD CONSTRAINT FKServico_Ti144788 FOREIGN KEY (TipoAnimalId) REFERENCES TipoAnimal (Id);
ALTER TABLE Horario_Dia ADD CONSTRAINT FKHorario_Di910759 FOREIGN KEY (HorarioId) REFERENCES Horario (Id);
ALTER TABLE Horario_Dia ADD CONSTRAINT FKHorario_Di629082 FOREIGN KEY (DiaId) REFERENCES Dia (Id);
ALTER TABLE Dia_Hora ADD CONSTRAINT FKDia_Hora264109 FOREIGN KEY (DiaId) REFERENCES Dia (Id);
ALTER TABLE Dia_Hora ADD CONSTRAINT FKDia_Hora302043 FOREIGN KEY (HoraHora) REFERENCES Hora (Hora);
