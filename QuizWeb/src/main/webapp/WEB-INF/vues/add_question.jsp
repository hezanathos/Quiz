<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>quiz - ajouter question</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">
    <link href="../styles/out/index.css" rel="stylesheet"></link>
    <link href="../styles/out/add_question.css" rel="stylesheet"></link>
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
    <div class="add-question-content">
        <div class="add-question-content-header">
            <h2>Ajouter une question</h2>
            <h4><a>Rétablir</a></h4>
        </div>

        <form>
            <input class="lbl-question" type="text" placeholder="Intitulé de la question">
            <ul>
                <li>
                    <input type="text" placeholder="Bonne réponse">
                </li>
                <li>
                    <input type="text" placeholder="Proposition #2">
                </li>
                <li>
                    <input type="text" placeholder="Proposition #3">
                </li>
                <li>
                    <input type="text" placeholder="Proposition #4">
                </li>
                
            </ul>
            <input type="submit" value="Valider">
        </form>
    </div>
</body>

</html>