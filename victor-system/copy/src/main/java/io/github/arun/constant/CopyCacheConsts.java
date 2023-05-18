package io.github.arun.constant;

import org.springframework.cglib.beans.BeanCopier;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 对象拷贝缓存常量
 * @author Arun
 * @date 2023-05-18 14:01
 */
public class CopyCacheConsts {

    /**
     * BeanCopier的缓存
     */
    public static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>();

}
