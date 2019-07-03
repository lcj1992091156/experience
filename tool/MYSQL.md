mysql  主从同步
首先给分成两个库 分别配置 主从库的  my.in 配置文件之后重启服务 
然后使用主库  这个语句
show master status   查询主库的file position 信息
之后执行
grant replication slave,reload,super on *.* to slave@127.0.0.1 identified by 'slave'
添加同步用户 给予同步权限

然后从库执行
change master to master_host='127.0.0.1',master_user='slave',master_password='slave', master_log_file='bin.000001',master_log_pos=838; 将主库同步数据用户和
file position信息 添加到 从库中 
之后重启两个库的服务   然后从库执行
执行Show slave status查询
###  