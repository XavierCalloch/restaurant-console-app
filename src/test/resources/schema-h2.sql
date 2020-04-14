create table plat
(
id int(11) not null primary key auto_increment,
nom varchar(100) not null unique,
prix_en_centimes_euros int(7) not null
);