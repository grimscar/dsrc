package script.working.vegvisir;

import script.location;
import script.obj_id;
import script.library.create;

public class vegvisir extends script.base_script
{
    public int OnSpeaking(obj_id self, String text) throws InterruptedException
    {
        if (text.equals("bunkerSpawn"))
        {
            location loc = getLocation(self);
            obj_id container = create.object("object/building/general/boss_nass_dungeon.iff", loc);
            attachScript(container, "restoration.quests.character_slot.cave_master");
         }
        return SCRIPT_CONTINUE;
    }
}
