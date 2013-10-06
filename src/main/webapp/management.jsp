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
        <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

        <link href="css/bootstrap-formhelpers.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap-formhelpers-countries.flags.css" rel="stylesheet" media="screen">
        <link href="css/bootstrap-formhelpers-currencies.flags.css" rel="stylesheet" media="screen">

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="../../assets/js/html5shiv.js"></script>
          <script src="../../assets/js/respond.min.js"></script>
        <![endif]-->
        <style type="text/css">

            body {
                margin: 0;
                background-color: white;
            }


    		#header {
    			margin: 0 auto;
    			float:none;
    			width: 500px;
    			margin-top: 40px;
    		}

    		#mainList {
    			/*margin: 0 auto;
    			width: 540px;*/
    			margin-top: 80px;
    		}

    		#evalTable {
    		/*	text-align: center;*/
    		}

    		.match {
    			margin-top: 100px;
    		}

            .customPopup {
                overflow: hidden;
                background: #fff;
                border-radius:10px;
                -moz-border-radius:10px;
                -webkit-border-radius:10px;

                overflow: visible;

            }



            .calendar-z-index {
                z-index: 10000;
            }

            #navPanel {
                text-align: right;
            }

    	</style>
      </head>
    <body>
        <jsp:include page="<%= Constant.URL_HEADER_PAGE%>" />
        
        <div id="mainList" class="col-md-12">
            <div class="row">
                <div id="navPanel" class="col-md-2">
                    <ul class="nav nav-pills nav-stacked navbar-right" >
                        <li class="dropdown active">
                            <a id="navItem1" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown">Матчи <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="navItem1">
                                <li><a id="showMatchListButton" href="#matchList">Список матчей</a></li>
                                <li><a id="showMatchSetListButton" href="#matchSetList">Группы матчей</a></li>
                                <li><a id="inputMatchButton" href="#matchList">Добавить матч</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a id="navItem1" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown">Клубы <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="navItem1">
                                <li><a id="showTeamListButton" href="#teamList">Показать список</a></li>
                                <li><a id="inputTeamButton" href="#teamList">Добавить клуб</a></li>
                            </ul>
                        </li>
                         <li class="dropdown">
                            <a id="navItem1" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown">Пользователи <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="navItem1">
                                <li><a id="showUserListButton" href="#userList">Показать список</a></li>
                                <li><a href="#userList">Добавить пользователя</a></li>
                                <li><a href="#userList">Еще какя хрень...</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>

                <!-- ======================================= Contant panel =================================== -->
                <div id="contentList" class="col-md-10">

                    <div class="tab-content">
                        <div class="tab-pane active" id="matchList">
                            <table class="table table-hover" id="matchTable">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Заголовок</th>
                                        <th>Команды</th>
                                        <th>Время</th>
                                        <th>Дата</th>
                                        <th>Результат</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane" id="matchSetList">
                             <table class="table table-hover" id="matchSetTable">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Заголовок</th>
                                        <th>Дата</th>
                                        <th>Закрыт</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane" id="teamList">
                             <table class="table table-hover" id="teamTable">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Название команды</th>
                                    </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane" id="userList">
                            User List!
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--=============================================Popups================================================-->
        <div class="modal fade in customPopup" id="insertMatchPopup">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">x</button>
                <h3>Добавить Матч</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="inputMatchForm" action="#">
                    <fieldset>

                        <!-- Name input-->
                        <div class="control-group">
                          <label class="control-label" for="inputMatchName">Заголовок Матча</label>
                          <div class="controls">
                            <input id="inputMatchName" name="textinput" type="text" placeholder="Заголовок Матча" class="form-control input-xlarge">
                          </div>
                        </div>

                        <!-- Date input -->
                        <div class="control-group">
                            <label class="control-label" for="inputMatchDate">Дата</label>
                            <div id="inputMatchDate" class="bfh-datepicker">
                                <div class="input-group" data-toggle="bfh-datepicker">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                                    <input type="text" class="form-control input-xlarge" readonly>
                                </div>

                                <div class="bfh-datepicker-calendar calendar-z-index">
                                    <table class="calendar table table-bordered">
                                        <thead>
                                            <tr class="months-header">
                                                <th class="month" colspan="4">
                                                    <a class="previous" href="#"><i class="glyphicon glyphicon-chevron-left"></i></a>
                                                    <span></span>
                                                    <a class="next" href="#"><i class="glyphicon glyphicon-chevron-right"></i></a>
                                                </th>
                                                <th class="year" colspan="3">
                                                    <a class="previous" href="#"><i class="glyphicon glyphicon-chevron-left"></i></a>
                                                    <span></span>
                                                    <a class="next" href="#"><i class="glyphicon glyphicon-chevron-right"></i></a>
                                                </th>
                                            </tr>
                                            <tr class="days-header">
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <!-- Time input-->
                        <div class="control-group">
                            <label class="control-label" for="inputMatchTime">Время</label>
                            <div id="inputMatchTime" class="bfh-timepicker">
                                <div class="input-group bfh-timepicker-toggle" data-toggle="bfh-timepicker">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                                    <input type="text" class="form-control input-xlarge" readonly>
                                </div>
                                <div class="bfh-timepicker-popover">
                                    <table class="table">
                                        <tbody>
                                            <tr>
                                                <td class="hour">
                                                    <a class="next" href="#"><i class="glyphicon glyphicon-chevron-up"></i></a><br>
                                                    <input type="text" class="input-sm" readonly><br>
                                                    <a class="previous" href="#"><i class="glyphicon glyphicon-chevron-down"></i></a>
                                                </td>
                                                <td class="separator">:</td>
                                                <td class="minute">
                                                    <a class="next" href="#"><i class="glyphicon glyphicon-chevron-up"></i></a><br>
                                                    <input type="text" class="input-sm" readonly><br>
                                                    <a class="previous" href="#"><i class="glyphicon glyphicon-chevron-down"></i></a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>


                        <!-- Select Team1 -->
                        <div class="control-group">
                          <label class="control-label" for="inputTeam1">Первая команда</label>
                          <div class="controls">
                            <select id="inputTeam1" name="selectbasic1" class="form-control input-xlarge">
                              <option>Option one</option>
                              <option>Option two</option>
                            </select>
                          </div>
                        </div>

                        <!-- Select Team2 -->
                        <div class="control-group">
                          <label class="control-label" for="inputTeam2">Вторая командаc</label>
                          <div class="controls">
                            <select id="inputTeam2" name="selectbasic2" class="form-control input-xlarge">
                              <option>Option one</option>
                              <option>Option two</option>
                            </select>
                          </div>
                        </div>

                    </fieldset>
                </form>
            </div>

            <div class="modal-footer">
                <a id href="#" data-dismiss="modal"  aria-hidden="true" class="btn btn-danger">Закрыть</a>
                <button id="inputMatchSendButton" form="inputMatchForm" class="btn btn-success">Отправить</button>
            </div>
        </div>

        <div class="modal fade in customPopup" id="inputTeamPopup">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">x</button>
                <h3>Добавить команду</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="inpuTeamForm" action="#">
                    <fieldset>
                        <!-- Name input-->
                        <div class="control-group">
                          <label class="control-label" for="inputTeamName">Название Клуба</label>
                          <div class="controls">
                            <input id="inputTeamName" name="textinput" type="text" placeholder="Название Клуб" class="form-control input-xlarge">
                          </div>
                        </div>
                    </fieldset>
                </form>
            </div>

            <div class="modal-footer">
                <a id href="#" data-dismiss="modal"  aria-hidden="true" class="btn btn-danger">Закрыть</a>
                <button id="inputTeamSendButton" form="inpuTeamForm" class="btn btn-success">Отправить</button>
            </div>
        </div>
        <!--=============================================Popups End============================================-->

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="http://code.jquery.com/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap-select.min.js"></script>
        <script src="js/bootstrap-select.js"></script>
        <script src="js/bootstrap-dropdown.js"></script>
        <script src="js/bootstrap-tooltip.js"> </script>
        <script src="js/bootstrap-form.js"> </script>
        <script src="js/bootstrap-tab.js"> </script>
        <script src="js/bootstrap-datetimepicker.min.js"></script>
        <script src="js/bootstrap-formhelpers-datepicker.js"></script>
        <script src="js/bootstrap-formhelpers-datepicker.en_US.js"></script>
        <script src="js/bootstrap-formhelpers-timepicker.js"></script>
        <script src="js/bootstrap-formhelpers-timepicker.js.en_US.js"></script>
        <script type="text/javascript">


        $(document).ready(function() {

            var rootpath = 'http://' + location.hostname + ':' + location.port;

            $('#datetimepicker4').datetimepicker({
                pickTime: false
            });
           
            var httpRequest;

            if (window.XMLHttpRequest) {
                httpRequest = new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            }


            httpRequest.onreadystatechange = function(){

                 if (httpRequest.readyState === 4) {
                     if (httpRequest.status === 200) {

                        var result = JSON.parse(httpRequest.responseText);
                        console.log('result.type: ' + result.type);
                        if (result.type.localeCompare){
                            var compTeamRes = result.type.localeCompare("teamList");
                            var compMatchSetRes = result.type.localeCompare("matchSetList");
                            var compMatchRes = result.type.localeCompare("matchList");
                        }
                        
                        // Teams companents update.
                        if (compTeamRes === 0) {
                            // Update objects associated with a list of team.
                            redrawTeamTable($('#teamTable'), result.content);

                            // Update team select in insert match popup.
                            $('#inputTeam2')[0].options.length = 0;
                            $('#inputTeam1')[0].options.length = 0;
                            for (var i = 0; i < result.content.length; i++) {

                                $('#inputTeam1')[0].options[i] = new Option(result.content[i].name, result.content[i].id);
                                $('#inputTeam2')[0].options[i] = new Option(result.content[i].name, result.content[i].id);
                            }
                        }

                        if (compMatchRes === 0) {
                            redrawMatchTable($('#matchTable'), result.content);
                        }

                        if (compMatchSetRes === 0) {
                            redrawMatchSetTable($('#matchSetTable'), result.content);
                        }

                       // makeProgress(move);
                       console.log(httpRequest.responseText);

                    } else {
                        // alert('There was a problem with the request.');
                    }
                }
            };

            var dataUpdater = {

                updateTeams: function () {

                    var showTeamContollerUrl = rootpath + "/showTeamListController";

                    var params = "?inputTeamNameKey=" + "test";
                    httpRequest.open('GET', showTeamContollerUrl + params, true); 
                    httpRequest.send(null);
                    selectedChecker = null;

                    event.preventDefault()
                },

                updateMatches: function () {

                    var showMatchContollerUrl = rootpath + "/showMatchListController";

                    var params = "?inputTeamNameKey=" + "test";
                    httpRequest.open('GET', showMatchContollerUrl + params, true); 
                    httpRequest.send(null);
                    selectedChecker = null;

                    event.preventDefault();
                },

                updateMatchSets: function () {
                    var showMatchSetListControllerUrl = rootpath + "/showMatchSetListController";

                    var params = "";
                    httpRequest.open('GET', showMatchSetListControllerUrl + params, true);
                    httpRequest.send(null);

                    event.preventDefault();
                },

                updateMatchSet: function () {
                    var updateMatchSetControllerUrl = rootpath + "/updateMatchSetController";
                }

            };

            dataUpdater.updateMatches();


            function redrawTeamTable( table, rowList ) {

                table.find('tbody').find('tr').remove();
                for (var i = 0; i < rowList.length; i++ ) {

                    table.find('tbody').append('<tr><td>' + rowList[i].id + '</td><td>' + rowList[i].name + '</td></tr>');
                }
            }

             function redrawMatchTable( table, rowList ) {

                table.find('tbody').find('tr').remove();
                for (var i = 0; i < rowList.length; i++ ) {

                    table.find('tbody').append('<tr><td>' + rowList[i].id + '</td>' + 
                                                   '<td>' + rowList[i].name + '</td>' +
                                                   '<td>' + rowList[i].team1Id + ' : ' + rowList[i].team2Id + '</td>' +
                                                   '<td>' + rowList[i].time + '</td>' +
                                                   '<td>' + rowList[i].date + '</td>' +
                                                   '<td> - </td>' + '</tr>');
                }
            }

            function redrawMatchSetTable( table, rowList ) {

                table.find('tbody').find('tr').remove();
                for (var i = 0; i < rowList.length; i++ ) {
                    table.find('tbody').append('<tr><td>' + rowList[i].id + '</td>' + 
                                                   '<td>' + rowList[i].title + '</td>' +
                                                   '<td>' + rowList[i].date + '</td>' +
                                                   '<td>' + rowList[i].closed + '</td>' +
                                                   '<td> <button type="submit" class="btn btn-default closeMatchSetButton" value="' + rowList[i].id + '">Закрыть</button></td>' + '</tr>');
                }

                $('.closeMatchSetButton').on('click', function () {
                    
                });
            }

            $("#inputMatchSendButton").on('click', function() {

                var addMatchContollerUrl = rootpath + "/addMatchController";
                console.log("send match");
                var params = "?inputMatchNameKey=" + $("#inputMatchName")[0].value 
                            + "&inputMatchDateKey=" + $("#inputMatchDate").find('input')[0].value
                            + "&inputMatchTimeKey=" + $("#inputMatchTime").find('input')[0].value
                            + "&inputTeam1IdKey=" + $('#inputTeam1')[0].value
                            + "&inputTeam2IdKey=" + $('#inputTeam2')[0].value;

                httpRequest.open('GET', addMatchContollerUrl + params, true); 
                httpRequest.send(null);
                selectedChecker = null;
            }); 

            $('#showMatchSetListButton').on('click', function () {
                dataUpdater.updateMatchSets();
                $(this).tab('show');
            });

            $('#showMatchListButton').on('click', function () {
                dataUpdater.updateMatches();
                       
                $(this).tab('show');
            });

            $('#showTeamListButton').on('click', function () {
                dataUpdater.updateTeams();
                $(this).tab('show');
            });

            $('#showUserListButton').on('click', function () {
                $(this).tab('show');
            });


            $('.dropdown-toggle').dropdown();

            $('.selectpicker').selectpicker({
                 style: 'btn-info',
                 size: 4
            });

            $('#inputTeamPopup').modal({
                keyboard: true,
                show: false,
                backdrop: true
            });

            $('#inputTeamButton').on('click' , function() {
                var w = 400; // popup width
                var h = 300; // popip hieght
                var popupPosition = {
                    left: (screen.width/2)-(w/2) + 'px',
                    top: (screen.height/2)-(h/2) + 'px',
                    width: w,
                    height: h  
                }
                $( '#inputTeamPopup' ).modal('show');
                $( '#inputTeamPopup' ).css(popupPosition);

            });

            $("#inputTeamSendButton").on('click', function() {

                var addTeamContollerUrl = rootpath + "/addTeamController";

                var params = "?inputTeamNameKey=" + $("#inputTeamName")[0].value;
                        httpRequest.open('GET', addTeamContollerUrl + params, true); 
                        httpRequest.send(null);
                        selectedChecker = null;

            });
            

            $('#insertMatchPopup').modal({
                keyboard: true,
                show: false,
                backdrop: true
            });

            $('#inputMatchButton').on('click', function() {
                var w = 600; // popup width
                var h = 530; // popip hieght
                var popupPosition = {
                    left: (screen.width/2)-(w/2) + 'px',
                    top: (screen.height/2)-(h/2) + 'px',
                    width: w,
                    height: h
                }

                dataUpdater.updateTeams();  

                $( '#insertMatchPopup' ).modal('show');
                $( '#insertMatchPopup' ).css(popupPosition);
            });

            $('form').form();

        });
        </script>
    </body>
</html>