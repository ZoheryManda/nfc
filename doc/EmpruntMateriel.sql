/*==============================================================*/
/* Nom de SGBD :  MySQL 4.0                                     */
/* Date de création :  04/04/2016 22:26:47                      */
/*==============================================================*/




/*==============================================================*/
/* Table : materiel                                             */
/*==============================================================*/
create table materiel
(
   idmateriel                     int(12)                        not null auto_increment,
   idtype                         int(12)                        not null,
   libelle                        longtext                       not null,
   codemateriel                   longtext                       not null,
   uiid                           longtext,
   primary key (idmateriel)
)
;

/*==============================================================*/
/* Index : association_5_fk                                     */
/*==============================================================*/
create index association_5_fk on materiel
(
   idtype
);

/*==============================================================*/
/* Table : mouvement                                            */
/*==============================================================*/
create table mouvement
(
   idmvt                          int(150)                       not null auto_increment,
   idmateriel                     int(12)                        not null,
   idutilisateur                  int(12)                        not null,
   dateemprunt                    datetime                       not null,
   dateremise                     datetime                       not null,
   primary key (idmvt)
)
;

/*==============================================================*/
/* Index : association_2_fk                                     */
/*==============================================================*/
create index association_2_fk on mouvement
(
   idutilisateur
);

/*==============================================================*/
/* Index : association_3_fk                                     */
/*==============================================================*/
create index association_3_fk on mouvement
(
   idmateriel
);

/*==============================================================*/
/* Table : typemateriel                                         */
/*==============================================================*/
create table typemateriel
(
   idtype                         int(12)                        not null auto_increment,
   valeur                         varchar(50)                    not null,
   description                    varchar(50)                    not null,
   primary key (idtype)
)
;

/*==============================================================*/
/* Table : typeutilisateur                                      */
/*==============================================================*/
create table typeutilisateur
(
   id                             int(100)                       not null auto_increment,
   val                            longtext,
   descr                          longtext,
   primary key (id)
)
;

/*==============================================================*/
/* Table : utilisateur                                          */
/*==============================================================*/
create table utilisateur
(
   idutilisateur                  int(12)                        not null auto_increment,
   id                             int(100)                       not null,
   nom                            longtext                       not null,
   prenom                         longtext                       not null,
   telephone                      longtext                       not null,
   datenaissance                  date                           not null,
   adresse                        longtext,
   primary key (idutilisateur)
)
;

/*==============================================================*/
/* Index : association_4_fk                                     */
/*==============================================================*/
create index association_4_fk on utilisateur
(
   id
);

alter table materiel add constraint fk_association_5 foreign key (idtype)
      references typemateriel (idtype) on delete restrict on update restrict;

alter table mouvement add constraint fk_association_2 foreign key (idutilisateur)
      references utilisateur (idutilisateur) on delete restrict on update restrict;

alter table mouvement add constraint fk_association_3 foreign key (idmateriel)
      references materiel (idmateriel) on delete restrict on update restrict;

alter table utilisateur add constraint fk_association_4 foreign key (id)
      references typeutilisateur (id) on delete restrict on update restrict;

