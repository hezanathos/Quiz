<!-- Steven ZANOU -->
<!-- JSP contenant le formulaire d'inscription -->
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Inscription</title>
	</head>
	<body>
		<p>INSCRIPTION</p>
		
		<form action="" method="post">
		
			<label>Adresse mail</label>  
    		<input type="text" name="courriel" placeholder="nom@domaine.com" required>
    		<br/>
    		
    		<label>Mot de passe</label>  
    		<input type="motDePasse" name="motDePasse" placeholder="motDePasse" required>
    		<input type="motDePasse" name="re_motDePasse" placeholder="re_motDePasse" required>
    		<br/>
    		
    		<label>Nom</label>  
    		<input type="text" name="nom" placeholder="Nom" required>
    		<br/>
    		
    		<label>Prénom</label>  
    		<input type="text" name="prenom" placeholder="Prénom" required>
    		<br/>
    		
    		<button type="submit">Inscription</button>
    		<a href="">Annuler</a>
  		</form>

	</body>
</html>