DROP TABLE IF EXISTS stocks;
DROP TABLE IF EXISTS companies;

CREATE TABLE companies (
    id int not null auto_increment,
    symbol varchar(4) not null,
    primary key (id)
);

CREATE TABLE stocks (
    id int not null auto_increment,
    companyId int not null,
    price float not null,
    volume int not null,
    date timestamp not null,
    primary key (id),
    foreign key (companyId) references companies(id)
);