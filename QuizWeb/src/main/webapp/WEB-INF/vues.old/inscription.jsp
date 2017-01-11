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
		<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
    	<link rel="stylesheet" href="../styles/out/index.css">
    	<link rel="stylesheet" href="../styles/out/register.css">
		<title>Inscription</title>
	</head>
	<body>
		<p>INSCRIPTION</p>
  		
  		<form:form method="POST" action="inscription.do" commandName="formulaireInscription">
      		<table>
       			<tr><th></th><th></th></tr>
       			<tr>
           			<td><form:label path="mail" >Adresse mail :</form:label></td>
           			<td><form:input path="mail" placeholder="nom@domaine.com" /></td>
       			</tr>
     			<tr>
           			<td><form:label path="mdp" >Mot de passe :</form:label></td>
           			<td><form:input type="password" path="mdp" placeholder="motDePasse" /></td>
           			<td><form:input type="password" path="mdp" placeholder="re_motDePasse" /></td>
       			</tr>
       			<tr>
           			<td><form:label path="nom" >Nom :</form:label></td>
           			<td><form:input path="nom" placeholder="Nom" required/></td>
       			</tr>
       			<tr>
           			<td><form:label path="prenom" >Prénom :</form:label></td>
           			<td><form:input path="prenom" placeholder="Prénom" required/></td>
       			</tr>
          		<tr>
         			<td colspan="2" class="submit"><input type="submit" value="Inscription"/></td>
         			<td> <a href="">Annuler</a> </td>
        		</tr>
   			</table> 
  		</form:form>

	</body>
</html>