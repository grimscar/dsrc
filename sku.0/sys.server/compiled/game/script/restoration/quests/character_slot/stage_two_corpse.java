package script.restoration.quests.character_slot;

import script.library.create;
import script.library.utils;
import script.menu_info;
import script.obj_id;
import script.string_id;

public class stage_two_corpse extends script.base_script
{
    public static final String DNA_STF = "restoration/dna_canister";
    public static final string_id NO_LOOT = new string_id(DNA_STF, "bones_not_interested");
    public static final String LOOT = "Digging through the bones you find ";
    public static final String INSTRUCTIONS = "Reactor Repair Manual";
    private static final String INSTRUCTIONS_TEMPLATE = "object/tangible/quest/ancient_instructions.iff";

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        obj_id instructions = utils.getItemByTemplateInInventoryOrEquipped(player, INSTRUCTIONS_TEMPLATE);
        obj_id playerInv = utils.getInventoryContainer(player);
        if (instructions == null)
        {
            obj_id papers = create.object(INSTRUCTIONS_TEMPLATE, playerInv, false, false);
            setName(papers, INSTRUCTIONS);
            String part1 = getStringObjVar(self, "part_1");
            String part2 = getStringObjVar(self, "part_2");
            String part3 = getStringObjVar(self, "part_3");
            String part4 = getStringObjVar(self, "part_4");
            setObjVar(papers, "part_1", part1);
            setObjVar(papers, "part_2", part2);
            setObjVar(papers, "part_3", part3);
            setObjVar(papers, "part_4", part4);
            setObjVar(papers, "ancient", 1);
            sendSystemMessageTestingOnly(player, LOOT+getName(papers));
        }
        else 
        {
            sendSystemMessage(player, NO_LOOT);
        }
        return SCRIPT_CONTINUE;
    }
}