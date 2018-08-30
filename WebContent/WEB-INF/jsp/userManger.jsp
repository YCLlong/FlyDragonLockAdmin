<%@page import="org.springframework.beans.factory.annotation.Autowired"%>
<%@page import="cn.ycl.service.impl.UserMangerService"%>
<%@page import="java.util.List"%>
<%@page import="cn.ycl.vo.UserMangerQueryVo"%>
<%@page import="cn.ycl.entity.TbUserManger"%>
<%@page import="cn.ycl.common.PageModel"%>
<%@page import="org.springframework.context.ApplicationContext" %>
<%@page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	PageModel<TbUserManger> pageModel = (PageModel<TbUserManger>)request.getAttribute("model");
	UserMangerQueryVo condition = (UserMangerQueryVo)request.getAttribute("condition");
	if(condition==null){
		condition=new UserMangerQueryVo();
		condition.setCurrentPage(1);
		condition.setQq("");
	}
	if(condition.getCurrentPage()==null || condition.getCurrentPage()<=0){
		condition.setCurrentPage(1);
	}
	if(condition.getQq()==null){
		condition.setQq("");
	}
	
%>
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
					<li name="navName" class="active"><a href="userMangerHome.action">用户管理</a></li>
					<li name="navName"><a href="registerHome.action">注册记录</a></li>
					<li name="navName"><a href="fileMangerHome.action">传输记录</a></li>
					<li name="navName"><a href="logHome.action">日志记录</a></li>
				</ul>
			</div>
		</nav>
	</div>

	<!-- 页面主题部分-->
	<!--条件搜索部分-->
	<div class="container" style="margin-top: 80px;">
		<div class="row">
			<form method="post" action="userMangerHome.action" id="condition">
				<div class="col-xs-2">
					<select class="form-control" style="margin-left: -15px;" name="userStatus">
						<option value="1" <%=condition.getUserStatus()==1 ? " selected='selected'":""%>>On-line</option>
						<option value="2" <%=condition.getUserStatus()==2 ? " selected='selected'":""%>>Off-line</option>
						<option value="3" <%=condition.getUserStatus()==3 ? " selected='selected'":""%>>All</option>
					</select>
				</div>
				<div class="col-xs-2">
					<span style="color: blue; line-height: 30px;margin-left: -35px;">记录数<span
						class="badge" style="line-height: 20px;"><%=pageModel.getRecordNum() %></span></span>
				</div>
				<div class="col-xs-2 col-xs-offset-5">
					<input type="text" name="qq" class="form-control" id="exampleInputEmail3"
						placeholder="请输入用户帐号" style="margin-left: 25px;" value="<%=condition.getQq()%>"/>
				</div>
				<div class="col-xs-1">
					<button type="submit" class="btn btn-default">Search</button>
				</div>
			</form>
		</div>
		<div class="row" style="margin-top: 10px;">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>帐号</th>
							<th>设备号</th>
							<th>IP地址</th>
							<th>操作</th>
						</tr>
					</thead>
					<%
					List<TbUserManger> list =pageModel.getDataList();
					if(list!=null){
						for(TbUserManger temInfo:list){
					
					%>
					<tbody>
						<tr>
							<td><%=temInfo.getQq() %></td>
							<td><%=temInfo.getImei() %></td>
							<% if(temInfo.getIp()==null){%>
							<td><p style="color: red;">离线</p></td>
							<%}else{ %>
							<td><%=temInfo.getIp() %></td>
							<%} %>
							<td>
								<% if(temInfo.getIp()!=null){
									//在springmvc中非controller类中调用service接口，以获取springmvc容器方法实现  
								    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
									UserMangerService service = (UserMangerService)ac.getBean("userMangerService");
								%>
								<button type="button" class="btn btn-info btn-xs"
									onclick="byebye('<%=temInfo.getIp() %>')">强制下线</button>
									<%if(service.isBlackListUser(temInfo.getIp())){ %>
									<button type="button" onclick="deleteFromBlackList('<%=temInfo.getIp() %>')"
									class="btn btn-success btn-xs">移出黑名单</button>
									<%}else{ %>
									<button type="button" onclick="addToBlackList('<%=temInfo.getIp() %>')"
										class="btn btn-warning btn-xs">拉黑</button>
									<%}} %>
								<button type="button" onclick="deleteUser('<%=temInfo.getQq() %>')"
									class="btn btn-danger btn-xs">删除</button>
							</td>
						</tr>
					</tbody>
					<%}
						} %>
				</table>
			</div>
		</div>
	</div>

	
	<script type="text/javascript">
	function judge(index){
		window.location.href="userMangerHome.action?" + $('#condition').serialize()+"&currentPage=" +index
	}
	
	/* 加入黑名单 */
	function addToBlackList(ip) {
		var con=confirm("是否要将ip为：" + ip + "的用户加入黑名单？");
		if(con==true){
			$.ajax({
				type:"post",
				data:"ip=" + ip,
				url:"addToBlackList.action",
				dateType:"text",
				success:function(resp){
					if(resp=="success"){
						alert("操作成功");
						judge(1);
					}else{
						alert("操作失败");
					}
				},
				error:function(resp){
					alert("请求失败");
				}
			});
		}
	}
	/* 删除用户 */
	function deleteUser(qq) {
		var con=confirm("是否要将账号为：" + qq + "的用户删除？");
		if(con==true){
			$.ajax({
				type:"post",
				data:"qq="+qq,
				url:"deleteUser.action",
				dateType:"text",
				success:function(resp){
					if(resp=="success"){
						alert("操作成功");
						judge(1);
					}else{
						alert("操作失败");
					}
				},
				error:function(resp){
					alert("请求失败");
				}
			});
		}
	}
	
	//从黑名单中移除
	function deleteFromBlackList(ip){
		var con=confirm("是否要将ip为：" + ip + "的用户从黑名单移除？");
		if(con==true){
			$.ajax({
				type:"post",
				data:"ip=" + ip,
				url:"deleteFromBlackList.action",
				dateType:"text",
				success:function(resp){
					if(resp=="success"){
						alert("操作成功");
						judge(1);
					}else{
						alert("操作失败");
					}
				},
				error:function(resp){
					alert("请求失败");
				}
			});
		}
	}
	
	/* 强制下线 */
	function byebye(ip) {
		var con=confirm("是否要将ip为：" + ip + "的用户强制下线？");
		if(con==true){
			$.ajax({
				type:"post",
				data:"ip=" + ip,
				url:"byebye.action",
				dateType:"text",
				success:function(resp){
					if(resp=="success"){
						alert("操作成功");
						judge(1);
					}else{
						alert("操作失败");
					}
				},
				error:function(resp){
					alert("请求失败");
				}
			});
		}
	}
	</script>
	
	<!--分页按钮部分-->
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
