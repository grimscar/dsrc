package script.restoration.quests.character_slot;

import script.dictionary;
import script.menu_info;
import script.obj_id;
import script.string_id;

public class stage_two_reactor extends script.base_script
{
    public static final String DNA_STF = "restoration/dna_canister";
    public static final string_id GOOD = new string_id(DNA_STF, "reactor_good");
    public static final string_id BAD = new string_id(DNA_STF, "reactor_bad");
    public static final string_id FIXED = new string_id(DNA_STF, "reactor_fixed");
    public static final string_id FIXED_NAME = new string_id(DNA_STF, "reactor_fixed_name");
    public static final String PART_ONE_DONE = "parts_1_done";
    public static final String PART_TWO_DONE = "parts_2_done";
    public static final String PART_THREE_DONE = "parts_3_done";
    public static final String PART_FOUR_DONE = "parts_4_done";
    public static final String PART_ONE = "part_1";
    public static final String PART_TWO = "part_2";
    public static final String PART_THREE = "part_3";
    public static final String PART_FOUR = "part_4";
    public static final String PARTS_DONE = "parts_done";

    public int checkCotentsForReboot(obj_id self, dictionary d) throws InterruptedException
    {
        boolean parts1Done = getBooleanObjVar(self, PART_ONE_DONE);
        boolean parts2Done = getBooleanObjVar(self, PART_TWO_DONE);
        boolean parts3Done = getBooleanObjVar(self, PART_THREE_DONE);
        boolean parts4Done = getBooleanObjVar(self, PART_FOUR_DONE);
        obj_id terminal = d.getObjId("terminal");
        if (parts1Done && parts2Done && parts3Done && parts4Done)
        {
            messageTo(terminal, "updateTerminal", d, 0f, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToReceiveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        String part1 = getStringObjVar(self, PART_ONE);
        String part2 = getStringObjVar(self, PART_TWO);
        String part3 = getStringObjVar(self, PART_THREE);
        String part4 = getStringObjVar(self, PART_FOUR);
        boolean parts1Done = getBooleanObjVar(self, PART_ONE_DONE);
        boolean parts2Done = getBooleanObjVar(self, PART_TWO_DONE);
        boolean parts3Done = getBooleanObjVar(self, PART_THREE_DONE);
        boolean parts4Done = getBooleanObjVar(self, PART_FOUR_DONE);
        int partsDone = getIntObjVar(self, PARTS_DONE);
        if (partsDone == 4)
        {
            sendSystemMessage(transferer, FIXED);
            return SCRIPT_OVERRIDE;
        }
        if (getName(item).equals(part1) && !parts1Done)
        {
            sendSystemMessage(transferer, GOOD);
            setObjVar(self, PART_ONE_DONE, 1);
        }
        else if (getName(item).equals(part2) && !parts2Done)
        {
            sendSystemMessage(transferer, GOOD);
            setObjVar(self, PART_TWO_DONE, 1);
        }
        else if (getName(item).equals(part3) && !parts3Done)
        {
            sendSystemMessage(transferer, GOOD);
            setObjVar(self, PART_THREE_DONE, 1);
        }
        else if (getName(item).equals(part4) && !parts4Done)
        {
            sendSystemMessage(transferer, GOOD);
            setObjVar(self, PART_FOUR_DONE, 1);
        }
        else
        {
            sendSystemMessage(transferer, BAD);
            return SCRIPT_OVERRIDE;
        }
        if (checkForCompleted(self))
        {
            setName(self, FIXED_NAME);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        setOwner(self, player);
        return SCRIPT_CONTINUE;
    }
    public boolean checkForCompleted(obj_id self) throws InterruptedException
    {
        boolean parts1Done = getBooleanObjVar(self, PART_ONE_DONE);
        boolean parts2Done = getBooleanObjVar(self, PART_TWO_DONE);
        boolean parts3Done = getBooleanObjVar(self, PART_THREE_DONE);
        boolean parts4Done = getBooleanObjVar(self, PART_FOUR_DONE);
        if (parts1Done && parts2Done && parts3Done && parts4Done)
        {
            return true;
        }
        return false;
    }
}