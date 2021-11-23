package script.terminal;

import script.*;
import script.library.city;
import script.library.money;
import script.library.sui;
import script.library.utils;

public class grimtest_terminal extends script.terminal.base.base_terminal
{
    public terminal_thing()
    {
    }
    public static final string_id SID_TERMINAL_PERMISSIONS = new string_id("player_structure", "permissions");
    public static final string_id SID_MOVE_FIRST_ITEM = new string_id("player_structure", "move_first_item");
    public static final string_id SID_MOVED_FIRST_ITEM = new string_id("player_structure", "moved_first_item_pob");
    public static final string_id SID_DELETE_ALL_ITEMS = new string_id("player_structure", "delete_all_items");
    public static final string_id SID_ITEMS_DELETED = new string_id("player_structure", "items_deleted_pob_ship");
    public static final string_id SID_ROOT_ITEM_MENU = new string_id("player_structure", "find_items_root_menu");
    public static final string_id SID_FIND_ALL_HOUSE_ITEMS = new string_id("player_structure", "find_items_find_all_house_items");
    public static final string_id SID_SEARCH_FOR_HOUSE_ITEMS = new string_id("player_structure", "find_items_search_for_house_items");
    public static final string_id SID_TERMINAL_REDEED_STORAGE = new string_id("player_structure", "redeed_storage");
    public static final string_id SID_STORAGE_INCREASE_REDEED_TITLE = new string_id("player_structure", "sui_storage_redeed_title");
    public static final string_id SID_STORAGE_INCREASE_REDEED_PROMPT = new string_id("player_structure", "sui_storage_redeed_prompt");
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) throws InterruptedException
    {
    }
}