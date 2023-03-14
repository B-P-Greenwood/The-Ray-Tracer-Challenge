package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Intersections {
    private final ArrayList<Intersect> intersections = new ArrayList<>();

    public Intersections(Intersect ...intersects){
        for (Intersect i : intersects){
            intersections.add(i);
        }

        Collections.sort(intersections, new Comparator<Intersect>() {
            
            @Override
            public int compare(Intersect i1, Intersect i2) {
                if(i1.getValue() == i2.getValue()){
                    return 0;
                }else if(i1.getValue() > i2.getValue()){
                   return 1;
                }else{
                    return -1;
                }
            }      
        });
    }

    public Intersect getItem(int key){
        return intersections.get(key);
    }

    public int getCount(){ return intersections.size(); }

    public Intersect hit(){
        for (Intersect i : intersections){
            if(i.getValue() > 0) return i;
        }
        return null;
    }

}
