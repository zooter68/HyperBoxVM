/*
 * Hyperbox - Enterprise Virtualization Manager
 * Copyright (C) 2013 Maxime Dor
 * 
 * http://hyperbox.altherian.org
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.altherian.hboxd;

import org.altherian.hbox.HyperboxAPI;
import org.altherian.tool.logging.Logger;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class Hyperbox {
   
   private static Properties buildProperties;
   private static String version;
   private static String revision;
   private static String cfgFile = "conf/main.cfg";
   
   private static void failedToLoad(Exception e) {
      Logger.error("Unable to access the build.properties file: " + e.getMessage());
      Logger.error("Version and revision will not be accurate");
   }
   
   public static String getVersion() {
      return version;
   }
   
   public static String getRevision() {
      return revision;
   }
   
   public static String getVersionFull() {
      return HyperboxAPI.getFullVersion(getVersion(), getRevision());
   }
   
   public static String getConfigFilePath() {
      return cfgFile;
   }
   
   static {
      buildProperties = new Properties();
      try {
         buildProperties.load(Hyperbox.class.getResourceAsStream("/server.build.properties"));
      } catch (IOException e) {
         failedToLoad(e);
      } catch (NullPointerException e) {
         failedToLoad(e);
      } finally {
         version = buildProperties.getProperty("version", HyperboxAPI.VERSION_UNKNOWN);
         revision = buildProperties.getProperty("revision", HyperboxAPI.REVISION_UNKNOWN);
      }
   }
   
   public static void processArgs(Set<String> args) {
      HyperboxAPI.processArgs(args);
      
      if (args.contains("-?") || args.contains("--help")) {
         System.out.println("Hyperbox available executable switches:\n");
         System.out.println("--help or -? : Print this help");
         // TODO enable more command line switches
         System.out.println("--apiversion : Print API version");
         System.out.println("--apirevision : Print API revision");
         System.out.println("--netversion : Print Net protocol version");
         System.out.println("--version : Print Server version");
         System.out.println("--revision : Print Server revision");
         System.exit(0);
      }
      if (args.contains("--version")) {
         System.out.println(getVersionFull());
         System.exit(0);
      }
      if (args.contains("--revision")) {
         System.out.println(getRevision());
         System.exit(0);
      }
   }
   
}
