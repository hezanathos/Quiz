<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter une question</title>
</head>
<body>
<div class = "container">
    <div class="header">
        <h1>QJOUTER UNE QUESTION</h1>
    </div>

    <div class="mainbody">
    <form:form>
        <div class="questioncorps">Question : <form:input type="text" path=""/></div>
        <div class="bonereponse">La bonne réponse.<form:input type="text" path=""/></div>
        <div class="proposition">Proposition N 1:<form:input type="text" path=""/></div>
        <div class="proposition">Proposition N 2:<form:input type="text" path=""/></div>
        <div class="proposition">Proposition N 3:<form:input type="text" path=""/></div>
        <div class="button"><button>AJOUTER</button><button>RETABLIR</button><button>ANNULER</button></div>
   </form:form>
   	<!-- 
    	<div class="questioncorps">Question : <input type="text"/></div>
        <div class="bonereponse">La bonne réponse.<input type="text" /></div>
        <div class="proposition">Proposition N 1:<input type="text" /></div>
        <div class="proposition">Proposition N 2:<input type="text" /></div>
        <div class="proposition">Proposition N 3:<input type="text" /></div>
        <div class="button"><button>AJOUTER</button><button>RETABLIR</button><button>ANNULER</button></div>
	
   	 -->
   
   
    </div>

    <div class="footer">

    </div>
</div>
</body>
</html>