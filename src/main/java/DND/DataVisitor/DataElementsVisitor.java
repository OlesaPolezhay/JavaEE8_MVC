package DND.DataVisitor;

import DND.CharacterCreator.Class.Classes.CharacterClass;
import DND.CharacterCreator.Race.CharacterRace;
import DND.CharacterCreator.Stats;
import DND.CharacterCreator.Character;

import java.util.TreeMap;

public interface DataElementsVisitor {
    public TreeMap visit(Character ch);
    public TreeMap visit(CharacterClass cl);
    public TreeMap visit(CharacterRace cr);
    public TreeMap visit(Stats st);

}