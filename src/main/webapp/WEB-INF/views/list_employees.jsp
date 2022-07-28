<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>

<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="s" %>

<br> <br>
<a  href="addEmployee"> add more employee </a> 
<br> <br>

<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>
<br> <br>
<hr>    
<table border=1 style="margin-left: auto; margin-right: auto;">
  <tr>
    <th>EMPNO</th>
    <th>ENAME</th>
    <th>SAL</th>
    <th>DEPTNO</th>
    <th>ACTIONS</th>
  </tr>    

  <c:if test="${!empty employees}">
  
     <c:forEach  items="${employees}"  var="emp">
        <tr>
          <td> <c:out value="${emp.empno}"/> </td>
          <td> <c:out value="${emp.ename}"/> </td>
          <td> <c:out value="${emp.sal}"/> </td>
          <td> <c:out value="${emp.deptno}"/> </td>
          <td> 
            <s:authorize access="hasRole('ROLE_MANAGER')">
             <a href="editEmployee?id=${emp.empno}"> <img src="images/image_edit.png" width="40" height="40"> </a>
              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
             <a href="deleteEmployee?id=${emp.empno}"> <img src="images/image_delete.png" width="40" height="40"> </a>
            </s:authorize> 
          </td>             
        </tr>
     </c:forEach>
     
  </c:if>
</table>

<center>
<c:if test="${isPrevious}">
  <a href="listEmployees?pageNumber=${currentPage-1}">Previous</a>
</c:if>  
 | 
<c:if test="${isNext}">
  <a href="listEmployees?pageNumber=${currentPage+1}">Next</a>
</c:if>
</center>


