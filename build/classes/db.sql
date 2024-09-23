--�������ݿ�
create database shoppingcart;
--��������ݿ�
use shoppingcart;
--������
--������Ʒ��

create table tb_product
(
    pid varchar(20) primary key,
    name varchar(30) not null,
    price numeric(6,2),
    imagePath varchar(30),
    pdesc varchar(100)
);
--�����û���

create table tb_user
(
  uid varchar(20) primary key,
  userName varchar(15),
  loginPwd varchar(15),
  mobile varchar(11),
  addr varchar(100)
);
--����������

create table tb_order
(
  oid varchar(20) primary key,
  user_uid varchar(20),
  totalMoney numeric(8,2),
  constraint tb_order_user_id_FK foreign key(user_uid) references tb_user(uid)
);
--������ĿItem��

create table tb_item
(
   id int ,
   product_pid varchar(20),
   number int,
   sumMoney numeric(6,2),
   order_oid varchar(20),
   constraint tb_item_product_pid_FK foreign key(product_pid) references tb_product(pid),
   constraint tb_item_order_oid_FK foreign key(order_oid) references tb_order(oid)
);

