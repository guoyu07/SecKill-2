package com.sky.service;

import com.sky.dto.Exposer;
import com.sky.dto.SeckillExecution;
import com.sky.entity.Seckill;
import com.sky.exception.RepeatKillException;
import com.sky.exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSecKillList() throws Exception {
        List<Seckill> list= seckillService.getSecKillList();
        logger.info("list = {}" , list);
    }

    @Test
    public void getById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill = {}", seckill);
    }

    @Test
    public void exportSeckillLogic() throws Exception {
        long id = 1001;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if (exposer.isExposed()){
            logger.info("exposer = {}", exposer);
            long phone = 13389087890L;
            String md5 = exposer.getMd5();
            try{
                SeckillExecution execution = seckillService.executeSeckill(id, phone, md5);
                logger.info("execution = {}", execution);
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SeckillCloseException e){
                logger.error(e.getMessage());
            }
        }else {
            logger.warn("expose = {}", exposer);
        }
    }

}