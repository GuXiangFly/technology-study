package cn.guxiangfly.core.framework.application

import cn.guxiangfly.core.framework.common.TApplication
import cn.guxiangfly.core.framework.controller.WordCountController


object WordCountApplication extends App with TApplication{

    // 启动应用程序
    start(){
        val controller = new WordCountController()
        controller.dispatch()
    }

}
