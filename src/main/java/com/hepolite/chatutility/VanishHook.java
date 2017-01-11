package com.hepolite.chatutility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.kitteh.vanish.VanishPlugin;

public class VanishHook
{
	private final VanishPlugin vanish;

	public VanishHook()
	{
		Plugin tmp = Bukkit.getServer().getPluginManager().getPlugin("VanishNoPacket");
		vanish = (VanishPlugin) (tmp == null ? null : tmp);
		if (vanish == null)
			ChatUtility.getInstance().getLogger().info("Couldn't hook into VanishNoPacket...");
		else
			ChatUtility.getInstance().getLogger().info("Hooked into VanishNoPacket!");
	}

	/** Returns true if the VanishNoPacket plugin is valid */
	public final boolean hasVanishNoPacket()
	{
		return vanish != null;
	}

	/** Returns the Herochat plugin instance if it was valid */
	public final VanishPlugin getVanishNoPacket()
	{
		return vanish;
	}

	/** Returns true if the given player is vanished */
	public final boolean isPlayerVanished(Player player)
	{
		return hasVanishNoPacket() ? getVanishNoPacket().getManager().isVanished(player) : false;
	}

	/** Returns true if the observer is able to see the given player */
	public boolean isPlayerVisible(Player observer, Player player)
	{
		return !isPlayerVanished(player) || observer.canSee(player);
	}
}
