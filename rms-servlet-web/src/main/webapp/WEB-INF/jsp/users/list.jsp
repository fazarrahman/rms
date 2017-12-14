<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="rms" uri="/WEB-INF/tld/link.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<table
			class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
			<thead>
				<tr>
					<th class="mdl-data-table__cell--non-numeric">User Name</th>
					<th class="mdl-data-table__cell--non-numeric">User Type</th>
					<th class="mdl-data-table__cell--non-numeric">Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td class="mdl-data-table__cell--non-numeric" align="left"><a
							href="form?id=${user.id}">${user.userName}</a></td>
						<td align="left"><c:out value="${user.userType}" /></td>
						<td align="center"><a href="delete?id=${user.id}">delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="3">
					<a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect"
					href="<%=request.getContextPath()%>/users/form">Create
						new user</a>
					<td>
				</tr>
			</tfoot>
		</table>
		</main>
	</div>
	<script src="../../..<%=request.getContextPath()%>/js/scripts.js"></script>
</body>
</html>