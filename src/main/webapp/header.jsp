<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="by.parf.checkers.service.Constant"%>


<c:url value="<%= Constant.URL_LOGIN_CONTROLLER %>" var="urlLoginController"/>
<c:url value="<%= Constant.URL_MAIN_CONTROLLER %>" var="urlMainController"/>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
<a class="navbar-brand" href="#">PrognozLiga</a>
<c:if test="${empty user}">
    <div class="row pull-right" style="margin-right: 0px">
        <div class="col-md-12">
          <form class="form-inline" role="form" action="<%= Constant.URL_LOGIN_CONTROLLER %>">
            <div class="form-group">
              <label class="sr-only" for="exampleInputEmail2">Email address</label>
              <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Enter email" name="<%= Constant.KEY_INPUT_EMAIL %>">
            </div>
            <div class="form-group">
              <label class="sr-only" for="exampleInputPassword2">Password</label>
              <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password" name="<%= Constant.KEY_INPUT_PASSWORD%>">
            </div>
            <button type="submit" class="btn btn-default navbar-btn">Войти</button>
          </form>
        </div>
      </div>
</c:if>

<c:if test="${not empty user}">
  <ul class="nav navbar-nav">
    <c:if test="${user.profile.id == 0}">
      <li><a href="<%= Constant.URL_MANAGEMENT_CONTROLLER %>">Управление</a></li>
    </c:if>
    <li><a href="<%= Constant.URL_MAIN_CONTROLLER %>">Главная</a></li>
    <li>
      <a href='#'>
        Рейтинг <span class="badge"> 2 </span>
      </a>
    </li>
  </ul>
  <ul class="nav navbar-nav navbar-right">
    <li><p class="navbar-text"><c:out value="Hi, ${user.firstName}  ${user.lastName}"></p></c:out></li>
    <li><a href="<%= Constant.URL_LOGOUT_CONTROLLER %>" >Выйти</a></li>
  </ul>
</c:if>
</nav>


<c:if test="${not empty errorMessageKey}">
  <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="top: 50px">
    <div class=error>
      <div id="topErrorMessage" class="alert alert-danger"><c:out value="${errorMessageKey}"></c:out></div>
    </div>
  </nav>
</c:if>



