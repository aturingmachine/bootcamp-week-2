DROP TABLE IF EXISTS stocks;

CREATE TABLE stocks (
    id int not null auto_increment,
    symbol varchar(4) not null,
    price float not null,
    volume int not null,
    date timestamp not null,
    primary key (id)
);