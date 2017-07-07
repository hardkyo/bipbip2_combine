drop table member_detail;
drop table member;

create table member
(
	id varchar2(16), 
	name varchar2(24) not null,
	pass varchar2(16) not null,
	email varchar2(16),
	address VARCHAR2(100),
  	phone VARCHAR2(20),
	constraint member_id_pk primary key(id)
); 
// ¼öÁ¤


