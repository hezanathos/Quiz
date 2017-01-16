<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>quiz - créer quiz</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">
    <link href="resources/styles/compiled/index.css" rel="stylesheet"></link>
    <link href="resources/styles/compiled/create_quiz.css" rel="stylesheet"></link>
</head>

<body>
    <div class="global-header">
        <div class="brand">
            quiz
        </div>
        <div class="profile">
            <a href="deconnexion">Deconnexion</a> (Michel de Lakonta)
        </div>
    </div>
    <div class="create-quiz-content">
        <div class="create-quiz-content-header">
            <h2>Créer un quiz</h2>
            <h4><a>Annuler</a></h4>
        </div>
        <form:form method = "post" action="" commandName="quiz">
            <form:input class="lbl-quiz" type="text" placeholder="Nom du quiz" path="libelle" />
            <div class="select-question">
                <select>
                 <c:forEach items="${listquestion}" var="questionunique">
                 <option><c:out value="${questionunique.libelle}"/></option>
                 </c:forEach>
                
                </select>
                <h6><a href="ajouterQuestion">Ajouter</a></h6>
            </div>
            <ul>
                <li>Quelle est la taille de truc ?<span>x</span></li>
                <li>Quelle est la taille de truc ?<span>x</span></li>
                <li>Quelle est la taille de truc ?<span>x</span></li>
                <li>Quelle est la taille de truc ?<span>x</span></li>
            </ul>
            <input type="submit" value="Créer le quiz">
        </form:form>
    </div>
</body>

</html>