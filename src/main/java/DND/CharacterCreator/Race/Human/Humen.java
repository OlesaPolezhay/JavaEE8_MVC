package DND.CharacterCreator.Race.Human;

import DND.CharacterCreator.Race.CharacterRace;
import DND.CharacterCreator.Stats;

public class Humen extends CharacterRace {


    Humen(String name,Stats stat){
        this.name=name;
        this.bonuses=stat;
    }

    @Override
    public void shout() {
        System.out.println("Avada Kedavra");
    }
}
