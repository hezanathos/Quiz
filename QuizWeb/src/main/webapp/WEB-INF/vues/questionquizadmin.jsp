<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Question Quiz Admin</title>
</head>
<body>
<div class = "container">
    <div class="header">
        <h1>CREER UN QUIZ</h1>
    </div>

    <div class="mainbody">
    <form:form>
      <div class="label"><label>Libellé: </label><form:input type="text" placeholder="**********" path="libelle"/></div>
      <button>AJOUTER UNE QUESTION</button>
      <div class="table">
          <table>
              <tr>
                  <th>Cocher</th><th>Question</th><th>Supprimer</th><th>Editer</th>
              </tr>
              <tr>
                  <td><input type="checkbox"></td><td>?</td><td>?</td><td>?</td>
              </tr>
          </table>

      </div>
        <input type = "submit" value = "??"/>
        <button>CREER</button><button>ANNULER</button>
    </div>
</form:form>
    <div class="footer">

    </div>
</div>
</body>
</html>