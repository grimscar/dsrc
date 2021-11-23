package script.terminal;

import script.*;
import script.library.city;
import script.library.money;
import script.library.sui;
import script.library.utils;

public class grimtest_terminal extends script.terminal.base.base_terminal
{
    public grimterminal()
    {
    }
    public static final string_id SID_BAZAAR_OPTIONS = new string_id("terminal_ui", "bazaar_options");
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        return super.OnObjectMenuRequest(self, player, mi);
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnRequestAuctionFee(obj_id self, obj_id who, obj_id location, obj_id item, boolean premium, modifiable_int amount) throws InterruptedException
    {
        debugSpeakMsg(self, "test " + getName(who));
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        setOwner(self, self);
        attachScript(self, "planet_map.map_loc_attach");
        return super.OnInitialize(self);
    }
}