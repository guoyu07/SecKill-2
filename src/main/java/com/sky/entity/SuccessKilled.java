package com.sky.entity;

import java.util.Date;

public class SuccessKilled {
    private long successId;
    private long userPhone;
    private short status;
    private Date createTime;
    private Seckill seckill;

    public long getSuccessId() {
        return successId;
    }

    public void setSuccessId(long successId) {
        this.successId = successId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "successId=" + successId +
                ", userPhone=" + userPhone +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
