$(document).ready(function () {
	
	loadNavigationBar();
    loadUsers();
    $('#addForm').hide();
    $('#editUserHeader').hide();
	$('#addUserHeader').hide();
});


function showAddForm()
{
	$('#errorMessages').empty();
	$('#userTable').hide();
	$('#addUserHeader').show();
	$('#addForm').show();
	$('#editUserHeader').hide();
	
	var roleFormTemplate = $('#roleTemplate');
	var roleTemplate = '<select class="form-control" id="role" name="role">';
	
	 $.ajax({
        type: 'GET',
        url:'http://localhost:8080/admin/roles',		
        success: function(allRoles) {        
		$.each(allRoles, function(index, role){	
		
			   roleTemplate+='<option value="'+role.id+'">'+role.roleName+'</option>';
            })		
			 roleFormTemplate.append(roleTemplate);			
        },
        error: function() {

        }
    })	

}




function addUser()
{
		$('#userTable').show();
	 $('#errorMessages').empty();
		 var firstName = document.getElementById("firstName").value;
		 var lastName = document.getElementById("lastName").value;
		 var email = document.getElementById("email").value;
		 var role = document.getElementById("role").value;
	     var password = document.getElementById("password").value;
	     var confirmPassword = document.getElementById("confirmPassword").value;
				console.log('firstName: ' + firstName );
				console.log('lastName: ' + lastName );
				console.log('email: ' + email );
				console.log('role: ' + role );
				console.log('password: ' + password );
				console.log('confirmPassword: ' + confirmPassword );
				
			
		if(!firstName.length>0){
			 $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text("Error: First Name can't be empty!"));
		}
			if(!lastName.length>0){
			 $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text("Error: Last Name can't be empty!"));
		}
			if(!email.length>0){
			 $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text("Error: Email can't be empty!"));
		}
			if(!password.length>0){
			 $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text("Error: First Name can't be empty!"));
		}
	
			if(!confirmPassword.length>0){
			 $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text("Error: Confirm Passowrd can't be empty!"));
		}
		
		if(confirmPassword != password){
			 $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text("Error: Password doesn't match!"));
		}

			var input = {"firstName": firstName,
						"lastName": lastName,
						"email": email,
						"role": role,
						"password": password }

        $.ajax({
           type: 'POST',
           url:'http://localhost:8080/admin/addUser',
		   contentType : 'application/json; charset=utf-8',
           data: JSON.stringify(input),
           success: function() {
               $('#errorMessages').empty();
              console.log('USer Added !!! ' );
			  hideAddForm();
			  
           },
           error: function () {
               $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.')); 
           }
        })
	
}




function showEditForm(userId)
{
	$('#errorMessages').empty();
	 showAddForm();
	$('#addUserHeader').hide();
	
		
	$.ajax({
        type: 'GET',
        url:'http://localhost:8080/admin/users/'+userId,
		
        success: function(user) {
		
		
		    $('#firstName').val(user.firstName);
			$('#lastName').val(user.lastName);
			$('#email').val(user.email);
		 	$("#role").val(user.role.id).trigger("chosen:updated");
			$('#password').val(user.password);
			$('#confirmPassword').val(user.password);
			
			$('#editUserHeader').show();
         
        },
        error: function() {
             $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
        }
    })
	
	
}




function loadUsers() {

     var contentRows = $('#userRows');


	
	$.ajax({
        type: 'GET',
        url:'http://localhost:8080/admin/users',
		
        success: function(allUsers) {
         $.each(allUsers, function(index, user){
				var userId = user.id;
                var firstName = user.firstName;
                var lastName = user.lastName;
				var email = user.email;
				var role = user.role.roleName;
		
                
              var row = '<tr>';
                    row += '<td>' + firstName + '</td>';
                    row += '<td>' + lastName + '</td>';
					row += '<td>' + email + '</td>';
					row += '<td>' + role + '</td>';
                    row += '<td><button type="button" class="btn btn-secondary" onclick="showEditForm('+ userId +')">Edit</button></td>'; 
					row += '</tr>';
                
                contentRows.append(row);
				
            })
        },
        error: function() {
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

