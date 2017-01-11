<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>quizdamin</title>
</head>
<body>
<div class = "container">
    <div class="header">
  		<h1>Administrer les quiz</h1>
    </div>

    <div class="mainbody">
    <div class="table">
    <c:if test="${!empty quiz}">
		 <table>
		   <tr>
                <th>Libellé</th>
                <th>Date</th>
                <th>Supprimer</th>
                <th>Editer</th>
                <th>(Re)Lancer</th>
           </tr>

	  <c:forEach items="${quiz}" var="quizunique">
	   <tr>
	    <td><c:out value="${quizunique.getLibelle}"/></td>
	    <td><c:out value="${quizunique.getDateDebutQuiz}"/></td>
	    <td>??button for sup</td>
	    <td>??button for edit</td>
	    <td>??button for lancer</td>
	   </tr>
	  </c:forEach>
	 </table>
	</c:if>
	
	
     </div>
     
        <div class="button?"><button>AJOUTER UN QUIZ</button></div>
    </div>

    <div class="footer">

    </div>
</div>
</body>
</html>