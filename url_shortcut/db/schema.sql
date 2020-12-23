DROP TABLE IF EXISTS site;
DROP TABLE IF EXISTS encoded_url;

CREATE TABLE site (
    id serial primary key,
    name varchar(1024),
    login varchar (200),
    password varchar (200)
);

CREATE TABLE encoded_url (
    id serial primary key,
    url varchar(2000),
    code varchar(256),
    followings int
);

