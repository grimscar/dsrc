package script.working.vegvisir;

import script.obj_id;
import script.string_id;
import script.library.posture;

public class kneel_easter_egg extends script.base_script
{
    public static final string_id WHISPER = new String("You hear a faint whisper from Mother Jungle in your mind...");
    public static final string_id FAILURE = new String("Something is amiss or not an Ithorian/Kneeling/Self");
    public kneel_easter_egg()
    {
    }

    public int OnChangedPosture(obj_id self, int oldPosture, int newPosture) throws InterruptedException
    {
        if (newPosture == POSTURE_CROUCHED && oldPosture != POSTURE_CROUCHED)
        {
            sendSystemMessage(self, WHISPER, null);
            return SCRIPT_DEFAULT;
        }
        sendSystemMessage(self, FAILURE, null);
        return SCRIPT_CONTINUE;
    }
}