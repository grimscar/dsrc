package script.restoration.quests.character_slot;

import script.dictionary;
import script.location;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;
import script.library.create;
import script.library.luck;
import script.library.utils;

public class canister_logic extends script.base_script
{
    public static final String DNA_STF = "restoration/dna_canister";
    private static final string_id SID_USE = new string_id(DNA_STF, "dna_use");
    private static final string_id SID_CORRECT = new string_id(DNA_STF, "dna_correct_profession");
    private static final string_id SID_WRONG = new string_id(DNA_STF, "dna_wrong_profession");
    public static final string_id CONATINER_EMPTY = new string_id(DNA_STF, "dna_container_empty");
    public static final string_id CONATINER_WORKING = new string_id(DNA_STF, "dna_container_working");
    public static final string_id CONATINER_DONE = new string_id(DNA_STF, "dna_container_done");
    private final String TERMINAL_PRIZE_TEMPLATE = "object/tangible/container/drum/poi_prize_box_off.iff";
    private final String TERMINAL_PRIZE_TEMPLATE_ON = "object/tangible/container/drum/poi_prize_box_on.iff";
    private final String CANISTER_TEMPLATE_PREFIX = "object/tangible/collection/dna_canister_";

    public int OnAttach(obj_id self) throws InterruptedException
    {
        spawnEmptyContainer(self, null);
        utils.setScriptVar(self, "activated", false);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_USE);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (isRightProfession(self, player) && !utils.getBooleanScriptVar(self, "activated"))
            {
                sendSystemMessage(player, SID_CORRECT);
                dictionary d = new dictionary();
                d.put("player", player);
                messageTo(self, "spawnWorkingContainer", null, 0f, false);
                messageTo(self, "spawnRewardContainer", d, 15f, false);
                utils.setScriptVar(self, "activated", true);
            }
            else
            {
                sendSystemMessage(player, SID_WRONG);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public boolean isRightProfession(obj_id self, obj_id player) throws InterruptedException
    {
        String strRequiredProfession = getStringObjVar(self, "profession");
        String requiredProfession = strRequiredProfession+"_master";
        return hasSkill(player, requiredProfession);
    }
    public void spawnEmptyContainer(obj_id self, dictionary d) throws InterruptedException
    {
        location loc = getLocation(self);
        loc.z += 2f;
        obj_id container = create.object(TERMINAL_PRIZE_TEMPLATE, loc);
        setObjVar(self, "container", container);
        setName(container, CONATINER_EMPTY);
        setYaw(container, 90);
    }
    public void spawnWorkingContainer(obj_id self, dictionary d) throws InterruptedException
    {
        obj_id cntr = getObjIdObjVar(self, "container");
        destroyObject(cntr);
        removeObjVar(self, "container");
        location loc = getLocation(self);
        loc.z += 2f;
        obj_id container = create.object(TERMINAL_PRIZE_TEMPLATE_ON, loc);
        setObjVar(self, "container", container);
        setName(container, CONATINER_WORKING);
        setYaw(container, 90);
    }
    public void spawnRewardContainer(obj_id self, dictionary d) throws InterruptedException
    {
        obj_id cntr = getObjIdObjVar(self, "container");
        destroyObject(cntr);
        removeObjVar(self, "container");
        location loc = getLocation(self);
        loc.z += 2f;
        obj_id container = create.object(TERMINAL_PRIZE_TEMPLATE, loc);
        setObjVar(self, "container", container);
        setName(container, CONATINER_DONE);
        setYaw(container, 90);
        obj_id player = d.getObjId("player");
        spawnCanisterLoot(self, player);
    }
    public void spawnCanisterLoot(obj_id self, obj_id player) throws InterruptedException
    {
        obj_id container = getObjIdObjVar(self, "container");
        int totalChance = rand(0,10000);

        if (luck.isLucky(player, 0.1f))
        {
            obj_id canister = create.object(CANISTER_TEMPLATE_PREFIX+"5.iff", container, false , false);
            setOwner(canister, player);
            return;
        }

        if (totalChance <= 4000)
        {
            obj_id canister = create.object(CANISTER_TEMPLATE_PREFIX+"1.iff", container, false , false);
            setOwner(canister, player);
        }
        else if (totalChance <= 7300)
        {
            obj_id canister = create.object(CANISTER_TEMPLATE_PREFIX+"2.iff", container, false , false);
            setOwner(canister, player);
        }
        else if (totalChance <= 8800)
        {
            obj_id canister = create.object(CANISTER_TEMPLATE_PREFIX+"3.iff", container, false , false);
            setOwner(canister, player);
        }
        else if (totalChance <= 9900)
        {
            obj_id canister = create.object(CANISTER_TEMPLATE_PREFIX+"4.iff", container, false , false);
            setOwner(canister, player);
        }
        else
        {
            obj_id canister = create.object(CANISTER_TEMPLATE_PREFIX+"5.iff", container, false , false);
            setOwner(canister, player);
        }
    }
}
