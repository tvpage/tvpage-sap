package com.hybris.tvpage.controllers.pages;

import com.tvpagefacades.TvpageStoreFrontFacade;
import com.tvpagefacades.data.TvpageMetaData;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/storefront/**")
public class TvpageStorefrontController extends AbstractPageController {
    private static final Logger log = Logger.getLogger(TvpageStorefrontController.class);
    private static final String TVPAGE_STOREFRONT_CMS_PAGE = "tvpageStorefront";

    @Resource(name = "tvpageStorefrontFacade")
    private TvpageStoreFrontFacade tvpageStorefrontFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String getAmbassadorPage(final Model model,
                                    final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException, UnsupportedEncodingException {
        String restOfTheUrl = (String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        final ContentPageModel contentPage = getContentPageForLabelOrId(TVPAGE_STOREFRONT_CMS_PAGE);
        String tvPageHtml = tvpageStorefrontFacade.getTvpageStorefrontHtml("dummy");
        TvpageMetaData tvPageMetaTags = tvpageStorefrontFacade.getTvpageStorefrontMetaTags("dummy");
        model.addAttribute("tvPagehtml", tvPageHtml);
        //TODO: update meta tags
        //setUpMetaDataForContentPage(model,contentPage,tvPageMetaTags);
        storeCmsPageInModel(model, contentPage);
        updatePageTitle(model, contentPage);
        return getViewForPage(model);
    }

    protected void updatePageTitle(final Model model, final AbstractPageModel cmsPage) {
        storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(cmsPage.getTitle()));
    }
}