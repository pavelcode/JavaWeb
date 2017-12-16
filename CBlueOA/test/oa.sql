--删除用户
drop user cblueoa;
--创建数据库
create database cblue_oa default character set 'utf8';
--创建oa的用户
create user cblueoa identified  by '123';
--给用户分配权限
grant all on cblue_oa.* to cblueoa;

