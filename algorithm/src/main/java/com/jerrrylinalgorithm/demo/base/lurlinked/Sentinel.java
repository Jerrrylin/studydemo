package main.java.com.jerrrylinalgorithm.demo.base.lurlinked;

/**
 * 哨兵节点简化
 * @author
 */
public class Sentinel {
    /**
     * 在数组a中，查找key，返回key所在位置
     * @return n代表数据a的长度
     */
    int find(char[] a,int n ,char key){
        if(a==null||n<=0){
            return -1;
        }
        int i = 0;
        while(i<n){
            if(a[i]==key){
                return i;
            }
            ++i;
        }
        return -1;
    }

    /**
     *在数组a中，查找key,返回key所在位置
     *
     */
    int findBySentinel(char[]a,int n,char key){
        if(a==null||n<=0){
            return -1;
        }
        //这里因为要将a[n-1]的值替换成key,所以要特殊处理这个值
        if(a[n-1]==key){
            return n-1;
        }

        //把a[n-1]的值临时保存在变量tmp,以方便回复
        //之所以这样做是为了：希望find()代码不要改变a数组中的内容
        char tmp =a[n-1];
        //把key的值放到a[n-1]中，此时
        a[n-1] = key;

        int i=0;
        //
        while(a[i]!=key){
            ++i;
        }

        a[n-1] = tmp;
        if(i==n-1){
            return -1;
        }else{
            return i;
        }

    }

}
