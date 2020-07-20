/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 * The files in this addon are licensed under the Apache Software License, v. 2
 * except as noted otherwise in the LICENSE file.
 */
package com.tvpage.spartacussampledataaddon.setup.impl;

import de.hybris.platform.addonsupport.setup.impl.DefaultAddonSampleDataImportService;
import de.hybris.platform.core.initialization.SystemSetupContext;

public class TvpageSpaSampleAddOnSampleDataImportService extends DefaultAddonSampleDataImportService {
    @Override
    protected void importContentCatalog(final SystemSetupContext context, final String importRoot, final String catalogName) {
        super.importContentCatalog(context, importRoot, catalogName);
        if (catalogName.equals("electronics") || catalogName.equals("powertools") || catalogName.equals("apparel-uk")) {
            synchronizeContentCatalog(context, catalogName + "-spa", true);
        }
    }
}
