package com.swimtothend.base.demo;

import java.text.MessageFormat;

/**
 * create by BloodFly at 2019/2/26
 */
public class MessageFormatDemo {
    public static void main(String[] args) {
        MessageFormat messageFormat = new MessageFormat("#'{data[{0}].{1}}");
        String msg = "{0}{1}{2}{3}{4}{5}{6}{7}{8}{9}";
        Object [] array = new Object[]{"A","B","C","D","E","F","G","H","I",};
        String value = MessageFormat.format(msg, array);
        System.out.println(value);  // 输出：ABCDEFGHI

        value = MessageFormat.format("oh, {0} is 'a' pig", "ZhangSan");
        System.out.println(value);  // 输出：oh, ZhangSan is a pig

        System.out.println(MessageFormat.format("{0}{1}", 1, 2)); // 结果12
        System.out.println(MessageFormat.format("'{0}{1}", 1, 2));// 结果{0}{1}
        System.out.println(MessageFormat.format("'{'0}{1}", 1, 2));// 结果{0}{1}


        System.out.println(MessageFormat.format("{0,number,#}", 1000));
        System.out.println(MessageFormat.format("{0,number,integer}", 1000));
        System.out.println(MessageFormat.format("{0,number,integer}", 0)

        );



    }
}
