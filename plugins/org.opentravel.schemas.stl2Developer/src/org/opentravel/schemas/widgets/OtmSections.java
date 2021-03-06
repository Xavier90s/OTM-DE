/**
 * Copyright (C) 2014 OpenTravel Alliance (info@opentravel.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.opentravel.schemas.widgets;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.opentravel.schemas.controllers.OtmActions;
import org.opentravel.schemas.properties.Messages;

/**
 * 
 * Create and control twistie section.
 * 
 * @author Dave Hollander
 * 
 */
public class OtmSections extends OtmWidgets {

    public static int[] facetSection = { 200, 201 };
    public static int[] libMetadataSection = { 208, 209 };
    public static int[] propertySection = { 202, 203 };
    public static int[] documentationSection = { 204, 205 };
    public static int[] findingsSection = { 206, 207 };
    public static int[] contextSection = { 210, 211 };

    public OtmSections(final OtmActions actions, final OtmHandlers handlers) {
        super(actions, handlers);

    }

    /**
     * Create a twistie section on the global form in the page. Note - caller must establish the
     * call back. See commented code below. Note - caller needs to get the section.getParent to
     * access the composite.
     */
    public Composite formatSection(final FormToolkit toolkit, final ScrolledForm form,
            final int[] properties) {
        final OtmEventData wd = new OtmEventData();
        wd.label = Messages.getString("OtmW." + properties[0]); //$NON-NLS-1$
        final String tooltip = Messages.getString("OtmW." + properties[1]); //$NON-NLS-1$
        wd.businessEvent = OtmActions.getNoOp();
        wd.actionHandler = null;

        Composite comp = null;
        final Section section = toolkit.createSection(form.getBody(), Section.DESCRIPTION
                | ExpandableComposite.TWISTIE | ExpandableComposite.TITLE_BAR
                | ExpandableComposite.EXPANDED);
        final TableWrapData td = new TableWrapData(TableWrapData.FILL);
        td.colspan = 1;
        td.grabHorizontal = true;
        section.setLayoutData(td);

        comp = toolkit.createComposite(section);
        comp.setLayout(new TableWrapLayout());
        section.setClient(comp);
        section.setText(wd.label);
        section.setDescription(tooltip);
        // widgetTbl.put(wd.key, wd);
        // wd.wParent = comp;
        ((ExpandableComposite) comp.getParent()).addExpansionListener(new ExpansionAdapter() {
            @Override
            public void expansionStateChanged(final ExpansionEvent e) {
                form.reflow(true);
            }
        });

        return comp;
    }

}
