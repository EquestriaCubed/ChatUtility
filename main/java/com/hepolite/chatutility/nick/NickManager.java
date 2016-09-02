package com.hepolite.chatutility.nick;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.hepolite.chatutility.ChatUtility;

public class NickManager
{
	private final HashMap<UUID, Account> playerAccounts = new HashMap<UUID, Account>();
	private final HashMap<String, Account> playerNicks = new HashMap<String, Account>();

	public NickManager()
	{
	}

	/** Returns the account for the given player; will create the account if it does not exist */
	public final Account getAccount(Player player)
	{
		if (player == null)
			return null;
		UUID uuid = player.getUniqueId();
		if (playerAccounts.containsKey(uuid))
			return playerAccounts.get(uuid);
		Account account = new Account(player, null);
		playerAccounts.put(uuid, account);
		return account;
	}

	/** Returns the account for the given uuid; returns null if the account didn't exist */
	public final Account getAccount(UUID uuid)
	{
		return playerAccounts.get(uuid);
	}

	/** Sets the nickname of the given player; removes the nickname if the nick is null. Returns true if anything was changed */
	public final boolean setNick(Player player, String nick)
	{
		if (nick == null)
			return removeNick(player);

		nick = nick.replaceAll(ChatUtility.getSettings().regex, "").trim();
		String problem = validateNick(nick);
		if (problem != null)
		{
			player.sendMessage(ChatUtility.getSettings().nickMessage + problem);
			return false;
		}

		removeNick(player);
		Account account = getAccount(player);
		account.setNick(nick);
		playerNicks.put(nick.toLowerCase(), account);
		return true;
	}

	/** Removes the nickname of the given player; returns true if the nick was removed */
	public final boolean removeNick(Player player)
	{
		return player == null ? false : removeNick(player.getUniqueId());
	}

	/** Removes the nickname of the given uuid; returns true if the nick was removed */
	public final boolean removeNick(UUID uuid)
	{
		Account account = getAccount(uuid);
		if (account == null || account.getNick() == null)
			return false;
		playerNicks.remove(account.getNick().toLowerCase());
		account.setNick(null);
		return true;
	}

	/** Returns a human-readable error message if there is anything wrong with the nick. Returns null otherwise */
	private final String validateNick(String nick)
	{
		if (nick.isEmpty())
			return String.format("The nickname must contain at least one valid character");
		if (nick.length() > ChatUtility.getSettings().maxLength)
			return String.format("The nickname is too long (Max %d characters)", ChatUtility.getSettings().maxLength);
		if (ChatUtility.getSettings().blacklist.contains(nick.toLowerCase().replaceAll("[._ ]", "")))
			return String.format("The nickname '%s' is not allowed", ChatUtility.getSettings().nickPrefix + nick + ChatColor.WHITE);
		if (isNickUsed(nick))
			return String.format("The nickname '%s' is already in use", ChatUtility.getSettings().nickPrefix + nick + ChatColor.WHITE);
		return null;
	}

	/** Returns true if the given nick is currently in use */
	public final boolean isNickUsed(String nick)
	{
		return playerNicks.containsKey(nick.toLowerCase());
	}

	// /////////////////////////////////////////////////////////////////////

	/** Returns true if the given player has a nick */
	public final boolean hasNick(Player player)
	{
		return getNick(player) != null;
	}

	/** Returns the nickname of the given player, or null if the player has no nickname */
	public final String getNick(Player player)
	{
		return player == null ? null : getAccount(player).getNick();
	}

	/** Returns the player with the given nick */
	public final Account getAccount(String nick)
	{
		return nick == null ? null : playerNicks.get(nick.toLowerCase());
	}
}
