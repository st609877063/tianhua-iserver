<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<div class="m_right">

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<form class="form-search">
				编号：<input class="input-medium search-query" type="text" name="bianhao" value="<s:property value="bianhao"/>"/> 
				<button type="submit" class="btn">查找</button>
			</form>
			
			<s:if test="userVo == null">
			<h1>请输入用户编号查询</h1>
			</s:if>
		</div>
	</div>


	<div class="row-fluid">
		<div class="span12">
			<h1><small>个人基本信息</small></h1>
			<table class="table table-striped table-hover table-condensed">
				<tbody>
					<tr>
						<td width="10%" height="60px"><span class="label">编号</span></td>
						<td width="10%" height="60px"><s:property value="userVo.bianhao"/></td>
						<td width="10%" height="60px"><span class="label">姓名</span></td>
						<td width="10%" height="60px"><s:property value="userVo.truename"/></td>
						<td width="10%" height="60px"><span class="label">机构</span></td>
						<td width="10%" height="60px"><s:property value="userVo.jigou"/></td>
						<td width="10%" height="60px"><span class="label">级别</span></td>
						<td width="10%" height="60px"><s:property value="userVo.jibie"/></td>
						<td width="10%" height="60px"><span class="label">性别</span></td>
						<td width="10%" height="60px"><s:if test="userVo.sex == 0">女</s:if>
							<s:if test="userVo.sex == 1">男</s:if>
							<s:if test="userVo.sex == 2">未填</s:if>
						</td>
					</tr>
					<tr class="info">
						<td width="10%" height="60px"><span class="label">出生日期</span></td>
						<td width="10%" height="60px"><s:property value="userVo.birthday"/></td>
						<td width="10%" height="60px"><span class="label">身份证号</span></td>
						<td width="10%" height="60px"><s:property value="userVo.shenfenzhenghao"/></td>
						<td width="10%" height="60px"><span class="label">民族</span></td>
						<td width="10%" height="60px"><s:property value="userVo.mingzu"/></td>
						<td width="10%" height="60px"><span class="label">籍贯</span></td>
						<td width="10%" height="60px"><s:property value="userVo.jiguan"/></td>
						<td width="10%" height="60px"><span class="label">机构名称</span></td>
						<td width="10%" height="60px"><s:property value="userVo.jigou"/></td>
					</tr>
					<tr class="success">
						<td width="10%" height="60px"><span class="label">级别</span></td>
						<td width="10%" height="60px"><s:property value="userVo.jibie"/></td>
						<td width="10%" height="60px"><span class="label">到校时间</span></td>
						<td width="10%" height="60px"><s:property value="userVo.daoxiaoshijian"/></td>
						<td width="10%" height="60px"><span class="label">所属学科</span></td>
						<td width="10%" height="60px"><s:property value="userVo.susuoxueke"/></td>
						<td width="10%" height="60px"><span class="label">岗位类别</span></td>
						<td width="10%" height="60px"><s:property value="userVo.gangweileibie"/></td>
						<td width="10%" height="60px"><span class="label">职称</span></td>
						<td width="10%" height="60px"><s:property value="userVo.zhicheng"/></td>
					</tr>
					<tr class="error">
						<td width="10%" height="60px"><span class="label">任职时间</span></td>
						<td width="10%" height="60px"><s:property value="userVo.renzhishijian"/></td>
						<td width="10%" height="60px"><span class="label">最近续聘时间</span></td>
						<td width="10%" height="60px"><s:property value="userVo.xupinshijian"/></td>
						<td width="10%" height="60px"><span class="label">岗位等级</span></td>
						<td width="10%" height="60px"><s:property value="userVo.gangweidengji"/></td>
						<td width="10%" height="60px"><span class="label">岗位聘用时间</span></td>
						<td width="10%" height="60px"><s:property value="userVo.gangweishijian"/></td>
						<td width="10%" height="60px"><span class="label">学缘</span></td>
						<td width="10%" height="60px"><s:property value="userVo.xueyuan"/></td>
					</tr>
					<tr class="warning">
						<td width="10%" height="60px"><span class="label">最高学位</span></td>
						<td width="10%" height="60px"><s:property value="userVo.zuigaoxuewei"/></td>
						<td width="10%" height="60px"><span class="label">博士毕业时间</span></td>
						<td width="10%" height="60px"><s:property value="userVo.boshishijian"/></td>
						<td width="10%" height="60px"><span class="label">博士毕业院校</span></td>
						<td width="10%" height="60px"><s:property value="userVo.boshixuexiao"/></td>
						<td width="10%" height="60px"><span class="label">博士专业</span></td>
						<td width="10%" height="60px"><s:property value="userVo.boshizhuanye"/></td>
						<td width="10%" height="60px"><span class="label">硕士毕业时间</span></td>
						<td width="10%" height="60px"><s:property value="userVo.shuoshishijian"/></td>
					</tr>
					<tr>
						<td width="10%" height="60px"><span class="label">硕士毕业学校</span></td>
						<td width="10%" height="60px"><s:property value="userVo.shuoshixuexiao"/></td>
						<td width="10%" height="60px"><span class="label">硕士专业</span></td>
						<td width="10%" height="60px"><s:property value="userVo.shuoshizhuanye"/></td>
						<td width="10%" height="60px"><span class="label">本科毕业时间</span></td>
						<td width="10%" height="60px"><s:property value="userVo.benkeshijian"/></td>
						<td width="10%" height="60px"><span class="label">本科毕业学校</span></td>
						<td width="10%" height="60px"><s:property value="userVo.benkexuexiao"/></td>
						<td width="10%" height="60px"><span class="label">本科专业</span></td>
						<td width="10%" height="60px"><s:property value="userVo.benkezhuanye"/></td>
					</tr>
					<tr class="info">
						<td width="10%" height="60px"><span class="label">博士后备注</span></td>
						<td width="10%" height="60px"><s:property value="userVo.boshihou"/></td>
						<td width="10%" height="60px"><span class="label">社会兼职备注</span></td>
						<td width="10%" height="60px"><s:property value="userVo.shehuijianzhi"/></td>
						<td width="10%" height="60px"><span class="label">政治面貌备注</span></td>
						<td width="10%" height="60px"><s:property value="userVo.zhengzhimianmao"/></td>
						<td width="10%" height="60px"><span class="label">出国进修月数</span></td>
						<td width="10%" height="60px"><s:property value="userVo.chuguo"/></td>
						<td width="10%" height="60px"><span class="label">在外攻读学位月数</span></td>
						<td width="10%" height="60px"><s:property value="userVo.zaiwaixuewei"/></td>
					</tr>
					<tr class="success">
						<td width="10%" height="60px"><span class="label">合计月数</span></td>
						<td width="10%" height="60px"><s:property value="userVo.zaiwaiyueshu"/></td>
						<td width="10%" height="60px"><span class="label">联系方式</span></td>
						<td width="10%" height="60px"><s:property value="userVo.lianxi"/></td>
						<td width="10%" height="60px"><span class="label">email</span></td>
						<td colspan="5"><s:property value="userVo.email"/></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<div class="row-fluid">
		<div class="span12">
			<s:if test="chuguoList != null && !chuguoList.isEmpty()">
			<h1><small>出国信息</small></h1>
			<table class="table table-striped table-hover table-condensed">
				<thead>
					<tr>
						<th>所属单位</th>
						<th>出访国家</th>
						<th>出访单位</th>
						<th>所学专业</th>
						<th>出国方式</th>
						<th>项目名称</th>
						<th>出国身份</th>
						<th>出国日期</th>
						<th>回国日期</th>
						<th>所获学位</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="chuguoList" status="chuguoListSt">
					<s:if test="#chuguoListSt.index % 4 == 0"><tr class="success"></s:if>
					<s:elseif test="#chuguoListSt.index % 4 == 1"><tr class="error"></s:elseif>
					<s:elseif test="#chuguoListSt.index % 4 == 2"><tr class="warning"></s:elseif>
					<s:elseif test="#chuguoListSt.index % 4 == 3"><tr></s:elseif>
					<s:else><tr class="info"></s:else>
					<tr>
						<td><s:property value="danwei"/></td>
						<td><s:property value="guojia"/></td>
						<td><s:property value="chufangdanwei"/></td>
						<td><s:property value="zhuanye"/></td>
						<td><s:property value="fangshi"/></td>
						<td><s:property value="xiangmuming"/></td>
						<td><s:property value="chuguoshenfen"/></td>
						<td><s:property value="chuguoshijian"/></td>
						<td><s:property value="huiguoshijian"/></td>
						<td><s:property value="suohuoxuewei"/></td>
						<td><s:property value="beizhu"/></td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
			</s:if>
			<s:else><h1><small>出国信息：暂无</small></h1></s:else>
		</div>
	</div>
	
	
</div>

</div><!-- m_right -->
</div><!-- m_cont -->
</div><!-- zwp_main_c2_a -->

</body>
</html>