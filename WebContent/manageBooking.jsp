<!doctype html>

<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="logic.Bean.CenterOwnerBean,logic.Bean.CenterBean" %>
<jsp:useBean id="owner" class="logic.Bean.CenterOwnerBean" scope="session" />
<jsp:useBean id="center" class="logic.Bean.CenterBean" scope="session" />
<%owner = (CenterOwnerBean)session.getAttribute("loggedOwner");
center = (CenterBean)session.getAttribute("centerInfo"); %>
<head>
  <meta charset="utf-8">
  <title>BeEcological - Manage Booking</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles/manageBooking.css">
</head>
<body>
<nav class="navbar navbar-expand-lg fixed-top navbar-dark" style = "background-color:#0B526D">
<!-- logo sulla navbar -->
  <a class="navbar-brand" href="HomeOwnerServlet?username=<%=owner.getUsername()%>&ownerphone=<%=owner.getPhoneNumber()%>
  &mail=<%=owner.getEmailAddress()%>&centername=<%=center.getName()%>&address=<%=center.getAddress()%>&centerphone=<%=center.getCenterPhone()%>">
    <img src="img/logo-owner.png" width="320" height=45 class="d-inline-block align-top" alt="">
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto"> <!-- ml mette i pulsanti della navbar a sinistra -->
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=owner.getUsername() %></a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="OwnerProfileServlet?username=<%=owner.getUsername()%>">Your Profile</a>
          <a class="dropdown-item" href="homepage.jsp" onclick=<%session.setAttribute("loggedOwner", null);%> >Logout</a>
        </div>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          HELP</a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#" onclick="myFunction()">Read FAQs</a>
          <a class="dropdown-item" href="#" onclick="myFunction()">Contact Us</a>
          <a class="dropdown-item" href="#" onclick="myFunction()">About Us</a>
        </div>
      </li>
    </ul>
  </div>
</nav>

<div class = "container">
	<h1 style = "color: #0B526D;text-align: center">Manage Booking</h1>
	<hr></hr>
	<h2 style = "color:#0B526D">Add booking</h2>
</div>

  <form action="InsertBookingServlet" method="post"> 
	<div class="container">
  		<div class="row">
    		<div class="col-sm">
				<div class = "row" style = "margin-left: 70px">
				<p class = "lead" style = "margin-top: 12px;margin-right: 20px">Unloader username</p> <input class="form-control" id="usr" name="userToRegister" required style = "width: 300px;margin-bottom:20px">
				</div>
				<div class = "row" style = "margin-left: 70px">
				<p class = "lead" style = "margin-top: 12px;margin-right: 80px">Unload date</p> <input type="date" class = "eco-datepicker" id="datePicker" name="date" required>
				</div>	
				<div class = "row" style = "margin-left: 70px">
				<p class = "lead" style = "margin-top: 30px;margin-right: 80px">Unload time</p> <input class="form-control" id="timeValue" name="time" required style = "width: 300px;margin-top:20px">
				</div>
    		</div>
    		<div class="col-sm">
      			<button class = "btn btn-primary" type="submit"  style="height: 70px; margin-top: 75px;margin-left:100px">ADD BOOKING</button>
    			<input type="hidden" name="username" value="<%=owner.getUsername()%>" />
		     	<input type="hidden" name="ownerphone" value="<%=owner.getPhoneNumber()%>" />
		     	<input type="hidden" name="mail" value="<%=owner.getEmailAddress()%>" />
		     	<input type="hidden" name="centername" value="<%=center.getName()%>" />
		     	<input type="hidden" name="centerphone" value="<%=center.getCenterPhone()%>" />
		     	<input type="hidden" name="address" value="<%=center.getAddress()%>" />
    		</div>
  		</div>
	</div>
  </form>
  
<div class = "container">
<hr></hr>
<h2 style = "color:#0B526D">Confirm booking request</h2>
<p></p>
<table class="table">
  <thead style= "background-color: #0B526D; color:white;">
    <tr>
      <th scope="col">ID</th>
      <th scope="col">User</th>
      <th scope="col">Date</th>
      <th scope="col">Time</th>
    </tr>
  </thead>
  <tbody>
 	<c:forEach items="${bookWait}" var="list">
    <tr class = "table-row" data-href='ConfirmBookingServlet?userToRegister1=${list.user}&date1=${list.date}&time1=${list.time}&username=<%=owner.getUsername()%>&ownerphone=<%=owner.getPhoneNumber()%>&mail=<%=owner.getEmailAddress()%>&centername=<%=center.getName()%>&centerphone=<%=center.getCenterPhone()%>&address=<%=center.getAddress()%>'>
    	<td>${list.ID}</td>
        <td>${list.user}</td>
        <td>${list.date}</td>
        <td>${list.time}</td>
    </tr>
</c:forEach>
  </tbody>
</table>  	
</div>
<hr></hr>
















  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
 <script>
 $(document).ready(function($) {
    $(".table-row").click(function() {
    	var r = confirm("Do you want to accept this booking?");
    	if (r==true){
        	window.document.location = $(this).data("href");
    	}
    	else {
   
    	}
    });
});
 </script>
   <script>
function myFunction() {
  alert("Functionality not implemented.");
}
</script>
</body>
</html>