package com.hepolite.chatutility.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.hepolite.chatutility.ChatUtility;

public class CmdMessagePlayer extends CmdMessage
{
	@Override
	protected void sendMessage(CommandSender sender, String target, String message)
	{
		if (target.equals("*"))
		{
			ChatUtility.getInstance().getLogger().info("(broadcast) " + sender.getName() + " >> *: " + message);
			Bukkit.getServer().broadcastMessage(message);
		}
		else
		{
			@SuppressWarnings("deprecation")
			Player player = Bukkit.getPlayer(target);
			if (player != null)
			{
				ChatUtility.getInstance().getLogger().info("(player) " + sender.getName() + " >> " + player.getName() + ": " + message);
				player.sendMessage(message);
			}
			else
				sender.sendMessage("Target " + target + " was not found.");
		}
	}
}
