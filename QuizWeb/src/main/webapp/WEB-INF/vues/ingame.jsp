<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"  isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>quiz</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=PT+Sans" rel="stylesheet">

    <link href="resources/styles/compiled/index.css" rel="stylesheet"></link>
    <link href="resources/styles/compiled/ingame.css" rel="stylesheet"></link>
</head>

<body>
    <div class="global-header">
        <div class="brand">
            quiz
        </div>
        <div class="profile">
            <a href="deconnexion">Deconnexion</a> 
        </div>
    </div>
    <div class="ingame-content">
        <div class="bread"></div>

        <div class="pending-question">
            <div class="chrono">
                <span id="secondes">--</span><span id="ms">--</span>
                <div class="bar">
                    <div class="inner-bar" id="inner-bar">

                    </div>
                </div>
            </div>
            
              <c:if test="${!empty quiz && !empty listProp}">
            	
            		<div class="question">
                		<div class="lbl-question">
                			<c:forEach items="${listProp}" var="lprop">
                				<c:if test="${lprop.id == quiz.id}">
                					<c:out value="${lprop.question.libelle}"/>
                				</c:if>
                			
                			</c:forEach>
                    		
                		</div>
                		<div class="answers">
                    		<div class="row">
                        		<c:forEach items="${listProp}" var="lprop">
                            		<c:if test="${lprop.question.id == quiz.id}">
                            		<div class="prop">
                            			<div class="text">
                							<c:out value="${lprop.libelle}"/>
                						</div> 
                					</div> 
                					</c:if>
                            	</c:forEach>
                            	<div class="score">
                                	<div class="inner-score"></div>
                            	</div>	   
                			</div>
           			 	</div>
           			 </div>
            	</c:if>
        </div>
        <div class="results">
            <div class="results-question">
                <div class="stat">
                    <h1>90%</h1>
                    <h6>de bonnes réponses</h6>
                </div>
                <div class="stat">
                    <h1>3'45</h1>
                    <h6>réponse la plu rapide</h6>
                </div>
                <div class="stat">
                    <h1>7%</h1>xr
                    <h6>n'ont pas répondu</h6>
                </div>
            </div>
            <div class="bread">Résultats globaux</div>

            <div class="global-results">
                <div class="list-leaderboard">
                    <div class="item">
                        <div class="index">1.</div>
                        <div class="nick">Tozman</div>
                        <div class="g-score">3</div>
                    </div>
                    <div class="item">
                        <div class="index">2.</div>
                        <div class="nick">Lourzer</div>
                        <div class="g-score">2</div>

                    </div>
                    <div class="item">
                        <div class="index">3.</div>
                        <div class="nick">Mille</div>
                        <div class="g-score">2</div>

                    </div>
                    <div class="item">
                        <div class="index">4.</div>
                        <div class="nick">salugToz</div>
                        <div class="g-score">0</div>

                    </div>
                </div>
            </div>

        </div>

    </div>
    <script type="text/javascript" src="resources/script/jquery.js"></script>
    <script type="text/javascript" src="resources/script/chrono.js"></script>

</body>

</html>