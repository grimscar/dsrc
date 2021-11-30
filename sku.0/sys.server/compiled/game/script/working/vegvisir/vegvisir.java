package script.working.vegvisir;

import java.util.StringTokenizer;
import script.*;
import script.library.*;

public class vegvisir extends script.base_script
{
    public int OnSpeaking(obj_id self, String text) throws InterruptedException
    {
        StringTokenizer st = new java.util.StringTokenizer(txt);
        String command = null;
        if (text.equals("bunker"))
        {
            location loc = getLocation(self);
            obj_id container = create.object("object/building/general/boss_nass_dungeon.iff", loc);
            attachScript(container, "restoration.quests.character_slot.cave_master");
        }
        if(command.equals("yaw"))
        {
            obj_id target = getIntendedTarget(self);
            if (target == null)
            {
                String tar = st.nextToken();
                String amnt = st.nextToken();
                obj_id targ = utils.stringToObjId(tar);
                modifyYaw(targ, Float.parseFloat(amnt));
                sendSystemMessageTestingOnly(self, tar +" "+amnt);
            }
            String amount = st.nextToken();
            modifyYaw(target, Float.parseFloat(amount));
        }
        if(command.equals("pitch"))
        {
            obj_id target = getIntendedTarget(self);
            if (target == null)
            {
                String tar = st.nextToken();
                String amnt = st.nextToken();
                obj_id targ = utils.stringToObjId(tar);
                modifyPitch(targ, Float.parseFloat(amnt));
                sendSystemMessageTestingOnly(self, tar +" "+amnt);
            }
            String amount = st.nextToken();
            modifyPitch(target, Float.parseFloat(amount));
        }
        if(command.equals("roll"))
        {
            obj_id target = getIntendedTarget(self);
            if (target == null)
            {
                String tar = st.nextToken();
                String amnt = st.nextToken();
                obj_id targ = utils.stringToObjId(tar);
                modifyRoll(targ, Float.parseFloat(amnt));
                sendSystemMessageTestingOnly(self, tar +" "+amnt);
            }
            String amount = st.nextToken();
            sendSystemMessageTestingOnly(self, amount);
            modifyRoll(target, Float.parseFloat(amount));
        }
        if(command.equals("move"))
        {
            obj_id target = getIntendedTarget(self);
            String tar = "";
            if (target == null)
            {
                tar = st.nextToken();
                target = utils.stringToObjId(tar);
            }
            String direction = st.nextToken();
            String amnt = st.nextToken();
            //obj_id targ = utils.stringToObjId(tar);
            float amount = utils.stringToFloat(amnt);
            location loc = getLocation(target);
            switch (direction)
            {
                case "x":
                    loc.x += amount;
                    break;
                case "y":
                    loc.y += amount;
                    break;
                case "z":
                    loc.z += amount;
                    break;
                default:
                    break;
            }
            setLocation(target, loc);
            sendSystemMessageTestingOnly(self, tar +" "+amnt);
        }
        return SCRIPT_CONTINUE;
    }
}