<html>

    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/modern-business.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    
<script>
	function gosubmit(btncmd){
		control.cmd.value=btncmd;
		control.submit();
	}
</script>


<body>

    <div class="container">

        <div class="row">

            <div class="col-lg-12">
                <h1 class="page-header">
                Control With Web
                </h1>
            </div>

        </div>
      


		<center>
		<form name="control" action="/SerialPortCommunicator/ctrl" method="post">
			<input type="hidden" name="cmd" value="none"/>
		</form>
		</center>

        <div class="row well">
            <div class="col-lg-12 col-md-12">
                <a class="btn btn-lg btn-primary" href="#" onclick="gosubmit('red')"   >&nbsp;&nbsp;Red&nbsp;&nbsp;</a>
                <a class="btn btn-lg btn-primary" href="#" onclick="gosubmit('green')" >Green</a>
                <a class="btn btn-lg btn-primary" href="#" onclick="gosubmit('yellow')"  >Yellow&nbsp;&nbsp;</a>
            </div>
        </div>
<!--         <div class="row well"> -->
<!--             <div class="col-lg-12 col-md-12"> -->
<!--                 <a class="btn btn-lg btn-primary" href="#" onclick="gosubmit('ALEFT')"   >Clockwise</a> -->
<!--                 <a class="btn btn-lg btn-primary" href="#" onclick="gosubmit('ACENTER')" >Stop</a> -->
<!--                 <a class="btn btn-lg btn-primary" href="#" onclick="gosubmit('ARIGHT')"  >CounterClkw</a> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="row well"> -->
<!--             <div class="col-lg-12 col-md-12"> -->
<!--                 <a class="btn btn-lg btn-primary" href="#" onclick="gosubmit('BLEFT')"  >B Left</a> -->
<!--                 <a class="btn btn-lg btn-primary" href="#" onclick="gosubmit('BCENTER')">B Center</a> -->
<!--                 <a class="btn btn-lg btn-primary" href="#" onclick="gosubmit('BRIGHT')" >B Right</a> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="row well"> -->
<!--             <div class="col-lg-12 col-md-12"> -->
<!--                 <a class="btn btn-lg btn-primary" href="#" onclick="gosubmit('CLEFT')"  >C Left</a> -->
<!--                 <a class="btn btn-lg btn-primary" href="#" onclick="gosubmit('CCENTER')">C Center</a> -->
<!--                 <a class="btn btn-lg btn-primary" href="#" onclick="gosubmit('CRIGHT')" >C Right</a> -->
<!--             </div> -->
<!--         </div> -->
	</div>
</body>
</html>
