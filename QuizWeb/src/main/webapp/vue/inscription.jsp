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
    		<input type="text" name="mail" placeholder="nom@domain.com" required>
    		<br/>
    		
    		<label>Mot de passe</label>  
    		<input type="password" name="password" placeholder="password" required>
    		<input type="password" name="re_password" placeholder="re_password" required>
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