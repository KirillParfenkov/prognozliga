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
    			/*margin: 0 auto;
    			width: 540px;
    			margin-top: 50px;*/
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

            }

            #navPanel {
                text-align: right;
            }

    	</style>
      </head>
    <body>
        <style>

        </style>
        <div id="header">
            <jsp:include page="<%= Constant.URL_HEADER_PAGE%>" />
        </div>

        <h1>Management Page</h1>

        <div id="mainList" class="col-md-12">
            <div class="row">
                <div id="navPanel" class="col-md-2">
                    <ul class="nav nav-pills nav-stacked navbar-right" >
                        <li class="active" class="dropdown">
                            <a id="navItem1" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown">Матчи <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="navItem1">
                                <li><a href="#">Показать список</a></li>
                                <li><a id="inputMatchButton" href="#">Добавить матч</a></li>
                                <li><a href="#">qwerwe</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a id="navItem1" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown">Клубы <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="navItem1">
                                <li><a href="#">Показать список</a></li>
                                <li><a id="inputTeamButton" href="#">Добавить клуб</a></li>
                                <li><a href="#">qwerwe</a></li>
                            </ul>
                        </li>
                         <li class="dropdown">
                            <a id="navItem1" role="button" href="#" class="dropdown-toggle" data-toggle="dropdown">Пользователи <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="navItem1">
                                <li><a href="#">Показать список</a></li>
                                <li><a href="#">Добавить пользователя</a></li>
                                <li><a href="#">qwerwe</a></li>
                                <li><a href="#">Еще какя хрень</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <div id="content" class="col-md-10">
                </div>
            </div>
        </div>

        <div>
            <a class="btn btn-default" id="sendRequet">Send request</a>
        </div>

        <div class="modal fade in customPopup" id="requestPopup">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">x</button>
                <h3>Добавить Матч</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="inputMatchForm" action="/main.jsp">
                    <fieldset>
                        <!-- Name input-->
                        <div class="control-group">
                          <label class="control-label" for="inputName">Заголовок Матча</label>
                          <div class="controls">
                            <input id="inputName" name="textinput" type="text" placeholder="Заголовок Матча" class="form-control input-xlarge">
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
                <button form="inputMatchForm" class="btn btn-success">Отправить</button>
            </div>
        </div>

        <div class="modal fade in customPopup" id="inputTeamPopup">
            <div class="modal-header">
                <button class="close" data-dismiss="modal">x</button>
                <h3>Добавить команду</h3>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" id="inpuTeamForm">
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

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="http://code.jquery.com/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap-select.min.js"></script>
        <script src="js/bootstrap-select.js"></script>
        <script src="js/bootstrap-dropdown.js"></script>
        <script src="js/bootstrap-tooltip.js"> </script>
        <script src="js/bootstrap-form.js"> </script>

        <script type="text/javascript">


            var httpRequest;

            if (window.XMLHttpRequest) {
                httpRequest = new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            }

            httpRequest.onreadystatechange = function(){

                 if (httpRequest.readyState === 4) {
                     if (httpRequest.status === 200) {

                       // var move = JSON.parse(httpRequest.responseText);
                       // makeProgress(move);

                    } else {
                        // alert('There was a problem with the request.');
                    }
                }
            };



        $(document).ready(function() {

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

            $("#inputTeamSendButton").on('click', function() {\

                var addTeamContollerUrl = "";

                var params = "?name=" + $("#inputTeamName")[0].value;
                        httpRequest.open('GET', addTeamContollerUrl + params, true); 
                        httpRequest.send(null);
                        selectedChecker = null;

            });

            

            $('#requestPopup').modal({
                keyboard: true,
                show: false,
                backdrop: true
            });

            $('#sendRequet').on('click', function() {
                var w = 600; // popup width
                var h = 420; // popip hieght
                var popupPosition = {
                    left: (screen.width/2)-(w/2) + 'px',
                    top: (screen.height/2)-(h/2) + 'px',
                    width: w,
                    height: h  
                }
                $( '#requestPopup' ).modal('show');
                $( '#requestPopup' ).css(popupPosition);
            });

            $('form').form();

        });
        </script>
    </body>
</html>