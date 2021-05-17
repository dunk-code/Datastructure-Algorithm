package school.xauat.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建一个HashMap存放电台和电台能够覆盖的城市
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        HashSet k1 = new HashSet();
        HashSet k2 = new HashSet();
        HashSet k3 = new HashSet();
        HashSet k4 = new HashSet();
        HashSet k5 = new HashSet();
        k1.add("北京");
        k1.add("上海");
        k1.add("天津");
        k2.add("广州");
        k2.add("北京");
        k2.add("深圳");
        k3.add("成都");
        k3.add("上海");
        k3.add("杭州");
        k4.add("上海");
        k4.add("天津");
        k5.add("杭州");
        k5.add("大连");
        broadcasts.put("k1",k1);
        broadcasts.put("k2",k2);
        broadcasts.put("k3",k3);
        broadcasts.put("k4",k4);
        broadcasts.put("k5",k5);
        //创建一个ArrayList存放选择的电台
        ArrayList<String>selects = new ArrayList<>();
        //创建一个HashSet存放需要覆盖的所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        //创建一个maxKey，保存每一次遍历时最大未覆盖地区对应的电台key
        String maxKey = null;
        //创建一个临时HashSet存放临时的数据
        HashSet<String> tempSet = new HashSet<>();
        while(!allAreas.isEmpty()){
            maxKey = null;
            for(String key : broadcasts.keySet()){
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                tempSet.retainAll(allAreas);
                if(tempSet.size() > 0 && (maxKey == null
                        || tempSet.size() > broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            if(maxKey != null){
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println(selects);
    }
}
