package com.hepolite.chatutility.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.hepolite.chatutility.ChatUtility;

public class CmdReload extends CmdNick
{
	public CmdReload()
	{
		super("channelnick.reload", true, true);
	}

	@Override
	public void onInvoked(CommandSender sender, String[] args)
	{
		ChatUtility.getSettings().reload();
		sender.sendMessage(ChatColor.AQUA + "Reloaded ChatUtility configs!");
	}
}
