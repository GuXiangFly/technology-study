package cn.guxiangfly.study.threadlocal;

public class TrafficCenterThreadLocal {


    public  static  final ThreadLocal<String> workTypeThreadLocal = new ThreadLocal<String>();
    public  static  final ThreadLocal<PojoBean> pojoBeanThreadLocal = new ThreadLocal<PojoBean>();

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
