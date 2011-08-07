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
import hudson.model.*;
import org.kohsuke.stapler.export.Exported;
import org.kohsuke.stapler.export.ExportedBean;
import hudson.model.AbstractProject;
import hudson.model.JobProperty;


@ExportedBean
public class DisplaynameProperty extends JobProperty<AbstractProject<?, ?>> {

    private String displayname;
    
    public DisplaynameProperty(String displayname) {
        this.displayname = displayname;
    }    
    
    @Override
    public DescriptorImpl getDescriptor() {
        return DESCRIPTOR;
    }
    
    @SuppressWarnings("unused")
    @Exported
    public String getDisplayname() {
        return displayname;
    }      
    
   
   @Extension
    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();

    public static final class DescriptorImpl extends JobPropertyDescriptor {
        public DescriptorImpl() {
            super(DisplaynameProperty.class);
            load();
        }

        @Override
        public boolean isApplicable(Class<? extends Job> jobType) {
            return AbstractProject.class.isAssignableFrom(jobType);
        }

        @Override
        public String getDisplayName() {
            return Messages.plugin_ColumnName();
        }

        @Override
        public DisplaynameProperty newInstance(org.kohsuke.stapler.StaplerRequest req, net.sf.json.JSONObject jsonObject) throws Descriptor.FormException {
            String displayname = jsonObject.getString("displayname");
            if ((displayname != null) && (displayname.trim().length() != 0))
                return new DisplaynameProperty(displayname);
            else
                return null;
        }
    }

      
}
