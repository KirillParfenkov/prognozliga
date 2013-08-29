<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="by.parf.checkers.service.Constant"%>


<c:url value="<%= Constant.URL_LOGIN_CONTROLLER %>" var="urlLoginController"/>
<c:url value="<%= Constant.URL_MAIN_CONTROLLER %>" var="urlMainController"/>

<c:if test="${empty user}">

    <div class="row">
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
            <button type="submit" class="btn btn-default">Войти</button>
          </form>
        </div>
      </div>

</c:if>

<c:if test="${not empty user}">
    <c:out value="Hi, ${user.firstName}  ${user.lastName}"></c:out>
    <a href="<%= Constant.URL_LOGOUT_CONTROLLER %>" >Выйти</a>
    <a href="<%= Constant.URL_MANAGEMENT_CONTROLLER %>">Управление</a>
</c:if>

<c:if test="${not empty errorMessageKey}">
  <div class=error>
     <c:out value="${errorMessageKey}"></c:out>
  </div>
</c:if>

