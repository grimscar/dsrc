package script.restoration.quests.character_slot;

import script.*;

public class stage_one_holo_bank extends script.base_script
{
    public static final String DNA_STF = "restoration/dna_canister";
    private static final string_id SID_GET_RECORDING = new string_id(DNA_STF, "get_recording");
    private final String FILLER_PREFIX = "recording_filler_";
    private final String ANSWER_PREFIX = "recording_answer_";

    private static final String[] SPECIES = 
    {
        "ep3/ep3_clone_relics_warren_scientist",
        "dressed_geonosian_scientist_01",
        "dressed_combatmedic_trainer_rodian_male_01",
        "warren_scientist_s01",
        "scientist_griffax_jin",
        "dressed_biologist_08",
        "outbreak_pram_dramango",
        "dressed_mercenary_medic_rodian_female_01"
    };
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_GET_RECORDING);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            handleSendingMessage(self, player);
        }
        return SCRIPT_CONTINUE;
    }
    public void handleSendingMessage(obj_id self, obj_id player) throws InterruptedException
    {
        int rndChance = rand(0,10);
        string_id message = new string_id(DNA_STF, FILLER_PREFIX+rndChance);
        prose_package pp = new prose_package();
        pp.stringId = message;
        if (rndChance <= 3)
        {
            int randMessage = rand(1,5);
            message = new string_id(DNA_STF, ANSWER_PREFIX+randMessage);
            pp.stringId = message;
            pp.other.set(getStringObjVar(self, "passcode"));
        }
        commPlayer(player, player, pp, "object/mobile/"+handleSpecies(SPECIES)+".iff");
    }
    public String handleSpecies(String[] array) throws InterruptedException
    {
        return array[rand(0,array.length-1)];
    }
}
