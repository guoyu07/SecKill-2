-- 数据库初始化版本

-- 创建数据库
CREATE DATABASE seckill;
-- 使用数据库
use seckill;
-- 创建秒杀库存表
CREATE table seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
`name` varchar(120) NOT NULL COMMENT '商品名称',
`number` int NOT NULL COMMENT '库存',
`start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀开启时间',
`end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀结束时间',
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY (seckill_id),
KEY idx_start_time(start_time),
KEY idx_end_time(end_time),
KEY idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT ='秒杀库存表';

-- 初始化数据
insert into
  seckill(name,number,start_time,end_time)
values
  ('3000元秒杀iPhone6', '3000', '2017-08-21 00:00:00', '2017-08-30 00:00:00'),
  ('5000元秒杀Surface Pro', '5000', '2017-08-21 00:00:00', '2017-08-30 00:00:00'),
  ('1000元秒杀iPad3', '1000', '2017-08-21 00:00:00', '2017-08-30 00:00:00'),
  ('2000元秒杀小米6', '2000', '2017-08-21 00:00:00', '2017-08-30 00:00:00');

-- 秒杀成功明细表
create table success_killed(
`seckill_id` bigint NOT NULL COMMENT '秒杀商品id',
`user_phone` bigint NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT -1 COMMENT '-1无效 0成功 1已付款',
`create_time` timestamp NOT NULL COMMENT '创建时间',
PRIMARY KEY (seckill_id,user_phone),
KEY idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT ='秒杀库存表';

