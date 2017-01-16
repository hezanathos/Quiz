<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>quiz - home</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">
    <link href="resources/styles/out/index.css" rel="stylesheet"></link>
    <link href="resources/styles/out/home.css" rel="stylesheet"></link>
</head>

<body>
    <div class="global-header">
        <div class="brand">
            quiz
        </div>
        <div class="profile">
            <a>Deconnexion</a> (Michel de Lakonta)
        </div>
    </div>
    <div class="home-content">
        <div class="home-content-header">
            <h2>Quiz créés</h2>
            <h4><a href="ajouterQuiz">Créer un quiz</a></h4>
        </div>
       	<div class="home-content-list">
       
        <c:if test="${!empty quiz}">
			  <c:forEach items="${quiz}" var="quizunique">
			  
				  <div class="home-content-list-item">
	                <div class="title"><c:out value="${quizunique.libelle}"/></div>
	                <div class="actions">
	                    <a href="demarrerQuiz?id=${quizunique.id}">Lancer</a>
	                    <a href="modifierQuiz?id=${quizunique.id}">Modifier</a>
	                    <a href="supprimerQuiz?id=${quizunique.id}">Supprimer</a>
	                </div>
	           	 </div>
			  
			  </c:forEach>
			</c:if>
  <!--
            <div class="home-content-list-item">
                <div class="title">Ceci est un quiz de ouf</div>
                <div class="actions">
                    <a>Lancer</a>
                    <a>Modifier</a>
                    <a>Supprimer</a>
                </div>
            </div>
            <div class="home-content-list-item">
                <div class="title">Ceci est un quiz de ouf</div>
                <div class="actions">
                    <a>Lancer</a>
                    <a>Modifier</a>
                    <a>Supprimer</a>
                </div>
            </div>
            <div class="home-content-list-item">
                <div class="title">Ceci est un quiz de ouf</div>
                <div class="actions">
                    <a>Lancer</a>
                    <a>Modifier</a>
                    <a>Supprimer</a>
                </div>
            </div>
            <div class="home-content-list-item">
                <div class="title">Ceci est un quiz de ouf</div>
                <div class="actions">
                    <a>Lancer</a>
                    <a>Modifier</a>
                    <a>Supprimer</a>
                </div>
            </div>
            <div class="home-content-list-item">
                <div class="title">Ceci est un quiz de ouf</div>
                <div class="actions">
                    <a>Lancer</a>
                    <a>Modifier</a>
                    <a>Supprimer</a>
                </div>
            </div>
              -->
        </div>
    </div>
</body>

</html>