<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty srcUrl}">
    <div id="widget-carousel"></div>
    <script>
        (function (d, s) {
            __TVPage__ = window.__TVPage__ || {};
            __TVPage__.config = __TVPage__.config || {};
            __TVPage__.config["widget-carousel"] = {
                targetEl: "widget-carousel",
                type: "carousel",
                data: ${not empty product && not empty product.tvpageVideoJson ? product.tvpageVideoJson : []},
            };

            window.addEventListener("load", function () {
                var js = d.createElement(s),
                    fjs = d.getElementsByTagName(s)[0];

                js.async = "async";
                js.src = "${srcUrl}";
                fjs.parentNode.insertBefore(js, fjs);
            }, false);
        }(document, 'script'));
    </script>
</c:if>

