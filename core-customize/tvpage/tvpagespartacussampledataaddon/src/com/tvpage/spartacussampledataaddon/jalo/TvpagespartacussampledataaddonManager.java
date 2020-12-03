package com.tvpage.spartacussampledataaddon.jalo;

import com.tvpage.spartacussampledataaddon.constants.TvpagespartacussampledataaddonConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

public class TvpagespartacussampledataaddonManager extends GeneratedTvpagespartacussampledataaddonManager {
    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(TvpagespartacussampledataaddonManager.class.getName());

    public static final TvpagespartacussampledataaddonManager getInstance() {
        ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
        return (TvpagespartacussampledataaddonManager) em.getExtension(TvpagespartacussampledataaddonConstants.EXTENSIONNAME);
    }

}
