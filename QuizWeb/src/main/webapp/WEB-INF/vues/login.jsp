<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>quiz - login</title>
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
    <link rel="stylesheet" href="resources/styles/compiled/index.css">
    <link rel="stylesheet" href="resources/styles/compiled/login.css">

</head>

<body>
    <div class="box">
        <div class="header">
            <div class="brand">quiz</div>
            <div class="catch">the best quiz app ever</div>
        </div>
        <br>
        <div class="form-content">
            <div class="content">
                <h1>Connexion</h1>
                <form:form method="POST" action="connexion.do" commandName="formulaireConnexion">
                	
                    <form:input type="text" placeholder="Votre identifiant" path="mail"/>
                    <form:input type="password" placeholder="Votre mot de passe" path="mdp"/>
                    <input type="submit" value="Se connecter">
                </form:form>
                <br>
                <a href="inscription">Je n'ai pas de compte</a>
            </div>
        </div>
    </div>
</body>

</html>