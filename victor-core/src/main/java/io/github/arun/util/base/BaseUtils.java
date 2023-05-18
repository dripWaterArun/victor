package io.github.arun.util.base;

import io.github.arun.util.copy.CopyUtils;

/**
 * 基础工具类
 * @author Arun
 * @date 2023-05-18 14:59
 */
public class BaseUtils {

    /**
     * 字节码转换对象属性，速度比反射快（创建新对象）
     * @param source 源对象
     * @return 拷贝后的新对象
     * @author daiux
     * @date 2023-05-18 15:10
     */
    public static<T> T copyProperties(Object source, Class<T> clazz) {
        return CopyUtils.copyProperties(source, clazz);
    }

    /**
     * 已有对象拷贝
     * @param source 原对象
     * @param targets 目标对象
     * @param <T> 泛型
     * @return 保留目标对象原有属性，并把源对象的属性拷贝过来
     * @author daixu
     * @date 2023-05-18 15:12
     */
    public static<T> T copyProperties(Object source, T targets) {
        return CopyUtils.copyProperties(source, targets);
    }
}
