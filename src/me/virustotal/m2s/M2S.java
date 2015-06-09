package me.virustotal.m2s;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import me.virustotal.m2s.commands.M2SCmd;
import me.virustotal.m2s.utils.BinvoxUtilities;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class M2S extends JavaPlugin {

	public File configFile = new File(this.getDataFolder().getPath(),"config.yml");
	public File modelFolder = new File(this.getDataFolder().getPath(),"models");
	public File pluginFolder = new File(this.getDataFolder().getPath());
	public File schematicFolder;
	public WorldEditPlugin we;
	
	public List<String> menu = new ArrayList<String>();
	
	public String convertMessage;
	public String dneMessage;
	public String fileMoved;
	
	public String convertFormatMessage;
	public String moveFormatMessage;
	public String listFormatMessage;
	
	public String noModelMessage;
	public String noSchematicMessage;
	
	public void onEnable()
	{
		if(!pluginFolder.exists())
			pluginFolder.mkdir();
		if(!modelFolder.exists())
			modelFolder.mkdir();
		if(!configFile.exists())
			this.saveDefaultConfig();
		else
			this.reloadConfig();
		
		for(String string : this.getConfig().getStringList("m2s-menu"))
		{
			this.menu.add(ChatColor.translateAlternateColorCodes('&',string));
		}
		
		this.convertMessage = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("convert-message"));
		this.dneMessage = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("dne-message"));
		this.fileMoved = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("file-moved"));
		
		this.convertFormatMessage = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("convert-format-message"));
		this.moveFormatMessage = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("move-format-message"));
		this.listFormatMessage = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("list-format-message"));
		
		this.noModelMessage = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("no-model-message"));
		this.noSchematicMessage = ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("no-schematic-message"));
		
		String os = BinvoxUtilities.getOS();
		if(os.equals("unknown"))
		{
			Bukkit.getLogger().log(Level.WARNING,"Your operating is not supported, disabling now!");
			Bukkit.getPluginManager().disablePlugin(this);
		}

		this.we = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
		this.schematicFolder = new File(this.we.getDataFolder().getAbsolutePath() + File.separator + "schematics");
		
		Bukkit.getLogger().log(Level.INFO, "I, VirusTotal am not responsible for any content in binvox or glutt.");
		Bukkit.getLogger().log(Level.INFO, "If you have any issues with either of those programs please contact their respective authors.");
		Bukkit.getLogger().log(Level.INFO, "If this program throws an error contact VirusTotal.");
		
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
