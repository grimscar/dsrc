package script.working.vegvisir;

import script.obj_id;
import script.string_id;
import script.library.posture;

public class kneel_easter_egg extends script.base_script
{
    public static final string_id WHISPER = new string_id("mind_whisper", "You hear a faint whisper from Mother Jungle in your mind...");
    public static final string_id FAILURE = new string_id("failure", "Something is amiss or not an Ithorian/Kneeling/Self");
    public kneel_easter_egg()
    {
    }
    /*public int kneel(obj_id player, obj_id target) throws InterruptedException
    {
        int targetSpecies = getSpecies(target);
        int posture = getPosture(player);
        obj_id playerKneeling = getIntendedTarget(target);
        if (targetSpecies == SPECIES_ITHORIAN && posture == POSTURE_CROUCHED && playerKneeling != player)
        {
            sendSystemMessage(player, WHISPER);
        }
        sendSystemMessage(player, FAILURE);
        return SCRIPT_CONTINUE;
    }*/
    /*
    public int kneel(obj_id object) throws InterruptedException
    {
        obj_id target = getIntendedTarget(object);
        obj_id player = getSelf();
        int targetSpecies = getSpecies(target);
        //int posture = getPosture(player);
        //obj_id playerKneeling = getIntendedTarget(target);
        if (targetSpecies == SPECIES_ITHORIAN)
        {
            sendSystemMessage(player, WHISPER);
        }
        sendSystemMessage(player, FAILURE);
        return SCRIPT_CONTINUE;
    }*/

    public int kneel(obj_id object) throws InterruptedException
    {
        obj_id player = getSelf();
        sendSystemMessage(player, WHISPER);
        return SCRIPT_CONTINUE;
    }
}