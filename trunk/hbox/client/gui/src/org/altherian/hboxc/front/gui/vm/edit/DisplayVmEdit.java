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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.altherian.hboxc.front.gui.vm.edit;

import net.miginfocom.swing.MigLayout;

import org.altherian.hbox.comm.in.MachineIn;
import org.altherian.hbox.comm.io.BooleanSettingIO;
import org.altherian.hbox.comm.io.PositiveNumberSettingIO;
import org.altherian.hbox.comm.io.StringSettingIO;
import org.altherian.hbox.comm.out.hypervisor.MachineOut;
import org.altherian.hbox.constant.MachineAttribute;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class DisplayVmEdit {
   
   private MachineIn mIn;
   private MachineOut mOut;
   
   private JPanel panel;
   private JLabel vramLabel;
   private JLabel monitorCountLabel;
   private JLabel accel2dLabel;
   private JLabel accel3dLabel;
   private JTextField vramField;
   private JTextField monitorCountField;
   private JCheckBox accel2dBox;
   private JCheckBox accel3dBox;
   
   private JLabel vrdeEnableLabel;
   private JLabel vrdePortLabel;
   private JLabel vrdeAddressLabel;
   private JLabel vrdeAuthTypeLabel;
   private JLabel vrdeAuthTimeoutLabel;
   private JLabel vrdeAllowMultiConnLabel;
   
   private JCheckBox vrdeEnableValue;
   private JTextField vrdePortValue;
   private JTextField vrdeAddressValue;
   private JTextField vrdeAuthTypeValue;
   private JTextField vrdeAuthTimeoutValue;
   private JCheckBox vrdeAllowMultiConnValue;
   
   public DisplayVmEdit() {
      vramLabel = new JLabel("VRAM");
      vramField = new JTextField();
      monitorCountLabel = new JLabel("Monitors");
      monitorCountField = new JTextField();
      accel2dLabel = new JLabel("2D Acceleration");
      accel2dBox = new JCheckBox();
      accel3dLabel = new JLabel("3D Acceleration");
      accel3dBox = new JCheckBox();
      
      JPanel gfxPanel = new JPanel(new MigLayout());
      gfxPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Graphics"));
      gfxPanel.add(vramLabel);
      gfxPanel.add(vramField, "growx,pushx,wrap");
      gfxPanel.add(monitorCountLabel);
      gfxPanel.add(monitorCountField, "growx,pushx,wrap");
      gfxPanel.add(accel2dLabel);
      gfxPanel.add(accel2dBox, "growx,pushx,wrap");
      gfxPanel.add(accel3dLabel);
      gfxPanel.add(accel3dBox, "growx,pushx,wrap");
      
      vrdeEnableLabel = new JLabel("Enabled");
      vrdePortLabel = new JLabel("Port");
      vrdeAddressLabel = new JLabel("Address");
      vrdeAuthTypeLabel = new JLabel("Authentication Type");
      vrdeAuthTimeoutLabel = new JLabel("Authentication Timeout");
      vrdeAllowMultiConnLabel = new JLabel("Allow Multi Connections");
      
      vrdeEnableValue = new JCheckBox();
      vrdePortValue = new JTextField();
      vrdeAddressValue = new JTextField();
      vrdeAuthTypeValue = new JTextField();
      vrdeAuthTimeoutValue = new JTextField();
      vrdeAllowMultiConnValue = new JCheckBox();
      
      JPanel vrdePanel = new JPanel(new MigLayout());
      vrdePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Remote Console"));
      vrdePanel.add(vrdeEnableLabel);
      vrdePanel.add(vrdeEnableValue, "growx,pushx,wrap");
      vrdePanel.add(vrdePortLabel);
      vrdePanel.add(vrdePortValue, "growx,pushx,wrap");
      vrdePanel.add(vrdeAddressLabel);
      vrdePanel.add(vrdeAddressValue, "growx,pushx,wrap");
      vrdePanel.add(vrdeAuthTypeLabel);
      vrdePanel.add(vrdeAuthTypeValue, "growx,pushx,wrap");
      vrdePanel.add(vrdeAuthTimeoutLabel);
      vrdePanel.add(vrdeAuthTimeoutValue, "growx,pushx,wrap");
      vrdePanel.add(vrdeAllowMultiConnLabel);
      vrdePanel.add(vrdeAllowMultiConnValue, "growx,pushx,wrap");
      
      panel = new JPanel(new MigLayout());
      panel.add(gfxPanel, "growx,pushx,wrap");
      panel.add(vrdePanel, "growx,pushx,wrap");
   }
   
   public Component getComp() {
      return panel;
   }
   
   public void update(MachineOut mOut, MachineIn mIn) {
      this.mIn = mIn;
      this.mOut = mOut;
      
      vramField.setText(mOut.getSetting(MachineAttribute.VRAM).getString());
      monitorCountField.setText(mOut.getSetting(MachineAttribute.MonitorCount).getString());
      accel2dBox.setSelected(mOut.getSetting(MachineAttribute.Accelerate2dVideo).getBoolean());
      accel3dBox.setSelected(mOut.getSetting(MachineAttribute.Accelerate3d).getBoolean());
      vrdeEnableValue.setSelected(mOut.getSetting(MachineAttribute.VrdeEnabled).getBoolean());
      vrdePortValue.setText(mOut.getSetting(MachineAttribute.VrdePort).getString());
      vrdeAddressValue.setText(mOut.getSetting(MachineAttribute.VrdeAddress).getString());
      vrdeAuthTypeValue.setText(mOut.getSetting(MachineAttribute.VrdeAuthType).getString());
      vrdeAuthTimeoutValue.setText(mOut.getSetting(MachineAttribute.VrdeAuthTimeout).getString());
      vrdeAllowMultiConnValue.setSelected(mOut.getSetting(MachineAttribute.VrdeMultiConnection).getBoolean());
   }
   
   public void save() {
      if (!mOut.getSetting(MachineAttribute.VRAM).getString().contentEquals(vramField.getText())) {
         mIn.setSetting(new PositiveNumberSettingIO(MachineAttribute.VRAM, Long.parseLong(vramField.getText())));
      }
      if (!mOut.getSetting(MachineAttribute.MonitorCount).getString().contentEquals(monitorCountField.getText())) {
         mIn.setSetting(new PositiveNumberSettingIO(MachineAttribute.MonitorCount, Long.parseLong(monitorCountField.getText())));
      }
      if (!mOut.getSetting(MachineAttribute.Accelerate2dVideo).getBoolean().equals(accel2dBox.isSelected())) {
         mIn.setSetting(new BooleanSettingIO(MachineAttribute.Accelerate2dVideo, accel2dBox.isSelected()));
      }
      if (!mOut.getSetting(MachineAttribute.Accelerate3d).getBoolean().equals(accel3dBox.isSelected())) {
         mIn.setSetting(new BooleanSettingIO(MachineAttribute.Accelerate3d, accel3dBox.isSelected()));
      }
      if (!mOut.getSetting(MachineAttribute.VrdeEnabled).getBoolean().equals(vrdeEnableValue.isSelected())) {
         mIn.setSetting(new BooleanSettingIO(MachineAttribute.VrdeEnabled, vrdeEnableValue.isSelected()));
      }
      if (!mOut.getSetting(MachineAttribute.VrdePort).getString().contentEquals(vrdePortValue.getText())) {
         mIn.setSetting(new PositiveNumberSettingIO(MachineAttribute.VrdePort, Long.parseLong(vrdePortValue.getText())));
      }
      if (!mOut.getSetting(MachineAttribute.VrdeAddress).getString().contentEquals(vrdeAddressValue.getText())) {
         mIn.setSetting(new StringSettingIO(MachineAttribute.VrdeAddress, vrdeAddressValue.getText()));
      }
      if (!mOut.getSetting(MachineAttribute.VrdeAuthType).getString().contentEquals(vrdeAuthTypeValue.getText())) {
         mIn.setSetting(new StringSettingIO(MachineAttribute.VrdeAuthType, vrdeAuthTypeValue.getText()));
      }
      if (!mOut.getSetting(MachineAttribute.VrdeAuthTimeout).getString().contentEquals(vrdeAuthTimeoutValue.getText())) {
         mIn.setSetting(new StringSettingIO(MachineAttribute.VrdeAuthTimeout, vrdeAuthTimeoutValue.getText()));
      }
      if (!mOut.getSetting(MachineAttribute.VrdeMultiConnection).getBoolean().equals(vrdeAllowMultiConnValue.isSelected())) {
         mIn.setSetting(new BooleanSettingIO(MachineAttribute.VrdeMultiConnection, vrdeAllowMultiConnValue.isSelected()));
      }
   }
   
}
