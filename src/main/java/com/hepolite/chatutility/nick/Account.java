package com.hepolite.chatutility.nick;

import java.util.UUID;

import org.bukkit.entity.Player;

public class Account
{
	private final UUID uuid;
	private final String name;
	private String nick;

	public Account(Player player, String nick)
	{
		this.uuid = player.getUniqueId();
		this.name = player.getName();
		this.nick = nick;
	}

	/** Returns the nickname of the player */
	public final String getNick()
	{
		return nick;
	}

	/** Sets the nickname of the player */
	public final void setNick(String nick)
	{
		this.nick = nick;
	}

	/** Returns the name of the player associated with this account */
	public final String getName()
	{
		return name;
	}

	/** Returns the uuid of the player associated with this account */
	public final UUID getUUID()
	{
		return uuid;
	}
}
