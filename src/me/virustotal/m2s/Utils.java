package me.virustotal.m2s;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;

import org.bukkit.Bukkit;

public class Utils {
	
	public static String getOS()
	{
		String name = System.getProperties().getProperty("os.name").toLowerCase();
		if(name.startsWith("windows"))
		{
			return "windows";
		}
		else if(name.startsWith("mac"))
		{
			return "mac";
		}
		else if(name.startsWith("linux"))
		{
			return "linux";
		}
		return "unknown";
	}
	
	public static boolean hasBinVox(String os, File folderPath)
	{
		if(os.equals("windows"))
		{
			if(new File(folderPath.getAbsolutePath() + File.pathSeparator + "binvox.exe").exists())
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static void downloadBinVox(String os, File folderPath)
	{
		if(os.equals("windows"))
		{
			windowsDownload(os,folderPath);
		}
		
	}
	
	private static void windowsDownload(String os, File folderPath)
	{
		try {
			URL file = new URL("http://www.cs.princeton.edu/~min/binvox/win/binvox.exe");
			ReadableByteChannel rbc = Channels.newChannel(file.openStream());
			FileOutputStream fos = new FileOutputStream(folderPath.getAbsolutePath() + File.separator + "binvox.exe");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			Bukkit.getLogger().log(Level.INFO, "Binvox for " + os + " has been downloaded!");
		} catch (IOException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Download failed for binvox for os: " + os);
			Bukkit.getLogger().log(Level.SEVERE, "Please report the failure to VirusTotal on spigot");
			return;
		}
		try { 
			URL file = new URL("http://www.ibiblio.org/pub/packages/development/graphics/glut/glutdlls.zip");
			ReadableByteChannel rbc = Channels.newChannel(file.openStream());
			FileOutputStream fos = new FileOutputStream(folderPath.getAbsolutePath() + File.separator + "glutdlls.zip");
			fos.close();
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch(IOException e)
		{
			Bukkit.getLogger().log(Level.SEVERE, "Download failed for glut for os: " + os);
			Bukkit.getLogger().log(Level.SEVERE, "Please report the failure to VirusTotal on spigot");
			return;
		}
		
		
		
	}

}
