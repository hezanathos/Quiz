<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>quiz</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">
    <link href="../styles/out/index.css" rel="stylesheet"></link>
</head>

<body>


    <!--
    <h1>Ceci est un titre h1 de chez SquizzApp</h1>
    <h2>Ceci est un titre h2 de chez SquizzApp</h2>
    <h3>Ceci est un titre h3 de chez SquizzApp</h3>
    <h4>Ceci est un titre h4 de chez SquizzApp</h4>
    <h5>Ceci est un titre h5 de chez SquizzApp</h5>
    <h6>Ceci est un titre h6 de chez SquizzApp</h6>
    <input type="text" placeholder="Ceci est du text">
    <input type="email" placeholder="Ceci est du email">
    <input type="password" placeholder="Ceci est du password">
    <input type="button" value="Ceci est du button">
    <input type="submit" value="Ceci est du submit">
    <a href="#">Ceci est un lien</a>

    <textarea>
        BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR BONSOIR
    </textarea>

    <form>
        <label for="Adresse e-mail">Username</label>
        <input type="text" name="username">
        <label for="Mot de passe">Mot de passe</label>
        <input type="text" name="username">
        <label for="MDR LOL">MDR LOL</label>
        <input type="text" name="username">
    </form>


    <div class="question">
        <div class="label">
            <h1>Quelle est la capitale de l'Australie ?</h1>
        </div>
        <div class="propositions">
            <ul class="row-prop">
                <li class="selected">Syndey</li>
                <li>Melbourne</li>
            </ul>
            <ul class="row-prop">
                <li class="correct">Canberra</li>
                <li class="wrong">Perth</li>
            </ul>
        </div>
    </div>


    <div class="list">
        <div class="item-list">test</div>
        <div class="item-list">test</div>
        <div class="item-list">test</div>
        <div class="item-list">test</div>
        <div class="item-list">test</div>
    </div>
-->

    <div class="game">
        <div class="leaderboard">
            Leaderboard
        </div>
        <div class="plate">
            <div class="chrono">
                <span id="secondes">13</span><span id="ms">123</span>
            </div>
            <div class="content">
                <div class="question">
                    <div class="label">
                        <h1>Quelle est la capitale de l'Australie ?</h1>
                    </div>
                    <div class="propositions">
                        <ul class="row-prop">
                            <li class="selected">Syndey</li>
                            <li>Melbourne</li>
                        </ul>
                        <ul class="row-prop">
                            <li class="correct">Canberra</li>
                            <li class="wrong">Perth</li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="bottom">
                <h3 class="breadcrum">QUESTION <span>15</span> sur <span>20</span></h3>
                <div class="bar">
                    <div class="bar-active">
                    </div>
                </div>
            </div>
        </div>

    </div>
</body>

</html>