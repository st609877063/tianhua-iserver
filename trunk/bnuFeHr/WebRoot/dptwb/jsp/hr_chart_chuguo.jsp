<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<script src="<%=basePath%>/js/jscharts.js"></script>

<div class="m_right">

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<form class="form-search">
				编号：<input class="input-medium search-query" type="text" name="bianhao" value="<s:property value="bianhao"/>"/> 
				<button type="submit" class="btn">查找</button>
			</form>
		</div>
	</div>

	<div class="row-fluid">
		<div class="span12">
			<div id="chartcontainer" width="700" height="400"></div>
		</div>
	</div>
	
</div>

</div><!-- m_right -->
</div><!-- m_cont -->
</div><!-- zwp_main_c2_a -->


<script type="text/javascript">
/*
var myData = new Array([10, 20], [15, 10], [20, 30], [25, 10], [30, 5]);
var myChart = new JSChart('chartcontainer', 'line');
myChart.setDataArray(myData);
myChart.draw();
 */

/*
var myData = new Array(['unit', 20], ['unit two', 10], ['unit three', 30], ['other unit', 10], ['last unit', 30]);
var myChart = new JSChart('chartcontainer', 'bar');
myChart.setDataArray(myData);
myChart.draw();
 */

var myData = new Array(['unit', 20], ['unit two', 10], ['unit three', 30], ['other unit', 10], ['last unit', 30]);
var myChart = new JSChart('chartcontainer', 'pie');
myChart.setDataArray(myData);
myChart.draw();

</script>


</body>
</html>