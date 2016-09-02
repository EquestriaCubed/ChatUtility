package com.hepolite.chatutility.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.hepolite.chatutility.ChatUtility;
import com.hepolite.chatutility.nick.Account;
import com.hepolite.chatutility.nick.HerochatAdapter;

public class CmdNickRealnames extends CmdNick
{
	public CmdNickRealnames()
	{
		super("channelnick.realnames", true, true);
	}

	@Override
	public void onInvoked(CommandSender sender, String[] args)
	{
		boolean foundAnyone = false;
		for (Player player : Bukkit.getOnlinePlayers())
		{
			boolean shouldDisplay = (sender instanceof Player ? ChatUtility.getVanishHook().isPlayerVisible((Player) sender, player) : true);
			if (!shouldDisplay)
				continue;
			Account account = ChatUtility.getNickManager().getAccount(player);
			if (account.getNick() == null)
				continue;

			String name = (ChatUtility.getVanishHook().isPlayerVanished(player) ? "*" : "") + ChatUtility.getPermissionsExHook().getPrefix(account.getName()) + account.getName();
			sender.sendMessage(String.format("'%s' is %s.", HerochatAdapter.getNickPrefix(player) + account.getNick() + ChatColor.WHITE, name + ChatColor.WHITE));
			foundAnyone = true;
		}
		if (!foundAnyone)
			sender.sendMessage("Was unable to find any players with nicknames");
	}
}
