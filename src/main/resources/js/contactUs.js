$(document).ready(function () {
    
	loadNavigationBar();




});


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
			  ' <li class="nav-item ">'+
			 '   <a class="nav-link" href="./specials.html">Specials</a>'+
			 ' </li>'+
			 ' <li class="nav-item active">'+
			 '   <a class="nav-link" href="./contactUs.html">Contact</a>'+
			'  </li>'+
			' <li class="nav-item">'+
			 '   <a class="nav-link disabled">Contact</a>'+
			'  </li>'+
		   ' </ul>'+
		 ' </div>'+
		'</nav>';
		  
		navigation.append(navbarDark);
}


function addMessage() {
		
		
		 var name = document.getElementById("name").value;
		 var email = document.getElementById("email").value;
		 var phone = document.getElementById("phone").value;
		 var subject =  document.getElementById("subject").value;
		 var messageBody  = document.getElementById("message").value;
			console.log('name: ' + name );
			console.log('subject: ' + subject );
			console.log('phone: ' + phone );
	   
		   var input = {"name": name,
						"email": email,
						"phone": phone,
						"subject": subject,
						"messageBody": messageBody }
		
		

        $.ajax({
           type: 'POST',
           url: 'http://localhost:8080/contactus/message',
		   contentType : 'application/json; charset=utf-8',
		   dataType: 'json',
           data: JSON.stringify(input),
          
           success: function() {
        
            location.reload();
           },
           error: function () {
               $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.')); 
           }
        })
     
}














