/*
 * Copyright (c) 2011 Henrik Lynggaard Hansen 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Henrik Lynggaard Hansen
 */
package dk.hlyh.hudson.plugins.displayname;

import hudson.Extension;
import hudson.model.Job;
import hudson.views.ListViewColumn;
import hudson.views.ListViewColumnDescriptor;
import java.util.Map;
import org.kohsuke.stapler.DataBoundConstructor;

public class DisplaynameColumn extends ListViewColumn{
    
    @DataBoundConstructor
    public DisplaynameColumn() {
    }

    public String getDisplayname(Job job) {
        Map properties = job.getProperties();
        DisplaynameProperty property = (DisplaynameProperty) properties.get(DisplaynameProperty.DESCRIPTOR);
        return property != null ? property.getDisplayname() : job.getName();            
    }
    
    @Extension
    public static class DescriptorImpl extends ListViewColumnDescriptor {
        @Override
        public String getDisplayName() {
            return "Display Name";
        }
    }     
}
