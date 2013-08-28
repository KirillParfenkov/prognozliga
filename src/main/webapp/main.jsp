<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ page import="by.parf.checkers.service.Constant"%>

<html>
	  <head>
        <title>Bootstrap 101 Template</title>
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
    			width: 540px;
    			margin-top: 50px;
    		}

    		#evalTable {
    		/*	text-align: center;*/
    		}

    		.match {
    			margin-top: 100px;
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
            <div class="match">
                <div class="panel panel-default">
                    <div class="panel-heading">БАТЭ - Смаргонь</div>

                    <div class="panel-body">
                        <table class="table table-hover" id="evalTable">
                            <thead>
                                <tr>
                                    <th>Участник</th>
                                    <th>Прогноз</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr >
                                    <td>Kiryl Parfiankou</td>
                                    <td>7:0</td>
                                </tr>
                                <tr >
                                    <td>Oleg Nesterove</td>
                                    <td>6:1</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="panel-footer">
                        <form class="form-inline" role="form">
                            <div class="form-group">
                                <label class="sr-only" for="team1Goals">БАТЭ</label>
                                <input type="text" class="form-control" id="team1Goals" placeholder="БАТЭ">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="team2Goals">Смаргонь</label>
                                <input type="text" class="form-control" id="team2Goals" placeholder="Смаргонь">
                            </div>
                            <button type="submit" class="btn btn-default">Отправить</button>
                        </form>
                    </div>
                </div>
            </div>
                        <div class="match">
                <div class="panel panel-default">
                    <div class="panel-heading">БАТЭ - Смаргонь</div>

                    <div class="panel-body">
                        <table class="table table-hover" id="evalTable">
                            <thead>
                                <tr>
                                    <th>Участник</th>
                                    <th>Прогноз</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="success">
                                    <td>Kiryl Parfiankou</td>
                                    <td>7:0</td>
                                </tr>
                                <tr class="warning">
                                    <td>Oleg Nesterove</td>
                                    <td>6:1</td>
                                </tr>
                                <tr class="warning">
                                    <td>Vlad Ytsenko</td>
                                    <td>4:0</td>
                                </tr>
                                <tr class="danger">
                                    <td>Ruslan Abramov</td>
                                    <td>1:2</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
                        <div class="match">
                <div class="panel panel-default">
                    <div class="panel-heading">БАТЭ - Смаргонь</div>

                    <div class="panel-body">
                        <table class="table table-hover" id="evalTable">
                            <thead>
                                <tr>
                                    <th>Участник</th>
                                    <th>Прогноз</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="success">
                                    <td>Kiryl Parfiankou</td>
                                    <td>7:0</td>
                                </tr>
                                <tr class="warning">
                                    <td>Oleg Nesterove</td>
                                    <td>6:1</td>
                                </tr>
                                <tr class="warning">
                                    <td>Vlad Ytsenko</td>
                                    <td>4:0</td>
                                </tr>
                                <tr class="danger">
                                    <td>Ruslan Abramov</td>
                                    <td>1:2</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                
                </div>
            </div>
                        <div class="match">
                <div class="panel panel-default">
                    <div class="panel-heading">БАТЭ - Смаргонь</div>

                    <div class="panel-body">
                        <table class="table table-hover" id="evalTable">
                            <thead>
                                <tr>
                                    <th>Участник</th>
                                    <th>Прогноз</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="success">
                                    <td>Kiryl Parfiankou</td>
                                    <td>7:0</td>
                                </tr>
                                <tr class="warning">
                                    <td>Oleg Nesterove</td>
                                    <td>6:1</td>
                                </tr>
                                <tr class="warning">
                                    <td>Vlad Ytsenko</td>
                                    <td>4:0</td>
                                </tr>
                                <tr class="danger">
                                    <td>Ruslan Abramov</td>
                                    <td>1:2</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                
                </div>
            </div>
                        <div class="match">
                <div class="panel panel-default">
                    <div class="panel-heading">БАТЭ - Смаргонь</div>

                    <div class="panel-body">
                        <table class="table table-hover" id="evalTable">
                            <thead>
                                <tr>
                                    <th>Участник</th>
                                    <th>Прогноз</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="success">
                                    <td>Kiryl Parfiankou</td>
                                    <td>7:0</td>
                                </tr>
                                <tr class="warning">
                                    <td>Oleg Nesterove</td>
                                    <td>6:1</td>
                                </tr>
                                <tr class="warning">
                                    <td>Vlad Ytsenko</td>
                                    <td>4:0</td>
                                </tr>
                                <tr class="danger">
                                    <td>Ruslan Abramov</td>
                                    <td>1:2</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>


        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="http://code.jquery.com/jquery.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>

         <script type="text/javascript">

        $(document).ready(function() {
            $('.selectpicker').selectpicker({
                 style: 'btn-info',
                 size: 4
            });
            

            $('#requestPopup').modal({
                keyboard: true,
                show: false,
                backdrop: true
            });

            $('#sendRequet').on('click', function() {
                var w = 400; // popup width
                var h = 400; // popip hieght
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