<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="cn.ycl.vo.FileMangerQueryVo"%>
<%@page import="cn.ycl.entity.Send"%>
<%@page import="cn.ycl.common.PageModel"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	PageModel<Send> pageModel=(PageModel<Send>)request.getAttribute("model");
	FileMangerQueryVo condition = (FileMangerQueryVo)request.getAttribute("condition");
	if(condition==null){
		condition=new FileMangerQueryVo();
		condition.setCurrentPage(1);
		condition.setSender("");
		condition.setReceiver("");
		condition.setIp("");
		condition.setStartTime("");
		condition.setEndTime("");
	}
	if(condition.getCurrentPage()==null || condition.getCurrentPage()<=0){
		condition.setCurrentPage(1);
	}
	if(condition.getSender()==null){
		condition.setSender("");
	} 
	if(condition.getReceiver()==null){
		condition.setReceiver("");
	}
	if(condition.getIp()==null){
		condition.setIp("");
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
					<li name="navName"><a href="registerHome.action">注册记录</a></li>
					<li name="navName"  class="active"><a href="fileMangerHome.action">传输记录</a></li>
					<li name="navName"><a href="logHome.action">日志记录</a></li>
				</ul>
			</div>
		</nav>
	</div>

	<!-- 页面主题部分-->
	<!--条件搜索部分-->
	<div class="container" style="margin-top: 80px;">
		<div class="row">
			<form id="condition" action="fileMangerHome.action" method="post">
				<div class="col-xs-2">
					<div class="row">
						<label for="sender">发送方</label> <input type="text" name="sender" value="<%=condition.getSender() %>"
							class="form-control" id="sender" placeholder="发送方帐号"
							style="width: 110px; display: inline-block;" />
					</div>
				</div>

				<div class="col-xs-2">
					<div class="row">
						<label for="receiver">接收方</label> <input type="text" name="receiver" value="<%=condition.getReceiver() %>"
							class="form-control" id="receiver" placeholder="接收方账号"
							style="width: 110px; display: inline-block;" />
					</div>
				</div>
				
				<div class="col-xs-2">
					<div class="row">
						<label for="ip">IP</label> <input type="text"
							class="form-control" id="ip" placeholder="请输入操作IP" name="ip" value="<%=condition.getIp() %>"
							style="width: 130px; display: inline-block;" />
					</div>
				</div>

				<div class="col-xs-5">
					<div class="row" style="margin-left: 20px;">
						<label for="startTime">时间段</label> <input type="text" name="startTime" value="<%=condition.getStartTime() %>"
							class="form-control" id="startTime" placeholder="起始时间"
							style="width: 150px; display: inline-block;" />&nbsp;-&nbsp; <input
							type="text" class="form-control" id="endTime" placeholder="截至时间" name="endTime" value="<%=condition.getEndTime() %>"
							style="width: 150px; display: inline-block;" />
					</div>
				</div>
				<div class="col-xs-1">
					<button type="submit" class="btn btn-default">Search</button>
				</div>
			</form>
		</div>
		<div class="row" style="margin-top: 10px;">
			<div class="table-responsive">
				<table class="table table-striped table-bordered ">
					<thead>
						<tr>
							<th>发送方</th>
							<th>接收方</th>
							<th>操作IP</th>
							<th>文件名</th>
							<th>文件大小</th>
							<th>时间</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<%
						List<Send> list =pageModel.getDataList();
						if(list!=null){
							for(Send temInfo:list){
						%>
						<tr>
							<td><%=temInfo.getSender() %></td>
							<td><%=temInfo.getReceiver() %></td>
							<td><%=temInfo.getSendip() %></td>
							<td><%=temInfo.getFilename() %></td>
							<%
							 	NumberFormat format = NumberFormat.getInstance();
								format.setMaximumFractionDigits(2);
								Long fileSize = temInfo.getFilesize();
								if(fileSize/1024<1){
							%>
									<td><%=temInfo.getFilesize() %>B</td>
							<%
								}else if(fileSize/1024>1 && fileSize/1024/1024<1){
							%>
									<td><%=format.format((Double)(temInfo.getFilesize()/1024.0)) %>KB</td>
							<%			
								}else{
							%>
								<td><%=format.format((Double)(temInfo.getFilesize()/1024/1024.0)) %>MB</td>
							<%} %>
							<td><%=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format( temInfo.getSenddate())%></td>
							<td><%=temInfo.getDealed().equals("Y")? "已接收":"未接收" %></td>
						</tr>
						<%}
							} %>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!--分页按钮部分-->
	<script type="text/javascript">
		function judge(index){
			window.location.href="fileMangerHome.action?" + $('#condition').serialize()+"&currentPage=" +index
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



