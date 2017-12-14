<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@page
	import="com.mitrais.rms.model.MasterData,com.mitrais.rms.model.User,java.util.List,java.util.ArrayList"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">

<title>RMS</title>
<meta name="description" content="Index">
<meta name="author" content="Mitrais">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
<link rel="stylesheet"
	href="../../..<%=request.getContextPath()%>/css/styles.css?v=1.0">

<!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
  <![endif]-->
</head>

<body>
	<div class="demo-layout-transparent mdl-layout mdl-js-layout">
		<header class="mdl-layout__header mdl-layout__header--transparent">
			<div class="mdl-layout__header-row">
				<!-- Title -->
				<span class="mdl-layout-title">RMS</span>
				<!-- Add spacer, to align navigation to the right -->
				<div class="mdl-layout-spacer"></div>
				<!-- Navigation -->
				<nav class="mdl-navigation"> 
					Welcome, ${loggedUser.getUserName()}. <a
						class="mdl-navigation__link"
						href="<%=request.getContextPath()%>/logout">Log Out</a>
				</nav>
			</div>
		</header>
		<div class="mdl-layout__drawer">
			<span class="mdl-layout-title">RMS</span>
			<nav class="mdl-navigation">
				<a class="mdl-navigation__link"
					href="<%=request.getContextPath()%>/users/list">Users</a> <a
					class="mdl-navigation__link"
					href="<%=request.getContextPath()%>/logout">Log Out</a>
			</nav>
		</div>
		<main class="mdl-layout__content">
		<div class="mdl-card mdl-shadow--6dp">
			<div class="mdl-card__title mdl-color--primary mdl-color-text--white">
				<h2 class="mdl-card__title-text">Acme Co.</h2>
			</div>
			<form action="form?id=${user.id}" method="POST">
				<div class="mdl-card__supporting-text">

					<div class="mdl-textfield mdl-js-textfield">
						<input class="mdl-textfield__input" type="text" id="username"
							name="username" value="${user.getUserName()}" /> <label
							class="mdl-textfield__label" for="username">Username</label>
					</div>
					<div class="mdl-textfield mdl-js-textfield">
						<input class="mdl-textfield__input" type="password" id="password"
							name="password" value="${user.getPassword()}" /> <label
							class="mdl-textfield__label" for="password">Password</label>
					</div>
					<div class="mdl-selectfield mdl-js-selectfield">
						<select id="usertype" name="usertype">
							<%
									for (MasterData sc : (ArrayList<MasterData>) request.getAttribute("MasterDataList")) {
								%>
							<option value="<%=sc.getCodeId()%>"
								<%=session.getAttribute("user") != null
						&& (((User) session.getAttribute("user")).getUserType().equalsIgnoreCase(sc.getCodeId()))
								? "selected"
								: ""%>><%=sc.getDescription()%></option>
							<%
									}
								%>
						</select> <label class="mdl-textfield__label" for="usertype">User
							Type</label>
					</div>
					<input type="hidden" name="id" value="${user.getId()}">
				</div>
				<div>
					<p>${cookie['message'].getValue()}</p>
				</div>
				<div class="mdl-card__actions mdl-card--border">
					<button
						class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">Save</button>
				</div>
			</form>
		</div>
		<div>
			<a href="list">Return to user list page</a>
		</div>
		</main>
	</div>
	<script src="../../..<%=request.getContextPath()%>/js/scripts.js"></script>
</body>
</html>