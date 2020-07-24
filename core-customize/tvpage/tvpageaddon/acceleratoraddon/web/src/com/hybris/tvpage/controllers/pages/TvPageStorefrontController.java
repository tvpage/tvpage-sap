package com.hybris.tvpage.controllers.pages;

import com.tvpagefacades.TvPageStoreFrontFacade;
import com.tvpagefacades.data.MetaData;
import com.tvpagefacades.data.TvPageContentData;
import de.hybris.platform.acceleratorservices.storefront.data.MetaElementData;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;

@Controller
@RequestMapping("/storefront/**")
public class TvPageStorefrontController extends AbstractPageController {
    private static final Logger log = Logger.getLogger(TvPageStorefrontController.class);
    private static final String TVPAGE_STOREFRONT_CMS_PAGE = "tvpagestorefrontPage";

    @Resource(name = "tvPageStorefrontFacade")
    private TvPageStoreFrontFacade tvPageStorefrontFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String getAmbassadorPage(final Model model,
                                    final HttpServletRequest request, final HttpServletResponse response)
            throws CMSItemNotFoundException, UnsupportedEncodingException {
        String restOfTheUrl = (String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        final ContentPageModel contentPage = getContentPageForLabelOrId(TVPAGE_STOREFRONT_CMS_PAGE);
        TvPageContentData tvPageHtmlData = tvPageStorefrontFacade.getTvPageStorefrontHtml("dummy");
        TvPageContentData tvPageMetaTags = tvPageStorefrontFacade.getTvPageStorefrontMetaTags("dummy");
        model.addAttribute("tvPagehtml", tvPageHtmlData.getHtml());
        setUpMetaDataForContentPage(model, contentPage, tvPageMetaTags);
        storeCmsPageInModel(model, contentPage);
        updatePageTitle(model, contentPage);
        return getViewForPage(model);
    }

    protected void updatePageTitle(final Model model, final AbstractPageModel cmsPage) {
        storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(cmsPage.getTitle()));
    }

    protected void setUpMetaDataForContentPage(final Model model, final ContentPageModel contentPage, final TvPageContentData tvPageData) {
        List<MetaElementData> metatags = new ArrayList<MetaElementData>();
        model.addAttribute("metatags", metatags);
        for (MetaData tvPageMetaData : tvPageData.getMetatags()) {
            MetaElementData metaTag = new MetaElementData();
            metaTag.setName(tvPageMetaData.getKey());
            metaTag.setContent(tvPageMetaData.getValue());
            metatags.add(metaTag);
        }
    }
}