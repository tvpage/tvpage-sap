/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.tvpageservices.setup;

import com.tvpageservices.constants.TvpageservicesConstants;
import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;

import java.util.Collections;
import java.util.List;

@SystemSetup(extension = TvpageservicesConstants.EXTENSIONNAME)
public class TvpageServicesSystemSetup extends AbstractSystemSetup {
    @Override
    public List<SystemSetupParameter> getInitializationOptions() {
        return Collections.emptyList();
    }

    @SystemSetup(type = SystemSetup.Type.ESSENTIAL, process = SystemSetup.Process.ALL)
    public void createEssentialData(final SystemSetupContext context) {
        importImpexFile(context, "/tvpageservices/import/essentialdata_cms.impex", true);
        importImpexFile(context, "/tvpageservices/import/essentialdata_integrationobjects.impex", true);
        importImpexFile(context, "/tvpageservices/import/essentialdata_syncattributes.impex", true);
    }
}
