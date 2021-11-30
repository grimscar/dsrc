package script.restoration.quests.character_slot;

import script.dictionary;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class stage_two_instructions extends script.base_script
{
    public static final String DNA_STF = "restoration/dna_canister";
    public static final string_id SID_READ = new string_id(DNA_STF, "dna_read");
    public static final string_id ALREADY = new string_id(DNA_STF, "instructions_already");
    public static final string_id ATTEMPT = new string_id(DNA_STF, "instructions_attempt");
    public static final String INSTRUCT = "After looking through a few pages you believe you need an ";
    public static final String INSTRUCT_SUFFIX = " for the Reactor.";

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_READ);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        int reading = getIntObjVar(self, "index");
        if (reading != 0)
        {
            sendSystemMessage(player, ALREADY);
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.ITEM_USE)
        {
            sendSystemMessage(player, ATTEMPT);
            setObjVar(self, "index", 1);
            dictionary d = new dictionary();
            d.put("player", player);
            recurringMessageTo(self, "delayedInstructions", d, 2f);
        }
        return SCRIPT_CONTINUE;
    }
    public int delayedInstructions(obj_id self, dictionary params) throws InterruptedException
    {
        obj_id player = params.getObjId("player");
        String part1 = getStringObjVar(self, "part_1");
        String part2 = getStringObjVar(self, "part_2");
        String part3 = getStringObjVar(self, "part_3");
        String part4 = getStringObjVar(self, "part_4");
        int index = getIntObjVar(self, "index");
        switch (index)
        {
            case 1:
                sendSystemMessageTestingOnly(player, INSTRUCT+part1+INSTRUCT_SUFFIX);
                setObjVar(self, "index", index+1);
            break;
            case 2:
                sendSystemMessageTestingOnly(player, INSTRUCT+part2+INSTRUCT_SUFFIX);
                setObjVar(self, "index", index+1);
            break;
            case 3:
                sendSystemMessageTestingOnly(player, INSTRUCT+part3+INSTRUCT_SUFFIX);
                setObjVar(self, "index", index+1);
            break;
            case 4:
                sendSystemMessageTestingOnly(player, INSTRUCT+part4+INSTRUCT_SUFFIX);
                cancelRecurringMessageTo(self, "delayedInstructions");
                removeObjVar(self, "index");
            break;
        }
        return SCRIPT_CONTINUE;
    }
}