<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"  isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>quiz - home</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">
    <link href="resources/styles/compiled/index.css" rel="stylesheet"></link>
    <link href="resources/styles/compiled/home.css" rel="stylesheet"></link>
</head>

<body>
    <div class="global-header">
        <div class="brand">
            quiz
        </div>
        <div class="profile">
            <a href="deconnexion">Deconnexion</a>
        </div>
    </div>
    <div class="home-content">
        <div class="home-content-header">
            <h2></h2><h6></h6>
        </div>
        <div class="active-quiz">
        
        
            <div class="header-aquiz">
                
            </div>
           
            <div class="content-aquiz">
                <div class="participate" ><a href="listQuiz">Liste des quiz</a></div>
            </div>
            
           
            
            
            <c:if test="${!empty listQuiz}">
 				<table>
  					<tr>
   						<th>Libellé</th>
   						<th>Date</th>
   						<th>Jouer</th>
   						<th>Résultats</th>
   					</tr>

  					<c:forEach items="${listQuiz}" var="listQuiz">
   						<tr>
    						<td><c:out value="${listQuiz.libelle}"/></td>
    						
    						<td><c:out value="${listQuiz.dateDebutQuiz}"/></td>
    						<td>
    							<c:if test="${listQuiz.etat == 0}">
    								<a href="demarrerQuiz?idQuiz=${listQuiz.id}"><c:out value="Participer !"/></a>
    							</c:if>
    						</td>
    						<td>
    							<c:if test="${listQuiz.etat == 2}">
    								<a href="afficherStats"><c:out value="Statistiques"/></a>
    							</c:if>
    						</td>
    					</tr>
  					</c:forEach>
 				</table>
			</c:if>
            
            
        </div>
       
       
    </div>
</body>

</html>