<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty product.tvpageVideoJson}">
    <div id="sample-carousel"></div>
    <script>
        (function (d, s) {
            __TVPage__ = window.__TVPage__ || {};
            __TVPage__.config = __TVPage__.config || {};
            __TVPage__.config["sample-carousel"] = {
                loginId: "1759430",
                channel: {"id": "225234959"},
                targetEl: "sample-carousel"
            };

            window.addEventListener("load", function () {
                var js = d.createElement(s),
                    fjs = d.getElementsByTagName(s)[0];

                js.async = "async";
                js.src = "https://site.app.tvpage.com/1759430/tvpwidget/sample-carousel.js";
                fjs.parentNode.insertBefore(js, fjs);
            }, false);
            console.log(${product.tvpageVideoJson});
        }(document, 'script'));
    </script>
</c:if>
