create table tickets
(
  ticket_id number not null,
  doctor_id number not null,
  patient_id number,
  visit_date date not null
);

alter table tickets add constraint tickets_pk primary key (ticket_id) using index;
create unique index tickets_ix1 on tickets(doctor_id, visit_date);
create index tickets_ix2 on tickets(patient_id);
alter table tickets add constraint tickets_fk1 foreign key (doctor_id) references doctors(doctor_id) on delete cascade; 
alter table tickets add constraint tickets_fk2 foreign key (patient_id) references patients(patient_id) on delete set null;
create sequence tickets_seq start with 100 increment by 50 maxvalue 999999999;
comment on table tickets is 'Тикеты';
comment on column tickets.ticket_id is 'tickets UID';
comment on column tickets.doctor_id is 'docktors UID';
comment on column tickets.patient_id is 'patients UID';
comment on column tickets.visit_date is 'Дата и время приема';