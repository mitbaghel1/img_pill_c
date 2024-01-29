<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8" />
        <title>ECEWSI</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
        <link rel="shortcut icon" href="<%=request.getContextPath()%>/adminResources/images/favicon.ico">

        <!-- C3 charts css -->
        <link href="<%=request.getContextPath()%>/adminResources/css/c3.min.css" rel="stylesheet" type="text/css"  />

        <!-- App css -->
        <link href="<%=request.getContextPath()%>/adminResources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/adminResources/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/adminResources/css/metismenu.min.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/adminResources/css/style.css" rel="stylesheet" type="text/css" />

        <script src="<%=request.getContextPath()%>/adminResources/js/modernizr.min.js"></script>

    </head>


    <body>

        <!-- Begin page -->
        <div id="wrapper">

            <!-- Top Bar Start -->
            
            <!-- Top Bar End -->


            <!-- ========== Left Sidebar Start ========== -->

            <!-- Left Sidebar End -->



            <!-- ============================================================== -->
            <!-- Start right Content here -->
            <!-- ============================================================== -->
            <div class="content-page">
                <!-- Start content -->
                <div class="content">
                    <div class="container-fluid">

                         <div class="row">
                            <div class="col-12">
                                <div class="page-title-box">

                                    <!-- <ol class="breadcrumb float-right">
                                        <li class="breadcrumb-item"><a href="#">Adminox</a></li>
                                        <li class="breadcrumb-item"><a href="#">Dashboard</a></li>
                                        <li class="breadcrumb-item active">Dashboard 1</li>
                                    </ol> -->

                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </div> 
                        <!-- end row -->


                        <div class="row">

                            <div class="col-xl-3 col-sm-6">
                                <div class="card-box widget-box-two widget-two-custom">
                                    <i class="mdi mdi-account-multiple widget-two-icon"></i>
                                    <div class="wigdet-two-content">
                                        <p class="m-0 text-uppercase font-bold font-secondary text-overflow" title="Statistics">Total Users</p>
                                        <h2 class="font-600"><span data-plugin="counterup">${userCount}</span></h2>
                                    </div>
                                </div>
                            </div><!-- end col -->

                            <div class="col-xl-3 col-sm-6">
                                <div class="card-box widget-box-two widget-two-custom">
                                    <i class="mdi mdi-account-multiple widget-two-icon"></i>
                                    <div class="wigdet-two-content">
                                        <p class="m-0 text-uppercase font-bold font-secondary text-overflow" title="Statistics">Total Complains</p>
                                        <h2 class="font-600"><span data-plugin="counterup">${complaintCount}</span></h2>
                                    </div>
                                </div>
                            </div><!-- end col -->

                            <div class="col-xl-3 col-sm-6">
                                <div class="card-box widget-box-two widget-two-custom">
                                    <i class="mdi mdi-crown widget-two-icon"></i>
                                    <div class="wigdet-two-content">
                                        <p class="m-0 text-uppercase font-bold font-secondary text-overflow" title="Statistics">Total Feedback</p>
                                        <h2 class="font-600"><span data-plugin="counterup">${feedbackCount}</span></h2>
                                    </div>
                                </div>
                            </div><!-- end col -->

                            <div class="col-xl-3 col-sm-6">
                                <div class="card-box widget-box-two widget-two-custom">
                                    <i class="mdi mdi-auto-fix widget-two-icon"></i>
                                    <div class="wigdet-two-content">
                                        <p class="m-0 text-uppercase font-bold font-secondary text-overflow" title="Statistics">Rating</p>
                                        <h2 class="font-600"><span data-plugin="counterup">${avgfeedbackCount}</span></h2>
                                    </div>
                                </div>
                            </div><!-- end col -->

                        </div>
                        <!-- end row -->


                        
                        <!-- end row -->


                        <!--- end row -->

                    </div> <!-- container -->

                </div> <!-- content -->

            </div>


            <!-- ============================================================== -->
            <!-- End Right content here -->
            <!-- ============================================================== -->


        </div>
        <!-- END wrapper -->



        <!-- jQuery  -->
        <script src="<%=request.getContextPath()%>/adminResources/js/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/adminResources/js/popper.min.js"></script><!-- Popper for Bootstrap -->
        <script src="<%=request.getContextPath()%>/adminResources/js/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/adminResources/js/metisMenu.min.js"></script>
        <script src="<%=request.getContextPath()%>/adminResources/js/waves.js"></script>
        <script src="<%=request.getContextPath()%>/adminResources/js/jquery.slimscroll.js"></script>

        <!-- Counter js  -->
        <script src="<%=request.getContextPath()%>/adminResources/js/jquery.waypoints.min.js"></script>
        <script src="<%=request.getContextPath()%>/adminResources/js/jquery.counterup.min.js"></script>

        <!--C3 Chart-->
        <script type="text/javascript" src="<%=request.getContextPath()%>/adminResources/js/d3.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/adminResources/js/c3.min.js"></script>

        <!--Echart Chart-->
        <script src="<%=request.getContextPath()%>/adminResources/js/echarts-all.js"></script>

        <!-- Dashboard init -->
        <script src="<%=request.getContextPath()%>/adminResources/js/jquery.dashboard.js"></script>

        <!-- App js -->
        <script src="<%=request.getContextPath()%>/adminResources/js/jquery.core.js"></script>
        <script src="<%=request.getContextPath()%>/adminResources/js/jquery.app.js"></script>

    </body>
</html>