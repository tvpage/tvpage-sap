<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tvpage-template" tagdir="/WEB-INF/tags/addons/tvpageaddon/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>

<tvpage-template:page pageTitle="${pageTitle}">
    <cms:pageSlot position="Section1" var="feature">
        <cms:component component="${feature}" />
    </cms:pageSlot>
</tvpage-template:page>
