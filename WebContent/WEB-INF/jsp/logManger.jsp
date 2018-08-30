<%@ page language="java" pageEncoding="UTF-8"%>

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
					<li name="navName"><a href="fileMangerHome.action">传输记录</a></li>
					<li name="navName" class="active"><a href="logHome.action">日志记录</a></li>
				</ul>
			</div>
		</nav>
	</div>

	<!-- 页面主题部分-->
	<!--条件搜索部分-->
	<div class="container" style="margin-top: 80px;">
		<div class="row">
			<form>
				<div class="col-xs-3">
					<div class="row">
						<label for="operator">操作类型</label> <select name="operator"
							class="form-control" style="display: inline-block; width: 150px;">
							<option>所有操作</option>
							<option>发送文件</option>
							<option>接收文件</option>
							<option>用户注册</option>
							<option>重复连接</option>
							<option>操作错误</option>
							<option>正常退出</option>
							<option>非法入侵</option>
						</select>
					</div>
				</div>

				<div class="col-xs-3">
					<div class="row">
						<label for="email">IP</label> <input type="text"
							class="form-control" id="email" placeholder="请输入Ip地址"
							style="width: 150px; display: inline-block;" />
					</div>
				</div>

				<div class="col-xs-5">
					<div class="row" style="margin-left: 20px;">
						<label for="email">时间段</label> <input type="text"
							class="form-control" id="email" placeholder="起始时间"
							style="width: 150px; display: inline-block;" />&nbsp;-&nbsp; <input
							type="text" class="form-control" id="email" placeholder="截至时间"
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
							<th>ID</th>
							<th>操作IP</th>
							<th>操作端口</th>
							<th>操作类型</th>
							<th>操作时间</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1,001</td>
							<td>Lorem</td>
							<td>ipsum</td>
							<td>dolor</td>
							<td>hellow</td>
						</tr>

					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!--分页按钮部分-->
	<div class="container " style="min-width: 600px; ">
		<nav aria-label="Page navigation " style="float: right; ">
			<ul class="pagination ">
				<li><a href="# " aria-label="Previous "> <span
						aria-hidden="true ">&laquo;</span>
				</a></li>
				<li><a href="# ">1</a></li>
				<li><a href="# ">2</a></li>
				<li><a href="# ">3</a></li>
				<li><a href="# ">4</a></li>
				<li><a href="# ">5</a></li>
				<li><a href="# " aria-label="Next "> <span
						aria-hidden="true ">&raquo;</span>
				</a></li>
			</ul>
		</nav>
	</div>
</body>
</html>
