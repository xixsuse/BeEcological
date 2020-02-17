<!doctype html>

<html lang="en">
<%@ page import="Bean.CenterOwnerBean,Bean.CenterBean" %>
<jsp:useBean id="owner" class="Bean.CenterOwnerBean" scope="session" />
<jsp:useBean id="center" class="Bean.CenterBean" scope="session" />
<%owner = (CenterOwnerBean)session.getAttribute("loggedOwner");
center = (CenterBean)session.getAttribute("centerInfo"); %>
<head>
  <meta charset="utf-8">
  <title>BeEcological for Managers - Home</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles/homeOwner.css">
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
          <%=owner.getUsername()%></a>
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
<div class="container">
  <div class= "row"><h1 style = "color:#0B526D"> Your center: </h1><p class= "centername"><%= center.getName() %></p></div> 
  <hr></hr>
  <div class="row">
    <div class="col-sm">
      <p></p>
      <div style="width:382px;height:342px;border:1px solid #0B526D;margin-left: 50px">
      	<img src="img/jpeg/<%=center.getName()%>.jpg" width="380" height=340 class="d-inline-block align-top" alt="">
      </div>
    </div>
    <div class="col-sm">
    
      	<button class = "btn btn-primary" type="submit" onclick="myFunction()" style="margin-left: 100px;height: 100px; margin-top: 65px">MANAGE INFORMATION</button>
      	<input type="hidden" name="username" value="<%=owner.getUsername()%>" />
      	<input type="hidden" name="centername" value="<%=center.getName()%>" />

      <form action="HistoryANDunloadsServlet" method="post">
      	<button class = "btn btn-primary" type="submit"  style="margin-left: 100px;height: 100px; margin-top: 50px">HISTORY & UNLOADS</button>
      	<input type="hidden" name="username" value="<%=owner.getUsername()%>" />
      	<input type="hidden" name="ownerphone" value="<%=owner.getPhoneNumber()%>" />
      	<input type="hidden" name="mail" value="<%=owner.getEmailAddress()%>" />
      	<input type="hidden" name="centername" value="<%=center.getName()%>" />
      	<input type="hidden" name="centerphone" value="<%=center.getCenterPhone()%>" />
      	<input type="hidden" name="address" value="<%=center.getAddress()%>" />
      </form>
    </div>
    <div class="col-sm">
    <form action="ManageBookingServlet" method="post">
      <button class = "btn btn-primary" type="submit"  style="height: 100px; margin-top: 65px">MANAGE BOOKING</button>
      	<input type="hidden" name="username" value="<%=owner.getUsername()%>" />
      	<input type="hidden" name="ownerphone" value="<%=owner.getPhoneNumber()%>" />
      	<input type="hidden" name="mail" value="<%=owner.getEmailAddress()%>" />
      	<input type="hidden" name="centername" value="<%=center.getName()%>" />
      	<input type="hidden" name="centerphone" value="<%=center.getCenterPhone()%>" />
      	<input type="hidden" name="address" value="<%=center.getAddress()%>" />
    </form>
    <form action="RegisterUnloadServlet" method="post">
      <button class = "btn btn-primary" type="submit"  style="height: 100px; margin-top: 50px">REGISTER UNLOAD</button>
      	<input type="hidden" name="username" value="<%=owner.getUsername()%>" />
      	<input type="hidden" name="ownerphone" value="<%=owner.getPhoneNumber()%>" />
      	<input type="hidden" name="mail" value="<%=owner.getEmailAddress()%>" />
      	<input type="hidden" name="centername" value="<%=center.getName()%>" />
      	<input type="hidden" name="centerphone" value="<%=center.getCenterPhone()%>" />
      	<input type="hidden" name="address" value="<%=center.getAddress()%>" />
    </form>
    </div>
  </div>
  <h1 style = "color: #0B526D;margin-top: 30px">Center Information</h1>
  <hr></hr>
  <div class = "row">
    <div class="col-sm">
      <p></p>
      <div class= "row"><h4 style = "color:#0B526D"> Address: </h4><p class= "lead" style = "margin-left:127px"><%= center.getAddress() %></p></div>
      <div class= "row"><h4 style = "color:#0B526D"> Opening Hours: </h4><div style="width:200px;height:200px;border:1px solid #0B526D;margin-left:50px">Hours-table</div></div>  
    </div>
    <div class="col-sm">
      <p style = "margin-top:90px"></p>
      <div class= "row"><h4 style = "color:#0B526D"> Email: </h4><p class= "lead" style = "margin-left:200px;"><%= owner.getEmailAddress() %></p></div>
      <div class= "row"><h4 style = "color:#0B526D"> Cell Number: </h4><p class= "lead" style = "margin-left:122px"><%= owner.getPhoneNumber() %></p></div>
      <div class= "row"><h4 style = "color:#0B526D"> Phone Number: </h4><p class= "lead" style = "margin-left:94px"><%= center.getCenterPhone() %></p></div>
    </div>
  </div>
  </div>
<hr></hr>













<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script>
function myFunction() {
  alert("Functionality not implemented.");
}
</script>
</body>
</html>