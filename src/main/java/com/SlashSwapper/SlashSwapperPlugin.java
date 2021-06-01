package com.SlashSwapper;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.VarClientStr;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ScriptCallbackEvent;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.chat.ChatMessageManager;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
	name = "Slash Swapper"
)
public class SlashSwapperPlugin extends Plugin
{
	@Inject
	private Client client;


	@Inject
	private ChatMessageManager chatMessageManager;


	private int friendsChatInt = 9;
	private int clanChatInt = 41;






	@Subscribe
	public void onScriptCallbackEvent(ScriptCallbackEvent scriptCallbackEvent)
	{
		if (scriptCallbackEvent.getEventName().equals("preChatSendpublic")) {
			final String chatboxInput = client.getVar(VarClientStr.CHATBOX_TYPED_TEXT);
			if(chatboxInput == null){
				return;

			}

			if(chatboxInput.length()>=2 && chatboxInput.charAt(0)=='/' && chatboxInput.charAt(1) != '/'){
				int[] intStack = client.getIntStack();
				final int intStackSize = client.getIntStackSize();
				intStack[intStackSize - 1] = clanChatInt;
				intStack[intStackSize - 2] = 1;


				return;
			}


			if(chatboxInput.length()>2 && chatboxInput.charAt(0)=='/' && chatboxInput.charAt(1) == '/' && chatboxInput.charAt(2) != '/'){
				int[] intStack = client.getIntStack();
				final int intStackSize = client.getIntStackSize();
				intStack[intStackSize - 1] = friendsChatInt;
				intStack[intStackSize - 2] = 0;
				client.setVar(VarClientStr.CHATBOX_TYPED_TEXT,chatboxInput.substring(2));


				return;
			}


			}




		return;
		}

}
