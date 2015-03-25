create table SALLE(ID_SALLE int primary key, VILLE varchar(255), NB_SALLES int);
create table JOUER(ID_JOUER int primary key , ID_SALLE int, ID_FILM int);
create table FILM(ID_IMDB varchar(255) primary key, TITRE varchar(255), ANNEE int , TYPE varchar(255));


insert into SALLE values (1, 'Toulouse', 12);
insert into SALLE values (2, 'Bordeaux', 10);
insert into SALLE values (3, 'Paris', 15);
insert into SALLE values (4, 'Marseille', 8);
insert into SALLE values (5, 'Lyon', 12);
insert into SALLE values (6, 'Montpellier', 4);