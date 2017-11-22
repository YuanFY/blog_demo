drop database if EXISTS monitorsite;
CREATE DATABASE `monitorsite` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use monitorsite;

drop table if EXISTS tb_sys_user;
create table tb_sys_user(
	id int(11) not null AUTO_INCREMENT,
	`name` varchar(50) UNIQUE not null comment '用户名',
	`password` varchar(50) not null comment '密码',
	`email` varchar(50) DEFAULT null comment '邮箱',
	`phone` varchar(50) DEFAULT null comment '手机号',
	register_time TIMESTAMP not null comment '注册时间',
	PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


drop table if EXISTS tb_tweets;
create table tb_tweets(
	id VARCHAR(64) not null,
	tweets_content varchar(500) not null comment '动弹内容',
	tweets_images varchar(500) default null comment '动弹图片',
	tweets_time TIMESTAMP not null comment '动弹时间',
	like_num	int DEFAULT 0 COMMENT '点赞数',
	comment_num int default 0 comment '评论数',
	user_id int not null comment '用户编号',
	primary key(id),
	CONSTRAINT `t_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_sys_user` (`id`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
