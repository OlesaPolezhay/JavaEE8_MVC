package DND.CharacterCreator.Race;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import DND.CharacterCreator.Stats;
import DND.DataVisitor.DataElement;
import DND.DataVisitor.DataElementsVisitor;
import org.json.simple.JSONObject;

public abstract class CharacterRace implements DataElement {
    protected String name;
    protected Stats bonuses;

    public Stats getRaceBonuses(){
        return bonuses;
    }

    public abstract void shout();

    public String toString(){
        return name;
    }
    public void print(){
        System.out.println("Race: "+name);
        Set<String> key=bonuses.getAttrib().keySet();
        for(String title:key){
            if(bonuses.getAttrib().get(title)!=0){
                System.out.println("Race bonus: "+title+" +"+bonuses.getAttrib().get(title));
            }
        }
    }

    @Override
    public TreeMap access(DataElementsVisitor vis) {
        return vis.visit(this);
    }
}
