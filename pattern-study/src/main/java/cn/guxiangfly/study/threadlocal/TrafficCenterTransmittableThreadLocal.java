package cn.guxiangfly.study.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;

public class TrafficCenterTransmittableThreadLocal {


    public  static  final TransmittableThreadLocal<String> workTypeThreadLocal = new TransmittableThreadLocal<String>();
    public  static  final TransmittableThreadLocal<PojoBean> pojoBeanThreadLocal = new TransmittableThreadLocal<PojoBean>();

    public static void setPojoBean(PojoBean pojoBean) {
        pojoBeanThreadLocal.set(pojoBean);
    }

    public static PojoBean getPojoBean() {
        return pojoBeanThreadLocal.get();
    }

    public static void setWorkType(String workType) {
        workTypeThreadLocal.set(workType);
    }

    public static String getWorkType() {
        return workTypeThreadLocal.get();
    }
}
