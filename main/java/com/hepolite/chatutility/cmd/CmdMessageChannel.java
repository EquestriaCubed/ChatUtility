package com.hepolite.chatutility.cmd;

import org.bukkit.command.CommandSender;

import com.dthielke.Herochat;
import com.dthielke.api.Channel;
import com.hepolite.chatutility.ChatUtility;

public class CmdMessageChannel extends CmdMessage
{
	@Override
	protected void sendMessage(CommandSender sender, String channelName, String message)
	{
		if (!ChatUtility.getHerochatHook().hasHerochat())
		{
			sender.sendMessage("Herochat is unavailable.");
			return;
		}

		Channel channel = Herochat.getChannelManager().getChannel(channelName);
		if (channel == null)
			sender.sendMessage("Channel " + channelName + " was not found.");
		else
		{
			ChatUtility.getInstance().getLogger().info("(channel) " + sender.getName() + " >> " + channel.getName() + ": " + message);
			channel.announce(message);
		}
	}
}
