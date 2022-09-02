create table patients
(
  patient_id number not null,
  uuid varchar2(36) not null,
  first_name varchar2(128) not null,
  middle_name varchar2(128),
  last_name varchar2(128) not null,
  birthday date not null,
  address varchar2(255) 
);

alter table patients add constraint patients_pk primary key (patient_id) using index;
create unique index patients_ix1 on patients(uuid);
create sequence patients_seq start with 100 increment by 50 maxvalue 999999999;
comment on table patients is 'Пациенты';
comment on column patients.patient_id is 'patients UID';
comment on column patients.uuid is 'patients UUID';
comment on column patients.first_name is 'Имя';
comment on column patients.middle_name is 'Отчество';
comment on column patients.last_name is 'Фамилия';
comment on column patients.birthday is 'Дата рождения';
comment on column patients.address is 'Адрес';