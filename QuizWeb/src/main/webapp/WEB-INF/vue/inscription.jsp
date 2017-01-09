<!-- Steven ZANOU -->
<!-- JSP contenant le formulaire d'inscription -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Inscription</title>
	</head>
	<body>
		<p>INSCRIPTION</p>
  		
  		<form:form method="POST" action="inscription" commandName="formulaireInscription">
      		<table>
       			<tr><th></th><th></th></tr>
       			<tr>
      				<%-- génère un label avec un for="courriel"  --%>
           			<td><form:label path="courriel" >Adresse mail :</form:label></td>
          			<%-- génère un input avec id="courriel", name="courriel" et value=l'email de la personne reçue via le modèle --%>
           			<td><form:input path="courriel" placeholder="nom@domaine.com" required/></td>
       			</tr>
     			<tr>
           			<td><form:label path="motDePasse" >Mot de passe :</form:label></td>
           			<td><form:input type="password" path="motDePasse" placeholder="motDePasse" required/></td>
           			<td><form:input type="password" path="re_motDePasse" placeholder="re_motDePasse" required/></td>
       			</tr>
       			<tr>
           			<td><form:label path="nom" >Nom :</form:label></td>
           			<td><form:input path="nom" placeholder="Nom" placeholder="Nom" required/></td>
       			</tr>
       			<tr>
           			<td><form:label path="prenom" >Prénom :</form:label></td>
           			<td><form:input path="prenom" placeholder="Prénom" placeholder="Prénom" required/></td>
       			</tr>
          		<tr>
         			<td colspan="2" class="submit"><input type="submit" value="Inscription"/></td>
         			<td> <a href="">Annuler</a> </td>
        		</tr>
   			</table> 
  		</form:form>

	</body>
</html>