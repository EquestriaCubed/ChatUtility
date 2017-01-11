package com.hepolite.chatutility.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;
import com.hepolite.chatutility.ChatUtility;
import com.hepolite.chatutility.nick.Account;
import com.hepolite.chatutility.nick.HerochatAdapter;

public class CmdNickRealname extends CmdNick
{
	public CmdNickRealname()
	{
		super("channelnick.realname", true, true);
	}

	@Override
	public void onInvoked(CommandSender sender, String[] args)
	{
		if (args.length < 1)
			return;

		@SuppressWarnings("deprecation")
		Player player = Bukkit.getPlayer(args[0]);
		if (player != null)
		{
			String name = ChatUtility.getPermissionsExHook().getPrefix(player) + player.getName() + ChatColor.WHITE;
			String nick = HerochatAdapter.getNickPrefix(player) + ChatUtility.getNickManager().getNick(player) + ChatColor.WHITE;
			if (ChatUtility.getNickManager().hasNick(player))
				sender.sendMessage(String.format("Player %s is '%s'.", name, nick));
			else
				sender.sendMessage(String.format("Player %s has no nickname.", name));
		}

		String nick = Joiner.on(' ').join(args);
		Account account = ChatUtility.getNickManager().getAccount(nick);
		nick = HerochatAdapter.getNickPrefix(player) + nick + ChatColor.WHITE;
		if (account != null && account.getNick() != null)
		{
			String name = ChatUtility.getPermissionsExHook().getPrefix(account.getName()) + account.getName() + ChatColor.WHITE;
			sender.sendMessage(String.format("'%s' is %s", nick, name));
		}
		else if (player == null)
			sender.sendMessage(String.format("No player has the nickname '%s'.", nick));
	}
}
