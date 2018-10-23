create table users
(
  ID       int auto_increment
    primary key,
  USERNAME varchar(50) not null,
  PASSWORD varchar(20) not null,
  EMAIL    varchar(50) null
);

create table notes
(
  ID      int auto_increment
    primary key,
  USER_ID int          null,
  TEXT    varchar(250) null,
  TITLE   varchar(30)  not null,
  constraint notes_users_ID_fk
  foreign key (USER_ID) references users (id)
);