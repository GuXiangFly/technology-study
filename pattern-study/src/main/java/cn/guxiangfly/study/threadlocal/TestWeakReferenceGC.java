package cn.guxiangfly.study.threadlocal;

import java.lang.ref.WeakReference;

public class TestWeakReferenceGC {

    public static void main(String[] args) {

        PojoBean pojoBean = new PojoBean();
        pojoBean.setContent("helloPojo");
        pojoBean.setFlowType("offline");

        WeakReference<PojoBean> pojoBeanWeakReference = new WeakReference<>(pojoBean);

        pojoBean = null;

        System.out.println(pojoBeanWeakReference.get());

        System.gc();

        System.out.println(pojoBeanWeakReference.get());
    }
}
