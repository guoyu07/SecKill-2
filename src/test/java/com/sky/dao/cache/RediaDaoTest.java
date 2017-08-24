package com.sky.dao.cache;

import com.sky.dao.SeckillDao;
import com.sky.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class RediaDaoTest {
    private long id = 1001;

    @Autowired
    private RedisDao rediaDao;

    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void testSeckill() throws Exception {
        Seckill seckill = rediaDao.getSeckill(id);
        if(seckill == null){
            seckill = seckillDao.queryById(id);
            if(seckill != null){
                String result = rediaDao.setSeckill(seckill);
                seckill = rediaDao.getSeckill(id);
                System.out.println(seckill);
            }
        }
    }
}