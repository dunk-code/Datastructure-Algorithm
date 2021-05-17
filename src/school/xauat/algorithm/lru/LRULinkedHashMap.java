package school.xauat.algorithm.lru;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ：zsy
 * @date ：Created 2021/5/14 11:23
 * @description：使用LinkedHashMap构建LRU页面置换算法
 */
public class LRULinkedHashMap {

    public static void main(String[] args) {
        LRUCache<Integer, Integer> lruCache = new LRUCache<>(3);
        lruCache.put(1,2);
        lruCache.put(2,2);
        lruCache.put(3,2);
        lruCache.get(2);
        Iterator<Map.Entry<Integer, Integer>> it = lruCache.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry entry = it.next();
            System.out.println(entry.getKey() + "   " + entry.getValue());
        }
        lruCache.put(4,3);
        lruCache.get(2);
        lruCache.put(4,5);
        it = lruCache.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry entry = it.next();
            System.out.println(entry.getKey() + "   " + entry.getValue());
        }
    }

    static class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int CACHE_SIZE;

        public LRUCache(int cacheSize) {
            //true表示让hashmap按照访问顺序，最新访问的放在头部，最老访问的放在尾部
            super((int)Math.ceil(cacheSize / 0.75f) + 1, 0.75f, true);
            this.CACHE_SIZE = cacheSize;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            //当map中数量大于指定缓存数量，就删除最老的数据
            return size() > CACHE_SIZE;
        }
    }

}

