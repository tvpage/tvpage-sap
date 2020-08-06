package com.tvpage.addon.tags;

import com.tvpagefacades.data.*;
import de.hybris.platform.acceleratorservices.util.HtmlElementHelper;
import de.hybris.platform.acceleratorservices.util.SpringHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;
import java.io.IOException;
import java.util.*;

public class TvpageMetaTag extends TagSupport implements TryCatchFinally {

    private static final Logger LOG = Logger.getLogger(TvpageMetaTag.class.getName());

    protected HtmlElementHelper htmlElementHelper; // NOSONAR
    protected TvpageMetaData tvpageMetaData; // NOSONAR

    @Override
    public int doStartTag() {
        loadServices();

        Optional.ofNullable(tvpageMetaData)
                .map(TvpageMetaData::getHtml)
                .map(TvpageMetaHtmlData::getHead)
                .map(TvpageMetaHtmlHeadData::getTags)
                .orElse(Collections.emptyList())
                .forEach(metaTagData -> writeMetaTagData(metaTagData));

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }


    @Override
    public void release() {
        super.release();
        reset();
    }

    @Override
    public void doCatch(Throwable throwable) {
        // Nothing to do
    }

    @Override
    public void doFinally() {
        reset();
    }

    protected void writeMetaTagData(final MetaTagData metaTagData) {

        htmlElementHelper.writeOpenElement(pageContext, metaTagData.getTag(), getAttributesMap(metaTagData.getAttributes()));

        if (!("meta".equals(metaTagData.getTag()) || "link".equals(metaTagData.getTag()) || "base".equals(metaTagData.getTag()))) {
            final JspWriter out = pageContext.getOut();
            try {
                if (StringUtils.isNotBlank(metaTagData.getValue())) {
                    out.write(metaTagData.getValue());
                    out.write("\n");
                }

                htmlElementHelper.writeEndElement(pageContext, metaTagData.getTag());
                out.write("\n");
            } catch (IOException e) {
                LOG.warn("Could not write tag value: " + e);
            }
        }
    }

    protected Map<String, String> getAttributesMap(final List<MetaTagAttributeData> metaTagAttributeDataList) {
        final Map<String, String> attributes = new LinkedHashMap<>();
        if (!CollectionUtils.isEmpty(metaTagAttributeDataList)) {
            for (MetaTagAttributeData metaTagAttributeData : metaTagAttributeDataList) {
                attributes.put(metaTagAttributeData.getAttribute(), metaTagAttributeData.getValue());
            }
        }
        return attributes;
    }


    protected void loadServices() {
        if (htmlElementHelper == null) {
            htmlElementHelper = lookupHtmlElementHelper();
        }
    }

    protected void reset() {
        htmlElementHelper = null;
        tvpageMetaData = null;
    }

    protected HtmlElementHelper lookupHtmlElementHelper() {
        return SpringHelper.getSpringBean(pageContext.getRequest(), "htmlElementHelper", HtmlElementHelper.class, true);
    }


    public void setData(final TvpageMetaData tvpageMetaData) {
        this.tvpageMetaData = tvpageMetaData;
    }

}
