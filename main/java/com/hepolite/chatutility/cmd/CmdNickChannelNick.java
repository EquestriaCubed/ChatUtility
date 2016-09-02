package com.hepolite.chatutility.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.hepolite.chatutility.ChatUtility;

public class CmdNickChannelNick extends CmdNick
{
	public CmdNickChannelNick()
	{
		super(null, true, true);
	}

	@Override
	public void onInvoked(CommandSender sender, String[] args)
	{
		if (args.length < 1)
			return;
		if (args[0].equalsIgnoreCase("help"))
		{
			sender.sendMessage(ChatUtility.getSettings().nickMessage + "ChannelNick");
			sender.sendMessage(ChatColor.RED + "/ChannelNick help " + ChatColor.WHITE + " - Get this help menu. ");
			if (sender.hasPermission("channelnick.reload"))
				sender.sendMessage(ChatColor.RED + "/ChannelNick reload " + ChatColor.WHITE + " - Reloads the config.");
			if (sender.hasPermission("channelnick.nick"))
				sender.sendMessage(ChatColor.RED + "/nick <nickname> " + ChatColor.WHITE + " - Sets your nickname. Leave blank to unset it.");
			if (sender.hasPermission("channelnick.realname"))
				sender.sendMessage(ChatColor.RED + "/realname <nickname> " + ChatColor.WHITE + " - Show the real name that corresponds to a given nickname or player name.");
			if (sender.hasPermission("channelnick.realnames"))
				sender.sendMessage(ChatColor.RED + "/realnames " + ChatColor.WHITE + " - Show the real names of all online players with a nick.");
			if (sender.hasPermission("channelnick.ooc"))
				sender.sendMessage(ChatColor.RED + "/ooc <message> " + ChatColor.WHITE + " - Send a message as OOC to the focused channel");
			if (sender.hasPermission("channelnick.it"))
				sender.sendMessage(ChatColor.RED + "/it <message>" + ChatColor.WHITE + " - Send a world action to the focused channel");
			if (sender.hasPermission("channelnick.delnick"))
				sender.sendMessage(ChatColor.RED + "/delnick <nickname> " + ChatColor.WHITE + " - Removes a player's nickname");
			if (sender.hasPermission("channelnick.ooctoggle"))
				sender.sendMessage(ChatColor.RED + "/ooctoggle " + ChatColor.WHITE + " - Toggles your OOC status to always talk in OOC.");
			if (sender.hasPermission("channelnick.ooca"))
				sender.sendMessage(ChatColor.RED + "/ooca <message> " + ChatColor.WHITE + " - Send a message as OOC to the focused channel as an admin.");
		}
		else if (args[0].equalsIgnoreCase("reload"))
		{
			if (sender.hasPermission("channelnick.reload"))
			{
				ChatUtility.getSettings().reload();
				sender.sendMessage(ChatUtility.getSettings().nickMessage + "Reloaded Settings");
			}
		}
	}
}
