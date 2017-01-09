<!-- Steven ZANOU -->
<!-- JSP contenant le formulaire de connexion -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Accueil</title>
	</head>
	<body>
		<p>BIENVENUE</p>
  		
  		<form:form method="POST" action="connexion" commandName="formulaireConnexion">
      		<table>
       			<tr><th></th><th></th></tr>
       			<tr>
      				<%-- génère un label avec un for="courriel"  --%>
           			<td><form:label path="courriel" >Adresse mail :</form:label></td>
          			<%-- génère un input avec id="courriel", name="courriel" et value=l'email de la personne reçue via le modèle --%>
           			<td><form:input path="courriel" placeholder="nom@domain.com" required/></td>
       			</tr>
     			<tr>
           			<td><form:label path="motDePasse" >Mot de passe :</form:label></td>
           			<td><form:input type="password" path="motDePasse" placeholder="password" required/></td>
       			</tr>
          		<tr>
         			<td colspan="2" class="submit"><input type="submit" value="Connexion"/></td>
         			<td> ou <a href="inscription">Inscription</a> </td>
        		</tr>
   			</table> 
  		</form:form>

	</body>
</html>