package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.groundquests;
import script.library.trial;
import script.library.utils;

import script.*;

public class ancient_stage_three_terminal extends script.base_script
{
	public static final String c_stringFile = "conversation/ancient_stage_three_terminal";
	public static final String PuzzleStatus = "puzzleStatus";
    public static final String ValveOneOpen = "valveOne";
    public static final String ValveTwoOpen = "valveTwo";
    public static final String ValveThreeOpen = "valveThree";
	public static final String ValveOneAnswer = "valveOneAnswer";
    public static final String ValveTwoAnswer = "valveTwoAnswer";
    public static final String ValveThreeAnswer = "valveThreeAnswer";
    public static final String ROOM_AHEAD = "basement";

	public int assignValveStatus() throws InterruptedException
	{
		utils.setScriptVar(getSelf(), ValveOneAnswer, rand(1,2));
		utils.setScriptVar(getSelf(), ValveTwoAnswer, rand(1,2));
		utils.setScriptVar(getSelf(), ValveThreeAnswer, rand(1,2));
		return SCRIPT_CONTINUE;
	}
	public boolean valveAnswerCheck(obj_id self) throws InterruptedException
	{
		int v1 = utils.getIntScriptVar(getSelf(), ValveOneOpen);
		int v2 = utils.getIntScriptVar(getSelf(), ValveTwoOpen);
		int v3 = utils.getIntScriptVar(getSelf(), ValveThreeOpen);
		int v1a = utils.getIntScriptVar(getSelf(), ValveOneAnswer);
		int v2a = utils.getIntScriptVar(getSelf(), ValveTwoAnswer);
		int v3a = utils.getIntScriptVar(getSelf(), ValveThreeAnswer);
		if (v1 == v1a && v2 == v2a && v3 == v3a)
		{
			openBasement(self);
			return true;
		}
		return false;
	}
	public void openBasement(obj_id self) throws InterruptedException
	{
		obj_id building = getTopMostContainer(self);
		if (!trial.isCellPublic(self, ROOM_AHEAD))
		{
			trial.makeCellPublic(building, ROOM_AHEAD);
		}
	}
	public boolean ancient_stage_three_terminal_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
	{
		return true;
	}
	public boolean ancient_stage_three_terminal_condition_puzzleSolved(obj_id player, obj_id npc) throws InterruptedException
	{
		return true;
	}
	public String puzzleStatus(obj_id player, obj_id npc) throws InterruptedException
	{
        boolean status = utils.getBooleanScriptVar(npc, PuzzleStatus);
		if (status)
		{
			return new String("Operational");
		}
		return new String("Malfunctioning");
	}
	public String valveUpdateStatus(obj_id self, int valve) throws InterruptedException
	{
		String valveStr = "filler";
		switch (valve)
		{
			case 1:
				valveStr = ValveOneOpen;
			break;
			case 2:
				valveStr = ValveTwoOpen;
			break;
			case 3:
				valveStr = ValveThreeOpen;
			break;
		}
        int status = utils.getIntScriptVar(self, valveStr);
		if (status == 2)
		{
			return new String("Open");
		}
		else if (status == 1)
		{
			return new String("Closed");
		}
		return new String("Jammed");
	}

	public int ancient_stage_three_terminal_handleBranch1(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] Initial Weclome Screen:
		//-- NPC: Initial Weclome Screen:

