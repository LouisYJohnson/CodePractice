package exams.yongyou;

import java.util.Iterator;
import java.util.Map;

public class test1 {
    public void scan(Map<String, String> map){
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() + " " + next.getValue());
        }
    }


}
