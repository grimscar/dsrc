package script.restoration.quests.character_slot;

import script.location;
import script.obj_id;
import script.string_id;
import script.library.create;
import script.library.trial;

public class cave_master extends script.base_script
{
    private final String[] ALL_PROFESSIONS_BY_TEMPLATE = 
    {
        "combat_brawler",
        "combat_marksman",
        "combat_unarmed",
        "combat_1hsword",
        "combat_2hsword",
        "combat_polearm",
        "combat_smuggler",
        "combat_pistol",
        "combat_carbine",
        "combat_rifleman",
        "combat_bountyhunter",
        "combat_commando",
        "science_medic",
        "science_doctor",
        "science_combatmedic",
        "outdoors_squadleader",
        "outdoors_scout",
        "outdoors_ranger",
        "outdoors_creaturehandler",
        "outdoors_bio_engineer",
        "social_entertainer",
        "social_dancer",
        "social_musician",
        "social_imagedesigner",
        "crafting_artisan",
        "crafting_merchant",
        "crafting_armorsmith",
        "crafting_weaponsmith",
        "crafting_chef",
        "crafting_tailor",
        "crafting_architect",
        "crafting_droidengineer",
        "crafting_shipwright"
    };
    private final String[] ALL_PROFESSIONS_BY_NAME = 
    {
        "Brawler",
        "Marksman",
        "Teras Kasi",
        "Fencer",
        "Swordsman",
        "Pikeman",
        "Smuggler",
        "Pistoleer",
        "Carbineer",
        "Rifleman",
        "Bounty Hunter",
        "Commando",
        "Medic",
        "Doctor",
        "Combat Medic",
        "Squad Leader",
        "Scout",
        "Ranger",
        "Creature Handler",
        "Bio Engineer",
        "Entertainer",
        "Dancer",
        "Musician",
        "Image Designer",
        "Artisan",
        "Merchant",
        "Armorsmith",
        "Weaponsmith",
        "Chef",
        "Tailor",
        "Architect",
        "Droid Engineer",
        "Shipwright"
    };
    //This is for picking between a certain pool for each stage so multiple professions don't get selected.
    private final int MAX_CAT_ONE = 11;
    private final int MIN_CAT_TWO = 12;
    private final int MAX_CAT_TWO = 23;
    private final int MIN_CAT_THREE = 24;
    private final String ROOM_ONE = "room1";
    private final String ROOM_TWO = "room2";
    private final String ROOM_THREE = "room3";
    private final String ROOM_FOUR = "basement";
    public static final String TERMINAL_PROFESSION = "Old terminal lableled [";
    private final String CANISTER_SCRIPT = "restoration.quests.character_slot.canister_logic";
    private final String TERMINAL_ONE_SCRIPT = "restoration.quests.character_slot.stage_one_terminal";
    private final String TERMINAL_TWO_SCRIPT = "restoration.quests.character_slot.stage_two_terminal";
    private final String TERMINAL_THREE_SCRIPT = "conversation.ancient_stage_three_terminal";
    private final String TERMINAL_ONE_TEMPLATE = "object/tangible/quest/ac_terminal_loc3_03.iff";
    private final String TERMINAL_TWO_TEMPLATE = "object/tangible/terminal/systems_control_terminal.iff";
    private final String TERMINAL_THREE_TEMPLATE = "object/tangible/terminal/terminal_player_hangar_structure.iff";
    private final String TERMINAL_PROFESSION_TEMPLATE = "object/tangible/terminal/terminal_command_console.iff";
    private final String TERMINAL_PRIZE_TEMPLATE = "object/tangible/container/drum/poi_prize_box_off.iff";
    private final String CANISTER_TEMPLATE_PREFIX = "object/tangible/collection/dna_canister_";
    public final String DNA_STF = "restoration/dna_canister";
    public final string_id TERMINAL_ONE = new string_id(DNA_STF, "stage_one_terminal");
    public final string_id TERMINAL_TWO = new string_id(DNA_STF, "stage_two_terminal");
    public final string_id TERMINAL_THREE = new string_id(DNA_STF, "stage_three_terminal");
    public final string_id CONATINER_DONE = new string_id(DNA_STF, "dna_container_done");
    public final string_id DENY_ENTRY = new string_id(DNA_STF, "dna_deny_entry");

    public int OnAttach(obj_id self) throws InterruptedException
    {
        spawnEverything(self);
        lockBunker(self);
        return SCRIPT_CONTINUE;
    }
    public boolean isDungeonActivated(obj_id self) throws InterruptedException
    {
        return false;
    }
    public void spawnEverything(obj_id self) throws InterruptedException
    {
        spawnStageOneTerminal(self);
        spawnStageTwoTerminal(self);
        spawnStageThreeTerminal(self);
        spawnCanisters(self);
    }
    public void spawnStageOneTerminal(obj_id self) throws InterruptedException
    {
        location loc = getLocation(self);
        loc.x = -6.001418f;
        loc.y = -6.75f;
        loc.z = -10.244226f;
        loc.cell = getCellId(self, ROOM_ONE);
        obj_id terminal = create.object(TERMINAL_ONE_TEMPLATE, loc);
        detachAllScripts(terminal);
        attachScript(terminal, TERMINAL_ONE_SCRIPT);
        setName(terminal, TERMINAL_ONE);
    }
    public void spawnStageTwoTerminal(obj_id self) throws InterruptedException
    {
        location loc = getLocation(self);
        loc.x = 7.8f;
        loc.y = -6.75f;
        loc.z = -12.02f;
        loc.cell = getCellId(self, ROOM_TWO);
        obj_id terminal = create.object(TERMINAL_TWO_TEMPLATE, loc);
        detachAllScripts(terminal);
        attachScript(terminal, TERMINAL_TWO_SCRIPT);
        setName(terminal, TERMINAL_TWO);
        setYaw(terminal, 180);
    }
    
