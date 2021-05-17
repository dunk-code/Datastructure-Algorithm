package school.xauat.datastructres.disjointset;


import java.util.*;

public class review {
    public static void main(String[] args) {
        Solution s = new Solution();
        /*
        names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"],
        synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
         */
        String[] names = {"John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"};
        String[] synonyms = {"(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"};
        String[] ss = s.trulyMostPopular(names, synonyms);
        System.out.println(Arrays.toString(ss));
    }

    static class Solution {
        Map<String, String> f = new HashMap<>();
        public String[] trulyMostPopular(String[] names, String[] synonyms) {
            //创建边之间的关系
            for(String cur : names) {
                String name = getName(cur);
                f.put(name, name);
            }
            //将同义词插入map中
            for(String synonym : synonyms) {
                int index = synonym.indexOf(",");
                String x = synonym.substring(1, index);
                String y = synonym.substring(index + 1, synonym.length() - 1);
                if(!f.containsKey(x)) {
                    f.put(x, x);
                }
                if(!f.containsKey(y)) {
                    f.put(y, y);
                }
            }
            //合并同义词
            for(String synonym : synonyms) {
                int index = synonym.indexOf(",");
                String x = synonym.substring(1, index);
                String y = synonym.substring(index + 1, synonym.length() - 1);
                union_vertices(x, y);
            }
            //构建连通图
            Map<String, Integer> map = new HashMap<>();
            for(String cur : names) {
                int num = getNum(cur);
                String name = getName(cur);
                map.put(find_root(name),
                        map.getOrDefault(find_root(name), 0) + num);
            }
            //遍历输出答案
            List<String> list = new ArrayList<>();
            for(String key : map.keySet()) {
                String name = key;
                int nums = map.get(key);
                StringBuilder builder = new StringBuilder();
                builder.append(name);
                builder.append("(");
                builder.append(nums);
                builder.append(")");
                list.add(builder.toString());
            }
            Collections.reverse(list);
            return list.toArray(new String[list.size()]);
        }

        public String getName(String s) {
            int index = s.indexOf("(");
            return s.substring(0, index);
        }

        public int getNum(String s) {
            int index1 = s.indexOf("(");
            int index2 = s.indexOf(")");
            return Integer.parseInt(s.substring(index1 + 1, index2));
        }

        public String find_root(String x) {
            if(f.get(x) != x) {
                return f.get(x);
            }
            return x;
        }

        public void union_vertices(String x, String y) {
            String x_root = find_root(x);
            String y_root = find_root(y);
            if(!x_root.equals(y_root)) {
                if(x_root.compareTo(y_root) > 0) {
                    f.put(x_root, y_root);
                } else {
                     f.put(y_root, x_root);
                }
            }
        }

    }
}
