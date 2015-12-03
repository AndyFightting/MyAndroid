package com.suguiming.myandroid.tool.HanZi2PinYin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

//  jar包官网：   http://pinyin4j.sourceforge.net/
public class PinYinToolkit {
    /**
     * 汉字转换汉语拼音首字母，英文字符不变
     * 例如:北京--> bj
     * @param chinese 汉字
     * @return String
     */
    public static String getFirstLetter(String chinese){
        StringBuilder pinyin = new StringBuilder();
        char[] nameChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);    //设置拼音大小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); //不要声调
        for(int i = 0; i < nameChar.length;i++){
            if(nameChar[i] > 128){
                try {
                    String[] py = PinyinHelper.toHanyuPinyinStringArray(nameChar[i],defaultFormat);//多音字数组
                    if(py != null){
                        pinyin.append(py[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyin.append(nameChar[i]);
            }
        }
        return pinyin.toString();
    }

    /**
     * 汉字转换汉语拼音，英文字符不变
     * 例如:北京--> beijing
     * @param chinese 汉字
     * @return String
     */
    public static String getFullLetter(String chinese){
        StringBuilder pinyin = new StringBuilder();
        char[] nameChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for(int i = 0; i < nameChar.length;i++){
            if(nameChar[i] > 128){
                try {
                    String[] py = PinyinHelper.toHanyuPinyinStringArray(nameChar[i],defaultFormat);
                    if(py != null){
                        pinyin.append(py[0]);
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyin.append(nameChar[i]);
            }
        }
        return pinyin.toString();
    }

    /**
     * 拼音转换汉语拼音，拼音首字母大写，英文字符不变
     * 例如:北京--> BeiJing
     * @param chinese 汉字
     * @return String
     */
    public static String getFirstUpFullLetter(String chinese){
        StringBuilder pinyin = new StringBuilder();
        char[] nameChar = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for(int i = 0; i < nameChar.length;i++){
            if(nameChar[i] > 128){
                try {
                    String[] py = PinyinHelper.toHanyuPinyinStringArray(nameChar[i],defaultFormat);
                    if(py != null){
                        pinyin.append(py[0].substring(0, 1).toUpperCase() + py[0].substring(1));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyin.append(nameChar[i]);
            }
        }
        return pinyin.toString();
    }
}