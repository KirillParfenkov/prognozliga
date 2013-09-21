<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="by.parf.checkers.service.Constant"%>

<html>
	  <head>
        <title>Bootstrap 101 Template</title>
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

    	</style>
      </head>
    <body>
        <style>

        </style>
        <div id="header">
            <jsp:include page="<%= Constant.URL_HEADER_PAGE%>" />
        </div>

        <div id="mainList">
            <c:forEach var="matchSet" items="${matchSetList}">
                <div class="match">
                        <div class="panel panel-default">
                            <div class="panel-heading">${matchSet.date}</div>

                            <div class="panel-body">
                                
                                <table class="table table-hover table-condensed" id="evalTable_${matchSet.id}">
                                    <thead>
                                        <tr>
                                            <th>Участник</th>
                                            <c:forEach var="match" items="${matchSet.matches}">
                                                <th>${match.teamFirst.name} - ${match.teamSecond.name}</th>
                                            </c:forEach>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr >
                                            <td>Kiryl Parfiankou</td>
                                            <td>7:0</td>
                                        </tr>
                                        <tr >
                                            <td>Oleg Nesterov</td>
                                            <td>6:1</td>
                                        </tr>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td>
                                                User
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
                                </table>
                            </div>
 <!--                               <div class="panel-footer">
                                    <div class="row">
                                        <div class="col-md-2 col-md-offset-10"><button type="submit" class="btn btn-default">Отправить</button></div>
                                    </div>
                                </div> -->
                        </div>
                </div>
            </c:forEach>
        </div>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="http://code.jquery.com/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>

        <script type="text/javascript">

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

                var dataUpdater = {

                    updateEstimation: function (estimations, matchSetId) {

                        var addEstimationControllerUrl = "http://localhost:8080/addEstimationController";

                        var params = "?inputEstimations=" + estimations.join() + "&matchSet=" + matchSetId;
                        httpRequest.open('GET', addEstimationControllerUrl + params, true); 
                        httpRequest.send(null);
                        selectedChecker = null;
                    }

                };

                $('.sendEstimationButton').on('click', function () {
                    var estimations = new Array();
                    var matchSetId = $( this )[0].value;
                    $('#evalTable_' + matchSetId).find('tfoot').find('input').each(function() {
                        estimations.push($( this )[0].value);
                    })

                    dataUpdater.updateEstimation(estimations, matchSetId);
                   
                });

            });

        </script>
    </body>
</html>