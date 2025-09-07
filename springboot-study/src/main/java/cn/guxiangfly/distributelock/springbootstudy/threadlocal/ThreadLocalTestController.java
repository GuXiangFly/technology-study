package cn.guxiangfly.distributelock.springbootstudy.threadlocal;

import com.alibaba.ttl.TtlRunnable;
import org.apache.http.client.utils.DateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.*;

@RestController
public class ThreadLocalTestController {

    ExecutorService   executorService = new ThreadPoolExecutor(10, 20, 1000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(100), new ThreadPoolExecutor.AbortPolicy());


    volatile  int count = 0;

    @GetMapping("/threadlocaltest")
    public String test1(){
        count ++;
        PojoBean pojoBean = new PojoBean();
        pojoBean.setContent("test_count_" +count);
        pojoBean.setFlowType("nowTime:" + DateUtils.formatDate(new Date()));
        TrafficCenterTransmittableThreadLocal.setPojoBean(pojoBean);
        System.out.println("ThreadLocal pojoBean:" + pojoBean);

        executorService.execute(new Runnable(){
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                PojoBean pojoBean = TrafficCenterTransmittableThreadLocal.getPojoBean();
                System.out.println("ThreadLocal pojoBean in Thread  normal\t\t:" + pojoBean);
            }
        });


        executorService.execute(Objects.requireNonNull(TtlRunnable.get(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            PojoBean pojoBean2 = TrafficCenterTransmittableThreadLocal.getPojoBean();
            System.out.println("ThreadLocal pojoBean in Thread TtlRunnable\t:" + pojoBean2);

        })));

        TrafficCenterTransmittableThreadLocal.remove();
        return "success";
    }
}
