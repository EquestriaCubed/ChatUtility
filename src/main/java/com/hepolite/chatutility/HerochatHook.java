package com.hepolite.chatutility;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.dthielke.Herochat;

public class HerochatHook
{
	private final Herochat herochat;

	public HerochatHook()
	{
		Plugin tmp = Bukkit.getServer().getPluginManager().getPlugin("Herochat");
		herochat = (Herochat) (tmp == null ? null : tmp);
		if (herochat == null)
			ChatUtility.getInstance().getLogger().info("Couldn't hook into Herochat...");
		else
			ChatUtility.getInstance().getLogger().info("Hooked into Herochat!");
	}

	/** Returns true if the Herochat plugin is valid */
	public final boolean hasHerochat()
	{
		return herochat != null;
	}

	/** Returns the Herochat plugin instance if it was valid */
	public final Herochat getHerochat()
	{
		return herochat;
	}
}
