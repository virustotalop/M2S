package me.virustotal.m2s.commands;

import java.io.File;
import java.io.IOException;

import me.virustotal.m2s.M2S;

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
				else if(args.length == 1)
				{
					
					return true;
				}
				else if(args.length == 2)
				{
					if(args[0].equals("convert"))
					{
						String modelPath = plugin.modelFolder.getAbsolutePath() + File.separator + args[1];
						if(new File(modelPath).exists())
						{
							String binvoxPath = plugin.pluginFolder.getAbsolutePath() + File.separator + "binvox.exe";
							String path = "\"" + binvoxPath + "\"" + " " + "\"" + modelPath + "\"" + " -d 256 -t schematic -aw -c -dc -dmin 1";
							try {
								Runtime.getRuntime().exec(path);
								sender.sendMessage(plugin.convertMessage);
							} catch (IOException e) {
								e.printStackTrace();
							}
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
							}
						} else {
							sender.sendMessage(plugin.dneMessage);
							return true;
						}
					}
				}
				else if(args.length == 3)
				{
					
				}
				return true;
			}
			sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
			return true;
		}
		return false;
	}

}
