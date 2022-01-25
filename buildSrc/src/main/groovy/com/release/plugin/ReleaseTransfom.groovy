package com.release.plugin

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager

/**
 * 是由 Android 提供了，在项⽬构建过程中把编译后的⽂件（jar ⽂件和 class ⽂件）添加⾃定义的中间处理过程的⼯具
 * @author yancheng* @since 2022/1/24
 */
class ReleaseTransfom extends Transform {

    /**
     * 对应的 task 名
     * @return
     */
    @Override
    String getName() {
        return 'cyc'
    }

    /**
     * 你要对那些类型的结果进⾏转换(是字节码还是资源⽂件？)
     * @return
     */
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        //处理class文件和jar文件
        return TransformManager.CONTENT_CLASS
    }

    /**
     * 适⽤范围包括什么(整个 project 还是其它)
     * @return
     */
    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        //处理哪些项目
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false
    }

    /**
     * 具体的「转换」过程
     * @param transformInvocation
     * @throws TransformException* @throws InterruptedException* @throws IOException
     */
    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        def inputs = transformInvocation.inputs
        def outputProvider = transformInvocation.outputProvider
        inputs.each {
            // jarInputs：各个依赖所编译成的 jar ⽂件
            it.jarInputs.each {
                // dest:
                //./app/build/intermediates/transforms/hencoderTransform/...
                File dest = outputProvider.getContentLocation(it.name, it.contentTypes, it.scopes, Format.JAR)
                FileUtils.copyFile(it.file, dest)
            }
            // derectoryInputs：本地 project 编译成的多个class ⽂件存放的⽬录
            it.directoryInputs.each {
                // dest:
                //./app/build/intermediates/transforms/hencoderTransform/...
                File dest = outputProvider.getContentLocation(it.name, it.contentTypes, it.scopes, Format.DIRECTORY)
                FileUtils.copyDirectory(it.file, dest)
            }
        }

        //修改字节码 上⾯的这段代码只是把编译完的内容原封不动搬
        //运到⽬标位置，没有实际⽤处。要修改字节码，需要引⼊其他⼯具，例如 javassist、ASM
    }
}


