package com.hepolite.chatutility.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;
import com.hepolite.chatutility.ChatUtility;
import com.hepolite.chatutility.nick.NickManager;

public class CmdNickSet extends CmdNick
{
	public CmdNickSet()
	{
		super("channelnick.nick", false, true);
	}

	@Override
	public void onInvoked(CommandSender sender, String[] args)
	{
		Player player = (Player) sender;
		NickManager manager = ChatUtility.getNickManager();

		if (args.length == 0)
		{
			if (manager.removeNick(player))
			{
				ChatUtility.getHerochatAdapter().sendOOCMessage(player, "I no longer have a nickname");
				player.sendMessage(ChatUtility.getSettings().nickMessage + "Your nickname was removed");
			}
			else
				player.sendMessage(ChatUtility.getSettings().nickMessage + "You don't have a nickname");
		}
		else
		{
			String nick = Joiner.on(' ').join(args);
			if (manager.setNick(player, nick))
			{
				ChatUtility.getHerochatAdapter().sendOOCMessage(player, "I'm now chatting as " + manager.getNick(player));
				player.sendMessage(ChatUtility.getSettings().nickMessage + "Your nickname is now " + ChatColor.AQUA + manager.getNick(player) + ChatColor.WHITE + " in channels: " + Joiner.on(' ').join(ChatUtility.getSettings().channels));
			}
		}
	}
}
