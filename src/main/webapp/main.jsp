<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="by.parf.checkers.service.Constant"%>

<html>
	  <head>
        <title>Prognozliga</title>
        <meta chatset='utf-8'>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="../../assets/js/html5shiv.js"></script>
          <script src="../../assets/js/respond.min.js"></script>
        <![endif]-->
        <style type="text/css">
    		#header {
    			margin: 0 auto;
    			float:none;
    			width: 500px;
    			margin-top: 40px;
    		}

    		#mainList {
    			margin: 0 auto;
    			width: 1040px;
    			margin-top: 50px;
    		}

    		#evalTable {
    		/*	text-align: center;*/
    		}

    		.match {
    			margin-top: 100px;
    		}

            body {
                margin: 0;
                background-color: white;
            }

            .rightPosition {
                right: 0;
            }

            #topErrorMessage {
                margin-top: 10px;
            }

            .hidden {
                display: none;
            }

            .alert {
                margin: 0px;
            }

    	</style>
      </head>
    <body>
        <style>

        </style>
       
        <jsp:include page="<%= Constant.URL_HEADER_PAGE%>" />

        <div id="mainList">
            <c:forEach var="matchSet" items="${matchSetList}">
                <div class="match" id="match_${matchSet.id}">
                        <div class="panel panel-default">
                            <div class="panel-heading">${matchSet.date}</div>

                            <div class="panel-body">
                                
                                <table class="table table-hover table-condensed" id="evalTable_${matchSet.id}">
                                    <thead>
                                        <tr>
                                            <th>Участник</th>
                                            <c:forEach var="match" items="${matchSet.matches}">
                                                <th class="col-md-1">${match.teamFirst.name} - ${match.teamSecond.name}</th>
                                            </c:forEach>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="userEvaluation" items="${matchSet.userList}">
                                            <tr>
                                                <td>${userEvaluation.key.firstName} ${userEvaluation.key.lastName}</td>

                                                <c:forEach var="match" items="${matchSet.matches}">
                                                    <td>${userEvaluation.value[match].teamFirstGoals}:${userEvaluation.value[match].teamSecondGoals}</td>
                                                </c:forEach>
                                            </tr>
                                        </c:forEach> 
                                    </tbody>
                                    <c:if test="${(matchSet.userList[user] == null) && (user != null)}">
                                        <tfoot>
                                            <tr>
                                                <td>
                                                    ${user.firstName} ${user.lastName} 
                                                </td>
                                                <c:forEach var="match" items="${matchSet.matches}">
                                                    <td>
                                                        <div class="form-group">
                                                            <input type="text" class="form-control" id="team1Goals">
                                                        </div>
                                                    </td>
                                                </c:forEach>
                                                <td>
                                                    <button type="submit" class="btn btn-default sendEstimationButton" value="${matchSet.id}">Отправить</button>
                                                </td>
                                            </tr>
                                        </tfoot>
                                    </c:if>
                                </table>
                                <div class="panel-footer hidden">
                                    <div class="alert alert-success hidden">
                                        Ваш прогноз успешно добавлен.
                                    </div> 
                                    <div class="alert alert-danger hidden">
                                        Ошибка. Возможно данные введены не корректно. 
                                    </div>
                                </div>
                            </div>
                        </div>
                </div>
            </c:forEach>
        </div>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="http://code.jquery.com/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>

        <script type="text/javascript">

            var rootpath = 'http://' + location.hostname + ':' + location.port;

            $(document).ready(function() {

                var httpRequest;

                if (window.XMLHttpRequest) {
                    httpRequest = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
                }

                httpRequest.onreadystatechange = function(){

                    if (httpRequest.readyState === 4) {
                        if (httpRequest.status === 200) {

                            //var result = JSON.parse(httpRequest.responseText);
                            //var compTeamRes = result.type.localeCompare("teamList");


                            // makeProgress(move);
                           console.log(httpRequest.responseText);

                        } else {
                            // alert('There was a problem with the request.');
                        }
                    }
                };

                var dataValidator = {

                    checkEstimation: function(estimations) {

                        for(var i = 0; i < estimations.length; i++) {
                            console.log(estimations[i]);
                            if (estimations[i] == null) {
                                return false;
                            }

                            var snipets = estimations[i].split(':');
                            if (snipets.length != 2) {
                                return false;
                            }
                            if (! ($.isNumeric(snipets[0]) && $.isNumeric(snipets[1])) ) {
                                return false;
                            }

                            if ( ((snipets[0] % 1) != 0) && ((snipets[1] % 1) != 0) ) {
                                return false;
                            }

                        }
                        return true;
                    }
                }

                var dataUpdater = {

                    updateEstimation: function (estimations, matchSetId) {

                        var addEstimationControllerUrl = rootpath + "/addEstimationController";

                        var params = "?inputEstimations=" + estimations.join() + "&matchSet=" + matchSetId;
                        httpRequest.open('GET', addEstimationControllerUrl + params, true); 
                        httpRequest.send(null);
                        selectedChecker = null;
                    },

                    insertEstimation: function (estimations, matchSetId) {

                        var estimationRow = '<tr><td>' + '${user.firstName} ${user.lastName}' + '</td>';
                        for (var i = 0; i < estimations.length; i++) {
                            estimationRow += '<td>' + estimations[i] + '</td>';
                        }
                        estimationRow += '</tr>';
                        $('#evalTable_' + matchSetId).append(estimationRow);

                    }

                };

                $('.sendEstimationButton').on('click', function () {
                    var estimations = new Array();
                    var matchSetId = $( this )[0].value;
                    $('#evalTable_' + matchSetId).find('tfoot').find('input').each(function() {
                        estimations.push($( this )[0].value);
                    })

                    if (dataValidator.checkEstimation(estimations)) {
                        $('#match_' + matchSetId).find('.panel-footer').removeClass('hidden');
                        $('#match_' + matchSetId).find('.alert-success').removeClass('hidden');
                        $('#match_' + matchSetId).find('.alert-danger').addClass('hidden');
                        $('#evalTable_' + matchSetId).find('tfoot').addClass('hidden');
                        dataUpdater.updateEstimation(estimations, matchSetId);
                        dataUpdater.insertEstimation(estimations, matchSetId);
                    } else {
                        $('#match_' + matchSetId).find('.panel-footer').removeClass('hidden');
                        $('#match_' + matchSetId).find('.alert-danger').removeClass('hidden');
                    }
                });

            });

        </script>
    </body>
</html>