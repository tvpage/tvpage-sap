<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="sample-carousel"></div>
<script>
    (function (d, s) {
        __TVPage__ = window.__TVPage__ || {};
        __TVPage__.config = __TVPage__.config || {};
        __TVPage__.config["sample-carousel"] = {
            targetEl: "sample-carousel",
            type: "carousel",
            <c:choose>
                <c:when test="${not empty product.tvpageVideoJson}">
                    data: ${product.tvpageVideoJson},
                </c:when>
                <c:otherwise>
                    channel: {"id": "225234959", parameters:{"referenceId":""}},
                </c:otherwise>
            </c:choose>
        };

        window.addEventListener("load", function () {
            var js = d.createElement(s),
                fjs = d.getElementsByTagName(s)[0];

            js.async = "async";
            js.src = "https://site.app.tvpage.com/1759430/tvpwidget/sample-carousel.js";
            fjs.parentNode.insertBefore(js, fjs);
        }, false);
    }(document, 'script'));
</script>

