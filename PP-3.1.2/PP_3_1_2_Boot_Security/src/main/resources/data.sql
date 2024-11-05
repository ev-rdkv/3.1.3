create table if not exists users
(
    id       bigint auto_increment primary key,
    username varchar(255) not null,
    age      int          not null,
    password varchar(255) not null
);

create table if not exists roles
(
    id   bigint primary key auto_increment,
    role varchar(255)
);

# ALTER TABLE roles
#     ADD UNIQUE (role);
insert into roles(role)
values ('ROLE_USER');
insert into roles(role)
values ('ROLE_ADMIN');

create table if not exists user_roles
(
    user_id bigint,
    role_id bigint
);

INSERT INTO users (id, username, age, password)
VALUES (1, 'user', 30, 'user');
INSERT INTO users (id, username, age, password)
VALUES (2, 'admin', 30, 'admin');

alter table user_roles
    add foreign key (user_id) references users (id);
alter table user_roles
    add foreign key (role_id) references roles (id);

insert into user_roles (user_id, role_id)
VALUES (1, 1);
insert into user_roles (user_id, role_id)
VALUES (2, 2);