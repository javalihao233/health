package cn.lihao.test.sort;

import java.util.Comparator;

/**
 * 自定义排序————Comparator接口   该接口有多个实现方法，其中compare用于实现自定义排序
 *
 * 需求：想根据字符串长度来进行排序
 */
public class StringComparator implements Comparator {

    /**
     * 返回结果和CompareTo一样  负数  0  正数
     * @param o1
     * @param o2
     * @return
     */
    @Override
    public int compare(Object o1, Object o2) {
        String s1 = (String) o1;
        String s2 = (String) o2;
        int result = s1.length()-s2.length();
        return result;
    }
}



