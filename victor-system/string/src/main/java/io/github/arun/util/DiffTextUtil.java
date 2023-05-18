package io.github.arun.util;

import fun.mike.dmp.Diff;
import fun.mike.dmp.DiffMatchPatch;
import io.github.arun.model.DiffText;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DiffTextUtil {

    /**
     * 找出两个文本不同的地方
     * @param text1 原文
     * @param text2 修改后的文本
     * @return 修改前和修改后文本区别的集合
     * @author daixu
     * @date 2023-03-29 09:11
     */
    public static List<DiffText> textComparisonDiffText(String text1, String text2) {
        List<DiffText> diffTextLinkedList = new ArrayList<>();
        // 解析文本区别
        DiffMatchPatch diffMatch = new DiffMatchPatch();
        List<Diff> diffList = diffMatch.diff_main(text1,text2,false);
        if (diffList!=null && diffList.size()>0) {
            for (int i = 0; i < diffList.size(); i++) {
                // 从第一个字符串开始就没有相等部分
                if(!"EQUAL".equals(diffList.get(i).operation.name()) && i == 0) {
                    DiffText diffText = new DiffText();
                    for (int j = 0; j < diffList.size(); j++) {
                        // 原文和修改后的文本有区别的地方
                        if ("DELETE".equals(diffList.get(j).operation.name())) {
                            diffText.setDiffText1(diffList.get(j).text);
                        }
                        // 修改后的文本和原文有区别的地方
                        if ("INSERT".equals(diffList.get(j).operation.name())) {
                            diffText.setDiffText2(diffList.get(j).text);
                        }
                        // 相等的地方为下一次的开始，进入下一次循环
                        if ("EQUAL".equals(diffList.get(j).operation.name()) || j == diffList.size()-1) {
                            diffText.setEqualText("");
                            diffTextLinkedList.add(diffText);
                            break;
                        }
                    }
                }

                // 相等部分
                if ("EQUAL".equals(diffList.get(i).operation.name())) {
                    DiffText diffText = new DiffText();
                    // 设置相等部分文本
                    diffText.setEqualText(diffList.get(i).text);
                    // 找出相等部分之后的文本有区别的内容
                    for (int j = i+1; j < diffList.size(); j++) {
                        // 原文和修改后的文本有区别的地方
                        if ("DELETE".equals(diffList.get(j).operation.name())) {
                            diffText.setDiffText1(diffList.get(j).text);
                        }
                        // 修改后的文本和原文有区别的地方
                        if ("INSERT".equals(diffList.get(j).operation.name())) {
                            diffText.setDiffText2(diffList.get(j).text);
                        }
                        // 相等的地方为下一次的开始，进入下一次循环
                        if ("EQUAL".equals(diffList.get(j).operation.name()) || j == diffList.size()-1) {
                            diffTextLinkedList.add(diffText);
                            break;
                        }
                    }
                }
            }
        }
        return diffTextLinkedList;
    }

    /**
     * 设置区别文本的字符串下标
     * @param diffTextList 修改前和修改后文本区别的集合
     * @param text1 原文本
     * @param text2 修改后的文本
     * @author daixu
     * @date 2023-03-29 09:16
     */
    public static void setTextComparisonDiffTextIndex(List<DiffText> diffTextList, String text1, String text2) {

        // 文本 1 和 文本 2 顺序往下查询的下标
        int index1 = 0;
        int index2 = 0;
        // 相等部分的字符串内容
        String equalText;
        // 相等部分的字符串长度
        int equalTextLength;
        // 原文与修改后的文本不同地方的字符串长度
        int diffText1Length;
        // 修改后的文本与原文不同地方的字符串长度
        int diffText2Length;

        if (diffTextList!=null && diffTextList.size()>0 && !StringUtils.isEmpty(text1) && !StringUtils.isEmpty(text2)) {
            for (DiffText item : diffTextList) {
                // 临时存储下标
                int indexTime1 = index1;
                int indexTime2 = index2;
                // 获取相等部分的字符串
                equalText = item.getEqualText();
                // 获取相等部分的字符串长度
                equalTextLength = equalText.length();
                // 初始化原文与修改后字符串的长度为 0
                diffText1Length = diffText2Length = 0;

                // 原文存在与修改后的文案不同的内容
                if (!StringUtils.isEmpty(item.getDiffText1())) {
                    diffText1Length = item.getDiffText1().length();
                }

                // 修改后的文本存在与原文不同的内容
                if (!StringUtils.isEmpty(item.getDiffText2())) {
                    diffText2Length = item.getDiffText2().length();
                }

                // 获取原文相等字符串的起始位置
                index1 = text1.indexOf(equalText,index1);
                // 查询不到返回 -1
                if (index1 < 0) {
                    index1 = indexTime1;
                } else {
                    // 获取原文与修改文案不同地方的位置：起始位置加上相等字符串的长度
                    index1+= equalTextLength;
                    // 原文与修改后的文案不同地方的长度大于 0 ，说明原文与修改后的文本存在不同的字符串
                    if (diffText1Length>0) {
                        // 设置原文与修改后文本不同地方的字符串下标 +1
                        item.setDiffIndex1(index1);
                    } else {
                        // 原文没有找到与修改后文本不同的地方，默认为 -1
                        item.setDiffIndex1(-1);
                    }
                    // 记录原文当前查询为止的下标，下次循环从这个位置往后查询
                    index1+= diffText1Length;
                }
                item.setDiffLength1(diffText1Length);

                // 获取修改后的文本相等字符串的起始位置
                index2 = text2.indexOf(equalText,index2);
                // 查询不到返回 -1
                if (index2 < 0 && diffText2Length>0) {
                    index2 = indexTime2;
                } else {
                    // 获取修改文案与原文不同地方的位置：起始位置加上相等字符串的长度
                    index2+= equalTextLength;
                    // 修改后的文案与原文不同地方的长度大于 0 ，说明原文与修改后的文本存在不同的字符串
                    if (diffText2Length>0) {
                        // 设置修改后文本与原文不同地方的字符串下标 +1
                        item.setDiffIndex2(index2);
                    } else {
                        // 修改后文本没有找到与原文不同的地方，默认为 -1
                        item.setDiffIndex2(-1);
                    }
                    // 记录修改后文本当前查询为止的下标，下次循环从这个位置往后查询
                    index2+= diffText2Length;
                }
                item.setDiffLength2(diffText2Length);
            }
        }
    }

}
