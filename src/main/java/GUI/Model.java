package GUI;
//part of model, the whole model is all previous project

import DND.CharacterCreator.Class.Factory.ClassFactory;
import DND.CharacterCreator.Character;
import DND.CharacterCreator.Race.Dwarf.DwarfFactory;
import DND.CharacterCreator.Race.Human.HumenFactory;
import DND.CharacterCreator.Race.RaceAbstractFactory;
import DND.CharacterCreator.Race.RaceFactory;
import DND.CharacterCreator.MementoCareTaker.CareTaker;
import DND.CharacterCreator.Stats;
import DND.DataVisitor.ElementVisitor;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Model {

    public static  Model getInst() {
        return inst;
    }
    private static Model inst=new Model();
    private  List<Character> items=new ArrayList<Character>();
    private  Stats stat;
    private  CareTaker caretaker;
    private  Character cha=null;
    private  ElementVisitor visitor=new ElementVisitor();
    private  JSONObject jsonObject = new JSONObject();
    private  JSONArray jarray=new JSONArray();

    public List<Character> getItems() {
        return items;
    }
    public JSONArray getJarray() {
        return jarray;
    }

    public void add2Jarray(JSONObject job) {
        this.jarray.add(job);
    }


    public  Stats getStat() {
        return stat;
    }

    public  void setStat(Stats stat) {
        this.stat = stat;
    }

    public  CareTaker getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(CareTaker caretaker) {
        this.caretaker = caretaker;
    }

    public  Character getCha() {
        return cha;
    }

    public ElementVisitor getVisitor() {
        return visitor;
    }

    public  void setVisitor(ElementVisitor visitor) {
        this.visitor = visitor;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(TreeMap tm) {
        this.jsonObject.putAll(tm);

    }

    void setCha(String name, String cla, String race) {
        RaceAbstractFactory factory = null;
        if(race.contains("Human")){factory=new HumenFactory();}
        else if(race.contains("Dwarf")){factory=new DwarfFactory();}
        this.cha=new Character(name,
                ClassFactory.getClass(cla),
                RaceFactory.getRace(factory, race));
    }

    public Character getItem(int i) {
        return items.get(i);
    }

    public void addItem(Character it) {
        this.items.add(it);
    }
    public void deleteItem(int it) {
        this.items.remove(it);
    }

}

