package script.working.vegvisir;

import script.obj_id;

public class kneel_easter_egg extends script.base_script
{
    public int kneelIthorian(obj_id player, obj_id target) throws InterruptedException
    {
        int targetSpecies = getSpecies(target);
        int posture = getPosture(player);
        if (targetSpecies == SPECIES_ITHORIAN && posture == POSTURE_CROUCHED)
        {
            sendSystemMessage(player, "You hear a faint whisper in your mind...", null);
        }
        return SCRIPT_CONTINUE;
    }
    
}