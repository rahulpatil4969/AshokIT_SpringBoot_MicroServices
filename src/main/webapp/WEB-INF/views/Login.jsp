<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"  prefix = "c" %>
<html>
 <head>
    <title> Login Page </title>
 </head>
 <body>
   <c:if test = "${message ne null}">
     <font color = 'red'> <c:out value = "${message}" /> </font>
   </c:if>
   <form action = "checkLogin"  method = "post">
     <table>
       <tr>
          <td> Username </td>
          <td> <input type="text"  name="username"> </td>
       </tr>
       <tr>
          <td> Password </td>
          <td> <input type="password"  name="password"> </td>
       </tr>
     </table>
     <input type="submit"  value="SUBMIT">
   </form>
  </body>
</html>  
          