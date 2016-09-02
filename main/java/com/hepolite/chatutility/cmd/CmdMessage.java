package com.hepolite.chatutility.cmd;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class CmdMessage implements CommandExecutor
{
	/** Returns true if the command was executed */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String name, String[] args)
	{
		if (args.length < 2)
			return false;

		String target = args[0];
		String message = StringUtils.join(args, ' ').substring(args[0].length() + 1);
		sendMessage(sender, target, ChatColor.translateAlternateColorCodes('&', message));
		return true;
	}

	/** Sends a message to the relevant targets */
	protected abstract void sendMessage(CommandSender sender, String target, String message);
}
