package com.hepolite.chatutility.cmd;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.hepolite.chatutility.ChatUtility;

public class CmdNickOOCToggle extends CmdNick
{
	public CmdNickOOCToggle()
	{
		super("channelnick.ooctoggle", false, true);
	}

	@Override
	public void onInvoked(CommandSender sender, String[] args)
	{
		Player player = (Player) sender;
		ChatUtility.getHerochatAdapter().toggleOOC(player);
		if (ChatUtility.getHerochatAdapter().isOOC(player))
			player.sendMessage(ChatUtility.getSettings().nickMessage + "You are now chatting in OOC.");
		else
			player.sendMessage(ChatUtility.getSettings().nickMessage + "You are no longer chatting in OOC.");
	}
}
