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

	public boolean ridth_tercarcha_condition_collectionCompleted(obj_id player, obj_id npc) throws InterruptedException
	{
		/*
		This is true when the whole questline is finished. Collection is obtained. Player has their 3rd character.
		*/
		return true;
	}

	public boolean ridth_tercarcha_condition_isOnBunkerQuest(obj_id player, obj_id npc) throws InterruptedException
	{
		/*
		This is true when the follow has happened:
		Player has talked to the crazy scientist
		Player has completed the crazed scientists tutorial quest
		Player has accepted to go into ancient bunkers to obtain the DNA for him
		*/
		return true;
	}

    public boolean ridth_tercarcha_condition_tutorialFinished(obj_id player, obj_id npc) throws InterruptedException
    {
        /*
        This is true after the player has completed the tutorial mission from the crazed scientist.
        */
	    return true;
    }

    public boolean ridth_tercarcha_condition_isOnTutorial(obj_id player, obj_id npc) throws InterruptedException
    {
        /*
        This is true after the player has talked to crazed scientist and accepted the quest
        */
	    return true;
    }

    public boolean ridth_tercarcha_condition_rejectedTutorial(obj_id player, obj_id npc) throws InterruptedException
    {
        /*
        This is true if the player tried to talk but rejected his help.
        This allows the player to get an immersive response when they talk to him
        again. He acknowledges their change in heart to help him.
        */
    	return true;
    }

    public boolean ridth_tercarcha_condition_rejectedBunkerMission(obj_id player, obj_id npc) throws InterruptedException
    {
        /*
        This is true if the player rejects the mission and doesn't help.
        */
	    return true;
    }

    public boolean ridth_tercarcha_condition_bunkerQuestComplete(obj_id player, obj_id npc) throws InterruptedException
    {
        /*
        This is true when the 5th DNA sample is returned.
        */
	    return true;
    }

    public boolean ridth_tercarcha_condition_isOnBunkerRepeat(obj_id player, obj_id npc) throws InterruptedException
    {
        /*
        This is true when the player finishes the intial main bunker and only has the 4 to do.
        */
	    return true;
    }


    public int ridth_tercarcha_tokenDI_bunkersCompletedCounter(obj_id player, obj_id npc) throws InterruptedException
    {
        /*
        This keeps track of how many bunkers have been completed.
        There are 5 total so the value should be 0, 1, 2, 3, 4 or 5.
        */
	    return 0;
    }

    public int ridth_tercarcha_handleBranch2(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
	    //--[BRANCH NOTE] You have done a wonderful job!(he exclaims out taking the final sample from you. Turning away from you, the man walks over, placing the DNA into a large device. His arms raise high for a moment showing off a large incubation tube as he pulls al ever that sets the event into motion. Electricity sparks from the machines as small crackles and pop erupt.)
	    //-- NPC: You have done a wonderful job!(he exclaims out taking the final sample from you. Turning away from you, the man walks over, placing the DNA into a large device. His arms raise high for a moment showing off a large incubation tube as he pulls al ever that sets the event into motion. Electricity sparks from the machines as small crackles and pop erupt.)

	    //--[RESPONSE NOTE](Continue watching...)
	    //-- PLAYER:(Continue watching...)
	    if (response.equals("s_33"))
	    {
		//--[NOTE](After a moment, all grow silent. A door opens, and a being steps out.) Behold, we have created LIFE!(he shouts out happily.) Everyone said I was crazy, but I have ascended beyond anything they could ever dream of doing. You will be remembered and immortalized in history; go now before someone finds us here.
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
			    //-- NPC:(After a moment, all grow silent. A door opens, and a being steps out.) Behold, we have created LIFE!(he shouts out happily.) Everyone said I was crazy, but I have ascended beyond anything they could ever dream of doing. You will be remembered and immortalized in history; go now before someone finds us here.
			    string_id message = new string_id(c_stringFile, "s_34");
			    utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

			    npcEndConversationWithMessage(player, message);

    			return SCRIPT_CONTINUE;
		    }

	    }

	    return SCRIPT_DEFAULT;
    }

    public int ridth_tercarcha_handleBranch5(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//--[BRANCH NOTE] You are back sooner than I thought. Have you gathered the DNA?
    	//-- NPC: You are back sooner than I thought. Have you gathered the DNA?

	    //--[RESPONSE NOTE] No, not yet.
	    //-- PLAYER: No, not yet.
	    if (response.equals("s_37"))
	    {
    		//--[NOTE] Then get back out there. You're wasting our time!
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			doAnimationAction(npc, "shoo");

			    //-- NPC: Then get back out there. You're wasting our time!
			    string_id message = new string_id(c_stringFile, "s_38");
			    utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

			    npcEndConversationWithMessage(player, message);

			    return SCRIPT_CONTINUE;
		    }

	    }

	    return SCRIPT_DEFAULT;
    }

    public int ridth_tercarcha_handleBranch7(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//--[BRANCH NOTE] I told you, you would return. We are too close to creating life itself! So you will help me, yes?
    	//-- NPC: I told you, you would return. We are too close to creating life itself! So you will help me, yes?

	    //--[RESPONSE NOTE] Yes.
	    //-- PLAYER: Yes.
	    if (response.equals("s_40"))
	    {
    		//--[NOTE] Perfect, here is the chip that contains all of the information you need for the location of the bunkers.(He hands you the chip and waves you off.) Hurry along now; time is of the essence, my friend!
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			//-- NPC: Perfect, here is the chip that contains all of the information you need for the location of the bunkers.(He hands you the chip and waves you off.) Hurry along now; time is of the essence, my friend!
			    string_id message = new string_id(c_stringFile, "s_42");
			    utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

			    npcEndConversationWithMessage(player, message);

			    return SCRIPT_CONTINUE;
		    }

	    }

	    //--[RESPONSE NOTE]
	    //-- PLAYER: No.
    	if (response.equals("s_41"))
    	{
		    //--[NOTE] Then why would you waste either of our time by acting like you were going to help? Begone from here.
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			//-- NPC: Then why would you waste either of our time by acting like you were going to help? Begone from here.
			    string_id message = new string_id(c_stringFile, "s_43");
			    utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

			    npcEndConversationWithMessage(player, message);

			    return SCRIPT_CONTINUE;
		    }

	    }

	    return SCRIPT_DEFAULT;
    }

    public int ridth_tercarcha_handleBranch10(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//--[BRANCH NOTE](The man eagerly looks towards you) Have you retrieved the device?(he asks as he peers around, clearly in a mix of his insanity and looking out for unwanted attention.)
    	//-- NPC:(The man eagerly looks towards you) Have you retrieved the device?(he asks as he peers around, clearly in a mix of his insanity and looking out for unwanted attention.)

	    //--[RESPONSE NOTE] Yes, here you go.
	    //-- PLAYER: Yes, here you go.
	    if (response.equals("s_45"))
	    {
    		//--[NOTE] Excellent!(he exclaims, grabbing the device from you. He frantically starts manipulating buttons and levers on it while he compares information from his datapad. After a few moments, he ejects a small disc from his datapad and hands it towards you.) If you take this, then you will be able to find the location of the next bunker. You will still help me, won't you?
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			doAnimationAction(npc, "fiddle");

			    //-- NPC: Excellent!(he exclaims, grabbing the device from you. He frantically starts manipulating buttons and levers on it while he compares information from his datapad. After a few moments, he ejects a small disc from his datapad and hands it towards you.) If you take this, then you will be able to find the location of the next bunker. You will still help me, won't you?
			    string_id message = new string_id(c_stringFile, "s_46");
			    int numberOfResponses = 0;

			    boolean hasResponse = false;

			    //-- PLAYER:(Take the data chip)
			    boolean hasResponse0 = false;
			    if (ridth_tercarcha_condition__defaultCondition(player, npc))
			    {
    				++numberOfResponses;
				    hasResponse = true;
				    hasResponse0 = true;
			    }

			    //-- PLAYER: No, that was way too dangerous.
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
					    responses[responseIndex++] = new string_id(c_stringFile, "s_47");
                    }
				    if (hasResponse1)
    				{
                    	responses[responseIndex++] = new string_id(c_stringFile, "s_49");
                    }
				    utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 11);

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

    public int ridth_tercarcha_handleBranch11(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//--[BRANCH NOTE] Excellent!(he exclaims, grabbing the device from you. He frantically starts manipulating buttons and levers on it while he compares information from his datapad. After a few moments, he ejects a small disc from his datapad and hands it towards you.) If you take this, then you will be able to find the location of the next bunker. You will still help me, won't you?
    	//-- NPC: Excellent!(he exclaims, grabbing the device from you. He frantically starts manipulating buttons and levers on it while he compares information from his datapad. After a few moments, he ejects a small disc from his datapad and hands it towards you.) If you take this, then you will be able to find the location of the next bunker. You will still help me, won't you?

	    //--[RESPONSE NOTE](Take the data chip)
	    //-- PLAYER:(Take the data chip)
	    if (response.equals("s_47"))
	    {
    		//--[NOTE] Thank you so much. I am sure these bunkers will pose a more significant threat than before. Please be safe and hurry!
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			//-- NPC: Thank you so much. I am sure these bunkers will pose a more significant threat than before. Please be safe and hurry!
			    string_id message = new string_id(c_stringFile, "s_48");
			    utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

			    npcEndConversationWithMessage(player, message);

			    return SCRIPT_CONTINUE;
		    }

	    }

	    //--[RESPONSE NOTE] No, that was way too dangerous.
	    //-- PLAYER: No, that was way too dangerous.
	    if (response.equals("s_49"))
	    {
    		//--[NOTE] I can't believe you would turn away after coming so far. Are you sure?
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			//-- NPC: I can't believe you would turn away after coming so far. Are you sure?
			    string_id message = new string_id(c_stringFile, "s_50");
			    int numberOfResponses = 0;

			    boolean hasResponse = false;

			    //-- PLAYER: Fine.(Take the data chip)
			    boolean hasResponse0 = false;
			    if (ridth_tercarcha_condition__defaultCondition(player, npc))
			    {
    				++numberOfResponses;
				    hasResponse = true;
				    hasResponse0 = true;
			    }

			    //-- PLAYER: Yes, I value my life more.
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
    					responses[responseIndex++] = new string_id(c_stringFile, "s_51");
                    }
				    if (hasResponse1)
                    {
    					responses[responseIndex++] = new string_id(c_stringFile, "s_53");
                    }
				    utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 13);

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

    public int ridth_tercarcha_handleBranch13(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//--[BRANCH NOTE] I can't believe you would turn away after coming so far. Are you sure?
    	//-- NPC: I can't believe you would turn away after coming so far. Are you sure?

	    //--[RESPONSE NOTE] Fine.(Take the data chip)
	    //-- PLAYER: Fine.(Take the data chip)
	    if (response.equals("s_51"))
	    {
    		//--[NOTE] Thank you so much. I am sure these bunkers will pose a more significant threat than before. Please be safe and hurry!
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			//-- NPC: Thank you so much. I am sure these bunkers will pose a more significant threat than before. Please be safe and hurry!
			    string_id message = new string_id(c_stringFile, "s_52");
			    utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

			    npcEndConversationWithMessage(player, message);

			    return SCRIPT_CONTINUE;
		    }

	    }

	    //--[RESPONSE NOTE] Yes, I value my life more.
	    //-- PLAYER: Yes, I value my life more.
	    if (response.equals("s_53"))
	    {
    		//--[NOTE] Be that way, but should you change your mind...
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			//-- NPC: Be that way, but should you change your mind...
			    string_id message = new string_id(c_stringFile, "s_54");
			    utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

			    npcEndConversationWithMessage(player, message);

			    return SCRIPT_CONTINUE;
    		    }

	    }

	    return SCRIPT_DEFAULT;
    }

    public int ridth_tercarcha_handleBranch16(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//--[BRANCH NOTE] I gave you a task. Have you retrieved the item?!
    	//-- NPC: I gave you a task. Have you retrieved the item?!

	    //--[RESPONSE NOTE] No, not yet.
	    //-- PLAYER: No, not yet.
	    if (response.equals("s_56"))
	    {
    		//--[NOTE] Then what are you wasting your time here then? Time is of the essence!
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
			    //-- NPC: Then what are you wasting your time here then? Time is of the essence!
			    string_id message = new string_id(c_stringFile, "s_57");
				utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");
            	npcEndConversationWithMessage(player, message);
			}
			return SCRIPT_CONTINUE;

    	}

    	return SCRIPT_DEFAULT;
    }

    public int ridth_tercarcha_handleBranch18(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//--[BRANCH NOTE] See! I knew you would return. Are you interested in hearing me out this time?
    	//-- NPC: See! I knew you would return. Are you interested in hearing me out this time?

	    //--[RESPONSE NOTE] Yes, let's hear it.
	    //-- PLAYER: Yes, let's hear it.
	    if (response.equals("s_60"))
	    {
    		//--[NOTE] Excellent! Excellent!(he exclaims as he pulls out a datapad. Reviewing the material closely, he begins to speak again.) I have triangulated a location that is near here. First, I need you to assist in gathering an ancient device that holds the key to finding the five other bunkers, which contain DNA samples that have the key to creating life itself.(He presses a few buttons on the datapad and brings up a map displaying the location to you.)
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			doAnimationAction(npc, "celebrate1");

			    //-- NPC: Excellent! Excellent!(he exclaims as he pulls out a datapad. Reviewing the material closely, he begins to speak again.) I have triangulated a location that is near here. First, I need you to assist in gathering an ancient device that holds the key to finding the five other bunkers, which contain DNA samples that have the key to creating life itself.(He presses a few buttons on the datapad and brings up a map displaying the location to you.)
			    string_id message = new string_id(c_stringFile, "s_62");
			    int numberOfResponses = 0;

			    boolean hasResponse = false;

			    //-- PLAYER:(Continue to listen...)
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_64");
                    }
				    utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 19);

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

	    //--[RESPONSE NOTE] On second thought, I still don't have time.
	    //-- PLAYER: On second thought, I still don't have time.
	    if (response.equals("s_76"))
	    {
    		//--[NOTE] You will know where you can find me.
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			//-- NPC: You will know where you can find me.
			    string_id message = new string_id(c_stringFile, "s_78");
			    utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

			    npcEndConversationWithMessage(player, message);

			    return SCRIPT_CONTINUE;
		    }

	    }

	    return SCRIPT_DEFAULT;
    }

    public int ridth_tercarcha_handleBranch19(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//--[BRANCH NOTE] Excellent! Excellent!(he exclaims as he pulls out a datapad. Reviewing the material closely, he begins to speak again.) I have triangulated a location that is near here. First, I need you to assist in gathering an ancient device that holds the key to finding the five other bunkers, which contain DNA samples that have the key to creating life itself.(He presses a few buttons on the datapad and brings up a map displaying the location to you.)
    	//-- NPC: Excellent! Excellent!(he exclaims as he pulls out a datapad. Reviewing the material closely, he begins to speak again.) I have triangulated a location that is near here. First, I need you to assist in gathering an ancient device that holds the key to finding the five other bunkers, which contain DNA samples that have the key to creating life itself.(He presses a few buttons on the datapad and brings up a map displaying the location to you.)

	    //--[RESPONSE NOTE](Continue to listen...)
	    //-- PLAYER:(Continue to listen...)
	    if (response.equals("s_64"))
	    {
    		//--[NOTE] Go to this bunker; you will find that it has a series of traps meant to keep people out. You could bring friends that specialize in an array of talents that could prove to be useful. Once the bunker's security discovers you, you won't have much time to finish. Else all my life's work would be lost to the ruins. Failure is not an option.
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
    		    {
			    //-- NPC: Go to this bunker; you will find that it has a series of traps meant to keep people out. You could bring friends that specialize in an array of talents that could prove to be useful. Once the bunker's security discovers you, you won't have much time to finish. Else all my life's work would be lost to the ruins. Failure is not an option.
			    string_id message = new string_id(c_stringFile, "s_66");
			    int numberOfResponses = 0;

			    boolean hasResponse = false;

			    //-- PLAYER: Okay, I am off then.
			    boolean hasResponse0 = false;
			    if (ridth_tercarcha_condition__defaultCondition(player, npc))
			    {
    				++numberOfResponses;
    				hasResponse = true;
    				hasResponse0 = true;
			    }

			    //-- PLAYER: No, that sounds too dangerous.
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
                    	responses[responseIndex++] = new string_id(c_stringFile, "s_68");
                    }
				    if (hasResponse1)
    				{
                        responses[responseIndex++] = new string_id(c_stringFile, "s_72");
                    }
				    utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 20);

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

    public int ridth_tercarcha_handleBranch20(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//--[BRANCH NOTE] Go to this bunker; you will find that it has a series of traps meant to keep people out. You could bring friends that specialize in an array of talents that could prove to be useful. Once the bunker's security discovers you, you won't have much time to finish. Else all my life's work would be lost to the ruins. Failure is not an option.
    	//-- NPC: Go to this bunker; you will find that it has a series of traps meant to keep people out. You could bring friends that specialize in an array of talents that could prove to be useful. Once the bunker's security discovers you, you won't have much time to finish. Else all my life's work would be lost to the ruins. Failure is not an option.

	    //--[RESPONSE NOTE] Okay, I am off then.
	    //-- PLAYER: Okay, I am off then.
	    if (response.equals("s_68"))
	    {
    		//--[NOTE] Excellent! Please hurry back once you have the device.
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			doAnimationAction(npc, "bow");

			    //-- NPC: Excellent! Please hurry back once you have the device.
			    string_id message = new string_id(c_stringFile, "s_70");
			    utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

			    npcEndConversationWithMessage(player, message);

			    return SCRIPT_CONTINUE;
		    }

	    }

	    //--[RESPONSE NOTE] No, that sounds too dangerous.
	    //-- PLAYER: No, that sounds too dangerous.
	    if (response.equals("s_72"))
	    {
    		//--[NOTE] You're no better than the rest. Begone!
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			doAnimationAction(npc, "wildpanic");

			    //-- NPC: You're no better than the rest. Begone!
			    string_id message = new string_id(c_stringFile, "s_74");
			    utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

			    npcEndConversationWithMessage(player, message);

			    return SCRIPT_CONTINUE;
		    }

	    }

	    return SCRIPT_DEFAULT;
    }

    public int ridth_tercarcha_handleBranch24(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//--[BRANCH NOTE](Waving his arms wildly over his head, the crazed scientist motions you over.) They said I was crazy. No one believes in the power of my work.(He glances around the area as if looking for someone.) The Empire would try to claim the credit if they knew the true powers that I'm on the brink of unlocking!(He motions you closer in.)
    	//-- NPC:(Waving his arms wildly over his head, the crazed scientist motions you over.) They said I was crazy. No one believes in the power of my work.(He glances around the area as if looking for someone.) The Empire would try to claim the credit if they knew the true powers that I'm on the brink of unlocking!(He motions you closer in.)

	    //--[RESPONSE NOTE](Lean in to listen...)
	    //-- PLAYER:(Lean in to listen...)
	    if (response.equals("s_82"))
	    {
    		//--[NOTE] I could create a new lifeform for you. You could live an alternative life and do anything you wished with it!
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			//-- NPC: I could create a new lifeform for you. You could live an alternative life and do anything you wished with it!
			    string_id message = new string_id(c_stringFile, "s_84");
    			int numberOfResponses = 0;

			    boolean hasResponse = false;

			    //-- PLAYER: Sure, you have my interest.
			    boolean hasResponse0 = false;
			    if (ridth_tercarcha_condition__defaultCondition(player, npc))
			    {
    				++numberOfResponses;
				    hasResponse = true;
				    hasResponse0 = true;
			    }

			    //-- PLAYER: I don't have time right now.
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
    					responses[responseIndex++] = new string_id(c_stringFile, "s_86");
					}
				    if (hasResponse1)
					{
    					responses[responseIndex++] = new string_id(c_stringFile, "s_102");
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

	    return SCRIPT_DEFAULT;
    }

    public int ridth_tercarcha_handleBranch25(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//--[BRANCH NOTE] I could create a new lifeform for you. You could live an alternative life and do anything you wished with it!
    	//-- NPC: I could create a new lifeform for you. You could live an alternative life and do anything you wished with it!

	    //--[RESPONSE NOTE] Sure, you have my interest.
	    //-- PLAYER: Sure, you have my interest.
	    if (response.equals("s_86"))
	    {
    		//--[NOTE] Excellent! Excellent!(he exclaims as he pulls out a datapad. Reviewing the material closely, he begins to speak again.) I have triangulated a location that is near here. First, I need you to assist in gathering an ancient device that holds the key to finding the five other bunkers, which contain DNA samples that have the key to creating life itself.(He presses a few buttons on the datapad and brings up a map displaying the location to you.)
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			doAnimationAction(npc, "celebrate1");

			    //-- NPC: Excellent! Excellent!(he exclaims as he pulls out a datapad. Reviewing the material closely, he begins to speak again.) I have triangulated a location that is near here. First, I need you to assist in gathering an ancient device that holds the key to finding the five other bunkers, which contain DNA samples that have the key to creating life itself.(He presses a few buttons on the datapad and brings up a map displaying the location to you.)
			    string_id message = new string_id(c_stringFile, "s_88");
			    int numberOfResponses = 0;

			    boolean hasResponse = false;

			    //-- PLAYER:(Continue to listen...)
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
    					responses[responseIndex++] = new string_id(c_stringFile, "s_90");
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

	    //--[RESPONSE NOTE] I don't have time right now.
	    //-- PLAYER: I don't have time right now.
	    if (response.equals("s_102"))
	    {
    		doAnimationAction(player, "shake_head_no");

		    //--[NOTE] Whatever, begone, I don't have time to waste either if you aren't interested. You'll be back, though; I know it.
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			doAnimationAction(npc, "wildpanic");

			    //-- NPC: Whatever, begone, I don't have time to waste either if you aren't interested. You'll be back, though; I know it.
			    string_id message = new string_id(c_stringFile, "s_104");
			    utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

			    npcEndConversationWithMessage(player, message);

			    return SCRIPT_CONTINUE;
		    }

	    }

	    return SCRIPT_DEFAULT;
    }

    public int ridth_tercarcha_handleBranch26(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//--[BRANCH NOTE] Excellent! Excellent!(he exclaims as he pulls out a datapad. Reviewing the material closely, he begins to speak again.) I have triangulated a location that is near here. First, I need you to assist in gathering an ancient device that holds the key to finding the five other bunkers, which contain DNA samples that have the key to creating life itself.(He presses a few buttons on the datapad and brings up a map displaying the location to you.)
    	//-- NPC: Excellent! Excellent!(he exclaims as he pulls out a datapad. Reviewing the material closely, he begins to speak again.) I have triangulated a location that is near here. First, I need you to assist in gathering an ancient device that holds the key to finding the five other bunkers, which contain DNA samples that have the key to creating life itself.(He presses a few buttons on the datapad and brings up a map displaying the location to you.)

	    //--[RESPONSE NOTE](Continue to listen...)
	    //-- PLAYER:(Continue to listen...)
	    if (response.equals("s_90"))
	    {
    		//--[NOTE] Go to this bunker; you will find that it has a series of traps meant to keep people out. You could bring friends that specialize in an array of talents that could prove to be useful. Once the bunker's security discovers you, you won't have much time to finish. Else all my life's work would be lost to the ruins. Failure is not an option.
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			//-- NPC: Go to this bunker; you will find that it has a series of traps meant to keep people out. You could bring friends that specialize in an array of talents that could prove to be useful. Once the bunker's security discovers you, you won't have much time to finish. Else all my life's work would be lost to the ruins. Failure is not an option.
			    string_id message = new string_id(c_stringFile, "s_92");
			    int numberOfResponses = 0;

			    boolean hasResponse = false;

			    //-- PLAYER: Okay, I am off then.
			    boolean hasResponse0 = false;
			    if (ridth_tercarcha_condition__defaultCondition(player, npc))
			    {
    				++numberOfResponses;
				    hasResponse = true;
				    hasResponse0 = true;
			    }

			    //-- PLAYER: No, that sounds too dangerous.
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
                    	responses[responseIndex++] = new string_id(c_stringFile, "s_94");
                    }
				    if (hasResponse1)
    				{
                    	responses[responseIndex++] = new string_id(c_stringFile, "s_98");
                    }
				    utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 27);

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

    public int ridth_tercarcha_handleBranch27(obj_id player, obj_id npc, string_id response) throws InterruptedException
    {
    	//--[BRANCH NOTE] Go to this bunker; you will find that it has a series of traps meant to keep people out. You could bring friends that specialize in an array of talents that could prove to be useful. Once the bunker's security discovers you, you won't have much time to finish. Else all my life's work would be lost to the ruins. Failure is not an option.
    	//-- NPC: Go to this bunker; you will find that it has a series of traps meant to keep people out. You could bring friends that specialize in an array of talents that could prove to be useful. Once the bunker's security discovers you, you won't have much time to finish. Else all my life's work would be lost to the ruins. Failure is not an option.

	    //--[RESPONSE NOTE] Okay, I am off then.
	    //-- PLAYER: Okay, I am off then.
	    if (response.equals("s_94"))
	    {
    		doAnimationAction(player, "nod");

		    //--[NOTE] Excellent! Please hurry back once you have the device.
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			doAnimationAction(npc, "celebrate");

			    //-- NPC: Excellent! Please hurry back once you have the device.
			    string_id message = new string_id(c_stringFile, "s_96");
    			utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

			    npcEndConversationWithMessage(player, message);

			    return SCRIPT_CONTINUE;
		    }

	    }

	    //--[RESPONSE NOTE] No, that sounds too dangerous.
	    //-- PLAYER: No, that sounds too dangerous.
	    if (response.equals("s_98"))
	    {
    		doAnimationAction(player, "shake_head_no");

		    //--[NOTE] You're no better than the rest. Begone!
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			doAnimationAction(npc, "fakepunch");

			    //-- NPC: You're no better than the rest. Begone!
			    string_id message = new string_id(c_stringFile, "s_100");
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
        /*
        setObjVar(self, "convo.appearance", "object/mobile/space_comm_hutt_02.iff");
        detachScript(self, "space.content_tools.spacestation");
        */
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
        {
		    return SCRIPT_OVERRIDE;
        }
	    //--[NOTE] It's d-d-done.... N-n-now make y-y-yourself scarce, if th-th-the Empire finds out w-w-what we have done, we're d-d-d-dead!
	    if (ridth_tercarcha_condition_collectionCompleted(player, npc))
	    {
		    doAnimationAction(npc, "nervous");

		    //-- NPC: It's d-d-done.... N-n-now make y-y-yourself scarce, if th-th-the Empire finds out w-w-what we have done, we're d-d-d-dead!
		    string_id message = new string_id(c_stringFile, "s_31");
		    chat.chat(npc, player, message);

		    return SCRIPT_CONTINUE;
	    }

	    //--[NOTE] You have done a wonderful job!(he exclaims out taking the final sample from you. Turning away from you, the man walks over, placing the DNA into a large device. His arms raise high for a moment showing off a large incubation tube as he pulls al ever that sets the event into motion. Electricity sparks from the machines as small crackles and pop erupt.)
	    if (ridth_tercarcha_condition_bunkerQuestComplete(player, npc))
	    {
    		doAnimationAction(npc, "pose_proudly");

		    //-- NPC: You have done a wonderful job!(he exclaims out taking the final sample from you. Turning away from you, the man walks over, placing the DNA into a large device. His arms raise high for a moment showing off a large incubation tube as he pulls al ever that sets the event into motion. Electricity sparks from the machines as small crackles and pop erupt.)
		    string_id message = new string_id(c_stringFile, "s_32");
		    int numberOfResponses = 0;

		    boolean hasResponse = false;

		    //-- PLAYER:(Continue watching...)
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
    				responses[responseIndex++] = new string_id(c_stringFile, "s_33");
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

	    //--[NOTE](Peering around to see if anyone else is about, the man looks at you.) Welcome back! You are doing well, and you have collected %DI so far. Please return once you have collected another!
	    if (ridth_tercarcha_condition_isOnBunkerRepeat(player, npc))
	    {
    		//-- NPC:(Peering around to see if anyone else is about, the man looks at you.) Welcome back! You are doing well, and you have collected %DI so far. Please return once you have collected another!
		    string_id message = new string_id(c_stringFile, "s_35");
    		prose_package pp = new prose_package();
			pp.stringId = message;
			pp.actor.set(player);
		    pp.target.set(npc);
		    pp.digitInteger = ridth_tercarcha_tokenDI_bunkersCompletedCounter(player, npc);

    		chat.chat(npc, player, null, null, pp);
		    return SCRIPT_CONTINUE;
	    }

	    //--[NOTE] You are back sooner than I thought. Have you gathered the DNA?
	    if (ridth_tercarcha_condition_isOnBunkerQuest(player, npc))
	    {
    		//-- NPC: You are back sooner than I thought. Have you gathered the DNA?
		    string_id message = new string_id(c_stringFile, "s_36");
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
    				responses[responseIndex++] = new string_id(c_stringFile, "s_37");
                }
			    utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 5);

			    npcStartConversation(player, npc, "ridth_tercarcha", message, responses);
		    }
		    else
		    {
    			chat.chat(npc, player, message);
		    }

		    return SCRIPT_CONTINUE;
	    }

	    //--[NOTE] I told you, you would return. We are too close to creating life itself! So you will help me, yes?
	    if (ridth_tercarcha_condition_rejectedBunkerMission(player, npc))
	    {
    		//-- NPC: I told you, you would return. We are too close to creating life itself! So you will help me, yes?
		    string_id message = new string_id(c_stringFile, "s_39");
		    int numberOfResponses = 0;

		    boolean hasResponse = false;

		    //-- PLAYER: Yes.
		    boolean hasResponse0 = false;
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			++numberOfResponses;
			    hasResponse = true;
			    hasResponse0 = true;
		    }

		    //-- PLAYER: No.
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
    				responses[responseIndex++] = new string_id(c_stringFile, "s_41");
                }
			    utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 7);

			    npcStartConversation(player, npc, "ridth_tercarcha", message, responses);
		    }
		    else
		    {
    			chat.chat(npc, player, message);
		    }

		    return SCRIPT_CONTINUE;
	    }

	    //--[NOTE](The man eagerly looks towards you) Have you retrieved the device?(he asks as he peers around, clearly in a mix of his insanity and looking out for unwanted attention.)
	    if (ridth_tercarcha_condition_tutorialFinished(player, npc))
	    {
    		//-- NPC:(The man eagerly looks towards you) Have you retrieved the device?(he asks as he peers around, clearly in a mix of his insanity and looking out for unwanted attention.)
		    string_id message = new string_id(c_stringFile, "s_44");
		    int numberOfResponses = 0;

		    boolean hasResponse = false;

		    //-- PLAYER: Yes, here you go.
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
    				responses[responseIndex++] = new string_id(c_stringFile, "s_45");
                }
			    utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 10);

			    npcStartConversation(player, npc, "ridth_tercarcha", message, responses);
		    }
		    else
		    {
    			chat.chat(npc, player, message);
		    }

		    return SCRIPT_CONTINUE;
	    }

	    //--[NOTE] I gave you a task. Have you retrieved the item?!
	    if (ridth_tercarcha_condition_isOnTutorial(player, npc))
	    {
    		//-- NPC: I gave you a task. Have you retrieved the item?!
		    string_id message = new string_id(c_stringFile, "s_55");
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
    				responses[responseIndex++] = new string_id(c_stringFile, "s_56");
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

	    //--[NOTE] See! I knew you would return. Are you interested in hearing me out this time?
	    if (ridth_tercarcha_condition_rejectedTutorial(player, npc))
	    {
    		doAnimationAction(npc, "beckon");

		    //-- NPC: See! I knew you would return. Are you interested in hearing me out this time?
		    string_id message = new string_id(c_stringFile, "s_58");
		    int numberOfResponses = 0;

		    boolean hasResponse = false;

		    //-- PLAYER: Yes, let's hear it.
		    boolean hasResponse0 = false;
		    if (ridth_tercarcha_condition__defaultCondition(player, npc))
		    {
    			++numberOfResponses;
			    hasResponse = true;
			    hasResponse0 = true;
		    }

		    //-- PLAYER: On second thought, I still don't have time.
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
    				responses[responseIndex++] = new string_id(c_stringFile, "s_60");
                }
			    if (hasResponse1)
                {
    				responses[responseIndex++] = new string_id(c_stringFile, "s_76");
                }
			    utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 18);

			    npcStartConversation(player, npc, "ridth_tercarcha", message, responses);
		    }
		    else
		    {
    			chat.chat(npc, player, message);
		    }

		    return SCRIPT_CONTINUE;
	    }

	    //--[NOTE](Waving his arms wildly over his head, the crazed scientist motions you over.) They said I was crazy. No one believes in the power of my work.(He glances around the area as if looking for someone.) The Empire would try to claim the credit if they knew the true powers that I'm on the brink of unlocking!(He motions you closer in.)
	    if (ridth_tercarcha_condition__defaultCondition(player, npc))
	    {
    		doAnimationAction(npc, "hail");

		    //-- NPC:(Waving his arms wildly over his head, the crazed scientist motions you over.) They said I was crazy. No one believes in the power of my work.(He glances around the area as if looking for someone.) The Empire would try to claim the credit if they knew the true powers that I'm on the brink of unlocking!(He motions you closer in.)
		    string_id message = new string_id(c_stringFile, "s_80");
		    int numberOfResponses = 0;

		    boolean hasResponse = false;

		    //-- PLAYER:(Lean in to listen...)
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
    				responses[responseIndex++] = new string_id(c_stringFile, "s_82");
                }
			    utils.setScriptVar(player, "conversation.ridth_tercarcha.branchId", 24);

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
    	if (!conversationId.equals("ridth_tercarcha"))
		    return SCRIPT_CONTINUE;

	    obj_id npc = self;

	    int branchId = utils.getIntScriptVar(player, "conversation.ridth_tercarcha.branchId");

	    if (branchId == 2 && ridth_tercarcha_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
        {
    		return SCRIPT_CONTINUE;
        }
	    if (branchId == 5 && ridth_tercarcha_handleBranch5(player, npc, response) == SCRIPT_CONTINUE)
        {
    		return SCRIPT_CONTINUE;
        }
	    if (branchId == 7 && ridth_tercarcha_handleBranch7(player, npc, response) == SCRIPT_CONTINUE)
        {
    		return SCRIPT_CONTINUE;
        }
	    if (branchId == 10 && ridth_tercarcha_handleBranch10(player, npc, response) == SCRIPT_CONTINUE)
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
	    if (branchId == 18 && ridth_tercarcha_handleBranch18(player, npc, response) == SCRIPT_CONTINUE)
        {
    		return SCRIPT_CONTINUE;
        }
	    if (branchId == 19 && ridth_tercarcha_handleBranch19(player, npc, response) == SCRIPT_CONTINUE)
        {
    		return SCRIPT_CONTINUE;
        }
	    if (branchId == 20 && ridth_tercarcha_handleBranch20(player, npc, response) == SCRIPT_CONTINUE)
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
	    if (branchId == 27 && ridth_tercarcha_handleBranch27(player, npc, response) == SCRIPT_CONTINUE)
        {
    		return SCRIPT_CONTINUE;
        }
	    chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");

	    utils.removeScriptVar(player, "conversation.ridth_tercarcha.branchId");

	    return SCRIPT_CONTINUE;
    }
}