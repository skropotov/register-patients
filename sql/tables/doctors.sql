create table doctors
(
  doctor_id number not null,
  uuid varchar2(36) not null,
  first_name varchar2(128) not null,
  middle_name varchar2(128),
  last_name varchar2(128) not null,
  speciality varchar2(128) 
);

alter table doctors add constraint doctors_pk primary key (doctor_id) using index;
create unique index doctors_ix1 on doctors(uuid);
create sequence doctors_seq start with 100 increment by 50 maxvalue 999999999;
comment on table doctors is 'Врачи';
comment on column doctors.doctor_id is 'doctors UID';
comment on column doctors.uuid is 'doctor UUID';
comment on column doctors.first_name is 'Имя';
comment on column doctors.middle_name is 'Отчество';
comment on column doctors.last_name is 'Фамилия';
comment on column doctors.speciality is 'Специальность';