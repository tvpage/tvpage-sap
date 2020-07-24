/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.hybris.tvpage.renderer;

import com.hybris.tvpage.constants.TvpageaddonConstants;
import de.hybris.platform.addonsupport.renderer.impl.DefaultAddOnCMSComponentRenderer;
import de.hybris.platform.cms2.model.contents.components.SimpleCMSComponentModel;


/**
 * b2bacceleratoraddon renderer for ProductAddToCartComponents
 */
public class TvpageComponentRenderer<C extends SimpleCMSComponentModel> extends DefaultAddOnCMSComponentRenderer<C> {
    @Override
    protected String getAddonUiExtensionName(final C component) {
        return TvpageaddonConstants.EXTENSIONNAME;
    }
}
