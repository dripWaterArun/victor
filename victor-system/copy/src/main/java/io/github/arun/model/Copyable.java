package io.github.arun.model;

import com.core.util.clone.CopyPros;
import io.github.arun.util.copy.CopyUtils;

/**
 * 对象拷贝
 * @author daixu
 * @dete 2022-09-15 22:55
 */
public interface Copyable<T> {
    @SuppressWarnings("unchecked")
    default T copy(Object obj) {
        return (T) CopyUtils.copyProperties(obj,this.getClass());
    }
}
