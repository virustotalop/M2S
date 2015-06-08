package me.virustotal.m2s.commands;

import java.io.File;
import java.io.IOException;

import me.virustotal.m2s.M2S;
import me.virustotal.m2s.utils.BinvoxUtilities;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class M2SCmd implements CommandExecutor {

	private M2S plugin;
	public M2SCmd(M2S plugin)
	{
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {

		if(label.equalsIgnoreCase("m2s"))
		{
			if(sender.hasPermission("m2s.use"))
			{

				if(args.length == 0)
				{
					for(String string : plugin.menu)
					{
						sender.sendMessage(string);
					}
					return true;
				}
				else if(args.length == 1) //only one arg
				{
					if(args[0].equalsIgnoreCase("convert"))
					{
						sender.sendMessage(plugin.convertFormatMessage);
						return true;
					}
					else if(args[0].equalsIgnoreCase("move"))
					{
						sender.sendMessage(plugin.moveFormatMessage);
						return true;
					}
					else if(args[0].equalsIgnoreCase("list"))
					{
						sender.sendMessage(plugin.listFormatMessage);
						return true;
					}
					for(String string : plugin.menu)
					{
						sender.sendMessage(string);
					}
					return true;
				}
				else if(args.length == 2) //two args
				{
					if(args[0].equals("convert"))
					{
						String modelPath = plugin.modelFolder.getAbsolutePath() + File.separator + args[1];
						if(new File(modelPath).exists())
						{
							if(BinvoxUtilities.getOS().equals("windows"))
							{
								this.runForWindows(modelPath);
							}
							else if(BinvoxUtilities.getOS().equals("linux"))
							{
								this.runForLinux(modelPath);
							}
							sender.sendMessage(plugin.convertMessage);
							return true;
						} else {
							sender.sendMessage(plugin.dneMessage);
							return true;
						}
					}
					else if(args[0].equalsIgnoreCase("move"))
					{
						String schematicPath = plugin.modelFolder.getAbsolutePath() + File.separator + args[1];
						File schematicFile = new File(schematicPath); 

						if(schematicFile.exists())
						{
							if(schematicFile.getName().endsWith(".schematic"))
							{
								schematicFile.renameTo(new File(plugin.schematicFolder + File.separator + schematicFile.getName()));
								sender.sendMessage(plugin.fileMoved);
								return true;
							}
						} 
						else 
						{
							sender.sendMessage(plugin.dneMessage);
							return true;
						}
					}
					else if(args[0].equalsIgnoreCase("list"))
					{
						if(args[1].equalsIgnoreCase("models"))
						{
							boolean hasFile = false;
							File[] ar = plugin.modelFolder.listFiles();
							for(File file : ar)
							{
								if(!file.getName().endsWith(".schematic"))
								{
									hasFile = true;
									sender.sendMessage(file.getName());
								}
							}
							if(!hasFile)
							{
								sender.sendMessage(plugin.noModelMessage);
							}
							return true;
						}
						else if(args[1].equalsIgnoreCase("schematics"))
						{
							boolean hasFile = false;
							File[] ar = plugin.modelFolder.listFiles();
							for(File file : ar)
							{
								if(file.getName().endsWith(".schematic"))
								{
									hasFile = true;
									sender.sendMessage(file.getName());
								}
							}
							if(!hasFile)
							{
								sender.sendMessage(plugin.noSchematicMessage);
							}
							return true;
						}
						else {
							sender.sendMessage(plugin.listFormatMessage);
							return true;
						}
					}
				}
				else {
					for(String string : plugin.menu)
					{
						sender.sendMessage(string);
					}
					return true;
				}
				return true;
			}
			sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
			return true;
		}
		return false;
	}

	public void runForWindows(String modelPath)
	{
		String binvoxPath = plugin.pluginFolder.getAbsolutePath() + File.separator + "binvox.exe";
		String path = "\"" + binvoxPath + "\"" + " " + "\"" + modelPath + "\"" + " -d 256 -t schematic -aw -c -dc -dmin 1 -down";
		try {
			Runtime.getRuntime().exec(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void runForLinux(String modelPath)
	{
		String binvoxPath = plugin.pluginFolder.getAbsolutePath() + File.separator + "binvox";
		String path = "screen 0 640x480x24 "+ binvoxPath  + " "  + modelPath  + " -d 256 -t schematic -aw -c -dc -dmin 1 -down";
		try {
			Runtime.getRuntime().exec(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