		//--[RESPONSE NOTE][Access Water Controls]
		//-- PLAYER:[Access Water Controls]
		if (response.equals("s_6"))
		{
			//--[NOTE] Current waterflow status:[Malfunctioning]
			if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
			{
				//-- NPC: Current waterflow status:[Malfunctioning]
				string_id message = new string_id(c_stringFile, "s_8");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER:[Access Valve #1 Controls]
				boolean hasResponse0 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER:[Access Valve #2 Controls]
				boolean hasResponse1 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER:[Access Valve #3 Controls]
				boolean hasResponse2 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];

					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_10");
					}
					if (hasResponse1)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_23");
					}
					if (hasResponse2)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_42");
					}
					if (valveAnswerCheck(npc))
					{
						groundquests.sendSignal(player, "waterFixed");
						message = new string_id(c_stringFile, "puzzle_finished");
						utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
						npcEndConversationWithMessage(player, message);
					}
					utils.setScriptVar(player, "conversation.ancient_stage_three_terminal.branchId", 2);
                    prose_package pp = new prose_package();
                    message = new string_id(c_stringFile, "s_8");
                    pp.stringId = message;
					String status = "Current Waterflow Status: ["+puzzleStatus(player, npc)+"]"+"\n\nValve 1: "+valveUpdateStatus(npc, 1)+"\nValve 2: "+valveUpdateStatus(npc, 2)+"\nValve 3: "+valveUpdateStatus(npc, 3);
                    pp.other.set(status);
					npcSpeak(player, pp);
					npcSetConversationResponses(player, responses);
				}
				else
				{
					utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");

					npcEndConversationWithMessage(player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		/*--[RESPONSE NOTE][Smash Controls]
		//-- PLAYER:[Smash Controls]
		if (response.equals("s_62"))
		{
			//--[NOTE] 5% chance this auto-solves otherwise this causes flooding that drastically slows down the player but after XX seconds a water system kicks in to drain allowing them through.
			if (ancient_stage_three_terminal_condition_canAttemptSmash(player, npc))
			{
				//-- NPC: 5% chance this auto-solves otherwise this causes flooding that drastically slows down the player but after XX seconds a water system kicks in to drain allowing them through.
				string_id message = new string_id(c_stringFile, "s_64");
				if (valveAnswerCheck(npc))
					{
						groundquests.sendSignal(player, "waterFixed");
						message = new string_id(c_stringFile, "puzzle_finished");
						utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
						npcEndConversationWithMessage(player, message);
					}
				utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");

				npcEndConversationWithMessage(player, message);

				return SCRIPT_CONTINUE;
			}

		}*/

		return SCRIPT_DEFAULT;
	}

	public int ancient_stage_three_terminal_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] Current waterflow status:[Malfunctioning]
		//-- NPC: Current waterflow status:[Malfunctioning]