    public void spawnStageThreeTerminal(obj_id self) throws InterruptedException
    {
        location loc = getLocation(self);
        loc.x = 1.4797235f;
        loc.y = -6.75f;
        loc.z = -4.7848353f;
        loc.cell = getCellId(self, ROOM_THREE);
        obj_id terminal = create.object(TERMINAL_THREE_TEMPLATE, loc);
        detachAllScripts(terminal);
        attachScript(terminal, TERMINAL_THREE_SCRIPT);
        setName(terminal, TERMINAL_THREE);
        setYaw(terminal, 90);
    }
    public void lockBunker(obj_id self) throws InterruptedException
    {
        String[] cells =
        {
            ROOM_TWO,
            ROOM_THREE,
            ROOM_FOUR
        };
        for (String cell : cells)
        {
            trial.makeCellPrivate(self, cell);
        }
    }
    public int OnAboutToReceiveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item) throws InterruptedException
    {
        if (!isPlayer(item))
        {
            return SCRIPT_CONTINUE;
        }
        boolean signedUp = getBooleanObjVar(item, "dna.buildingPass");//change to quest check
        if (!isGod(item) && !signedUp)
        {
            sendSystemMessage(item, DENY_ENTRY);
            return SCRIPT_OVERRIDE;
        }
        return SCRIPT_CONTINUE;
    }
    public void spawnCanisters(obj_id self) throws InterruptedException
    {
        //Spawn per stage
        int stageOneProfession = rand(0, MAX_CAT_ONE);
        int stageTwoProfession = rand(MIN_CAT_TWO, MAX_CAT_TWO);
        int stageThreeProfession = rand(MIN_CAT_THREE, ALL_PROFESSIONS_BY_TEMPLATE.length-1);

        location loc = getLocation(self);
        loc.x = -7.4f;
        loc.y = -6.75f;
        loc.z = -2.99f;
        loc.cell = getCellId(self, ROOM_ONE);

        obj_id stageOneCanister = create.object(TERMINAL_PROFESSION_TEMPLATE, loc);
        setName(stageOneCanister, TERMINAL_PROFESSION+ALL_PROFESSIONS_BY_NAME[stageOneProfession]+"]");
        setObjVar(stageOneCanister, "profession", ALL_PROFESSIONS_BY_TEMPLATE[stageOneProfession]);
        attachScript(stageOneCanister, CANISTER_SCRIPT);
        setYaw(stageOneCanister, -90);

        //stagetwo
        loc.x = 2f;
        loc.y = -6.8f;
        loc.z = -2.2f;
        loc.cell = getCellId(self, ROOM_THREE);

        obj_id stageTwoCanister = create.object(TERMINAL_PROFESSION_TEMPLATE, loc);
        setName(stageTwoCanister, TERMINAL_PROFESSION+ALL_PROFESSIONS_BY_NAME[stageTwoProfession]+"]");
        setObjVar(stageTwoCanister, "profession", ALL_PROFESSIONS_BY_TEMPLATE[stageTwoProfession]);
        attachScript(stageTwoCanister, CANISTER_SCRIPT);
        setYaw(stageTwoCanister, -90);

        //stagethree
        loc.x = -8.4f;
        loc.y = -13.8f;
        loc.z = -9f;
        loc.cell = getCellId(self, ROOM_FOUR);

        obj_id stageThreeCanister = create.object(TERMINAL_PROFESSION_TEMPLATE, loc);
        setName(stageThreeCanister, TERMINAL_PROFESSION+ALL_PROFESSIONS_BY_NAME[stageThreeProfession]+"]");
        setObjVar(stageThreeCanister, "profession", ALL_PROFESSIONS_BY_TEMPLATE[stageThreeProfession]);
        attachScript(stageThreeCanister, CANISTER_SCRIPT);
        setYaw(stageThreeCanister, -90);

        //spawn bottom chest
        loc.x = -2.5f;
        loc.y = -13.8f;
        loc.z = -17.1f;
        loc.cell = getCellId(self, ROOM_FOUR);

        obj_id basementCanister = create.object(TERMINAL_PRIZE_TEMPLATE, loc);
        setName(basementCanister, CONATINER_DONE);
        spawnCanisterLoot(self, basementCanister);
        loopIt(self, basementCanister);
    }
    public void spawnCanisterLoot(obj_id self, obj_id container) throws InterruptedException
    {
        int rndChance = rand(0,10000);
        if (rndChance <= 4000)
        {
            create.object(CANISTER_TEMPLATE_PREFIX+"1.iff", container, false , false);
        }
        else if (rndChance <= 7300)
        {
            create.object(CANISTER_TEMPLATE_PREFIX+"2.iff", container, false , false);
        }
        else if (rndChance <= 8800)
        {
            create.object(CANISTER_TEMPLATE_PREFIX+"3.iff", container, false , false);
        }
        else if (rndChance <= 9900)
        {
            create.object(CANISTER_TEMPLATE_PREFIX+"4.iff", container, false , false);
        }
        else
        {
            create.object(CANISTER_TEMPLATE_PREFIX+"5.iff", container, false , false);
        }
    }
    public void loopIt(obj_id self, obj_id container) throws InterruptedException
    {
        int max = 19;
        int start = 0;
        while (max > start)
        {
            ++start;
            spawnCanisterLoot(self, container);
        }
    }
}
