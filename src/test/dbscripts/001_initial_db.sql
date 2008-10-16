--snippet category
create table snippet_category
(
    id int not null primary key auto_increment,
	parent_id int,
	name varchar(64),
	icon varchar(16),
	created_at timestamp
);

--code snippet
create table snippets
(
    id int not null primary key auto_increment,
	category_id int,
	name varchar(128),
	mnemonic varchar(32),
	language int,
	icon int,
	author varchar(32),
	keywords varchar(256),
	url varchar(128),
	description mediumtext,
	code mediumtext,
	example mediumtext,
	type varchar(16),
	level int,
	flag int,
	created_at timestamp,
	modified_at timestamp
);

--meta datas
create table meta_datas
(
   id int not null,
   value varchar(64),
   type varchar(64)
);

--snippet comments
create table snippet_comments
(
    id int not null primary key auto_increment,
	snippet_id int,
	commentator  varchar(64) character set utf8,
	commentator_email varchar(128),
	subject varchar(256) character set utf8,
	content mediumtext character set utf8,
	created_at timestamp
);


--language
insert into snippet_category (parent_id, name, icon) values(null, 'Language', 'folder.gif');

insert into snippet_category (parent_id, name, icon) values(1001, 'Java', 'java.png');
insert into snippet_category (parent_id, name, icon) values(1001, 'Ruby', 'ruby.png');
insert into snippet_category (parent_id, name, icon) values(1001, 'Python', 'python.png');
insert into snippet_category (parent_id, name, icon) values(1001, 'Perl', 'perl.png');
insert into snippet_category (parent_id, name, icon) values(1001, 'PHP', 'php.png');
insert into snippet_category (parent_id, name, icon) values(1001, 'C/C++', 'cpp.png');
insert into snippet_category (parent_id, name, icon) values(1001, 'C#', 'csharp.png');
insert into snippet_category (parent_id, name, icon) values(1001, 'Javascript', 'javascript.png');
insert into snippet_category (parent_id, name, icon) values(1001, 'Regex', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1001, 'html', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1001, 'Shell', 'folder.gif');

insert into snippet_category (parent_id, name, icon) values(1002, 'JDBC', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1002, 'J2EE', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1002, 'J2ME', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1002, 'Swing', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1002, 'Spring', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1002, 'Hibernate', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1002, 'Struts', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1002, 'iBATIS', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1002, 'JBoss', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1002, 'Tomcat', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1002, 'Resin', 'folder.gif');

insert into snippet_category (parent_id, name, icon) values(1009, 'Prototype', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1009, 'JQuery', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1009, 'Dojo', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1009, 'YUI', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1009, 'ExtJS', 'folder.gif');

--os
insert into snippet_category (parent_id, name, icon) values(null, 'OS', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1030, 'Linux', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1030, 'Unix', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1030, 'Mac', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(1030, 'Windows', 'folder.gif');

--database
insert into snippet_category (parent_id, name, icon) values(null, 'Database', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(10029, 'Oracle', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(10029, 'MySQL', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(10029, 'PostgreSQL', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(10029, 'SQL Server', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(10029, 'Sybase', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(10029, 'SQL', 'folder.gif');


--server
insert into snippet_category (parent_id, name, icon) values(null, 'Server', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(35, 'Apache', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(35, 'Sendmail', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(35, 'Qmail', 'folder.gif');
insert into snippet_category (parent_id, name, icon) values(35, 'Squid', 'folder.gif');
