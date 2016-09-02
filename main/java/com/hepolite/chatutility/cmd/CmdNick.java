package com.hepolite.chatutility.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.hepolite.chatutility.ChatUtility;

public abstract class CmdNick implements CommandExecutor
{
	private final String permission;
	private final boolean allowConsole;
	private final boolean allowPlayer;

	public CmdNick(String permission, boolean allowConsole, boolean allowPlayer)
	{
		this.permission = permission;
		this.allowConsole = allowConsole;
		this.allowPlayer = allowPlayer;
	}

	/** Returns true if the command was executed */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if ((sender instanceof Player && !allowPlayer) || (!(sender instanceof Player) && !allowConsole))
		{
			sender.sendMessage(ChatUtility.getSettings().nickMessage + "You can't do that from here");
			return false;
		}
		if (permission == null || sender.hasPermission(permission))
			onInvoked(sender, args);
		else
			sender.sendMessage(ChatUtility.getSettings().nickMessage + "You don't have permission to do this");
		return true;
	}

	/** Invoked whenever the command is executed by someone who could use it */
	public abstract void onInvoked(CommandSender sender, String[] args);
}
