<!doctype html>

<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="logic.bean.CenterOwnerBean,logic.bean.CenterBean" %>
<jsp:useBean id="owner" class="logic.bean.CenterOwnerBean" scope="session" />
<jsp:useBean id="center" class="logic.bean.CenterBean" scope="session" />
<%owner = (CenterOwnerBean)session.getAttribute("loggedOwner");
center = (CenterBean)session.getAttribute("centerInfo"); %>
<head>
  <meta charset="utf-8">
  <title>BeEcological - History AND Unloads</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles/historyANDunloads.css">
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

<ul class="nav nav-pills" id="myTab" role="tablist">
  <li class="nav-item"><a class="nav-link active" id="tab1-tab" data-toggle="tab" href="#tab1" role="tab" aria-controls="tab1" aria-selected="true">Booking accepted </a></li>
  <li class="nav-item"><a class="nav-link" id="tab2-tab" data-toggle="tab" href="#tab2" role="tab" aria-controls="tab2" aria-selected="false">Registered unloads</a></li>
  <h4 style="color:white;margin-left: 180px">Booking & Unloads</h4>
</ul>




<div class="tab-content" id="myTabContent">
  <div class="tab-pane p-4 fade show active" id="tab1" role="tabpanel" aria-labelledby="tab1-tab">
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
 	<c:forEach items="${bookAccept}" var="list">
    <tr class = "table-row1" data-href='DeleteBookingServlet?userToDelete=${list.user}&date=${list.date}&time=${list.time}&username=<%=owner.getUsername()%>&ownerphone=<%=owner.getPhoneNumber()%>&mail=<%=owner.getEmailAddress()%>&centername=<%=center.getName()%>&centerphone=<%=center.getCenterPhone()%>&address=<%=center.getAddress()%>'>
        <td>${list.ID}</td>
        <td>${list.user}</td>
        <td>${list.date}</td>
        <td>${list.time}</td>
    </tr>
</c:forEach>
  </tbody>
</table>
  </div>



  <div class="tab-pane p-4 fade" id="tab2" role="tabpanel" aria-labelledby="tab2-tab">
  	  	<table class="table">
  <thead style= "background-color: #0B526D; color:white;">
    <tr>
      <th scope="col">User</th>
      <th scope="col">Date</th>
      <th scope="col">Time</th>
      <th scope="col">Waste</th>
      <th scope="col">WasteQuantity</th>
      <th scope="col">ecoPoints</th>
    </tr>
  </thead>
  <tbody>
 	<c:forEach items="${unloadRegister}" var="list">
    <tr class = "table-row2" data-href='DeleteUnloadServlet?userToDelete1=${list.user}&date1=${list.date}&time1=${list.time}&waste=${list.waste}&quantity=${list.wasteQuantity}&username=<%=owner.getUsername()%>&ownerphone=<%=owner.getPhoneNumber()%>&mail=<%=owner.getEmailAddress()%>&centername=<%=center.getName()%>&centerphone=<%=center.getCenterPhone()%>&address=<%=center.getAddress()%>'>
        <td>${list.user}</td>
        <td>${list.date}</td>
        <td>${list.time}</td>
        <td>${list.waste}</td>
        <td>${list.wasteQuantity}</td>
        <td>${list.ecoPoints}</td>
    </tr>
</c:forEach>
  </tbody>
</table>  	
  </div>
</div>






<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script>
 $(document).ready(function($) {
    $(".table-row1").click(function() {
    	var r = confirm("Are you sure you want to delete this booking?");
    	if (r==true){
        	window.document.location = $(this).data("href");
    	}
    	else {
   
    	}
    });
});
</script>
 
<script>
 $(document).ready(function($) {
    $(".table-row2").click(function() {
    	var r = confirm("Are you sure you want to delete this unload?");
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