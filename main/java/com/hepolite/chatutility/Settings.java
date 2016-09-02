package com.hepolite.chatutility;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Settings
{
	public String nickMessage;
	public String nickAddon;
	public String nickPrefix;
	public String adminPrefix;
	public List<String> channels;
	public List<String> blacklist;
	public String regex;
	public int maxLength;

	/** Reads all the various configurations from the config file */
	public final void load()
	{
		FileConfiguration config = ChatUtility.getInstance().getConfig();

		nickMessage = ChatColor.translateAlternateColorCodes('&', config.getString("nickMessage"));
		nickAddon = ChatColor.translateAlternateColorCodes('&', config.getString("nickAddon"));
		nickPrefix = ChatColor.translateAlternateColorCodes('&', config.getString("nickprefix"));
		adminPrefix = ChatColor.translateAlternateColorCodes('&', config.getString("adminprefix"));
		channels = config.getStringList("channels");
		blacklist = config.getStringList("blacklist");
		regex = config.getString("regex");
		maxLength = config.getInt("maxNickLength");
	}

	/** Handles the reloading of configurations */
	public final void reload()
	{
		ChatUtility.getInstance().reloadConfig();
		load();
	}
}
