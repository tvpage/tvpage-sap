package com.tvpage.addon.controllers.pages;

import com.tvpage.addon.constants.TvpageaddonWebConstants;
import com.tvpagefacades.TvpageStoreFrontFacade;
import com.tvpagefacades.data.TvpageMetaData;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(TvpageaddonWebConstants.TVPAGE_STOREFRONT_CMS_PAGE_URL + "/**")
public class TvpageStorefrontController extends AbstractPageController {
    private static final Logger LOG = Logger.getLogger(TvpageStorefrontController.class);

    private static final String TVPAGE_STOREFRONT_CMS_PAGE = "tvpageStorefront";

    @Resource(name = "tvpageStorefrontFacade")
    private TvpageStoreFrontFacade tvpageStorefrontFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String getStorefrontPage(final Model model, final HttpServletRequest request) throws CMSItemNotFoundException {
        String urlPath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String tvpageUrl = urlPath.substring(TvpageaddonWebConstants.TVPAGE_STOREFRONT_CMS_PAGE_URL.length());

        final ContentPageModel contentPage = getContentPageForLabelOrId(TVPAGE_STOREFRONT_CMS_PAGE);
        String tvPageHtml = tvpageStorefrontFacade.getTvpageStorefrontHtml(tvpageUrl);
        TvpageMetaData tvpageMetaData = tvpageStorefrontFacade.getTvpageStorefrontMetaTags(tvpageUrl);
        model.addAttribute("tvPagehtml", tvPageHtml);
        model.addAttribute("tvpageMetaData", tvpageMetaData);
        storeCmsPageInModel(model, contentPage);
        return getViewForPage(model);
    }
}