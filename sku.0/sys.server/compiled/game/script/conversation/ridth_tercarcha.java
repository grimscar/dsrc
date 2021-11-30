package script.conversation;

import script.library.ai_lib;
import script.library.chat;
import script.library.utils;

import script.*;


public class ridth_tercarcha extends script.base_script
{
	public ridth_tercarcha()
	{
	}
	public static String c_stringFile = "conversation/ridth_tercarcha";

	public boolean ridth_tercarcha_condition__defaultCondition(obj_id player, obj_id npc) throws InterruptedException
	{
		return true;
	}

	public boolean ridth_tercarcha_condition_rejectedFirstTime(obj_id player, obj_id npc) throws InterruptedException
	{
	// rejected his initial quest
		return true;
	}

	public boolean ridth_tercarcha_condition_doesPlayerQualify(obj_id player, obj_id npc) throws InterruptedException
	{
	//Are they old enough?
		return true;
	}

	public boolean ridth_tercarcha_condition_isOnQuest(obj_id player, obj_id npc) throws InterruptedException
	{
	// Player is actively on the main quest to collect his 5 DNA collections
		return true;
	}

	public boolean ridth_tercarcha_condition_completedCollection(obj_id player, obj_id npc) throws InterruptedException
	{
	// Completed the collection, now talking to him will grant the 3rd Character Slot
		return true;
	}

	public boolean ridth_tercarcha_condition_questDoneRepeat(obj_id player, obj_id npc) throws InterruptedException
	{
	// Player has already finished collection and have a 3rd character slot. Just want to repeat.
		return true;
	}

	public boolean ridth_tercarcha_condition_isOnQuestRepeat(obj_id player, obj_id npc) throws InterruptedException
	{
	// Player is on the quest, but the repeat version. So varies his conversation some from before
		return true;
	}

	public boolean ridth_tercarcha_condition_loopingQuestLine(obj_id player, obj_id npc) throws InterruptedException
	{
		return true;
	}

