package cn.guxiangfly.study.starter;

import cn.guxiangfly.study.starter.register.DefaultImportBeanDefinitionRegistrar;
import cn.guxiangfly.study.starter.selector.DefaultImportSelector;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

@Configuration
//@EnableConfigurationProperties(Dept.class)
//@Import(Dept.class)
//@Import({DefaultImportSelector.class})
@Import({DefaultImportBeanDefinitionRegistrar.class})
public class GuxiangflyConfiguration {

    @Bean(name = "books")
    public List<String> getBookList() {
        ArrayList<String> bookList = new ArrayList<>();
        bookList.add("西游记");
        bookList.add("红楼梦");
        bookList.add("三国演义");
        bookList.add("水浒传");
        return bookList;
    }
}
