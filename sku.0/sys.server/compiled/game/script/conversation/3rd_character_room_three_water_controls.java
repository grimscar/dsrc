package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.utils;
import script.*;

public class 3rd_character_room_three_water_controls extends script.base_script
{
	string c_stringFile = "conversation/3rd_character_room_three_water_controls";

	boolean 3rd_character_room_three_water_controls_condition__defaultCondition (obj_id player, obj_id npc)
	{
		return true;
	}

	int 3rd_character_room_three_water_controls_handleBranch1 (obj_id player, obj_id npc, string_id response)
	{
		//-- [BRANCH NOTE] Initial Weclome Screen:
		//-- NPC: Initial Weclome Screen:

		//-- [RESPONSE NOTE] [Access Water Controls]
		//-- PLAYER: [Access Water Controls]
		if (response.equals("s_4"))
		{
			//-- [NOTE] Current waterflow status:[Malfunctioning]
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: Current waterflow status:[Malfunctioning]
				string_id message = new string_id (c_stringFile, "s_6");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: [Access Valve #1 Controls]
				boolean hasResponse0 = false;
				if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: [Access Valve #2 Controls]
				boolean hasResponse1 = false;
				if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: [Access Valve #3 Controls]
				boolean hasResponse2 = false;
				if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}

				//-- PLAYER: [Log off]
				boolean hasResponse3 = false;
				if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse3 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses [] = new string_id [numberOfResponses];

					if (hasResponse0)
					{
						responses [responseIndex++] = new string_id (c_stringFile, "s_8");
					}
					if (hasResponse1)
					{
						responses [responseIndex++] = new string_id (c_stringFile, "s_9");
					}
					if (hasResponse2)
					{
						responses [responseIndex++] = new string_id (c_stringFile, "s_12");
					}
					if (hasResponse3)
					{
						responses [responseIndex++] = new string_id (c_stringFile, "s_11");
					}
					utils.setScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId", 2);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE] [Smash Controls]
		//-- PLAYER: [Smash Controls]
		if (response.equals("s_37"))
		{
			//-- [NOTE] 5% chance this auto-solves otherwise this causes flooding that drastically slows down the player but after XX seconds a water system kicks in to drain allowing them through.
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: 5% chance this auto-solves otherwise this causes flooding that drastically slows down the player but after XX seconds a water system kicks in to drain allowing them through.
				string_id message = new string_id (c_stringFile, "s_38");
				utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE] [Log off]
		//-- PLAYER: [Log off]
		if (response.equals("s_5"))
		{
			//-- [NOTE] System shutting down
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: System shutting down
				string_id message = new string_id (c_stringFile, "s_36");
				utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	// ----------------------------------------------------------------------

	int 3rd_character_room_three_water_controls_handleBranch2 (obj_id player, obj_id npc, string_id response)
	{
		//-- [BRANCH NOTE] Current waterflow status:[Malfunctioning]
		//-- NPC: Current waterflow status:[Malfunctioning]

		//-- [RESPONSE NOTE] [Access Valve #1 Controls]
		//-- PLAYER: [Access Valve #1 Controls]
		if (response.equals("s_8"))
		{
			//-- [NOTE] Please select an option:
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: Please select an option:
				string_id message = new string_id (c_stringFile, "s_13");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: [Open Valve #1]
				boolean hasResponse0 = false;
				if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: [Close Valve #1]
				boolean hasResponse1 = false;
				if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: [Return to Menu]
				boolean hasResponse2 = false;
				if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses [] = new string_id [numberOfResponses];

					if (hasResponse0)
					{
						responses [responseIndex++] = new string_id (c_stringFile, "s_14");
					}
					if (hasResponse1)
					{
						responses [responseIndex++] = new string_id (c_stringFile, "s_15");
					}
					if (hasResponse2)
					{
						responses [responseIndex++] = new string_id (c_stringFile, "s_16");
					}
					utils.setScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId", 3);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE] [Access Valve #2 Controls]
		//-- PLAYER: [Access Valve #2 Controls]
		if (response.equals("s_9"))
		{
			//-- [NOTE] Please select an option:
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: Please select an option:
				string_id message = new string_id (c_stringFile, "s_17");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: [Open Valve #2]
				boolean hasResponse0 = false;
				if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: [Close Valve #2]
				boolean hasResponse1 = false;
				if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: [Return to Menu]
				boolean hasResponse2 = false;
				if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses [] = new string_id [numberOfResponses];

					if (hasResponse0)
					{
						responses [responseIndex++] = new string_id (c_stringFile, "s_18");
					}
					if (hasResponse1)
					{
						responses [responseIndex++] = new string_id (c_stringFile, "s_20");
					}
					if (hasResponse2)
					{
						responses [responseIndex++] = new string_id (c_stringFile, "s_21");
					}
					utils.setScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId", 7);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE] [Access Valve #3 Controls]
		//-- PLAYER: [Access Valve #3 Controls]
		if (response.equals("s_12"))
		{
			//-- [NOTE] Please select an option:
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: Please select an option:
				string_id message = new string_id (c_stringFile, "s_22");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: [Open Valve #3]
				boolean hasResponse0 = false;
				if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: [Close Valve #3]
				boolean hasResponse1 = false;
				if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER: [Return to Menu]
				boolean hasResponse2 = false;
				if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses [] = new string_id [numberOfResponses];

					if (hasResponse0)
					{
						responses [responseIndex++] = new string_id (c_stringFile, "s_23");
					}
					if (hasResponse1)
					{
						responses [responseIndex++] = new string_id (c_stringFile, "s_24");
					}
					if (hasResponse2)
					{
						responses [responseIndex++] = new string_id (c_stringFile, "s_25");
					}
					utils.setScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId", 11);

					npcSpeak (player, message);
					npcSetConversationResponses (player, responses);
				}
				else
				{
					utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

					npcEndConversationWithMessage (player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE] [Log off]
		//-- PLAYER: [Log off]
		if (response.equals("s_11"))
		{
			//-- [NOTE] System shutting down
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: System shutting down
				string_id message = new string_id (c_stringFile, "s_35");
				utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	// ----------------------------------------------------------------------

	int 3rd_character_room_three_water_controls_handleBranch3 (obj_id player, obj_id npc, string_id response)
	{
		//-- [BRANCH NOTE] Please select an option:
		//-- NPC: Please select an option:

		//-- [RESPONSE NOTE] [Open Valve #1]
		//-- PLAYER: [Open Valve #1]
		if (response.equals("s_14"))
		{
			//-- [NOTE] Return to Access Valve #1 Controls
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: Return to Access Valve #1 Controls
				string_id message = new string_id (c_stringFile, "s_26");
				utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE] [Close Valve #1]
		//-- PLAYER: [Close Valve #1]
		if (response.equals("s_15"))
		{
			//-- [NOTE] Return to Access Valve #1 Controls
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: Return to Access Valve #1 Controls
				string_id message = new string_id (c_stringFile, "s_27");
				utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE] [Return to Menu]
		//-- PLAYER: [Return to Menu]
		if (response.equals("s_16"))
		{
			//-- [NOTE] Return to Current Waterflow Status
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: Return to Current Waterflow Status
				string_id message = new string_id (c_stringFile, "s_28");
				utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	// ----------------------------------------------------------------------

	int 3rd_character_room_three_water_controls_handleBranch7 (obj_id player, obj_id npc, string_id response)
	{
		//-- [BRANCH NOTE] Please select an option:
		//-- NPC: Please select an option:

		//-- [RESPONSE NOTE] [Open Valve #2]
		//-- PLAYER: [Open Valve #2]
		if (response.equals("s_18"))
		{
			//-- [NOTE] Return to Access Valve #2 Controls
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: Return to Access Valve #2 Controls
				string_id message = new string_id (c_stringFile, "s_29");
				utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE] [Close Valve #2]
		//-- PLAYER: [Close Valve #2]
		if (response.equals("s_20"))
		{
			//-- [NOTE] Return to Access Valve #2 Controls
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: Return to Access Valve #2 Controls
				string_id message = new string_id (c_stringFile, "s_30");
				utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE] [Return to Menu]
		//-- PLAYER: [Return to Menu]
		if (response.equals("s_21"))
		{
			//-- [NOTE] Return to Current Waterflow Status
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: Return to Current Waterflow Status
				string_id message = new string_id (c_stringFile, "s_31");
				utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	// ----------------------------------------------------------------------

	int 3rd_character_room_three_water_controls_handleBranch11 (obj_id player, obj_id npc, string_id response)
	{
		//-- [BRANCH NOTE] Please select an option:
		//-- NPC: Please select an option:

		//-- [RESPONSE NOTE] [Open Valve #3]
		//-- PLAYER: [Open Valve #3]
		if (response.equals("s_23"))
		{
			//-- [NOTE] Return to Access Valve #3 Controls
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: Return to Access Valve #3 Controls
				string_id message = new string_id (c_stringFile, "s_32");
				utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE] [Close Valve #3]
		//-- PLAYER: [Close Valve #3]
		if (response.equals("s_24"))
		{
			//-- [NOTE] Return to Access Valve #3 Controls
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: Return to Access Valve #3 Controls
				string_id message = new string_id (c_stringFile, "s_33");
				utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		//-- [RESPONSE NOTE] [Return to Menu]
		//-- PLAYER: [Return to Menu]
		if (response.equals("s_25"))
		{
			//-- [NOTE] Return to Current Waterflow Status
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				//-- NPC: Return to Current Waterflow Status
				string_id message = new string_id (c_stringFile, "s_34");
				utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

				npcEndConversationWithMessage (player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	// ----------------------------------------------------------------------

	// ======================================================================
	// User Script Triggers
	// ======================================================================

	trigger OnInitialize ()
	{
		if ((!isTangible (self)) || (isPlayer (self)))
		{
			detachScript(self, "conversation.3rd_character_room_three_water_controls");
		}
		setCondition (self, CONDITION_CONVERSABLE);

		return SCRIPT_CONTINUE;
	}

	trigger OnAttach ()
	{
		setCondition (self, CONDITION_CONVERSABLE);

		return SCRIPT_CONTINUE;
	}

	trigger OnObjectMenuRequest (obj_id player, menu_info menuInfo)
	{
		int menu = menuInfo.addRootMenu (menu_info_types.CONVERSE_START, null);
		menu_info_data menuInfoData = menuInfo.getMenuItemById (menu);
		menuInfoData.setServerNotify (false);
		setCondition (self, CONDITION_CONVERSABLE);

		return SCRIPT_CONTINUE;
	}

	trigger OnIncapacitated (obj_id killer)
	{
		clearCondition (self, CONDITION_CONVERSABLE);
		detachScript (self, "conversation.3rd_character_room_three_water_controls");

		return SCRIPT_CONTINUE;
	}


	// ======================================================================
	// Script Triggers
	// ======================================================================

	//-- This function should move to base_class.java
	boolean npcStartConversation(obj_id player, obj_id npc, string convoName, string_id greetingId, prose_package greetingProse, string_id[] responses)
	{
		Object[] objects = new Object[responses.length];
		System.arraycopy(responses, 0, objects, 0, responses.length);
		return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
	}

	// ----------------------------------------------------------------------

	trigger OnStartNpcConversation (obj_id player)
	{
		obj_id npc = self;

		if (ai_lib.isInCombat (npc) || ai_lib.isInCombat (player))
		{
			return SCRIPT_OVERRIDE;
		}
		//-- [NOTE] Initial Weclome Screen:
		if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
		{
			//-- NPC: Initial Weclome Screen:
			string_id message = new string_id (c_stringFile, "s_3");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: [Access Water Controls]
			boolean hasResponse0 = false;
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: [Smash Controls]
			boolean hasResponse1 = false;
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			//-- PLAYER: [Log off]
			boolean hasResponse2 = false;
			if (3rd_character_room_three_water_controls_condition__defaultCondition (player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse2 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses [] = new string_id [numberOfResponses];

				if (hasResponse0)
				{
					responses [responseIndex++] = new string_id (c_stringFile, "s_4");
				}
				if (hasResponse1)
				{
					responses [responseIndex++] = new string_id (c_stringFile, "s_37");
				}
				if (hasResponse2)
				{
					responses [responseIndex++] = new string_id (c_stringFile, "s_5");
				}
				utils.setScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId", 1);

				npcStartConversation (player, npc, "3rd_character_room_three_water_controls", message, responses);
			}
			else
			{
				chat.chat (npc, player, message);
			}

			return SCRIPT_CONTINUE;
		}

		chat.chat (npc, "Error:  All conditions for OnStartNpcConversation were false.");

		return SCRIPT_CONTINUE;
	}

	// ----------------------------------------------------------------------

	public int OnNpcConversationResponse (string conversationId, obj_id player, string_id response) throws InterruptedException
	{
		if (conversationId != "3rd_character_room_three_water_controls")
		{
			return SCRIPT_CONTINUE;
		}
		obj_id npc = self;

		int branchId = utils.getIntScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

		if (branchId == 1 && 3rd_character_room_three_water_controls_handleBranch1 (player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 2 && 3rd_character_room_three_water_controls_handleBranch2 (player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 3 && 3rd_character_room_three_water_controls_handleBranch3 (player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 7 && 3rd_character_room_three_water_controls_handleBranch7 (player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 11 && 3rd_character_room_three_water_controls_handleBranch11 (player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		chat.chat (npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

		utils.removeScriptVar (player, "conversation.3rd_character_room_three_water_controls.branchId");

		return SCRIPT_CONTINUE;
	}
}