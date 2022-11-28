package DND.CharacterCreator.Race.Dwarf;

import DND.CharacterCreator.Race.CharacterRace;
import DND.CharacterCreator.Stats;

public class Dwarf extends CharacterRace {

    Dwarf(String name, Stats stat){
        this.name=name;
        this.bonuses=stat;
    }

    @Override
    public void shout() {
        System.out.println("Alohomora");
    }

}