		//--[RESPONSE NOTE][Access Valve #1 Controls]
		//-- PLAYER:[Access Valve #1 Controls]
		if (response.equals("s_10"))
		{
			//--[NOTE] Please select an option: Valve 1
			if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
			{
				//-- NPC: Please select an option: Valve 1
				string_id message = new string_id(c_stringFile, "s_13");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER:[Open Valve #1]
				boolean hasResponse0 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER:[Close Valve #1]
				boolean hasResponse1 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER:[Return to Menu]
				boolean hasResponse2 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];

					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_14");
					}
					if (hasResponse1)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_17");
					}
					if (hasResponse2)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_20");
					}
					if (valveAnswerCheck(npc))
					{
						groundquests.sendSignal(player, "waterFixed");
						message = new string_id(c_stringFile, "puzzle_finished");
						utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
						npcEndConversationWithMessage(player, message);
					}
					utils.setScriptVar(player, "conversation.ancient_stage_three_terminal.branchId", 3);
					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
					utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
					npcEndConversationWithMessage(player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//--[RESPONSE NOTE][Access Valve #2 Controls]
		//-- PLAYER:[Access Valve #2 Controls]
		if (response.equals("s_23"))
		{
			//--[NOTE] Please select an option: Valve 2
			if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
			{
				//-- NPC: Please select an option: Valve 2
				string_id message = new string_id(c_stringFile, "s_25");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER:[Open Valve #2]
				boolean hasResponse0 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER:[Close Valve #2]
				boolean hasResponse1 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER:[Return to Menu]
				boolean hasResponse2 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];

					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_30");
					}
					if (hasResponse1)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_34");
					}
					if (hasResponse2)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_38");
					}
					if (valveAnswerCheck(npc))
					{
						groundquests.sendSignal(player, "waterFixed");
						message = new string_id(c_stringFile, "puzzle_finished");
						utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
						npcEndConversationWithMessage(player, message);
					}
					utils.setScriptVar(player, "conversation.ancient_stage_three_terminal.branchId", 7);
					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
					utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");

					npcEndConversationWithMessage(player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//--[RESPONSE NOTE][Access Valve #3 Controls]
		//-- PLAYER:[Access Valve #3 Controls]
		if (response.equals("s_42"))
		{
			//--[NOTE] Please select an option: Valve 3
			if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
			{
				//-- NPC: Please select an option: Valve 3
				string_id message = new string_id(c_stringFile, "s_44");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER:[Open Valve #3]
				boolean hasResponse0 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER:[Close Valve #3]
				boolean hasResponse1 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				//-- PLAYER:[Return to Menu]
				boolean hasResponse2 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];

					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_46");
					}
					if (hasResponse1)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_50");
					}
					if (hasResponse2)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_54");
					}
					if (valveAnswerCheck(npc))
					{
						groundquests.sendSignal(player, "waterFixed");
						message = new string_id(c_stringFile, "puzzle_finished");
						utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
						npcEndConversationWithMessage(player, message);
					}
					utils.setScriptVar(player, "conversation.ancient_stage_three_terminal.branchId", 11);
					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
					utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");

					npcEndConversationWithMessage(player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	// ----------------------------------------------------------------------

	public int ancient_stage_three_terminal_handleBranch3(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] Please select an option: Valve 1
		//-- NPC: Please select an option: Valve 1

		//--[RESPONSE NOTE][Open Valve #1]
		//-- PLAYER:[Open Valve #1]
		if (response.equals("s_14"))
		{
			if (valveAnswerCheck(npc))
			{
				string_id message = new string_id(c_stringFile, "puzzle_finished");
				utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
				npcEndConversationWithMessage(player, message);
			}
			utils.setScriptVar(npc, ValveOneOpen, 2);
			//--[NOTE] Return to Access Valve #1 Controls
			if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
			{
				//-- NPC: Return to Access Valve #1 Controls
				string_id message = new string_id(c_stringFile, "v1open");
				int numberOfResponses = 0;
				boolean hasResponse = false;
				boolean hasResponse0 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}
				boolean hasResponse1 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}
				boolean hasResponse2 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}
				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];
					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_14");
					}
					if(hasResponse1)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_17");
					}
					if(hasResponse2)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_20");
					}
					if (valveAnswerCheck(npc))
					{
						groundquests.sendSignal(player, "waterFixed");
						message = new string_id(c_stringFile, "puzzle_finished");
						utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
						npcEndConversationWithMessage(player, message);
					}
					utils.setScriptVar(player, "conversation.ancient_stage_three_terminal.branchId", 3);
					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
				utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
				npcEndConversationWithMessage(player, message);
				}
				return SCRIPT_CONTINUE;
			}

		}

		//--[RESPONSE NOTE][Close Valve #1]
		//-- PLAYER:[Close Valve #1]
		if (response.equals("s_17"))
		{
            utils.setScriptVar(npc, ValveOneOpen, 1);
			//--[NOTE] Return to Access Valve #1 Controls
			if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
			{
				//-- NPC: Return to Access Valve #1 Controls
				string_id message = new string_id(c_stringFile, "v1close");
				int numberOfResponses = 0;
				boolean hasResponse = false;
				boolean hasResponse0 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}
				boolean hasResponse1 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}
				boolean hasResponse2 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}
				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];
					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_14");
					}
					if(hasResponse1)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_17");
					}
					if(hasResponse2)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_20");
					}
					if (valveAnswerCheck(npc))
					{
						groundquests.sendSignal(player, "waterFixed");
						message = new string_id(c_stringFile, "puzzle_finished");
						utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
						npcEndConversationWithMessage(player, message);
					}
					utils.setScriptVar(player, "conversation.ancient_stage_three_terminal.branchId", 3);
					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
				utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
				npcEndConversationWithMessage(player, message);
				}
				return SCRIPT_CONTINUE;
			}

		}

		//--[RESPONSE NOTE][Return to Menu]
		//-- PLAYER:[Return to Menu]
		if (response.equals("s_20"))
		{
			//--[NOTE] Return to Current Waterflow Status
			if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
			{
				//-- NPC: Return to Current Waterflow Status
				string_id message = new string_id(c_stringFile, "s_8");
				int numberOfResponses = 0;
				boolean hasResponse = false;
				boolean hasResponse0 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}
				boolean hasResponse1 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}
				boolean hasResponse2 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}
				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];
					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_10");
					}
					if(hasResponse1)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_23");
					}
					if(hasResponse2)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_42");
					}
					if (valveAnswerCheck(npc))
					{
						groundquests.sendSignal(player, "waterFixed");
						message = new string_id(c_stringFile, "puzzle_finished");
						utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
						npcEndConversationWithMessage(player, message);
					}
					utils.setScriptVar(player, "conversation.ancient_stage_three_terminal.branchId", 2);
                    prose_package pp = new prose_package();
                    message = new string_id(c_stringFile, "s_8");
                    pp.stringId = message;
                    String status = "Current Waterflow Status: ["+puzzleStatus(player, npc)+"]"+"\n\nValve 1: "+valveUpdateStatus(npc, 1)+"\nValve 2: "+valveUpdateStatus(npc, 2)+"\nValve 3: "+valveUpdateStatus(npc, 3);
                    pp.other.set(status);
					npcSpeak(player, pp);
					npcSetConversationResponses(player, responses);
				}
				else
				{
				utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
				npcEndConversationWithMessage(player, message);
				}
				return SCRIPT_CONTINUE;
			}
		}
		return SCRIPT_DEFAULT;
	}

	// ----------------------------------------------------------------------

	public int ancient_stage_three_terminal_handleBranch7(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] Please select an option: Valve 2
		//-- NPC: Please select an option: Valve 2

		//--[RESPONSE NOTE][Open Valve #2]
		//-- PLAYER:[Open Valve #2]
		if (response.equals("s_30"))
		{
            utils.setScriptVar(npc, ValveTwoOpen, 2);
			//--[NOTE] Return to Access Valve #2 Controls
			if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
			{
				//-- NPC: Return to Access Valve #2 Controls
				string_id message = new string_id(c_stringFile, "v2open");
				int numberOfResponses = 0;
				boolean hasResponse = false;
				boolean hasResponse0 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}
				boolean hasResponse1 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}
				boolean hasResponse2 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}
				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];
					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_30");
					}
					if(hasResponse1)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_34");
					}
					if(hasResponse2)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_38");
					}
					if (valveAnswerCheck(npc))
					{
						groundquests.sendSignal(player, "waterFixed");
						message = new string_id(c_stringFile, "puzzle_finished");
						utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
						npcEndConversationWithMessage(player, message);
					}
					utils.setScriptVar(player, "conversation.ancient_stage_three_terminal.branchId", 7);
					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
				utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
				npcEndConversationWithMessage(player, message);
				}
				return SCRIPT_CONTINUE;
			}

		}

		//--[RESPONSE NOTE][Close Valve #2]
		//-- PLAYER:[Close Valve #2]
		if (response.equals("s_34"))
		{
            utils.setScriptVar(npc, ValveTwoOpen, 1);
			//--[NOTE] Return to Access Valve #2 Controls
			if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
			{
				//-- NPC: Return to Access Valve #2 Controls
				string_id message = new string_id(c_stringFile, "v2close");
				int numberOfResponses = 0;
				boolean hasResponse = false;
				boolean hasResponse0 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}
				boolean hasResponse1 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}
				boolean hasResponse2 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}
				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];
					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_30");
					}
					if(hasResponse1)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_34");
					}
					if(hasResponse2)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_38");
					}
					if (valveAnswerCheck(npc))
					{
						groundquests.sendSignal(player, "waterFixed");
						message = new string_id(c_stringFile, "puzzle_finished");
						utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
						npcEndConversationWithMessage(player, message);
					}
					utils.setScriptVar(player, "conversation.ancient_stage_three_terminal.branchId", 7);
					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
				utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
				npcEndConversationWithMessage(player, message);
				}
				return SCRIPT_CONTINUE;
			}

		}

		//--[RESPONSE NOTE][Return to Menu]
		//-- PLAYER:[Return to Menu]
		if (response.equals("s_38"))
		{
			//--[NOTE] Return to Current Waterflow Status
			if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
			{
				//-- NPC: Return to Current Waterflow Status
				string_id message = new string_id(c_stringFile, "s_8");
				int numberOfResponses = 0;
				boolean hasResponse = false;
				boolean hasResponse0 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}
				boolean hasResponse1 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}
				boolean hasResponse2 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}
				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];
					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_10");
					}
					if(hasResponse1)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_23");
					}
					if(hasResponse2)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_42");
					}
					if (valveAnswerCheck(npc))
					{
						groundquests.sendSignal(player, "waterFixed");
						message = new string_id(c_stringFile, "puzzle_finished");
						utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
						npcEndConversationWithMessage(player, message);
					}
					utils.setScriptVar(player, "conversation.ancient_stage_three_terminal.branchId", 2);
                    prose_package pp = new prose_package();
                    message = new string_id(c_stringFile, "s_8");
                    pp.stringId = message;
                    String status = "Current Waterflow Status: ["+puzzleStatus(player, npc)+"]"+"\n\nValve 1: "+valveUpdateStatus(npc, 1)+"\nValve 2: "+valveUpdateStatus(npc, 2)+"\nValve 3: "+valveUpdateStatus(npc, 3);
                    pp.other.set(status);
					npcSpeak(player, pp);
					npcSetConversationResponses(player, responses);
				}
				else
				{
				utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
				npcEndConversationWithMessage(player, message);
				}
				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	// ----------------------------------------------------------------------

	public int ancient_stage_three_terminal_handleBranch11(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] Please select an option: Valve 3
		//-- NPC: Please select an option: Valve 3

		//--[RESPONSE NOTE][Open Valve #3]
		//-- PLAYER:[Open Valve #3]
		if (response.equals("s_46"))
		{
			if (valveAnswerCheck(npc))
			{
				string_id message = new string_id(c_stringFile, "puzzle_finished");
				utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
				npcEndConversationWithMessage(player, message);
			}
            utils.setScriptVar(npc, ValveThreeOpen, 2);
			//--[NOTE] Return to Access Valve #3 Controls
			if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
			{
				//-- NPC: Return to Access Valve #3 Controls
				string_id message = new string_id(c_stringFile, "v3open");
				int numberOfResponses = 0;
				boolean hasResponse = false;
				boolean hasResponse0 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}
				boolean hasResponse1 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}
				boolean hasResponse2 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}
				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];
					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_46");
					}
					if(hasResponse1)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_50");
					}
					if(hasResponse2)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_54");
					}
					if (valveAnswerCheck(npc))
					{
						groundquests.sendSignal(player, "waterFixed");
						message = new string_id(c_stringFile, "puzzle_finished");
						utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
						npcEndConversationWithMessage(player, message);
					}
					utils.setScriptVar(player, "conversation.ancient_stage_three_terminal.branchId", 11);
					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
				utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
				npcEndConversationWithMessage(player, message);
				}
				return SCRIPT_CONTINUE;
			}

		}

		//--[RESPONSE NOTE][Close Valve #3]
		//-- PLAYER:[Close Valve #3]
		if (response.equals("s_50"))
		{
			if (valveAnswerCheck(npc))
			{
				string_id message = new string_id(c_stringFile, "puzzle_finished");
				utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
				npcEndConversationWithMessage(player, message);
			}
            utils.setScriptVar(npc, ValveThreeOpen, 1);
			//--[NOTE] Return to Access Valve #3 Controls
			if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
			{
				//-- NPC: Return to Access Valve #3 Controls
				string_id message = new string_id(c_stringFile, "v3close");
				int numberOfResponses = 0;
				boolean hasResponse = false;
				boolean hasResponse0 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}
				boolean hasResponse1 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}
				boolean hasResponse2 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}
				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];
					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_46");
					}
					if(hasResponse1)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_50");
					}
					if(hasResponse2)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_54");
					}
					if (valveAnswerCheck(npc))
					{
						groundquests.sendSignal(player, "waterFixed");
						message = new string_id(c_stringFile, "puzzle_finished");
						utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
						npcEndConversationWithMessage(player, message);
					}
					utils.setScriptVar(player, "conversation.ancient_stage_three_terminal.branchId", 11);
					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
				utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
				npcEndConversationWithMessage(player, message);
				}
				return SCRIPT_CONTINUE;
			}

		}

		//--[RESPONSE NOTE][Return to Menu]
		//-- PLAYER:[Return to Menu]
		if (response.equals("s_54"))
		{
			//--[NOTE] Return to Current Waterflow Status
			if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
			{
				//-- NPC: Return to Current Waterflow Status
				string_id message = new string_id(c_stringFile, "s_8");
				int numberOfResponses = 0;
				boolean hasResponse = false;
				boolean hasResponse0 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}
				boolean hasResponse1 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}
				boolean hasResponse2 = false;
				if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse2 = true;
				}
				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];
					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_10");
					}
					if(hasResponse1)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_23");
					}
					if(hasResponse2)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_42");
					}
					if (valveAnswerCheck(npc))
					{
						groundquests.sendSignal(player, "waterFixed");
						message = new string_id(c_stringFile, "puzzle_finished");
						utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
						npcEndConversationWithMessage(player, message);
					}
					utils.setScriptVar(player, "conversation.ancient_stage_three_terminal.branchId", 2);
                    prose_package pp = new prose_package();
                    pp.stringId = message;
                    String status = "Current Waterflow Status: ["+puzzleStatus(player, npc)+"]"+"\n\nValve 1: "+valveUpdateStatus(npc, 1)+"\nValve 2: "+valveUpdateStatus(npc, 2)+"\nValve 3: "+valveUpdateStatus(npc, 3);
                    pp.other.set(status);
					npcSpeak(player, pp);
					npcSetConversationResponses(player, responses);
				}
				else
				{
				utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");
				npcEndConversationWithMessage(player, message);
				}
				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}
	public int OnInitialize(obj_id self) throws InterruptedException
	{
		if ((!isTangible(self)) ||(isPlayer(self)))
		{
			detachScript(self, "conversation.ancient_stage_three_terminal");
		}
		setCondition(self, CONDITION_CONVERSABLE);

		return SCRIPT_CONTINUE;
	}

	public int OnAttach(obj_id self) throws InterruptedException
	{
		//set assign here
		setCondition(self, CONDITION_CONVERSABLE);
		assignValveStatus();
		utils.setScriptVar(self, ValveOneOpen, 0);
		utils.setScriptVar(self, ValveTwoOpen, 0);
		utils.setScriptVar(self, ValveThreeOpen, 0);
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
		detachScript(self, "conversation.ancient_stage_three_terminal");

		return SCRIPT_CONTINUE;
	}

	//-- This function should move to base_class.java
	public boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses) throws InterruptedException
	{
		Object[] objects = new Object[responses.length];
		System.arraycopy(responses, 0, objects, 0, responses.length);
		return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
	}

	public int OnStartNpcConversation(obj_id self, obj_id player) throws InterruptedException
	{
		obj_id npc = self;
		groundquests.sendSignal(player, "fixWater");
		if (ai_lib.isInCombat(npc) || ai_lib.isInCombat(player))
			return SCRIPT_OVERRIDE;

		//--[NOTE] Initial Weclome Screen:
		if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
		{
			//-- NPC: Initial Weclome Screen:
			string_id message = new string_id(c_stringFile, "s_4");
			int numberOfResponses = 0;
			boolean hasResponse = false;
			//-- PLAYER:[Access Water Controls]
			boolean hasResponse0 = false;
			if (ancient_stage_three_terminal_condition__defaultCondition(player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}
			/*-- PLAYER:[Smash Controls]
			boolean hasResponse1 = false;
			if (ancient_stage_three_terminal_condition_canAttemptSmash(player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}*/
			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses[] = new string_id[numberOfResponses];

				if (hasResponse0)
				{
					responses[responseIndex++] = new string_id(c_stringFile, "s_6");
				}
				/* Smash Controls
				if (hasResponse1)
				{
					responses[responseIndex++] = new string_id(c_stringFile, "s_62");
				}*/
				utils.setScriptVar(player, "conversation.ancient_stage_three_terminal.branchId", 1);
				npcStartConversation(player, npc, "ancient_stage_three_terminal", message, responses);
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
		if (!conversationId.equals("ancient_stage_three_terminal"))
		{
			return SCRIPT_CONTINUE;
		}
		obj_id npc = self;

		int branchId = utils.getIntScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");

		if (branchId == 1 && ancient_stage_three_terminal_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 2 && ancient_stage_three_terminal_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 3 && ancient_stage_three_terminal_handleBranch3(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 7 && ancient_stage_three_terminal_handleBranch7(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 11 && ancient_stage_three_terminal_handleBranch11(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

		utils.removeScriptVar(player, "conversation.ancient_stage_three_terminal.branchId");

		return SCRIPT_CONTINUE;
	}
}