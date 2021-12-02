package script.working.vegvisir;

import script.obj_id;
import script.library.utils;

public class vendor_buy_limit extends script.base_script
{
    public int OnAboutToReceiveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (utils.isNestedWithinAPlayer(item))
        {
            return SCRIPT_OVERRIDE;
        }
        return SCRIPT_CONTINUE;
    }
}