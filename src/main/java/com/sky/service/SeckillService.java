package com.sky.service;

import com.sky.dto.Exposer;
import com.sky.dto.SeckillExecution;
import com.sky.entity.Seckill;
import com.sky.exception.RepeatKillException;
import com.sky.exception.SeckillCloseException;
import com.sky.exception.SeckillException;

import java.util.List;


public interface SeckillService {

    /**
     *查询所有秒杀记录
     * @return
     */
    List<Seckill> getSecKillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时暴露秒杀接口地址
     * @param seckillId
     */
     Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
     SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
             throws SeckillException, RepeatKillException, SeckillCloseException;

    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);

}
