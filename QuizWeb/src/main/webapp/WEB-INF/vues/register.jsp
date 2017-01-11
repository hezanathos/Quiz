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
    <link rel="stylesheet" href="../styles/out/index.css">
    <link rel="stylesheet" href="../styles/out/register.css">

</head>

<body>
    <div class="box">
        <div class="header">
            <div class="brand">quiz</div>
            <div class="catch">the best quiz app ever</div>
        </div>
        <div class="form-content">
            <div class="content">
                <h1>Inscription</h1>
                <form:form method="POST" action="inscription.do" commandName="formulaireInscription">
                    <form:input type="text" placeholder="Prénom" required>
                    <form:input type="text" placeholder="Nom" required>
                    <form:input type="text" placeholder="Votre adresse-email" required>
                    <form:input type="password" placeholder="Votre mot de passe" required>
                    <form:input type="password" placeholder="Confirmer le mot de passe" required>
                    <form:input type="submit" value="S'inscrire">
                </form:form>
                <a>J'ai déjà un compte</a>
            </div>
        </div>

    </div>
</body>

</html>