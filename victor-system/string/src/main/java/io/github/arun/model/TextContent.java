package io.github.arun.model;

import lombok.Data;

@Data
public class TextContent {

    /**
     * 所有文本内容
     */
    private String textAll;

    /**
     * 原文本
     */
    private String text1;

    /**
     * 修改后的文本
     */
    private String text2;

}
