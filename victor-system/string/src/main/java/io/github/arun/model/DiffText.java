package io.github.arun.model;

import lombok.Data;

/**
 * 不同的文本内容实体类
 * @author daixu
 * @date 2023-03-28 08:54
 */
@Data
public class DiffText {

    /**
     * 相等的文本内容
     */
    private String equalText;

    /**
     * 第一段中不相等的部分
     */
    private String diffText1;

    /**
     * 第一段中不相等的内容的下标
     */
    private Integer diffIndex1;

    /**
     * 第一段中不相等的内容的字符长度
     */
    private Integer diffLength1;

    /**
     * 第二段中不相等的部分
     */
    private String diffText2;

    /**
     * 第二段中不相等的内容的下标
     */
    private Integer diffIndex2;

    /**
     * 第二段中不相等的内容的字符长度
     */
    private Integer diffLength2;
}
