$(document).ready(function () {
	
	loadNavigationBar();
  

});







function changePassword() {
	
		 var password = document.getElementById("password").value;
		 var confirmPassword = document.getElementById("confirmPassword").value;

	if(!confirmPassword.length>0 || !password.length>0){
			 $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text("Error: Password fields can't be empty!"));
		}
	
	if(confirmPassword != password){
			 $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text("Error: Password doesn't match!"));
		}

	
	var input = {"password": password,
				 "confirmPassword": confirmPassword }

        $.ajax({
           type: 'POST',
           url:'http://localhost:8080/account/changepassword',
		   contentType : 'application/json; charset=utf-8',
           data: JSON.stringify(input),
           success: function() {
               $('#errorMessages').empty();
              console.log('password changed' );
			
			  
           },
           error: function () {
               $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.')); 
           }
        })
	
	
	
}




function loadNavigationBar() {
	
      var navigation = $('#navigation');
		   
		 var navbarDark =   '<nav class="navbar navbar-expand-lg navbar-dark bg-dark">'+
		  '<a class="navbar-brand" href="#"> <img src="../images/homeicon.jpeg" alt="Logo" style="width:40px;"></a>'+
		  ' <ul class="navbar-nav">'+
			'  <li class="nav-item">'+
			 '   <a class="nav-link" href="./index.html">Home </a>'+
			'  </li>'+
			'  <li class="nav-item">'+
			  '  <a class="nav-link" href="./newInventory.html">New Inventory</a>'+
			 ' </li>'+
			 ' <li class="nav-item ">'+
			 '   <a class="nav-link" href="./usedInventory.html">Used Inventory </a>'+
			 ' </li>'+
			  ' <li class="nav-item">'+
			 '   <a class="nav-link" href="./specials.html">Specials</a>'+
			 ' </li>'+
			 	 ' <li class="nav-item">'+
			 '   <a class="nav-link" href="./contactUs.html">Contact</a>'+
			'  </li>'+
			 	 ' <li class="nav-item">'+
			 '   <a class="nav-link" href="./sales.html">Sales</a>'+
			'  </li>'+
			 	 ' <li class="nav-item ">'+
			 '   <a class="nav-link" href="./admin.html">Admin</a>'+
			'  </li>'+
			 ' <li class="nav-item active">'+
			 '   <a class="nav-link" href="./users.html">Users</a>'+
			'  </li>'+
		   ' </ul>'+
		 ' </div>'+
		'</nav>';
		  
		navigation.append(navbarDark);
		
}

