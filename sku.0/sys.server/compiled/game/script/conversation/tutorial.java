package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.groundquests;
import script.library.utils;
import script.*;

public class tutorial extends script.base_script
{
    public tutorial()
    {
    }
    public static String c_stringFile = "conversation/tutorial";
    public boolean tutorial_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
    {
    	return true;
    }

    public int tutorial_handleBranch1(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//-- [BRANCH NOTE] Branch 1 Welcome Message
    	//-- NPC: Branch 1 Welcome Message

	    //-- [RESPONSE NOTE] Player Menu 1
	    //-- PLAYER: Player Menu 1
	    if (response.equals("s_4"))
	    {
    		//-- [NOTE] Branch 2 Menu
		    if (tutorial_condition__defaultCondition(player, npc))
		    {
    			//-- NPC: Branch 2 Menu
			    string_id message = new string_id(c_stringFile, "s_5");
			    int numberOfResponses = 0;
			    boolean hasResponse = false;
			    //-- PLAYER: Player Menu 2
			    boolean hasResponse0 = false;
			    if (tutorial_condition__defaultCondition(player, npc))
			    {
    				++numberOfResponses;
				    hasResponse = true;
				    hasResponse0 = true;
			    }

			    //-- PLAYER: Player Menu 3 [Log off]
			    boolean hasResponse1 = false;
			    if (tutorial_condition__defaultCondition(player, npc))
			    {
    				++numberOfResponses;
				    hasResponse = true;
				    hasResponse1 = true;
			    }

			    if (hasResponse)
			    {
    				int responseIndex = 0;
				    string_id responses [] = new string_id[numberOfResponses];

				    if (hasResponse0)
                    {
    					responses [responseIndex++] = new string_id(c_stringFile, "s_6");
                    }
				    if (hasResponse1)
                    {
    					responses [responseIndex++] = new string_id(c_stringFile, "s_7");
                    }
				    utils.setScriptVar(player, "conversation.tutorial.branchId", 2);

				    npcSpeak(player, message);
				    npcSetConversationResponses(player, responses);
			    }
			    else
			    {
    				utils.removeScriptVar(player, "conversation.tutorial.branchId");
				    npcEndConversationWithMessage(player, message);
			    }
			    return SCRIPT_CONTINUE;
		    }
	    }
	    return SCRIPT_DEFAULT;
    }
    public int tutorial_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//-- [BRANCH NOTE] Branch 2 Menu
    	//-- NPC: Branch 2 Menu

	    //-- [RESPONSE NOTE] Player Menu 2
	    //-- PLAYER: Player Menu 2
	    if (response.equals("s_6"))
	    {
    		//-- [NOTE] Branch 3 loop back to Branch 1
		    if (tutorial_condition__defaultCondition(player, npc))
		    {
    			//-- NPC: Branch 3 loop back to Branch 1
			    string_id message = new string_id(c_stringFile, "s_9");
			    utils.removeScriptVar(player, "conversation.tutorial.branchId");

			    npcEndConversationWithMessage(player, message);

			    return SCRIPT_CONTINUE;
		    }

	    }

	    //-- [RESPONSE NOTE] Player Menu 3 [Log off]
	    //-- PLAYER: Player Menu 3 [Log off]
	    if (response.equals("s_7"))
	    {
    		//-- [NOTE] 
		    if (tutorial_condition__defaultCondition(player, npc))
		    {
    			//-- NPC: Branch 4 [Log off Message]
			    string_id message = new string_id(c_stringFile, "s_8");
			    utils.removeScriptVar(player, "conversation.tutorial.branchId");

			    npcEndConversationWithMessage(player, message);

			    return SCRIPT_CONTINUE;
		    }

	    }

	    return SCRIPT_DEFAULT;
    }

    // ----------------------------------------------------------------------

    // ======================================================================
    // User Script Triggers
    // ======================================================================

    public int OnInitialize(obj_id self) throws InterruptedException
    {
    	if ((!isTangible(self)) ||(isPlayer(self)))
        {
		    detachScript(self, "conversation.tutorial");
        }
	    setCondition(self, CONDITION_CONVERSABLE);

	    return SCRIPT_CONTINUE;
    }

    public int OnAttach(obj_id self) throws InterruptedException
    {
    	setCondition(self, CONDITION_CONVERSABLE);

	    return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) throws InterruptedException
    {
    	int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
    	menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
    	menuInfoData.setServerNotify(false);
    	setCondition(self, CONDITION_CONVERSABLE);

	    return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) throws InterruptedException
    {
    	clearCondition(self, CONDITION_CONVERSABLE);
    	detachScript(self, "conversation.tutorial");

	    return SCRIPT_CONTINUE;
    }

    // ======================================================================
    // Script Triggers
    // ======================================================================

    //-- This function should move to base_class.java
    public boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses) throws InterruptedException
    {
    	Object[] objects = new Object[responses.length];
    	System.arraycopy(responses, 0, objects, 0, responses.length);
    	return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
    }

    // ----------------------------------------------------------------------

    public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
    {
    	obj_id npc = self;

	    if (ai_lib.isInCombat(npc) || ai_lib.isInCombat(player))
        {
    		return SCRIPT_OVERRIDE;
        }
	    //-- [NOTE] Branch 1 Welcome Message
	    if (tutorial_condition__defaultCondition(player, npc))
	    {
    		//-- NPC: Branch 1 Welcome Message
		    string_id message = new string_id(c_stringFile, "s_3");
		    int numberOfResponses = 0;
		    boolean hasResponse = false;
		    //-- PLAYER: Player Menu 1
		    boolean hasResponse0 = false;
		    if (tutorial_condition__defaultCondition(player, npc))
		    {
    			++numberOfResponses;
			    hasResponse = true;
			    hasResponse0 = true;
		    }

		    if (hasResponse)
		    {
    			int responseIndex = 0;
			    string_id responses [] = new string_id[numberOfResponses];

			    if (hasResponse0)
                {
    				responses [responseIndex++] = new string_id(c_stringFile, "s_4");
                }
			    utils.setScriptVar(player, "conversation.tutorial.branchId", 1);
			    npcStartConversation(player, npc, "tutorial", message, responses);
		    }
		    else
		    {
    			chat.chat(npc, player, message);
		    }

		    return SCRIPT_CONTINUE;
	    }

	    chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");

	    return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) throws InterruptedException
    {
    	if (!conversationId.equals("tutorial"))
        {
		    return SCRIPT_CONTINUE;
        }
	    obj_id npc = self;

	    int branchId = utils.getIntScriptVar(player, "conversation.tutorial.branchId");

	    if (branchId == 1 && tutorial_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
        {
    		return SCRIPT_CONTINUE;
        }
	    if (branchId == 2 && tutorial_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
        {
    		return SCRIPT_CONTINUE;
        }
	    chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	    utils.removeScriptVar(player, "conversation.tutorial.branchId");

	    return SCRIPT_CONTINUE;
    }
}