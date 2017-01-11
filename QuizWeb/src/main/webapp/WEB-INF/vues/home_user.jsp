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
    <link href="../styles/out/index.css" rel="stylesheet"></link>
    <link href="../styles/out/home.css" rel="stylesheet"></link>
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
            <h2>Quiz actif</h2><h6>Lancé il y a 12 minutes</h6>
        </div>
        <div class="active-quiz">
            <div class="header-aquiz">
                Ceci est le titre du quiz en cours
            </div>
            <div class="part-aquiz">
                <b>13</b> participants
            </div>
            <div class="content-aquiz">
                <div class="participate">Participer !</div>
            </div>
        </div>
        <div class="home-content-header">
            <h2>Quiz à venir</h2>
        </div>
        <div class="incoming-quiz">
            <div class="title">Ceci est le titre d'un quiz à venir</div>
            <div class="date">34 Février 3245 à 24h73</div>
        </div>
    </div>
</body>

</html>