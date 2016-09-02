package com.hepolite.chatutility;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PermissionsExHook
{
	private final PermissionsEx permissionsEx;

	public PermissionsExHook()
	{
		Plugin tmp = Bukkit.getServer().getPluginManager().getPlugin("PermissionsEx");
		permissionsEx = (PermissionsEx) (tmp == null ? null : tmp);
		if (permissionsEx == null)
			ChatUtility.getInstance().getLogger().info("Couldn't hook into PermissionsEx...");
		else
			ChatUtility.getInstance().getLogger().info("Hooked into PermissionsEx!");
	}

	/** Returns true if the Herochat plugin is valid */
	public final boolean hasPermissionsEx()
	{
		return permissionsEx != null;
	}

	/** Returns the Herochat plugin instance if it was valid */
	public final PermissionsEx getPermissionsEx()
	{
		return permissionsEx;
	}

	/** Returns the prefix of the given player */
	public final String getPrefix(Player player)
	{
		return ChatColor.translateAlternateColorCodes('&', hasPermissionsEx() ? PermissionsEx.getUser(player).getPrefix() : "");
	}

	/** Returns the prefix of the given name */
	public final String getPrefix(String name)
	{
		return ChatColor.translateAlternateColorCodes('&', hasPermissionsEx() ? PermissionsEx.getUser(name).getPrefix() : "");
	}
}
