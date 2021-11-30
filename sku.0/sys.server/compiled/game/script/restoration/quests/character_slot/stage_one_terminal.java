package script.restoration.quests.character_slot;

import script.dictionary;
import script.location;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;
import script.library.create;
import script.library.groundquests;
import script.library.trial;

public class stage_one_terminal extends script.base_script
{
    public static final String DNA_STF = "restoration/dna_canister";
    public static final string_id SID_LOCKED = new string_id(DNA_STF, "dna_unlocked");
    public static final string_id SID_NOT_LOCKED = new string_id(DNA_STF, "dna_locked");
    private static final string_id HOLO_BANK_NAME = new string_id(DNA_STF, "holobank_name");
    public static final string_id WRONG_CODE = new string_id(DNA_STF, "incorrect_code");
    private final String STAGE_ONE_TERMINAL = "object/tangible/terminal/terminal_pob_ship.iff";
    private final String STAGE_ONE_TERMINAL_SCRIPT = "restoration.quests.character_slot.stage_one_holo_bank";
    public static final String ROOM_CURRENT = "room1";
    public static final String ROOM_AHEAD = "room2";

    public int OnAttach(obj_id self) throws InterruptedException
    {
        setObjVar(self, "passcode", trial.generateNewKeyCode(self));
        spawnAnswerTerminal(self);
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self) throws InterruptedException
    {
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
        boolean isUnlocked = trial.isCellPublic(self, ROOM_AHEAD);
        if (!isUnlocked)
        {
            mi.addRootMenu(menu_info_types.ITEM_USE, SID_LOCKED);
        }
        else
        {
            mi.addRootMenu(menu_info_types.ITEM_USE, SID_NOT_LOCKED);
        }
        sendDirtyObjectMenuNotification(self);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) throws InterruptedException
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (!trial.isCellPublic(self, ROOM_AHEAD))
            {
                keypad(player);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public void keypad(obj_id player) throws InterruptedException
    {
        obj_id self = getSelf();
        int pid = createSUIPage("Script.Keypad", self, player, "KeypadCallback");
        subscribeToSUIProperty(pid, "result.numberBox", "localtext");
        subscribeToSUIProperty(pid, "buttonEnter", "ButtonPressed");
        showSUIPage(pid);
        return;
    }
    public int KeypadCallback(obj_id self, dictionary params) throws InterruptedException
    {
        String result = params.getString("result.numberBox" + "." + "localtext");
        String button = params.getString("buttonEnter.ButtonPressed");
        obj_id player = params.getObjId("player");
        if (!isIdValid(player))
        {
            return SCRIPT_OVERRIDE;
        }
        if (button == null)
        {
            button = "none";
        }
        if (result == null)
        {
            result = "0";
        }
        String passkey = getStringObjVar(self, "passcode");
        if (passkey == null || passkey.equals(""))
        {
            passkey = "nothing";
        }
        if (button.equals("enter"))
        {
            if (passkey.equals(result))
            {
                groundquests.sendSignal(player, "correctCode");
                trial.makeCellPublic(getTopMostContainer(self), ROOM_AHEAD);
                return SCRIPT_CONTINUE;
            }
            else
            {
                sendSystemMessage(player, WRONG_CODE);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public void spawnAnswerTerminal(obj_id self) throws InterruptedException
    {
        location loc = new location();
        loc.x = 0.5f;
        loc.y = -6.75f;
        loc.z = -5f;
        loc.cell = getCellId(getTopMostContainer(self), ROOM_CURRENT);
        obj_id terminal = create.object(STAGE_ONE_TERMINAL, loc);
        detachAllScripts(terminal);
        attachScript(terminal, STAGE_ONE_TERMINAL_SCRIPT);
        setName(terminal, HOLO_BANK_NAME);
        setYaw(terminal, -90);
        setObjVar(terminal, "passcode", getStringObjVar(self, "passcode"));
    }
}
