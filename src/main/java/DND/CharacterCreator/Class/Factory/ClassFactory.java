package DND.CharacterCreator.Class.Factory;

import DND.CharacterCreator.Class.Classes.Barbarian;
import DND.CharacterCreator.Class.Classes.Bard;
import DND.CharacterCreator.Class.Classes.CharacterClass;

public class ClassFactory {
    public static CharacterClass getClass(String type){
        if("Barbarian".equalsIgnoreCase(type)) return new Barbarian();
        else if("Bard".equalsIgnoreCase(type)) return new Bard();
        return null;
    }

}

