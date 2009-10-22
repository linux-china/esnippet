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
    name varchar(128) CHARACTER SET utf8,
    mnemonic varchar(32),
    language int,
    icon int,
    author varchar(32) CHARACTER SET utf8,
    keywords varchar(256) CHARACTER SET utf8,
    url varchar(128),
    description mediumtext  CHARACTER SET utf8 ,
    code mediumtext  CHARACTER SET utf8,
    example mediumtext  CHARACTER SET utf8,
    type varchar(16),
    level int,
    flag int,
    type varchar(16),
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

--project short names
create table project_shortnames {
  id int not null primary key auto_increment,
  name varchar(128),
  short_name varchar(16),
  description varchar(512)
};

--sample data
INSERT INTO `snippet_category` VALUES (1,NULL,'Language','language.png','2008-06-04 22:21:19'),
        (2,1,'Java','java.png','2008-05-23 22:34:17'),
        (3,1,'Ruby','ruby.png','2008-05-23 22:34:17'),
        (4,1,'Python','python.png','2008-05-23 22:34:17'),
        (5,1,'Perl','perl.png','2008-05-26 13:56:16'),
        (6,1,'PHP','php.png','2008-05-23 22:34:17'),
        (7,1,'C/C++','cpp.png','2008-05-23 22:34:17'),
        (8,1,'C#','csharp.png','2008-05-23 22:34:17'),
        (9,1,'Javascript','javascript.png','2008-05-23 22:34:17'),
        (10,2,'JDBC','jdbc.png','2008-06-04 22:38:30'),
        (11,2,'J2EE','j2ee.png','2008-06-04 22:38:44'),
        (12,2,'Swing','swing.png','2008-06-04 22:41:58'),
        (13,2,'Spring','springframework.png','2008-06-04 23:15:22'),
        (14,2,'Hibernate','hibernate.png','2008-06-04 22:38:14'),
        (15,2,'Struts','apache.png','2008-06-04 22:39:10'),
        (17,2,'iBATIS','ibatis.png','2008-06-04 22:39:31'),
        (18,2,'JBoss','jboss.png','2008-06-04 22:38:56'),
        (19,9,'Prototype','prototype.png','2008-06-04 23:48:08'),
        (20,9,'JQuery','jquery.png','2008-06-04 22:55:15'),
        (21,9,'Dojo','dojo.png','2008-06-04 22:55:15'),
        (22,9,'YUI','yahoo.png','2008-06-04 22:55:15'),
        (23,9,'ExtJS','extjs.png','2008-06-04 22:55:15'),
        (24,NULL,'OS','folder.png','2008-05-23 22:38:09'),
        (25,24,'Linux','linux.png','2008-06-04 22:42:48'),
        (26,24,'Unix','unix.png','2008-06-04 22:43:08'),
        (27,24,'Mac','mac.png','2008-06-04 22:42:56'),
        (28,24,'Windows','windows.png','2008-06-04 22:44:11'),
        (29,NULL,'Database','database.png','2008-06-04 22:17:25'),
        (30,29,'Oracle','oracle.png','2008-06-04 22:15:47'),
        (31,29,'MySQL','mysql.png','2008-06-04 22:14:43'),
        (32,29,'PostgreSQL','postgresql.png','2008-06-04 22:15:04'),
        (33,29,'SQL Server','microsoft.png','2008-06-04 23:05:16'),
        (34,29,'Sybase','sybase.png','2008-06-04 22:57:40'),
        (35,NULL,'Server','server.png','2008-06-04 22:46:11'),
        (36,29,'SQLite','sqlite.png','2008-06-04 22:19:46'),
        (37,35,'Apache','apache.png','2008-06-04 23:08:38'),
        (38,35,'Qmail','qmail.png','2008-06-04 23:09:57'),
        (39,3,'Rails','rails.png','2008-06-04 23:22:01'),
        (40,4,'Django','django.png','2008-06-04 23:25:02'),
        (41,9,'MooTools','mootools.png','2008-06-04 23:35:30'),
        (42,2,'J2ME','j2me.png','2008-06-04 23:37:50'),
        (43,9,'GWT','google.png','2008-06-04 23:39:05'),
        (44,2,'JUnit','junit.png','2008-06-07 01:04:01'),
        (45,2,'TestNG','testng.png','2008-06-07 01:04:10'),
        (46,9,'Sproutcore','sproutcore.png','2008-06-20 01:11:57'),
        (47,1,'Regex','regex.png','2008-06-22 11:49:14'),
        (48,1,'HTML','xhtml.png','2008-06-22 17:56:31'),
        (49,2,'Jakarta','apache.png','2008-06-22 18:09:56'),
        (-1,NULL,'Added Recently','rss.png','2008-08-06 01:27:36'),
        (50,1,'Shell','shellscript.png','2008-10-17 16:40:23');

INSERT INTO meta_data VALUES (100,'Java','language'),
        (101,'Ruby','language'),
        (102,'PHP','language'),
        (103,'C++','language'),
        (104,'C#','language'),
        (105,'CSS','language'),
        (106,'Python','language'),
        (107,'SQL','language'),
        (108,'HTML','language'),
        (200,'java.png','icon'),
        (201,'ruby.png','icon'),
        (202,'php.png','icon'),
        (203,'cpp.png','icon'),
        (204,'csharp.png','icon'),
        (205,'css.png','icon'),
        (206,'python.png','icon'),
        (207,'sql.png','icon'),
        (208,'html.png','icon'),
        (109,'XML','language'),
        (110,'JavaScript','language'),
        (209,'xml.png','icon'),
        (210,'text.png','icon'),
        (111,'Text','language');


