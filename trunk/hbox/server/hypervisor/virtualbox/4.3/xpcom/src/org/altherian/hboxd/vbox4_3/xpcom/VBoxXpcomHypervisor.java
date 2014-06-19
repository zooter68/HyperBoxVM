/*
 * Hyperbox - Enterprise Virtualization Manager
 * Copyright (C) 2014 Maxime Dor
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.altherian.hboxd.vbox4_3.xpcom;

import org.altherian.hboxd.hypervisor.Hypervisor;
import org.altherian.tool.AxStrings;
import org.altherian.vbox4_3.VBoxHypervisor;

import org.virtualbox_4_3.VirtualBoxManager;

@Hypervisor(
      id = "vbox-4.3-xpcom",
      typeId = "xpcom",
      vendor = "Oracle",
      product = "Virtualbox",
      schemes = { "vbox-4.3-xpcom" })
public final class VBoxXpcomHypervisor extends VBoxHypervisor {
   
   private final String defaultHome = "/usr/lib/virtualbox";
   
   @Override
   public String getId() {
      return this.getClass().getAnnotation(Hypervisor.class).id();
   }
   
   @Override
   public String getTypeId() {
      return this.getClass().getAnnotation(Hypervisor.class).typeId();
   }
   
   @Override
   public String getVendor() {
      return this.getClass().getAnnotation(Hypervisor.class).vendor();
   }
   
   @Override
   public String getProduct() {
      return this.getClass().getAnnotation(Hypervisor.class).product();
   }
   
   @Override
   protected VirtualBoxManager connect(String options) {
      if (AxStrings.isEmpty(options)) {
         options = defaultHome;
      }
      
      return VirtualBoxManager.createInstance(options);
   }
   
   @Override
   protected void disconnect() {
      // nothing to do here
      System.gc();
   }
   
}
