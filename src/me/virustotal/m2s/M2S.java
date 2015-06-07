package me.virustotal.m2s;

import java.io.File;
import java.util.logging.Level;

import me.virustotal.m2s.commands.M2SCmd;
import me.virustotal.m2s.utils.BinvoxUtilities;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class M2S extends JavaPlugin {

	public File modelFolder = new File(this.getDataFolder().getPath(),"models");
	public File pluginFolder = new File(this.getDataFolder().getPath());

	public void onEnable()
	{
		
		if(!pluginFolder.exists())
			pluginFolder.mkdir();
		if(!modelFolder.exists())
			modelFolder.mkdir();
		
		
		String os = BinvoxUtilities.getOS();
		if(os.equals("unknown"))
		{
			Bukkit.getLogger().log(Level.WARNING,"Your operating is not supported, disabling now!");
			Bukkit.getPluginManager().disablePlugin(this);
		}

		Bukkit.getLogger().log(Level.INFO, "Checking for binvox...");
		if(BinvoxUtilities.hasBinVox(os, this.pluginFolder))
		{
			Bukkit.getLogger().log(Level.INFO, "Binvox is installed!");
		}
		else {
			Bukkit.getLogger().log(Level.INFO, "Downloading binvox for " + os);
			BinvoxUtilities.downloadBinVox(os, this.pluginFolder);
		}
		
		this.getCommand("m2s").setExecutor(new M2SCmd(this));
		
	}
}
