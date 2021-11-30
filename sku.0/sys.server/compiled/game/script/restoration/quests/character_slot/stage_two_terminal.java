package script.restoration.quests.character_slot;

import script.dictionary;
import script.location;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;
import script.library.create;
import script.library.groundquests;
import script.library.player_structure;
import script.library.trial;

public class stage_two_terminal extends script.base_script
{
    public static final String DNA_STF = "restoration/dna_canister";
    public static final string_id SID_OFFLINE = new string_id(DNA_STF, "dna_offline");
    public static final string_id SID_ONLINE = new string_id(DNA_STF, "dna_online");
    public static final string_id TERMINAL_TWO_ON = new string_id(DNA_STF, "stage_two_terminal_on");
    public static final string_id REACTOR_NAME = new string_id(DNA_STF, "reactor_name");
    public static final string_id CORPSE_NAME = new string_id(DNA_STF, "bones_name");
    public static final string_id CONTAINER_NAME = new string_id(DNA_STF, "container_name");
    public static final String ROOM_CURRENT = "room2";
    public static final String ROOM_AHEAD = "room3";
    public static final String CONTAINER_SCRIPT = "restoration.quests.character_slot.stage_two_containers";
    public static final String CORPSE_SCRIPT = "restoration.quests.character_slot.stage_two_corpse";
    public static final String REACTOR_SCRIPT = "restoration.quests.character_slot.stage_two_reactor";
    public static final String PART_ONE = "part_1";
    public static final String PART_TWO = "part_2";
    public static final String PART_THREE = "part_3";
    public static final String PART_FOUR = "part_4";
    public static final String CORPSE = "object/tangible/container/corpse/player_corpse.iff";
    public static final String CONTAINER_1 = "object/tangible/container/drum/large_plain_crate_s02.iff";
    public static final String CONTAINER_2 = "object/tangible/container/drum/large_plain_crate_s01.iff";
    public static final String CONTAINER_3 = "object/tangible/container/general/tech_chest.iff";
    public static final String CONTAINER_4 = "object/tangible/container/drum/warren_reactor_core.iff";
    public static final String REACTOR_TEMPLATE = "object/tangible/container/drum/ancient_generator.iff";
    private static final String[] SLIM_BOX_PARTS_BY_TEMPLATE =
    {
        "object/tangible/loot/generic/battery_useable_generic.iff",
        "object/tangible/loot/simple_kit/datapad_connectors.iff",
        "object/tangible/loot/simple_kit/wiring_blue.iff",
        "object/tangible/loot/simple_kit/wiring_red.iff"
    };
    private static final String[] WARHEAD_PARTS_BY_TEMPLATE =
    {
        "object/tangible/slicing/slicing_flow_analyzer.iff",
        "object/tangible/loot/generic/battery_useable_generic.iff",
        "object/tangible/loot/simple_kit/wiring_blue.iff",
        "object/tangible/loot/simple_kit/wiring_red.iff"
    };
    private static final String[] CABINET_PARTS_BY_TEMPLATE =
    {
        "object/tangible/loot/simple_kit/flat_canvas.iff",
        "object/tangible/loot/simple_kit/feed_tubes.iff",
        "object/tangible/loot/simple_kit/wiring_blue.iff",
        "object/tangible/loot/simple_kit/wiring_red.iff"
    };
    private static final String[] DROID_PARTS_BY_TEMPLATE =
    {
        "object/tangible/loot/simple_kit/motor_small_red.iff",
        "object/tangible/loot/simple_kit/motor_small_blue.iff",
        "object/tangible/loot/simple_kit/wiring_blue.iff",
        "object/tangible/loot/simple_kit/wiring_red.iff"
    };
    private static final String[] SLIM_BOX_PARTS_BY_NAME =
    {
        "Ancient Battery",
        "Ancient Connectors",
        "Ancient Blue Wiring",
        "Ancient Red Wiring"
    };
    private static final String[] WARHEAD_PARTS_BY_NAME =
    {
        "Ancient Flow Analyzer",
        "Ancient Used Battery",
        "Ancient Blue Wiring",
        "Ancient Red Wiring"
    };
    private static final String[] CABINET_PARTS_BY_NAME =
    {
        "Ancient Flat Canvas",
        "Ancient Feed Tubes",
        "Ancient Blue Wiring",
        "Ancient Red Wiring"
    };
    private static final String[] DROID_PARTS_BY_NAME =
    {
        "Ancient Red Motor",
        "Ancient Blue Motor",
        "Ancient Blue Wiring",
        "Ancient Red Wiring"
    };

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setObjVar(self, "powered", false);
        spawnContainers(self);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        boolean isUnlocked = getBooleanObjVar(self, "powered");
        if (!isUnlocked)
        {
            mi.addRootMenu(menu_info_types.ITEM_USE, SID_OFFLINE);
            dictionary d = new dictionary();
            d.put("terminal", self);
            messageTo(getObjIdObjVar(self, "reactor"), "checkCotentsForReboot", d, 0f, false);
        }
        else
        {
            mi.addRootMenu(menu_info_types.ITEM_USE, SID_ONLINE);
        }
        sendDirtyObjectMenuNotification(self);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        boolean isUnlocked = getBooleanObjVar(self, "powered");
        if (item == menu_info_types.ITEM_USE)
        {
            if (isUnlocked)
            {
                groundquests.sendSignal(player, "rebootReactor");
                trial.makeCellPublic(getTopMostContainer(self), ROOM_AHEAD);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public void assignParts(obj_id self, String[] array, int index) throws InterruptedException
    {
        int rand = rand(0,3);
        setObjVar(self, "part_"+index, array[rand]);
    }
    public void handlePartsForContainer(obj_id container, String[] arrayToSpawn, String[] arrayToName) throws InterruptedException
    {
        int indx = 0;
        for (String partName : arrayToSpawn)
        {
            obj_id part = create.object(partName, container, false, false);
            setName(part, arrayToName[indx]);
            setObjVar(part, "ancient", 1);
            ++indx;
        }
    }
    public void spawnContainers(obj_id self) throws InterruptedException
    {
        location loc = new location();
        loc.x = 5.89f;
        loc.y = -6.75f;
        loc.z = -17.2f;
        loc.cell = getCellId(getTopMostContainer(self), ROOM_CURRENT);
        obj_id container = create.object(REACTOR_TEMPLATE, loc);
        detachAllScripts(container);
        attachScript(container, REACTOR_SCRIPT);
        setName(container, REACTOR_NAME);
        setObjVar(self, "reactor", container);

        loc.x = 0.29f;
        loc.y = -6.75f;
        loc.z = -17.8f;
        loc.cell = getCellId(getTopMostContainer(self), ROOM_CURRENT);
        obj_id container2 = create.object(CONTAINER_1, loc);
        detachAllScripts(container2);
        attachScript(container2, CONTAINER_SCRIPT);
        setName(container2, CONTAINER_NAME);
        assignParts(self, SLIM_BOX_PARTS_BY_NAME, 1);
        handlePartsForContainer(container2, SLIM_BOX_PARTS_BY_TEMPLATE, SLIM_BOX_PARTS_BY_NAME);

        loc.x = -2.25f;
        loc.y = -6.75f;
        loc.z = -12.15f;
        loc.cell = getCellId(getTopMostContainer(self), ROOM_CURRENT);
        obj_id container3 = create.object(CONTAINER_2, loc);
        detachAllScripts(container3);
        attachScript(container3, CONTAINER_SCRIPT);
        setName(container3, CONTAINER_NAME);
        assignParts(self, CABINET_PARTS_BY_NAME, 2);
        handlePartsForContainer(container3, CABINET_PARTS_BY_TEMPLATE, CABINET_PARTS_BY_NAME);

        loc.x = 1.17f;
        loc.y = -6.75f;
        loc.z = -12.25f;
        loc.cell = getCellId(getTopMostContainer(self), ROOM_CURRENT);
        obj_id container4 = create.object(CONTAINER_3, loc);
        detachAllScripts(container4);
        attachScript(container4, CONTAINER_SCRIPT);
        setName(container4, CONTAINER_NAME);
        setYaw(container4, 180);
        assignParts(self, DROID_PARTS_BY_NAME, 3);
        handlePartsForContainer(container4, DROID_PARTS_BY_TEMPLATE, DROID_PARTS_BY_NAME);

        loc.x = 3.34f;
        loc.y = -6.75f;
        loc.z = -12.25f;
        loc.cell = getCellId(getTopMostContainer(self), ROOM_CURRENT);
        obj_id container5 = create.object(CONTAINER_4, loc);
        detachAllScripts(container5);
        attachScript(container5, CONTAINER_SCRIPT);
        setName(container5, CONTAINER_NAME);
        assignParts(self, WARHEAD_PARTS_BY_NAME, 4);
        handlePartsForContainer(container5, WARHEAD_PARTS_BY_TEMPLATE, WARHEAD_PARTS_BY_NAME);

        loc.x = 1.97f;
        loc.y = -6.75f;
        loc.z = -16.5f;
        loc.cell = getCellId(getTopMostContainer(self), ROOM_CURRENT);
        obj_id container1 = create.object(CORPSE, loc);
        detachAllScripts(container1);
        attachScript(container1, CORPSE_SCRIPT);
        setName(container1, CORPSE_NAME);
        setObjVar(container1, PART_ONE, getStringObjVar(self, PART_ONE));
        setObjVar(container1, PART_TWO, getStringObjVar(self, PART_TWO));
        setObjVar(container1, PART_THREE, getStringObjVar(self, PART_THREE));
        setObjVar(container1, PART_FOUR, getStringObjVar(self, PART_FOUR));

        setObjVar(container, PART_ONE, getStringObjVar(self, PART_ONE));
        setObjVar(container, PART_TWO, getStringObjVar(self, PART_TWO));
        setObjVar(container, PART_THREE, getStringObjVar(self, PART_THREE));
        setObjVar(container, PART_FOUR, getStringObjVar(self, PART_FOUR));
    }
    public int updateTerminal(obj_id self, dictionary d) throws InterruptedException
    {
        boolean power = getBooleanObjVar(self, "powered");
        if (!power)
        {
            destroyAncientParts(self);
        }
        setName(self, TERMINAL_TWO_ON);
        setObjVar(self, "powered", true);
        return SCRIPT_CONTINUE;
    }
    public void destroyAncientParts(obj_id self) throws InterruptedException
    {
        obj_id building = getTopMostContainer(self);
        obj_id[] players = player_structure.getPlayersInBuilding(building);
        if (players != null)
        {
            for (obj_id player : players)
            {
                obj_id[] contents = getInventoryAndEquipment(player);
                if (contents != null)
                {
                    for (obj_id inventoryObject : contents)
                    {
                        boolean ancient = hasObjVar(inventoryObject, "ancient");
                        if (ancient)
                        {
                            destroyObject(inventoryObject);
                        }
                    }
                }
            }
        }
    }
}
