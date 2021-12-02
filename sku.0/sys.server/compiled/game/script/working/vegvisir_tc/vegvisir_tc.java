package script.working.vegvisir_tc;

import script.*;
import script.library.*;

public class vegvisir_tc extends script.base_script
{
    public int OnSpeaking(obj_id self, String text) throws InterruptedException
    {
        if (text.equals("whatSpeed"))
        {
            float speed = getBaseRunSpeed(self);
            String speedstr = Float.toString(speed);
            sendSystemMessageTestingOnly(self, speedstr);
        }
        return SCRIPT_CONTINUE;
    }
}