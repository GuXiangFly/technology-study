package cn.guxiangfly.example5;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

/**
 * @Author guxiang02
 * @Date 2019/12/31 10:55
 **/
public class CallMethodUsingBeanComponent {
    public static void main(String[] args) throws Exception{
        MyService myService = new MyService();
        SimpleRegistry simpleRegistry = new SimpleRegistry();
        simpleRegistry.put("myService",myService);
        DefaultCamelContext camelContext = new DefaultCamelContext(simpleRegistry);
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("bean:myService?method=doSomething");
            }
        });
        camelContext.start();
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start", "Hello EveryOne");

    }
}
