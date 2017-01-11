package com.hepolite.chatutility.cmd;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;
import com.hepolite.chatutility.ChatUtility;

public abstract class CmdNickMessage extends CmdNick
{
	public CmdNickMessage(String permission)
	{
		super(permission, false, true);
	}

	@Override
	public void onInvoked(CommandSender sender, String[] args)
	{
		if (args.length < 1)
			return;
		sendMessage((Player) sender, Joiner.on(' ').join(args));
	}

	/** Sends the message */
	protected abstract void sendMessage(Player player, String message);

	// /////////////////////////////////////////////////////////////////////

	public final static class OOC extends CmdNickMessage
	{
		public OOC()
		{
			super("channelnick.ooc");
		}

		@Override
		protected void sendMessage(Player player, String message)
		{
			ChatUtility.getHerochatAdapter().sendOOCMessage(player, message);
		}
	}

	public final static class OOCA extends CmdNickMessage
	{
		public OOCA()
		{
			super("channelnick.ooca");
		}

		@Override
		protected void sendMessage(Player player, String message)
		{
			ChatUtility.getHerochatAdapter().sendAdminOOCMessage(player, message);
		}
	}

	public final static class IT extends CmdNickMessage
	{
		public IT()
		{
			super("channelnick.it");
		}

		@Override
		protected void sendMessage(Player player, String message)
		{
			ChatUtility.getHerochatAdapter().sendItMessage(player, message);
		}
	}
}
