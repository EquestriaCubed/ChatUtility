package com.hepolite.chatutility.nick;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.dthielke.Herochat;
import com.dthielke.api.Channel;
import com.dthielke.api.ChatResult;
import com.dthielke.api.event.ChannelChatEvent;
import com.hepolite.chatutility.ChatUtility;

public class HerochatAdapter implements Listener
{
	private final Set<UUID> oocPlayers = new HashSet<UUID>();

	/** Take in the player chat message and modify it if the player has a nick and is talking in a channel where nicks are allowed */
	@EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
	public void onHeroChatMessage(ChannelChatEvent event)
	{
		Channel channel = event.getChannel();
		if (!getAllowNicknames(channel))
			return;

		Player player = event.getChatter().getPlayer();
		if (isOOC(player))
		{
			sendOOCMessage(player, event.getMessage());
			event.setResult(ChatResult.FAIL);
			return;
		}
		if (ChatUtility.getNickManager().hasNick(player))
		{
			String nick = ChatUtility.getNickManager().getNick(player);
			ChatUtility.getInstance().getLogger().info("[" + player.getName() + " is chatting as " + nick + "]");

			//ChatUtility.getInstance().getLogger().info("FORMAT: " + event.getFormat());
			// FORMAT: &9{nick}&f<{prefix}{sender}{suffix}&f> {msg}
			// .replace("{nick}<{prefix}{sender}{suffix}", "{nick} <" + ChatUtility.getSettings().nickPrefix + nick")

			event.setFormat(event.getFormat().replace("{prefix}{sender}{suffix}", getNickPrefix(player) + nick));
			event.setFormat(event.getFormat().replace("&9{nick}&f", "&9{nick}" + ChatUtility.getSettings().nickAddon + "&f"));
			if (event.getFormat().equals("&7[{name}] * {msg}"))
			{
				sendActionMessage(player, event.getMessage());
				event.setResult(ChatResult.FAIL);
			}
		}
	}

	/** Returns the nick prefix for the given player */
	public final static String getNickPrefix(Player player)
	{
		return player.hasPermission("channelnick.admin") ? ChatUtility.getSettings().adminPrefix : ChatUtility.getSettings().nickPrefix;
	}

	/** Returns the active channel for the given player */
	private final Channel getActiveChannel(Player player)
	{
		try
		{
			return Herochat.getChatterManager().getChatter(player).getActiveChannel();
		}
		catch (Exception e)
		{
		}
		return null;
	}

	/** Returns true if the given channel allows nicknames */
	private final boolean getAllowNicknames(Channel channel)
	{
		return (channel == null ? false : ChatUtility.getSettings().channels.contains(channel.getName()));
	}

	/** Toggles the OOC state for the given player */
	public final void toggleOOC(Player player)
	{
		if (oocPlayers.contains(player.getUniqueId()))
			oocPlayers.remove(player.getUniqueId());
		else
			oocPlayers.add(player.getUniqueId());
	}

	/** Returns true if the given player has OOC toggled on */
	public final boolean isOOC(Player player)
	{
		return oocPlayers.contains(player.getUniqueId());
	}

	// ////////////////////////////////////////////////////////////

	/** Broadcasts an out-of-character message from the given player */
	public final void sendOOCMessage(Player player, String message)
	{
		Channel channel = getActiveChannel(player);
		if (getAllowNicknames(channel))
			channel.announce(ChatColor.DARK_GRAY + "<" + getNickPrefix(player) + ChatColor.BLUE + player.getName() + ChatColor.DARK_GRAY + "> [" + ChatColor.WHITE + ChatColor.ITALIC + message + ChatColor.RESET + ChatColor.DARK_GRAY + "]");
		else
			player.sendMessage(ChatUtility.getSettings().nickMessage + "You are not focused on a roleplay channel.");
	}

	/** Broadcasts an action message from the given player */
	public final void sendActionMessage(Player player, String message)
	{
		if (ChatUtility.getNickManager().hasNick(player))
			message = message.replaceAll(player.getName(), ChatUtility.getNickManager().getNick(player));

		Channel channel = getActiveChannel(player);
		if (getAllowNicknames(channel))
			channel.announce(ChatColor.GRAY + "* " + message);
		else
			player.sendMessage(ChatUtility.getSettings().nickMessage + "You are not focused on a roleplay channel.");
	}

	/** Broadcasts a world action message from the given player */
	public final void sendItMessage(Player player, String message)
	{
		Channel channel = getActiveChannel(player);
		if (getAllowNicknames(channel))
		{
			sendOOCMessage(player, "World action");
			sendActionMessage(player, message);
		}
		else
			player.sendMessage(ChatUtility.getSettings().nickMessage + "You are not focused on a roleplay channel.");
	}

	/** Broadcasts an out-of-character message from the given admin player */
	public final void sendAdminOOCMessage(Player player, String message)
	{
		Channel channel = getActiveChannel(player);
		if (getAllowNicknames(channel))
			channel.announce(ChatColor.DARK_GRAY + "<" + ChatUtility.getPermissionsExHook().getPrefix(player) + ChatUtility.getSettings().adminPrefix + player.getName() + ChatColor.DARK_GRAY + "> [" + ChatColor.WHITE + ChatColor.ITALIC + message + ChatColor.RESET + ChatColor.DARK_GRAY + "]");
		else
			player.sendMessage(ChatUtility.getSettings().nickMessage + "You are not focused on a roleplay channel.");
	}
}
