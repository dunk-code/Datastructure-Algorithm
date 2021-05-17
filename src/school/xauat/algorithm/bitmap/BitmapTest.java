package school.xauat.algorithm.bitmap;

/**
 * @author ：zsy
 * @date ：Created 2021/5/17 8:56
 * @description：Bitmap算法
 */
public class BitmapTest {
    public static void main(String[] args) {
        BitMap bitMap = new BitMap(128);
        bitMap.setBit(126);
        System.out.println(bitMap.getBit(16));
        System.out.println(bitMap.getBit(126));
    }
}

class BitMap {
    //每一个word是一个long类型的元素，对应一个64位二进制数据
    private long[] words;
    private int size;

    private int getWordIndex(int i) {
        return i >> 6;
    }

    public BitMap(int size) {
        this.size = size;
        this.words = new long[getWordIndex(size - 1) + 1];
    }

    public boolean getBit(int bitIndex) {
        if(bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超过Bitmap的有效范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        return (words[wordIndex] & (1L << bitIndex)) != 0;
    }

    public void setBit(int bitIndex) {
        if(bitIndex < 0 || bitIndex > size - 1) {
            throw new IndexOutOfBoundsException("超过Bitmap的有效范围");
        }
        int wordIndex = getWordIndex(bitIndex);
        words[wordIndex] |= (1L << bitIndex);
    }
}
