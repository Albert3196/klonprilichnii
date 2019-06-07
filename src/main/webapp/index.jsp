<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.ystu.neutralesprojekt.classes.* "%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.ystu.neutralesprojekt.servlets.ShopServlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<html>
    <meta charset="utf-8" />
    <link href="styles/main.css" rel="stylesheet">
    <body>
        <%! private int ig = 0;%>
        <%! private int g = 0;%>
        <%! private double sum = 0.0;%>
        <%! private List<Long> CookieZakaz;%>

        <%! ArrayList<com.ystu.neutralesprojekt.classes.Cookie> cookiesView = new ArrayList<com.ystu.neutralesprojekt.classes.Cookie>();%>
        <%cookiesView = Data.getInstance().getCookies();%>
        <%g=cookiesView.size();%>

        <h1>Список печенек:</h1>
        <% if (request.getAttribute("list") == null) {%>
            <% request.setAttribute("list", new ArrayList());%>
        <%}%>

        <% ((List)request.getAttribute("list")).clear(); %>

        <% for (int i=1; i<=g; i++) {%>
        <% ((List)request.getAttribute("list")).add (Data.getInstance().getCookieById(i)); %>
        <%}%>

        <div class="wrapper">
            <c:forEach items="${list}" var="item">
                <% ++ig; %>
                <div class="element">
                    <span><b>${item.name}</b></span>
                    <span>Цена: ${item.price}</span>
                    <img src="${item.img}" alt="cookie img" class="img" />
                    <form id=<%=ig%> name="BuyCookieBtn" method="post" action="/shop">
                        <input id="btn" value=<%=ig%> class="input" name="button" />
                        <button type="submit" class="button">Купить</button>
                    </form>
                </div>
            </c:forEach>
        </div>

        <%((List)request.getAttribute("list")).clear(); %>
        <% ig=0; %>

        <% Long id = (Long) request.getSession().getAttribute("idUser"); %>

        <% CookieZakaz = Data.getInstance().getZakazByPerson(id).getCookies(); %>

        <% if (CookieZakaz.size() != 0) {%>
            <h1>Корзина (<%=CookieZakaz.size()%>):</h1>
        <%}%>

        <div class="wrapper">
            <% sum=0.0; %>
            <%for (int j = 0; j< CookieZakaz.size(); j++){ %>
            <% sum = sum + Data.getInstance().getCookieById(CookieZakaz.get(j)).getPrice(); %>
                <div class="element">
                    <span><b>
                        <%=Data.getInstance().getCookieById(CookieZakaz.get(j)).getName()%>
                    </b></span>
                    <span>
                        Цена: <%=Data.getInstance().getCookieById(CookieZakaz.get(j)).getPrice()%>
                    </span>
                    <img src="<%=Data.getInstance().getCookieById(CookieZakaz.get(j)).getImg()%>" alt="cookie img" class="img" />
                </div>
            <% } %>
        </div>

        <% if (CookieZakaz.size() != 0) {%>
            <h1>Сумма: <%=sum%></h1>
        <%}%>
        <%-- } --%>
    </body>
</html>
