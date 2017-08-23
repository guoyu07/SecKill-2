var seckill = {
    URL: {
        now: function () {
            return '/seckill/time/now';
        },
        exposer: function (seckillId) {
            return '/seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },

    handleSeckillKill: function (seckillId, node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId), {}, function (result) {
            if (result && result['success']) {
                var exposer = result['data'];
                if (exposer['exposed']) {
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    console.log(killUrl);
                    $('#killBtn').one('click', function () {
                        $(this).addClass('disable');
                        $.post(killUrl, {}, function () {
                            if (result && result['success']) {
                                var killResult = result['data'];
                                var state = killResult['data'];
                                var stateInfo = killResult['stateInfo'];
                                node.html('<span class="label label-success">' + stateInfo + '</span>')
                            }
                        });
                    })
                    node.show();
                } else {
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];

                    //重新计算计时逻辑
                    seckill.countDown(seckillId, now, start, end);
                }
            }
            else {
                console.log('result' + result);
            }
        })
    },

    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },

    countDown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        if (nowTime > endTime) {
            seckillBox.html('秒杀结束');
        } else if (nowTime < startTime) {
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime('秒杀倒计时： %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown', function () {
                seckill.handleSeckillKill(seckillId, seckillBox);
            });
        } else {
            seckill.handleSeckillKill(seckillId, seckillBox);
        }
    },

    detail: {
        init: function (params) {
            var userPhone = $.cookie('userPhone');
            if (!seckill.validatePhone(userPhone)) {
                var userPhoneModal = $('#userPhoneModal');
                userPhoneModal.modal({
                    show: true,  //显示弹出层
                    backdrop: 'static',  //禁止位置关闭
                    keyboard: false      //关闭键盘事件
                });

                $('#userPhoneBtn').click(function () {
                    var inputPhone = $('#userPhoneKey').val();
                    if (seckill.validatePhone(inputPhone)) {
                        $.cookie('userPhone', inputPhone, {expires: 7, path: '/seckill'});
                        window.location.reload();
                    } else {
                        $('#userPhoneMessage').hide().html('<label class="label label-danger">手机号错误!</label>').show(300);
                    }
                });
            }

            var seckillId = params['seckillId'];
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result && result['success']) {
                    var nowTime = result['data'];
                    seckill.countDown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log('result:' + result);
                }
            });
        }
    }
}