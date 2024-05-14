drop table if exists chat_room;

create table chat_room (
    id bigint not null auto_increment,
    create_date datetime(6),
    title varchar(50) not null,
    primary key (id)
);