	public int ridth_tercarcha_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] *Back for more work?*
		//-- NPC: *Back for more work?*

		//--[RESPONSE NOTE] No, I'm still looking for the bunker.
		//-- PLAYER: No, I'm still looking for the bunker.
		if (response.equals("s_46"))
		{
			//--[NOTE] *Wasting time as usual, however, it is your own time you waste so it doesn't affect me now.* He chuckles slightly as he turns back to his work.
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: *Wasting time as usual, however, it is your own time you waste so it doesn't affect me now.* He chuckles slightly as he turns back to his work.
				string_id message = new string_id(c_stringFile, "s_47");
				utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

				npcEndConversationWithMessage(player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch4(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] *Good to see you again; how has your research gone.* He looks you over. *Are you needing another bunker location?*
		//-- NPC: *Good to see you again; how has your research gone.* He looks you over. *Are you needing another bunker location?*

		//--[RESPONSE NOTE] Yes please.
		//-- PLAYER: Yes please.
		if (response.equals("s_40"))
		{
			//--[NOTE] He nods his head pulling out a small data disc to transfer to you. *Here are the coordinates just like before. Be safe out there.*
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: He nods his head pulling out a small data disc to transfer to you. *Here are the coordinates just like before. Be safe out there.*
				string_id message = new string_id(c_stringFile, "s_41");
				utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

				npcEndConversationWithMessage(player, message);

				return SCRIPT_CONTINUE;
			}

		}

		//--[RESPONSE NOTE] Good just wanted to say hi.
		//-- PLAYER: Good just wanted to say hi.
		if (response.equals("s_43"))
		{
			//--[NOTE] *Oh, uh hi then?* he shrugs as he turns back to his work. *I don't have time for idle chatter.*
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: *Oh, uh hi then?* he shrugs as he turns back to his work. *I don't have time for idle chatter.*
				string_id message = new string_id(c_stringFile, "s_44");
				utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

				npcEndConversationWithMessage(player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch7(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE]
		//-- NPC: *You've gathered everything we need?* the man asks, turning to you with an eager look.

		//--[RESPONSE NOTE] Yes, here are the samples.
		//-- PLAYER: Yes, here are the samples.
		if (response.equals("s_32"))
		{
			//--[NOTE] *Excellent!* he echoes out as he grabs them from you, quickly turning to his machines. *You have done a wonderful job! I have everything already set up and ready. Let me insert this right here.* He states as he slides the samples into position. Pulling a nearby lever on the machine causes electricity to flow through the room as the lights dim.
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: *Excellent!* he echoes out as he grabs them from you, quickly turning to his machines. *You have done a wonderful job! I have everything already set up and ready. Let me insert this right here.* He states as he slides the samples into position. Pulling a nearby lever on the machine causes electricity to flow through the room as the lights dim.
				string_id message = new string_id(c_stringFile, "s_33");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER:[Continue watching]
				boolean hasResponse0 = false;
				if (ridth_tercarcha_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];

					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_34");
					}
					utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 8);

					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
					utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

					npcEndConversationWithMessage(player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch8(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] *Excellent!* he echoes out as he grabs them from you, quickly turning to his machines. *You have done a wonderful job! I have everything already set up and ready. Let me insert this right here.* He states as he slides the samples into position. Pulling a nearby lever on the machine causes electricity to flow through the room as the lights dim.
		//-- NPC: *Excellent!* he echoes out as he grabs them from you, quickly turning to his machines. *You have done a wonderful job! I have everything already set up and ready. Let me insert this right here.* He states as he slides the samples into position. Pulling a nearby lever on the machine causes electricity to flow through the room as the lights dim.

		//--[RESPONSE NOTE][Continue watching]
		//-- PLAYER:[Continue watching]
		if (response.equals("s_34"))
		{
			//--[NOTE] Loud sparks sound off as the lights flicker rapidly. After some time, the process ends as the man walks over towards a chamber. The man pushes some buttons as shielding around one of the containers open. You see the floating figure of a being in the Bacta tank. *Behold! We have created life itself and not just a clone replica!*
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: Loud sparks sound off as the lights flicker rapidly. After some time, the process ends as the man walks over towards a chamber. The man pushes some buttons as shielding around one of the containers open. You see the floating figure of a being in the Bacta tank. *Behold! We have created life itself and not just a clone replica!*
				string_id message = new string_id(c_stringFile, "s_35");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER:[Continue to listen]
				boolean hasResponse0 = false;
				if (ridth_tercarcha_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];

					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_36");
					}
					utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 9);

					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
					utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

					npcEndConversationWithMessage(player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch9(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] Loud sparks sound off as the lights flicker rapidly. After some time, the process ends as the man walks over towards a chamber. The man pushes some buttons as shielding around one of the containers open. You see the floating figure of a being in the Bacta tank. *Behold! We have created life itself and not just a clone replica!*
		//-- NPC: Loud sparks sound off as the lights flicker rapidly. After some time, the process ends as the man walks over towards a chamber. The man pushes some buttons as shielding around one of the containers open. You see the floating figure of a being in the Bacta tank. *Behold! We have created life itself and not just a clone replica!*

		//--[RESPONSE NOTE][Continue to listen]
		//-- PLAYER:[Continue to listen]
		if (response.equals("s_36"))
		{
			//--[NOTE] *We will go down in history for this, and the possibilities are endless at this point. I will never forget you and your deeds to prove that my theory was not crazy. They are all the crazy ones. If you wish to continue the research with me. I can give you locations as I gather them for you to gather more samples.*
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: *We will go down in history for this, and the possibilities are endless at this point. I will never forget you and your deeds to prove that my theory was not crazy. They are all the crazy ones. If you wish to continue the research with me. I can give you locations as I gather them for you to gather more samples.*
				string_id message = new string_id(c_stringFile, "s_37");
				utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

				npcEndConversationWithMessage(player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch11(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] *You've gathered everything we need?* the man asks turning to you with an eager look.
		//-- NPC: *You've gathered everything we need?* the man asks turning to you with an eager look.

		//--[RESPONSE NOTE] No, not yet.
		//-- PLAYER: No, not yet.
		if (response.equals("s_29"))
		{
			//--[NOTE] *You enjoy wasting time, don't you?* He lets out a sigh shaking his head at you. *Please don't disturb me while you still have a task to complete.*
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: *You enjoy wasting time, don't you?* He lets out a sigh shaking his head at you. *Please don't disturb me while you still have a task to complete.*
				string_id message = new string_id(c_stringFile, "s_30");
				utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

				npcEndConversationWithMessage(player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch13(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] *Welcome back! Ready to go to the next location?*
		//-- NPC: *Welcome back! Ready to go to the next location?*

		//--[RESPONSE NOTE] Yes
		//-- PLAYER: Yes
		if (response.equals("s_49"))
		{
			//--[NOTE] He nods his head while loading the next bunker up to your datapad. *Coordinates are set, make haste and be safe as always!*
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: He nods his head while loading the next bunker up to your datapad. *Coordinates are set, make haste and be safe as always!*
				string_id message = new string_id(c_stringFile, "s_52");
				utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

				npcEndConversationWithMessage(player, message);

				return SCRIPT_CONTINUE;
			}

		}

		//--[RESPONSE NOTE] Not at this time.
		//-- PLAYER: Not at this time.
		if (response.equals("s_50"))
		{
			//--[NOTE] *When you are ready just return to me then.*
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: *When you are ready just return to me then.*
				string_id message = new string_id(c_stringFile, "s_51");
				utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

				npcEndConversationWithMessage(player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch16(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] *You are too predictable; I knew you would return.* He pulls some equipment out and looks over at you. *Are you ready to commit now?*
		//-- NPC: *You are too predictable; I knew you would return.* He pulls some equipment out and looks over at you. *Are you ready to commit now?*

		//--[RESPONSE NOTE] Yes
		//-- PLAYER: Yes
		if (response.equals("s_20"))
		{
			//--[NOTE] *Take this device, and it will help guide you to the locations around the Galaxy. If you allow me, your Biological data will be scanned by these bunkers, and I found a way to spoof part of this security to allow you entry. Without it would mean sure death, though you will have to rush before the bunkers self-destruct protocol can finish.*
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: *Take this device, and it will help guide you to the locations around the Galaxy. If you allow me, your Biological data will be scanned by these bunkers, and I found a way to spoof part of this security to allow you entry. Without it would mean sure death, though you will have to rush before the bunkers self-destruct protocol can finish.*
				string_id message = new string_id(c_stringFile, "s_21");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER:[Step closer]
				boolean hasResponse0 = false;
				if (ridth_tercarcha_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];

					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_22");
					}
					utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 17);

					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
					utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

					npcEndConversationWithMessage(player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//--[RESPONSE NOTE] No, I can't shake this bad feeling.
		//-- PLAYER: No, I can't shake this bad feeling.
		if (response.equals("s_26"))
		{
			//--[NOTE] *Whatever, you know the drill. Once you find your spine, come back to me.* He waves you off dismissingly.
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: *Whatever, you know the drill. Once you find your spine, come back to me.* He waves you off dismissingly.
				string_id message = new string_id(c_stringFile, "s_27");
				utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

				npcEndConversationWithMessage(player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch17(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] *Take this device, and it will help guide you to the locations around the Galaxy. If you allow me, your Biological data will be scanned by these bunkers, and I found a way to spoof part of this security to allow you entry. Without it would mean sure death, though you will have to rush before the bunkers self-destruct protocol can finish.*
		//-- NPC: *Take this device, and it will help guide you to the locations around the Galaxy. If you allow me, your Biological data will be scanned by these bunkers, and I found a way to spoof part of this security to allow you entry. Without it would mean sure death, though you will have to rush before the bunkers self-destruct protocol can finish.*

		//--[RESPONSE NOTE][Step closer]
		//-- PLAYER:[Step closer]
		if (response.equals("s_22"))
		{
			//--[NOTE] He pulls a device out and scans over your body, and you feel a warm sensation flow through you. *There, and, if you wish to bring help. Please send them to me to offer them the same as you, though do not send me anyone young of mind. I will not work with children.*
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: He pulls a device out and scans over your body, and you feel a warm sensation flow through you. *There, and, if you wish to bring help. Please send them to me to offer them the same as you, though do not send me anyone young of mind. I will not work with children.*
				string_id message = new string_id(c_stringFile, "s_23");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER:[Continue listening]
				boolean hasResponse0 = false;
				if (ridth_tercarcha_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];

					if (hasResponse0)
						responses[responseIndex++] = new string_id(c_stringFile, "s_24");

					utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 18);

					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
					utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

					npcEndConversationWithMessage(player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch18(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] He pulls a device out and scans over your body, and you feel a warm sensation flow through you. *There, and, if you wish to bring help. Please send them to me to offer them the same as you, though do not send me anyone young of mind. I will not work with children.*
		//-- NPC: He pulls a device out and scans over your body, and you feel a warm sensation flow through you. *There, and, if you wish to bring help. Please send them to me to offer them the same as you, though do not send me anyone young of mind. I will not work with children.*

		//--[RESPONSE NOTE][Continue listening]
		//-- PLAYER:[Continue listening]
		if (response.equals("s_24"))
		{
			//--[NOTE] *Alright, that is all you should need then. There is a total of five unique canisters you will need to acquire. Once you finish that collection, return to me, and we can finish! One final note, again, if you bring friends. I did some research and there are various terminals detailing specific information that someone specialized in the given profession should be able to use to generate additional canisters* He nods dismissvely after.
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: *Alright, that is all you should need then. There is a total of five unique canisters you will need to acquire. Once you finish that collection, return to me, and we can finish! One final note, again, if you bring friends. I did some research and there are various terminals detailing specific information that someone specialized in the given profession should be able to use to generate additional canisters* He nods dismissvely after.
				string_id message = new string_id(c_stringFile, "s_25");
				utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

				npcEndConversationWithMessage(player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch21(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] The man turns and looks at you. *You seem to have seen some things in your time. I have an eye for someone of age.* He nods for a moment as he glances over his datapad, looking around the room. *Everyone thinks that I am crazy, but I will be the one to have the last laugh, I tell you.* He gestures you closer.
		//-- NPC: The man turns and looks at you. *You seem to have seen some things in your time. I have an eye for someone of age.* He nods for a moment as he glances over his datapad, looking around the room. *Everyone thinks that I am crazy, but I will be the one to have the last laugh, I tell you.* He gestures you closer.

		//--[RESPONSE NOTE][Walk closer]
		//-- PLAYER:[Walk closer]
		if (response.equals("s_5"))
		{
			//--[NOTE] He grins widely as he points towards the cloning chambers. *We have been cloning people for years now, and it is nothing to take one sample and generate replicas over and over. But what if we take that a step further and generate life itself?* He glances again at his datapad, going through some menus.
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: He grins widely as he points towards the cloning chambers. *We have been cloning people for years now, and it is nothing to take one sample and generate replicas over and over. But what if we take that a step further and generate life itself?* He glances again at his datapad, going through some menus.
				string_id message = new string_id(c_stringFile, "s_6");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER:[Continue listening]
				boolean hasResponse0 = false;
				if (ridth_tercarcha_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];

					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_7");
					}
					utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 22);

					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
					utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

					npcEndConversationWithMessage(player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch22(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] He grins widely as he points towards the cloning chambers. *We have been cloning people for years now, and it is nothing to take one sample and generate replicas over and over. But what if we take that a step further and generate life itself?* He glances again at his datapad, going through some menus.
		//-- NPC: He grins widely as he points towards the cloning chambers. *We have been cloning people for years now, and it is nothing to take one sample and generate replicas over and over. But what if we take that a step further and generate life itself?* He glances again at his datapad, going through some menus.

		//--[RESPONSE NOTE][Continue listening]
		//-- PLAYER:[Continue listening]
		if (response.equals("s_7"))
		{
			//--[NOTE] *For years, I have been studying some fragments of data that was recovered from the old archives across the Galaxy. This information leads me to believe there are numerous bunkers long forgotten that contain samples of DNA. If we combine these strands, it could unlock the potential to give us a basis to create a whole new lifeform in the design we want!*
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: *For years, I have been studying some fragments of data that was recovered from the old archives across the Galaxy. This information leads me to believe there are numerous bunkers long forgotten that contain samples of DNA. If we combine these strands, it could unlock the potential to give us a basis to create a whole new lifeform in the design we want!*
				string_id message = new string_id(c_stringFile, "s_8");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER:[Continue listening]
				boolean hasResponse0 = false;
				if (ridth_tercarcha_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];

					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_9");
					}
					utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 23);

					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
					utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

					npcEndConversationWithMessage(player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch23(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] *For years, I have been studying some fragments of data that was recovered from the old archives across the Galaxy. This information leads me to believe there are numerous bunkers long forgotten that contain samples of DNA. If we combine these strands, it could unlock the potential to give us a basis to create a whole new lifeform in the design we want!*
		//-- NPC: *For years, I have been studying some fragments of data that was recovered from the old archives across the Galaxy. This information leads me to believe there are numerous bunkers long forgotten that contain samples of DNA. If we combine these strands, it could unlock the potential to give us a basis to create a whole new lifeform in the design we want!*

		//--[RESPONSE NOTE][Continue listening]
		//-- PLAYER:[Continue listening]
		if (response.equals("s_9"))
		{
			//--[NOTE] *If you help me gather these samples, I will make it worth your time as I can create a life form for you as you see fit to use as you will.* He tilts his head, clearly waiting for a response.
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: *If you help me gather these samples, I will make it worth your time as I can create a life form for you as you see fit to use as you will.* He tilts his head, clearly waiting for a response.
				string_id message = new string_id(c_stringFile, "s_10");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER: Of course, what do I need to do?
				boolean hasResponse0 = false;
				if (ridth_tercarcha_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				//-- PLAYER: On second thought, this isn't a good idea.
				boolean hasResponse1 = false;
				if (ridth_tercarcha_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse1 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];

					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_11");
					}
					if (hasResponse1)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_12");
					}
					utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 24);

					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
					utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

					npcEndConversationWithMessage(player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch24(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] *If you help me gather these samples, I will make it worth your time as I can create a life form for you as you see fit to use as you will.* He tilts his head, clearly waiting for a response.
		//-- NPC: *If you help me gather these samples, I will make it worth your time as I can create a life form for you as you see fit to use as you will.* He tilts his head, clearly waiting for a response.

		//--[RESPONSE NOTE] Of course, what do I need to do?
		//-- PLAYER: Of course, what do I need to do?
		if (response.equals("s_11"))
		{
			//--[NOTE]
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: *I will upload a waypoint to your datapad of a known bunker location. If you allow me, your Biological data will be scanned by these bunkers, and I found a way to spoof part of this security to allow you entry. Without it would mean sure death, though you will have to rush before the bunkers self-destruct protocol can finish.*
				string_id message = new string_id(c_stringFile, "s_13");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER:[Step closer]
				boolean hasResponse0 = false;
				if (ridth_tercarcha_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];

					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_14");
					}
					utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 25);

					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
					utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

					npcEndConversationWithMessage(player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		//--[RESPONSE NOTE] On second thought, this isn't a good idea.
		//-- PLAYER: On second thought, this isn't a good idea.
		if (response.equals("s_12"))
		{
			//--[NOTE] *I had high hopes for you but, if you aren't going to assist, then get out of my way because I have much work to finish. Return when you are ready.*
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: *I had high hopes for you but, if you aren't going to assist, then get out of my way because I have much work to finish. Return when you are ready.*
				string_id message = new string_id(c_stringFile, "s_18");
				utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

				npcEndConversationWithMessage(player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch25(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE]
		//-- NPC: *I will upload a waypoint to your datapad of a known bunker location. If you allow me, your Biological data will be scanned by these bunkers, and I found a way to spoof part of this security to allow you entry. Without it would mean sure death, though you will have to rush before the bunkers self-destruct protocol can finish.*

		//--[RESPONSE NOTE][Step closer]
		//-- PLAYER:[Step closer]
		if (response.equals("s_14"))
		{
			//--[NOTE] He pulls a device out and scans over your body, and you feel a warm sensation flow through you. *There, and, if you wish to bring help. Please send them to me to offer them the same as you, though do not send me anyone young of mind. I will not work with children.*
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: He pulls a device out and scans over your body, and you feel a warm sensation flow through you. *There, and, if you wish to bring help. Please send them to me to offer them the same as you, though do not send me anyone young of mind. I will not work with children.*
				string_id message = new string_id(c_stringFile, "s_15");
				int numberOfResponses = 0;

				boolean hasResponse = false;

				//-- PLAYER:[Continue listening]
				boolean hasResponse0 = false;
				if (ridth_tercarcha_condition__defaultCondition(player, npc))
				{
					++numberOfResponses;
					hasResponse = true;
					hasResponse0 = true;
				}

				if (hasResponse)
				{
					int responseIndex = 0;
					string_id responses[] = new string_id[numberOfResponses];

					if (hasResponse0)
					{
						responses[responseIndex++] = new string_id(c_stringFile, "s_16");
					}
					utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 26);

					npcSpeak(player, message);
					npcSetConversationResponses(player, responses);
				}
				else
				{
					utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

					npcEndConversationWithMessage(player, message);
				}

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int ridth_tercarcha_handleBranch26(obj_id player, obj_id npc, string_id response) throws InterruptedException
	{
		//--[BRANCH NOTE] He pulls a device out and scans over your body, and you feel a warm sensation flow through you. *There, and, if you wish to bring help. Please send them to me to offer them the same as you, though do not send me anyone young of mind. I will not work with children.*
		//-- NPC: He pulls a device out and scans over your body, and you feel a warm sensation flow through you. *There, and, if you wish to bring help. Please send them to me to offer them the same as you, though do not send me anyone young of mind. I will not work with children.*

		//--[RESPONSE NOTE][Continue listening]
		//-- PLAYER:[Continue listening]
		if (response.equals("s_16"))
		{
			//--[NOTE] *Alright, that is all you should need then. There is a total of five unique canisters you will need to acquire. Once you finish that collection, return to me, and we can finish! One final note, again, if you bring friends. I did some research and there are various terminals detailing specific information that someone specialized in the given profession should be able to use to generate additional canisters* He nods dismissvely after.
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				//-- NPC: *Alright, that is all you should need then. There is a total of five unique canisters you will need to acquire. Once you finish that collection, return to me, and we can finish! One final note, again, if you bring friends. I did some research and there are various terminals detailing specific information that someone specialized in the given profession should be able to use to generate additional canisters* He nods dismissvely after.
				string_id message = new string_id(c_stringFile, "s_17");
				utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

				npcEndConversationWithMessage(player, message);

				return SCRIPT_CONTINUE;
			}

		}

		return SCRIPT_DEFAULT;
	}

	public int OnInitialize(obj_id self) throws InterruptedException
	{
		if ((!isTangible(self)) ||(isPlayer(self)))
			detachScript(self, "conversation.ridth_tercarcha");

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
		detachScript(self, "conversation.ridth_tercarcha");

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

		if (ai_lib.isInCombat(npc) || ai_lib.isInCombat(player))
			return SCRIPT_OVERRIDE;

		//--[NOTE] The man looks at you with an uninterested eye. *I can see you're still fresh. Return to me when you have some age behind you, and maybe you will be of use to my project.*
		if (ridth_tercarcha_condition_doesPlayerQualify(player, npc))
		{
			//-- NPC: The man looks at you with an uninterested eye. *I can see you're still fresh. Return to me when you have some age behind you, and maybe you will be of use to my project.*
			string_id message = new string_id(c_stringFile, "s_3");
			chat.chat(npc, player, message);

			return SCRIPT_CONTINUE;
		}

		//--[NOTE] *Back for more work?*
		if (ridth_tercarcha_condition_isOnQuestRepeat(player, npc))
		{
			//-- NPC: *Back for more work?*
			string_id message = new string_id(c_stringFile, "s_45");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: No, I'm still looking for the bunker.
			boolean hasResponse0 = false;
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses[] = new string_id[numberOfResponses];

				if (hasResponse0)
				{
					responses[responseIndex++] = new string_id(c_stringFile, "s_46");
				}
				utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 2);

				npcStartConversation(player, npc, "ridth_tercarcha", message, responses);
			}
			else
			{
				chat.chat(npc, player, message);
			}

			return SCRIPT_CONTINUE;
		}

		//--[NOTE] *Good to see you again; how has your research gone.* He looks you over. *Are you needing another bunker location?*
		if (ridth_tercarcha_condition_questDoneRepeat(player, npc))
		{
			//-- NPC: *Good to see you again; how has your research gone.* He looks you over. *Are you needing another bunker location?*
			string_id message = new string_id(c_stringFile, "s_39");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Yes please.
			boolean hasResponse0 = false;
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: Good just wanted to say hi.
			boolean hasResponse1 = false;
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses[] = new string_id[numberOfResponses];

				if (hasResponse0)
				{
					responses[responseIndex++] = new string_id(c_stringFile, "s_40");
				}
				if (hasResponse1)
				{
					responses[responseIndex++] = new string_id(c_stringFile, "s_43");
				}
				utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 4);

				npcStartConversation(player, npc, "ridth_tercarcha", message, responses);
			}
			else
			{
				chat.chat(npc, player, message);
			}

			return SCRIPT_CONTINUE;
		}

		//--[NOTE]
		if (ridth_tercarcha_condition_completedCollection(player, npc))
		{
			//-- NPC: *You've gathered everything we need?* the man asks, turning to you with an eager look.
			string_id message = new string_id(c_stringFile, "s_31");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Yes, here are the samples.
			boolean hasResponse0 = false;
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses[] = new string_id[numberOfResponses];

				if (hasResponse0)
					responses[responseIndex++] = new string_id(c_stringFile, "s_32");

				utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 7);

				npcStartConversation(player, npc, "ridth_tercarcha", message, responses);
			}
			else
			{
				chat.chat(npc, player, message);
			}

			return SCRIPT_CONTINUE;
		}

		//--[NOTE] *You've gathered everything we need?* the man asks turning to you with an eager look.
		if (ridth_tercarcha_condition_isOnQuest(player, npc))
		{
			//-- NPC: *You've gathered everything we need?* the man asks turning to you with an eager look.
			string_id message = new string_id(c_stringFile, "s_28");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: No, not yet.
			boolean hasResponse0 = false;
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses[] = new string_id[numberOfResponses];

				if (hasResponse0)
				{
					responses[responseIndex++] = new string_id(c_stringFile, "s_29");
				}
				utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 11);

				npcStartConversation(player, npc, "ridth_tercarcha", message, responses);
			}
			else
			{
				chat.chat(npc, player, message);
			}

			return SCRIPT_CONTINUE;
		}

		//--[NOTE] *Welcome back! Ready to go to the next location?*
		if (ridth_tercarcha_condition_loopingQuestLine(player, npc))
		{
			//-- NPC: *Welcome back! Ready to go to the next location?*
			string_id message = new string_id(c_stringFile, "s_48");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Yes
			boolean hasResponse0 = false;
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: Not at this time.
			boolean hasResponse1 = false;
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses[] = new string_id[numberOfResponses];

				if (hasResponse0)
				{
					responses[responseIndex++] = new string_id(c_stringFile, "s_49");
				}
				if (hasResponse1)
				{
					responses[responseIndex++] = new string_id(c_stringFile, "s_50");
				}
				utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 13);

				npcStartConversation(player, npc, "ridth_tercarcha", message, responses);
			}
			else
			{
				chat.chat(npc, player, message);
			}

			return SCRIPT_CONTINUE;
		}

		//--[NOTE] *You are too predictable; I knew you would return.* He pulls some equipment out and looks over at you. *Are you ready to commit now?*
		if (ridth_tercarcha_condition_rejectedFirstTime(player, npc))
		{
			//-- NPC: *You are too predictable; I knew you would return.* He pulls some equipment out and looks over at you. *Are you ready to commit now?*
			string_id message = new string_id(c_stringFile, "s_19");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER: Yes
			boolean hasResponse0 = false;
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			//-- PLAYER: No, I can't shake this bad feeling.
			boolean hasResponse1 = false;
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse1 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses[] = new string_id[numberOfResponses];

				if (hasResponse0)
				{
					responses[responseIndex++] = new string_id(c_stringFile, "s_20");
				}
				if (hasResponse1)
				{
					responses[responseIndex++] = new string_id(c_stringFile, "s_26");
				}
				utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 16);

				npcStartConversation(player, npc, "ridth_tercarcha", message, responses);
			}
			else
			{
				chat.chat(npc, player, message);
			}

			return SCRIPT_CONTINUE;
		}

		//--[NOTE] The man turns and looks at you. *You seem to have seen some things in your time. I have an eye for someone of age.* He nods for a moment as he glances over his datapad, looking around the room. *Everyone thinks that I am crazy, but I will be the one to have the last laugh, I tell you.* He gestures you closer.
		if (ridth_tercarcha_condition__defaultCondition(player, npc))
		{
			//-- NPC: The man turns and looks at you. *You seem to have seen some things in your time. I have an eye for someone of age.* He nods for a moment as he glances over his datapad, looking around the room. *Everyone thinks that I am crazy, but I will be the one to have the last laugh, I tell you.* He gestures you closer.
			string_id message = new string_id(c_stringFile, "s_4");
			int numberOfResponses = 0;

			boolean hasResponse = false;

			//-- PLAYER:[Walk closer]
			boolean hasResponse0 = false;
			if (ridth_tercarcha_condition__defaultCondition(player, npc))
			{
				++numberOfResponses;
				hasResponse = true;
				hasResponse0 = true;
			}

			if (hasResponse)
			{
				int responseIndex = 0;
				string_id responses[] = new string_id[numberOfResponses];

				if (hasResponse0)
				{
					responses[responseIndex++] = new string_id(c_stringFile, "s_5");
				}
				utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 21);

				npcStartConversation(player, npc, "ridth_tercarcha", message, responses);
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
		if (conversationId.equals("ridth_tercarcha"))
		{
			return SCRIPT_CONTINUE;
		}
		obj_id npc = self;

		int branchId = utils.getIntScriptVar(player, "conversation.ridth_tercarcha.branchId");

		if (branchId == 2 && ridth_tercarcha_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 4 && ridth_tercarcha_handleBranch4(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 7 && ridth_tercarcha_handleBranch7(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 8 && ridth_tercarcha_handleBranch8(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 9 && ridth_tercarcha_handleBranch9(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 11 && ridth_tercarcha_handleBranch11(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 13 && ridth_tercarcha_handleBranch13(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 16 && ridth_tercarcha_handleBranch16(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 17 && ridth_tercarcha_handleBranch17(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 18 && ridth_tercarcha_handleBranch18(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 21 && ridth_tercarcha_handleBranch21(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 22 && ridth_tercarcha_handleBranch22(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 23 && ridth_tercarcha_handleBranch23(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 24 && ridth_tercarcha_handleBranch24(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 25 && ridth_tercarcha_handleBranch25(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		if (branchId == 26 && ridth_tercarcha_handleBranch26(player, npc, response) == SCRIPT_CONTINUE)
		{
			return SCRIPT_CONTINUE;
		}
		chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

		utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

		return SCRIPT_CONTINUE;
	}
}