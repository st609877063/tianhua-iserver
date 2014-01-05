<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<script src="<%=basePath%>/js/Chart.min.js"></script>
<!-- 
<script src="<%=basePath%>/js/Chart.js"></script>
 -->
<!--[if lte IE 8]>
<script src="<%=basePath%>/js/excanvas.js"></script>
<![endif]-->

<div class="m_right">

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<form class="form-search">
				<table class="table">
				<tbody>
					<tr height="60px">
						<td><span class="label">筛选条件：</span></td>
						<td>机构：
							<select name="jigou">
							<option value="">全部</option>
							<s:iterator value="jigouList" var="jgvar">
							<option value="<s:property/>" <s:if test="jigou == #jgvar">selected</s:if>  ><s:property /></option>
							</s:iterator>
							</select>
						</td>
						<td>级别：
							<select name="jibie">
							<option value="">全部</option>
							<s:iterator value="jibieList" var="jbvar">
							<option value="<s:property/>" <s:if test="jibie == #jbvar">selected</s:if>  ><s:property/></option>
							</s:iterator>
							</select>
						</td>
					</tr>
					<tr height="60px">
						<td><span class="label">出图维度：</span></td>
						<td>维度：
							<select name="weidu">
							<option value="jigou" <s:if test="weidu == 'jigou'">selected</s:if> >机构</option>
							<option value="jibie" <s:if test="weidu == 'jibie'">selected</s:if> >级别</option>
							<option value="sex" <s:if test="weidu == 'sex'">selected</s:if> >性别</option>
							<option value="zhicheng" <s:if test="weidu == 'zhicheng'">selected</s:if> >职称</option>
							<option value="gangweidengji" <s:if test="weidu == 'gangweidengji'">selected</s:if> >岗位等级</option>
							<option value="xueyuan" <s:if test="weidu == 'xueyuan'">selected</s:if> >学缘</option>
							<option value="zuigaoxuewei" <s:if test="weidu == 'zuigaoxuewei'">selected</s:if> >最高学位</option>
							</select>
						</td>
						<td><button type="submit" class="btn">出图</button></td>
					</tr>
					<s:if test="chartRtn1 != ''">
					<tr height="60px">
						<td><span class="label">切换图形：</span></td>
						<td colspan="3">
						<button class="btn" type="button" data-type="changeChart" value="1">曲线图</button>
						<button class="btn" type="button" data-type="changeChart" value="2">柱状图</button>
						<button class="btn" type="button" data-type="changeChart" value="3">雷达图</button>

						<button class="btn" type="button" data-type="changeChart" value="4">极地区域图</button>
						<button class="btn" type="button" data-type="changeChart" value="5">饼图</button>
						<button class="btn" type="button" data-type="changeChart" value="6">环形图</button>
						</td>
					</tr>
					</s:if>
				</tbody>
				</table>
			</form>
		</div>
	</div>

	<div class="row-fluid">
		<div class="span12">
			<canvas id="myChart" width="800" height="500"></canvas>
		</div>
	</div>
	
</div>

</div><!-- m_right -->
</div><!-- m_cont -->
</div><!-- zwp_main_c2_a -->


<script type="text/javascript">
var options = {
	scaleFontSize : 20
};
	
var data = {
		labels : [<s:property value="chartRtn1"/>],
		datasets : [
			{
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(220,220,220,1)",
				pointColor : "rgba(220,220,220,1)",
				pointStrokeColor : "#fff",
				data : [<s:property value="chartRtn2"/>]
			}
		]
	};

var data2 = [
        	{
        		value: 30,
        		color:"#F7464A"
        	},
        	{
        		value : 50,
        		color : "#E2EAE9"
        	},
        	{
        		value : 100,
        		color : "#D4CCC5"
        	},
        	{
        		value : 40,
        		color : "#949FB1"
        	},
        	{
        		value : 120,
        		color : "#4D5360"
        	}
        ];

var ctx = $("#myChart").get(0).getContext("2d");
new Chart(ctx).Line(data, options);

(function($){
	 $("button[data-type=changeChart]").click(function(){
	    var value = $(this).attr("value");
	    if(value == 1) {
	    	//动画效果
	    	new Chart(ctx).Line(data, options);
	    } else if(value == 2) {
	    	new Chart(ctx).Bar(data, options);
	    } else if(value == 3) {
	    	new Chart(ctx).Radar(data, options);
	    } else if(value == 4) {
	    	alert("付费服务");
	    	//new Chart(ctx).PolarArea(data2);
	    } else if(value == 5) {
	    	alert("付费服务");
	    	//new Chart(ctx).Pie(data2);
	    } else if(value == 6) {
	    	alert("付费服务");
	    	//new Chart(ctx).Doughnut(data2,options);
	    }
	 });
})(jQuery);

</script>

</body>
</html>