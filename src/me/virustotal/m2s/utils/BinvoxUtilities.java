package me.virustotal.m2s.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;

import org.bukkit.Bukkit;

public class BinvoxUtilities {

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
			return new File(folderPath.getAbsolutePath() + File.separator + "binvox.exe").exists() 
					&& new File(folderPath.getAbsolutePath() + File.separator + "glut32.dll").exists();
		}
		else if(os.equals("linux"))
		{
			return new File(folderPath.getAbsolutePath() + File.separator + "binvox").exists();
		}
		return false;
	}

	public static void downloadBinVox(String os, File folderPath)
	{
		if(os.equals("windows"))
		{
			windowsDownload(os, folderPath);
		}
		else if(os.equalsIgnoreCase("linux"))
		{
			linuxDownload(os, folderPath);
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
			e.printStackTrace();
			return;
		}
		try { 
			URL file = new URL("http://www.ibiblio.org/pub/packages/development/graphics/glut/glutdlls.zip");
			ReadableByteChannel rbc = Channels.newChannel(file.openStream());
			FileOutputStream fos = new FileOutputStream(folderPath.getAbsolutePath() + File.separator + "glutdlls.zip");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			Bukkit.getLogger().log(Level.INFO, "Glutt for " + os + " has been downloaded!");
		} catch(IOException e)
		{
			Bukkit.getLogger().log(Level.SEVERE, "Download failed for glut for os: " + os);
			Bukkit.getLogger().log(Level.SEVERE, "Please report the failure to VirusTotal on spigot");
			e.printStackTrace();
			return;
		}

		Bukkit.getLogger().log(Level.INFO, "Unzipping glut...");

		String glutPath = folderPath.getAbsolutePath() + File.separator + "glutdlls.zip";
		try {
			UnzipUtility.unzip(glutPath, folderPath.getAbsolutePath());
			Bukkit.getLogger().log(Level.INFO, "Glut has been successfully unzipped!");
		} catch (IOException e) {
			Bukkit.getLogger().log(Level.SEVERE, "An errror has occured unzipping glut");
			Bukkit.getLogger().log(Level.SEVERE, "Please report the failure to VirusTotal on spigot");
			e.printStackTrace();
		}
	}

	private static void linuxDownload(String os, File folderPath)
	{
		try {
			URL file = new URL("http://www.cs.princeton.edu/~min/binvox/linux64/binvox");
			ReadableByteChannel rbc = Channels.newChannel(file.openStream());
			FileOutputStream fos = new FileOutputStream(folderPath.getAbsolutePath() + File.separator + "binvox");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			Runtime.getRuntime().exec("chmod +x " + folderPath.getAbsolutePath() + File.separator + "binvox");
			Bukkit.getLogger().log(Level.INFO, "Binvox for " + os + " has been downloaded!");
		} catch (IOException e) {
			Bukkit.getLogger().log(Level.SEVERE, "Download failed for binvox for os: " + os);
			Bukkit.getLogger().log(Level.SEVERE, "Please report the failure to VirusTotal on spigot");
			e.printStackTrace();
			return;
		}

		try {
			Process cat = Runtime.getRuntime().exec("cat /etc/*-release");
			BufferedReader reader = new BufferedReader(new InputStreamReader(cat.getInputStream()));
			String readLine = reader.readLine().toLowerCase();
			
			if(readLine.contains("centos"))
			{

			}
			else if(readLine.contains("debian"))
			{
				
			}
			else if(readLine.contains("ubuntu"))
			{
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}


	}


}
