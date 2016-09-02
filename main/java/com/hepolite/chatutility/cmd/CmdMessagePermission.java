package com.hepolite.chatutility.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.hepolite.chatutility.ChatUtility;

/** Sends a message to all players with the required permission */
public class CmdMessagePermission extends CmdMessage
{
	@Override
	protected void sendMessage(CommandSender sender, String permission, String message)
	{
		ChatUtility.getInstance().getLogger().info("(permission) " + sender.getName() + " >> " + permission + ": " + message);
		Bukkit.getServer().broadcast(message, permission);
	}
}
