package script.working.vegvisir;

import script.obj_id;

public class vendor_buy_limit extends script.base_script
{
    public vendor_buy_limit()
    {
    }

    public int setBuyLimit(obj_id player)
    {
        getResizeableContents(container);
        return SCRIPT_CONTINUE;
    }
}
