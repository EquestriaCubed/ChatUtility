package com.hepolite.chatutility;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.hepolite.chatutility.cmd.CmdMessageChannel;
import com.hepolite.chatutility.cmd.CmdMessagePermission;
import com.hepolite.chatutility.cmd.CmdMessagePlayer;
import com.hepolite.chatutility.cmd.CmdNickChannelNick;
import com.hepolite.chatutility.cmd.CmdNickDelnick;
import com.hepolite.chatutility.cmd.CmdNickMessage;
import com.hepolite.chatutility.cmd.CmdNickOOCToggle;
import com.hepolite.chatutility.cmd.CmdNickRealname;
import com.hepolite.chatutility.cmd.CmdNickRealnames;
import com.hepolite.chatutility.cmd.CmdNickSet;
import com.hepolite.chatutility.cmd.CmdReload;
import com.hepolite.chatutility.nick.HerochatAdapter;
import com.hepolite.chatutility.nick.NickManager;

public class ChatUtility extends JavaPlugin
{
	private static ChatUtility instance;
	private Settings settings;
	private HerochatHook herochatHook;
	private VanishHook vanishHook;
	private PermissionsExHook permissionsExHook;
	private NickManager nickManager;
	private HerochatAdapter herochatAdapter;

	@Override
	public void onEnable()
	{
		instance = this;
		herochatHook = new HerochatHook();
		vanishHook = new VanishHook();
		permissionsExHook = new PermissionsExHook();

		settings = new Settings();
		settings.load();
		nickManager = new NickManager();

		herochatAdapter = new HerochatAdapter();
		Bukkit.getPluginManager().registerEvents(herochatAdapter, this);

		getCommand("rs_channel").setExecutor(new CmdMessageChannel());
		getCommand("rs_permission").setExecutor(new CmdMessagePermission());
		getCommand("rs_player").setExecutor(new CmdMessagePlayer());

		getCommand("nick").setExecutor(new CmdNickSet());
		getCommand("realname").setExecutor(new CmdNickRealname());
		getCommand("realnames").setExecutor(new CmdNickRealnames());
		getCommand("delnick").setExecutor(new CmdNickDelnick());
		getCommand("ChannelNick").setExecutor(new CmdNickChannelNick());
		getCommand("ooc").setExecutor(new CmdNickMessage.OOC());
		getCommand("ooca").setExecutor(new CmdNickMessage.OOCA());
		getCommand("it").setExecutor(new CmdNickMessage.IT());
		getCommand("ooctoggle").setExecutor(new CmdNickOOCToggle());
		getCommand("chatutilityreload").setExecutor(new CmdReload());
	}

	@Override
	public void onDisable()
	{
	}

	/** Returns the plugin instance */
	public final static ChatUtility getInstance()
	{
		return instance;
	}

	/** Returns the Herochat hook */
	public final static HerochatHook getHerochatHook()
	{
		return instance.herochatHook;
	}

	/** Returns the Vanish hook */
	public final static VanishHook getVanishHook()
	{
		return instance.vanishHook;
	}

	/** Returns the PermissionsEx hook */
	public final static PermissionsExHook getPermissionsExHook()
	{
		return instance.permissionsExHook;
	}

	/** Returns the settings */
	public final static Settings getSettings()
	{
		return instance.settings;
	}

	/** Returns the nick manager */
	public final static NickManager getNickManager()
	{
		return instance.nickManager;
	}

	public static HerochatAdapter getHerochatAdapter()
	{
		return instance.herochatAdapter;
	}
}
