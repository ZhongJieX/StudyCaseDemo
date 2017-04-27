package com.example;

import com.google.auto.service.AutoService;
import com.sun.java.browser.net.ProxyInfo;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * @author xzj
 *         Created at 2017/4/26 17:56
 */
@AutoService(Processor.class)
public class ViewFinderProcessor extends AbstractProcessor {
    private Filer mFileUtils;            // 文件相关的辅助类
    private Elements mElementUtils;      // 元素相关的辅助类
    private Messager mMessager;          // 日志相关的辅助类

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFileUtils = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
    }

    /**
     * @return 指定哪些注解应该被注解处理器注册
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationTypes = new LinkedHashSet<>();
        annotationTypes.add(BindView.class.getCanonicalName());
        annotationTypes.add(OnClick.class.getCanonicalName());
        return annotationTypes;
    }

    /**
     * @return 指定使用的 Java 版本。通常返回 SourceVersion.latestSupported()。
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private Map<String, ProxyInfo> mProxyMap = new HashMap<String, ProxyInfo>();

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mProxyMap.clear();
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindView.class);

        return false;
    }

    private void processBindView(RoundEnvironment roundEnv) throws IllegalArgumentException {

    }
        /*Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(BindView.class);
        //一、收集信息
        for (Element element : elements) {
            //检查element类型
            if (!checkAnnotationUseValid(element)) {
                return false;
            }
            //field type
            VariableElement variableElement = (VariableElement) element;
            //class type
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();//TypeElement
            String qualifiedName = typeElement.getQualifiedName().toString();

            ProxyInfo proxyInfo = mProxyMap.get(qualifiedName);
            if (proxyInfo == null) {
                proxyInfo = new ProxyInfo(mElementUtils, typeElement);
                mProxyMap.put(qualifiedName, proxyInfo);
            }
            BindView annotation = variableElement.getAnnotation(BindView.class);
            int id = annotation.value();
            proxyInfo.mInjectElements.put(id, variableElement);
        }
    }

    private boolean checkAnnotationValid(Element annotatedElement, Class clazz) {
        if (annotatedElement.getKind() != ElementKind.FIELD) {
            error(annotatedElement, "%s must be declared on field.", clazz.getSimpleName());
            return false;
        }
        if (ClassValidator.isPrivate(annotatedElement)) {
            error(annotatedElement, "%s() must can not be private.", annotatedElement.getSimpleName());
            return false;
        }
        return true;
    }*/

    private void error(Element element, String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message, element);
    }
}
