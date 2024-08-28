package cn.guxiangfly.distributelock.springbootstudy.threadlocal;

public class PojoBean {

    String content;

    String flowType;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    @Override
    public String toString() {
        return "PojoBean{" +
                "content='" + content + '\'' +
                ", flowType='" + flowType + '\'' +
                '}';
    }
}
