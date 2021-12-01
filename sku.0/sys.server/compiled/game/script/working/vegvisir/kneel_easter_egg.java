package script.working.vegvisir;

import script.obj_id;
import script.string_id;

public class kneel_easter_egg extends script.base_script
{
    public static final string_id WHISPER = new string_id("mind_whisper", "You hear a faint whisper from Mother Jungle in your mind...");
    public kneel_easter_egg()
    {
    }
    public int kneelIthorian(obj_id player, obj_id target) throws InterruptedException
    {
        int targetSpecies = getSpecies(target);
        int posture = getPosture(player);
        if (targetSpecies == SPECIES_ITHORIAN && posture == POSTURE_CROUCHED)
        {
            sendSystemMessage(player, WHISPER);
        }
        return SCRIPT_CONTINUE;
    }
    
}