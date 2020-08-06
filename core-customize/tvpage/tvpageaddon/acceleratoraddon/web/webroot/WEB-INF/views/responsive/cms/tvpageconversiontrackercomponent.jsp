<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty tvpageAccountId}">
    <script type="text/javascript">
        (function () {
            var tvpa = document.createElement('script');
            tvpa.type = 'text/javascript';
            tvpa.async = true;
            tvpa.src = ('https:' == document.location.protocol ? 'https' : 'http') + '://a.tvpage.com/tvpa.min.js';
            var s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(tvpa, s);
        })();

        var _tvpa = _tvpa || [];
        _tvpa.push(['config', {
            li: "${tvpageAccountId}" // Account ID
        }]);

        _tvpa.push(['track', 'products', {
            "tid": "${orderData.code}",
            "orders": [
                <c:forEach items="${orderData.entries}" var="entry">
                {"sku": "${entry.product.code}", "price": "${entry.basePrice.value}", "quantity": "${entry.quantity}"},
                </c:forEach>
            ]
        }]);
    </script>
</c:if>
