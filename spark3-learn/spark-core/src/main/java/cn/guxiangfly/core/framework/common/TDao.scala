package cn.guxiangfly.core.framework.common

import cn.guxiangfly.core.framework.util.EnvUtil

trait TDao {

    def readFile(path:String) = {
        EnvUtil.take().textFile(path)
    }
}
