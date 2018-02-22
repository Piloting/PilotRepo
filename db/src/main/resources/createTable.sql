
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
  dateEnd timestamp
);

create table tStat(
  statId numeric(8) not null constraint tStat_pkey primary key,
  trackId numeric(8) not null,
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
  --pointId numeric(8) not null constraint tPoint_pkey primary key,
  trackId numeric(8) not null,
  date timestamp not null,
  x numeric(18,10),
  y numeric(18,10),
  z numeric(8),
  speed numeric(8)
);

create table tSequence(
    table_name varchar(50) NOT NULL,
    next_id numeric(8) NOT NULL
)

INSERT INTO tUser(userId, login, brief, name, pass)
VALUES (1000, 'Pilot', 'Pilot', 'Pilot Pilot', null);

INSERT INTO tUser(userId, login, brief, name, pass)
VALUES (1001, 'Victor', 'Victor', 'Victor Victor', null);

INSERT INTO tUser(userId, login, brief, name, pass)
VALUES (1002, 'Igor', 'Igor', 'Igor Igor', null);

INSERT INTO tdevice (deviceid, userid, brief, comment) 
VALUES (1000, 100, 'Xiaomi Redmi 4', null);

INSERT INTO tdevice (deviceid, userid, brief, comment) 
VALUES (1001, 1001, 'LG', null);

INSERT INTO tdevice (deviceid, userid, brief, comment) 
VALUES (1002, 1002, 'Xiaomi Redmi 3', null);

INSERT INTO ttrack (trackid, deviceid, name, comment, datestart, dateend) 
VALUES (1000, 1000, 'TestTrack', null, '2018-02-19 10:44:42.000000', '2018-02-19 12:44:49.000000');

INSERT INTO ttrack (trackid, deviceid, name, comment, datestart, dateend) 
VALUES (1001, 1001, 'TestTrack1', null, '2018-02-19 11:44:42.000000', '2018-02-19 13:44:49.000000');

INSERT INTO ttrack (trackid, deviceid, name, comment, datestart, dateend) 
VALUES (1002, 1002, 'TestTrack2', null, '2018-02-19 12:44:42.000000', '2018-02-19 14:44:49.000000');