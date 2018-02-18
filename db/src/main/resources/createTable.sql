
create table tUser(
  userId numeric(8) not null constraint tUser_pkey primary key,
  login varchar(50) not null UNIQUE,
  brief varchar(50),
  name varchar(250),
  pass varchar(50)
);

create table tDevice(
  deviceId numeric(8) not null constraint tDevice_pkey primary key,
  userId numeric(8) not null,
  brief varchar(50),
  comment varchar(250)
);

create table tTrack(
  trackId numeric(8) not null constraint tTrack_pkey primary key,
  deviceId numeric(8) not null,
  name varchar(250),
  comment varchar(250),
  dateStart timestamp,
  datEnd timestamp
);

create table tStat(
  trackId numeric(8) not null constraint tStat_pkey primary key,
  len numeric(8),
  time numeric(15),
  avgSpeed numeric(8,2),
  maxSpeed numeric(8,2),
  stopCount numeric(8),
  maxHeight numeric(8),
  minHeight numeric(8),
  moveTime numeric(15)
);

create table tPoint(
  trackId numeric(8) not null,
  date date not null,
  x numeric(18,10),
  y numeric(18,10),
  z numeric(8),
  speed numeric(8)
);

INSERT INTO tUser(userId, login, brief, name, pass)
VALUES (1000, 'Pilot', 'Pilot', 'Pilot Pilot', null);

INSERT INTO tUser(userId, login, brief, name, pass)
VALUES (1001, 'Victor', 'Victor', 'Victor Victor', null);

INSERT INTO tUser(userId, login, brief, name, pass)
VALUES (1002, 'Igor', 'Igor', 'Igor Igor', null);
