-- 数据库初始化版本

-- 创建数据库
CREATE DATABASE seckill;
-- 使用数据库
use seckill;
--创建秒杀库存表
CREATE TABLE seckill(
'seckill_id' bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
'name' varchar(120) NOT NULL COMMIT '商品名称',
'number' int NOT NULL COMMENT '库存',
'start_time' timestamp NOT NULL COMMIT '秒杀开启时间',
'end_time' timestamp NOT NULL COMMIT '秒杀结束时间',
'create_time' timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMIT '创建时间',
PRIMARY KEY (seckill_id),
KEY idx_start_time(start_time),
KEY idx_end_time(end_time),
KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf-8 COMMIT='秒杀库存表'

-- 初始化数据
insert into
  seckill(name,number,start_time,end_time)
values
  ('1000元秒杀iPhone6', '3000', '2017-08-21 00:00:00', '2017-08-21 00:00:00');