alter table article add share_link varchar(255);
update article set share_link='更多：http://www.chinaxiaokang.com/'

alter table magazine add show_status int(2);
update magazine set show_status=1