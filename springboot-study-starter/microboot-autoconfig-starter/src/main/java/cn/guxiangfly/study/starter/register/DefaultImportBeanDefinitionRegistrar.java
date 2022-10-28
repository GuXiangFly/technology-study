package cn.guxiangfly.study.starter.register;

import cn.guxiangfly.study.starter.Dept;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class DefaultImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        RootBeanDefinition deptDefinition = new RootBeanDefinition(Dept.class);
        beanDefinitionRegistry.registerBeanDefinition("deptInstance",deptDefinition);
    }
}
