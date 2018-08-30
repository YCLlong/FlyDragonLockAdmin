<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="cn.ycl.vo.RegisterMangerQueryVo"%>
<%@page import="cn.ycl.entity.Register"%>
<%@page import="cn.ycl.common.PageModel"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	PageModel<Register> pageModel = (PageModel<Register>)request.getAttribute("model");
	RegisterMangerQueryVo condition =(RegisterMangerQueryVo)request.getAttribute("condition");
	if(condition==null){
		condition=new RegisterMangerQueryVo();
		condition.setCurrentPage(1);
		condition.setQq("");
		condition.setImei("");
	}
	if(condition.getCurrentPage()==null || condition.getCurrentPage()<=0){
		condition.setCurrentPage(1);
	}
	if(condition.getQq()==null){
		condition.setQq("");
	} 
	if(condition.getImei()==null){
		condition.setImei("");
	}
	if(condition.getStartTime()==null){
		condition.setStartTime("");
	}
	if(condition.getEndTime()==null){
		condition.setEndTime("");
	}
	
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="shortcut icon" href="img/ico.png" type="image/x-icon" />
<title>FlyDragonLock</title>
<link href="css/bootstrap.css" rel="stylesheet">
<script src="js/jquery-3.2.1.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/my.js"></script>
</head>
<body>
	<!--导航条-->
	<div class="container-fluid">
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">FlyDragonLock</a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li name="navName"><a href="userMangerHome.action">用户管理</a></li>
					<li name="navName" class="active"><a href="registerHome.action">注册记录</a></li>
					<li name="navName"><a href="fileMangerHome.action">传输记录</a></li>
					<li name="navName"><a href="logHome.action">日志记录</a></li>
				</ul>
			</div>
		</nav>
	</div>

	<!--条件搜索部分-->
	<div class="container" style="margin-top: 80px;">
		<div class="row">
			<form id="condition" action="registerHome.action" method="post">
				<div class="col-xs-3">
					<div class="row">
						<label for="qq">帐号</label> <input type="text"
							class="form-control" id="qq" name="qq" placeholder="请输入用户帐号"
							style="width: 150px; display: inline-block;" value="<%=condition.getQq()%>"/>
					</div>
				</div>

				<div class="col-xs-3">
					<div class="row">
						<label for="imei">设备号</label> <input type="text" name="imei"
							class="form-control" id="imei" placeholder="请输入设备号"
							style="width: 150px; display: inline-block;" value="<%=condition.getImei()%>"/>
					</div>
				</div>


				<div class="col-xs-5">
					<div class="row" style="margin-left: 20px;">
						<label for="startTime">时间段</label> <input type="text"
							class="form-control" id="startTime" placeholder="起始(yyyy-MM-dd)" name="startTime"
							style="width: 150px; display: inline-block;" value="<%=condition.getStartTime()%>"/>&nbsp;-&nbsp; <input
							type="text" class="form-control" id="endTime" placeholder="截止(yyyy-MM-dd)" name="endTime"
							style="width: 150px; display: inline-block;" value="<%=condition.getEndTime()%>"/>
					</div>
				</div>
				<div class="col-xs-1">
					<button type="submit" class="btn btn-default">Search</button>
				</div>
			</form>
		</div>
		<%List<Register> list = pageModel.getDataList(); %>
		<div class="row" style="margin-top: 10px;">
			<div class="table-responsive">
				<table class="table table-striped table-bordered ">
					<thead>
						<tr>
							<th>ID</th>
							<th>帐号</th>
							<th>设备号</th>
							<th>时间</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
					<%
						if(list!=null){ 
						for(Register tempInfo:list){
					%>
						<tr>
							<td><%=tempInfo.getId() %></td>
							<td><%=tempInfo.getEmail() %></td>
							<td><%=tempInfo.getImei() %></td>
							<td><%=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(tempInfo.getTime()) %></td>
							<td><%=tempInfo.getState() %></td>
						</tr>
					<%}
						}
					%>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	
	
	<!--分页按钮部分-->
	<script type="text/javascript">
		function judge(index){
			window.location.href="registerHome.action?" + $('#condition').serialize()+"&currentPage=" +index
		}
	</script>
	<%
		int currentPage = pageModel.getCurrentPage();
		Long totalRecordCount = pageModel.getRecordNum();
		int totalPage = pageModel.getPageCount();
		
		/* p是分页范围 */
		int p = currentPage/5;
		if(currentPage%5!=0){
			p++;
		}
	%>
	<div class="container " style="min-width: 600px; ">
		<div style="float: left">
			<span class="badge" style="line-height: 20px;"><%=pageModel.getRecordNum() %>条记录</span>
		</div>
		<nav aria-label="Page navigation " style="float: right; ">
			<ul class="pagination ">
			<!-- 上一页 -->
				<%if(p>1){ %>
				<li><a href="javascript:judge(<%=(p-1)*5 %>)" aria-label="Previous "> <span
						aria-hidden="true ">&laquo;</span>
				</a></li>
				<%} %>
				<!-- 中间显示的页数 -->
				<%for(int i = (p-1)*5+1;i<=p*5;i++){ %>
					<li <%= i==currentPage ? "class='active'":"" %>><a href="javascript:judge(<%=i%>)"><%=i %></a></li>
					<%if(i==totalPage){break;} %>
				<%} %>
				<!-- 下一页 -->
				<%if(p*5<totalPage) {%>
				<li><a href="javascript:judge(<%=p*5+1 %>)" aria-label="Next "> <span
						aria-hidden="true ">&raquo;</span>
				</a></li>
				<%} %>
			</ul>
		</nav>
	</div>
</body>
</html>



