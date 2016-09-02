package com.hepolite.chatutility.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;
import com.hepolite.chatutility.ChatUtility;
import com.hepolite.chatutility.nick.Account;
import com.hepolite.chatutility.nick.HerochatAdapter;

public class CmdNickDelnick extends CmdNick
{
	public CmdNickDelnick()
	{
		super("channelnick.delnick", true, true);
	}

	@Override
	public void onInvoked(CommandSender sender, String[] args)
	{
		if (args.length < 1)
			return;

		@SuppressWarnings("deprecation")
		Player player = Bukkit.getPlayer(args[0]);
		String nick = Joiner.on(' ').join(args);
		Account account = ChatUtility.getNickManager().getAccount(nick);
		if (account != null)
			removeNick(sender, account);
		else if (player != null)
			removeNick(sender, ChatUtility.getNickManager().getAccount(player));
		else
			sender.sendMessage(ChatColor.RED + String.format("Found no nicknames or players with the name '%s'", nick));
	}

	/** Remove the nickname of the given target */
	private final void removeNick(CommandSender sender, Account account)
	{
		Player target = Bukkit.getPlayer(account.getUUID());
		String nick = HerochatAdapter.getNickPrefix(target) + account.getNick() + ChatColor.WHITE;
		if (ChatUtility.getNickManager().removeNick(account.getUUID()))
		{
			sender.sendMessage(ChatUtility.getSettings().nickMessage + String.format("Removed the nickname '%s'.", nick));
			if (target != null)
			{
				ChatUtility.getHerochatAdapter().sendOOCMessage(target, "I no longer have a nickname");
				target.sendMessage(ChatUtility.getSettings().nickMessage + "Your nickname was removed.");
			}
		}
		else
			sender.sendMessage(String.format("Failed to remove the nickname '%s'!", nick));
	}
}
