package GUI;

import DND.CharacterCreator.Character;
import DND.CharacterCreator.Class.Factory.ClassFactory;
import DND.CharacterCreator.Race.Dwarf.DwarfFactory;
import DND.CharacterCreator.Race.RaceAbstractFactory;
import DND.CharacterCreator.Race.RaceFactory;
import DND.CharacterCreator.MementoCareTaker.CareTaker;
import DND.CharacterCreator.Stats;
import DND.DataVisitor.DataElement;
import DND.DataVisitor.ElementVisitor;
import java.awt.Component;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;

public class Controller {
    private static Model mode=Model.getInst();
    private static int it=0;
    private static String[] listData=new String[5];//only 5 characters alowed

    public static void generateStats(){
        mode.setStat(Stats.generate());
        DNDFrame.jTextField2.setText(mode.getStat().getAttrib().get("Strength").toString());
        DNDFrame.jTextField3.setText(mode.getStat().getAttrib().get("Dexterity").toString());
        DNDFrame.jTextField4.setText(mode.getStat().getAttrib().get("Constitution").toString());
        DNDFrame.jTextField5.setText(mode.getStat().getAttrib().get("Intelligence").toString());
        DNDFrame.jTextField6.setText(mode.getStat().getAttrib().get("Wisdom").toString());
        DNDFrame.jTextField7.setText(mode.getStat().getAttrib().get("Charisma").toString());
    }

    public static void runMemento(){
        if(!mode.getStat().equals(null)){
            mode.setCaretaker(new CareTaker());
            mode.getCaretaker().add(mode.getStat().save());
            DNDFrame.jTextArea1.setText("Attributes saved");
        }
    }

    public static void returnMemento(){
        try{
            mode.getStat().undoToLastSave(mode.getCaretaker().getLast());
            DNDFrame.jTextField2.setText(mode.getStat().getAttrib().get("Strength").toString());
            DNDFrame.jTextField3.setText(mode.getStat().getAttrib().get("Dexterity").toString());
            DNDFrame.jTextField4.setText(mode.getStat().getAttrib().get("Constitution").toString());
            DNDFrame.jTextField5.setText(mode.getStat().getAttrib().get("Intelligence").toString());
            DNDFrame.jTextField6.setText(mode.getStat().getAttrib().get("Wisdom").toString());
            DNDFrame.jTextField7.setText(mode.getStat().getAttrib().get("Charisma").toString());
        }
        catch(NullPointerException ex){DNDFrame.jTextArea1.setText("No saved attributes found");
        }
    }

    public static void createCharacter(){
        if(mode.getStat()!=null){
            if(it<5) {
                String name=DNDFrame.jTextField1.getText().toString();
                String cla=DNDFrame.jComboBox2.getSelectedItem().toString();
                String race=DNDFrame.jComboBox1.getSelectedItem().toString();
                System.out.println(name+" "+cla+" "+race);
                mode.setCha(name,cla,race);
                mode.getCha().setAtributes(mode.getStat());
                mode.getCha().addRaceBonuses();
                mode.addItem(mode.getCha());
                System.out.println(mode.getCha().getDndclass().toString());
                mode.getVisitor().visit(mode.getCha());
                DNDFrame.jTextArea1.setText("Character "+name+" created!");
                listData[it]=race+"-"+cla+" "+name;
                DNDFrame.jList1.setListData(listData);
                it++;
            }
            else{    DNDFrame.jTextArea1.setText("Only 5 characters allowed");}
        }else{    DNDFrame.jTextArea1.setText("Generate attributes before creating character");}}

    public static void saveJSON() throws IOException{
        String str="[\n";

        for(Character ch:mode.getItems()){
            mode.getJsonObject().clear();
            List<DataElement> list = new ArrayList<>();
            list.add(ch);
            list.add(ch.getDndclass());
            list.add(ch.getRace());
            list.add(ch.getAtributes());


            for(DataElement elem:list){
                mode.setJsonObject(elem.access(mode.getVisitor()));
            }
            str+=mode.getJsonObject().toJSONString();
            str+="\n";

            if(mode.getItems().indexOf(ch)!=mode.getItems().size()-1){str+=",";}
        }

        str+="\n]";
        DNDFrame.jTextPane1.setText(str);
        FileWriter file = new FileWriter("output.json");
        file.write(str);
        file.close();
    }

    static void printSelected() {
        DNDFrame.jTextArea1.setText("");
        mode.getJsonObject().clear();
        List<DataElement> list = new ArrayList<>();
        list.add(mode.getCha());
        list.add(mode.getCha().getDndclass());
        list.add(mode.getCha().getRace());
        list.add(mode.getCha().getAtributes());

        String str="";
        for(DataElement elem:list){
            mode.setJsonObject(elem.access(mode.getVisitor()));
        }
        str=mode.getJsonObject().toJSONString();

        DNDFrame.jTextArea1.setText(str);

    }
}