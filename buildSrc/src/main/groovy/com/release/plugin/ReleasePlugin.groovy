package com.release.plugin

import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 定义插件
 * @author yancheng
 * @since 2022/1/24
 */
class ReleasePlugin implements Plugin<Project> {
    @Override
    void apply(Project target) {
        //创建扩展的对象, 给扩展起别名为release
        def extention = target.extensions.create('release',ReleaseExtension)
        //在整个文件执行后,再执行
        target.afterEvaluate{
            println "Hello ${extention.name} !!!"
        }

//        def transform = new ReleaseTransfom()
//        def baseExtension = target.extensions.getByType(BaseExtension)
//        baseExtension.registerTransform(transform)
    }
}