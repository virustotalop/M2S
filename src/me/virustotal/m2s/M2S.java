package me.virustotal.m2s;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class M2S extends JavaPlugin {
	
	public File modelFolder = new File(this.getDataFolder().getPath(),"models");
	public File pluginFolder = new File(this.getDataFolder().getPath());
	
	public void onEnable()
	{
		String os = Utils.getOS();
		if(os.equals("unknown"))
		{
			Bukkit.getLogger().log(Level.WARNING,"Your operating is not supported, disabling now!");
			Bukkit.getPluginManager().disablePlugin(this);
		}
		
		Bukkit.getLogger().log(Level.INFO, "Checking for binvox...");
		if(Utils.hasBinVox(os, this.pluginFolder))
		{
			Bukkit.getLogger().log(Level.INFO, "Binvox is installed!");
		}
		else {
			Bukkit.getLogger().log(Level.INFO, "Downloading binvox for " + os);
			Utils.downloadBinVox(os, this.pluginFolder);
		}
		
		
	}

}
