package cn.guxiangfly.ai.langchain4j;

import cn.guxiangfly.assistant.Assistant;
import cn.guxiangfly.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AIServiceTest {

    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testChat() {
//创建AIService
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
//调用service的接口
        String answer = assistant.chat("Hello");
        System.out.println(answer);
    }


    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testChatMemory5() {
        String answer1 = separateChatAssistant.chat(1, "我是环环");
        System.out.println(answer1);
        String answer2 = separateChatAssistant.chat(1, "我是谁");
        System.out.println(answer2);
        String answer3 = separateChatAssistant.chat(2, "我是谁");
        System.out.println(answer3);
    }
}