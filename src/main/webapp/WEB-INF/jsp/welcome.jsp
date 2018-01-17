<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<form action="/find" method="GET">
					<div class="form-group">
					    <label for="date">Date(yyyy-MM-dd)</label>
					    <input type="text" class="form-control" name="date" id="date">
					</div>
					<div class="form-group">
					    <label for="name">Name</label>
					    <input type="text" class="form-control" name="name" id="name">
					</div>
					<button type="submit" class="btn btn-primary btn-block">Search</button>
				</form>
			</div>
			
			<div class="col-md-offset-3 col-md-3">
				<form action="/average" method="GET">
					<div class="form-group">
					    <label for="date">Date(yyyy-MM-dd)</label>
					    <input type="text" class="form-control" name="dateAvg" id="date">
					</div>
					<button type="submit" class="btn btn-success btn-block">Calculate Average Price</button>
				</form>
				<a class="btn btn-block btn-danger" href="/" style="margin-top:10px">Home Page</a>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<c:if test="${fn:length(stockList) > 0}">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Symbol</th>
							<th scope="col">Date</th>
							<th scope="col">Open</th>
							<th scope="col">High</th>
							<th scope="col">Low</th>
							<th scope="col">Close</th>
							<th scope="col">Volume</th>
							<th scope="col">Adj_Close</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${stockList}" var="current">
							<tr>
								<td><c:out value="${current.symbol}" /></td>
								<td><fmt:formatDate value="${current.date}"
										pattern="yyyy-MM-dd" /></td>
								<td><c:out value="${current.open}" /></td>
								<td><c:out value="${current.high}" /></td>
								<td><c:out value="${current.low}" /></td>
								<td><c:out value="${current.close}" /></td>
								<td><c:out value="${current.volume}" /></td>
								<td><c:out value="${current.adjClose}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</c:if>
				<c:if test="${fn:length(stockList) <= 0}">
					<h1>No Result Or Input Format Is Wrong</h1>
				</c:if>
			</div>
		</div>
	</div>
	<!-- /.container -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"
		integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
