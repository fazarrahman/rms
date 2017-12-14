<%@page
	import="com.mitrais.rms.model.MasterData,com.mitrais.rms.model.User,java.util.List,java.util.ArrayList"%>
<jsp:include page="/header.jsp" />
<div class="mdl-layout mdl-js-layout mdl-color--grey-100">
	<main class="mdl-layout__content">
	<div class="mdl-card mdl-shadow--6dp">
		<div class="mdl-card__title mdl-color--primary mdl-color-text--white">
			<h2 class="mdl-card__title-text">Acme Co.</h2>
		</div>
		<form action="edit?id=${user.id}" method="POST">
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
						<%= request.getAttribute("user") != null && (((User)request.getAttribute("user")).getUserType().equalsIgnoreCase(sc.getCodeId())) ? "selected" : "" %>><%=sc.getDescription()%></option>
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
				<%
				     Cookie killMyCookie = new Cookie("message", null);
				     killMyCookie.setMaxAge(0);
				     killMyCookie.setPath("/");
				     response.addCookie(killMyCookie);
				%>
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
<jsp:include page="/footer.jsp" />