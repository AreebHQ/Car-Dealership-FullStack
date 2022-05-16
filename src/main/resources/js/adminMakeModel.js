$(document).ready(function () {
    
	loadNavigationBar();
	loadMakes();
	loadModels();
	$('#makesTemplate').hide();
	$('#modelsTemplate').show();
});




function addNewMake()
{
	
		 var newMakeInput = document.getElementById("newMake").value;
		console.log('new Make: ' + newMakeInput );
		
		
		
		 $.ajax({
           type: 'POST',
           url: 'http://localhost:8080/admin/makes',
		   contentType : 'application/json; charset=utf-8',
           data: newMakeInput,
          
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


function addNewModel()
{
	
		var newMakeInput = document.getElementById("makeOption").value;
		var newModelInput = document.getElementById("newModel").value;
		console.log('new Make: ' + newMakeInput );
		console.log('new Model: ' + newModelInput );
		var input = newMakeInput+'::'+newModelInput;
		
		
		$.ajax({
           type: 'POST',
           url: 'http://localhost:8080/admin/models',
		   contentType : 'application/json; charset=utf-8',
		   data: input,
          
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



function loadModels()
{
	
	 var contentRows = $('#modelRows');
	
	$.ajax({
        type: 'GET',
        url:'http://localhost:8080/admin/models',
		
        success: function(allModels) {
         $.each(allModels, function(index, model){
				var modelId = model.id;
				var makeName = model.make.name;
                var name = model.name;
                var date = model.date;
				var email = model.userEmail;
				
               
              var row = '<tr>';
					row += '<td>' + makeName + '</td>';
                    row += '<td>' + name + '</td>';
                    row += '<td>' + date + '</td>';
					row += '<td>' + email + '</td>';
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


function loadMakes() {

    var contentRows = $('#makeRows');

	var makeDropDownTemplate = $('#makeDropDownTemplate');
	var makeOptionsTemplate = '<select class="form-control" id="makeOption" name="makeOption">';	
	
	$.ajax({
        type: 'GET',
        url:'http://localhost:8080/admin/makes',
		
        success: function(allMakes) {
         $.each(allMakes, function(index, make){
				var makeId = make.id;
                var name = make.name;
                var date = make.date;
				var email = make.userEmail;
				
                 makeOptionsTemplate+='<option value="'+make.id+'">'+make.name+'</option>';
              var row = '<tr>';
                    row += '<td>' + name + '</td>';
                    row += '<td>' + date + '</td>';
					row += '<td>' + email + '</td>';
					row += '</tr>';
                
                contentRows.append(row);
				
            })
				 makeDropDownTemplate.append(makeOptionsTemplate);	
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
			 	 ' <li class="nav-item active">'+
			 '   <a class="nav-link" href="./admin.html">Admin</a>'+
			'  </li>'+
			 ' <li class="nav-item ">'+
			 '   <a class="nav-link" href="./users.html">Users</a>'+
			'  </li>'+
		   ' </ul>'+
		 ' </div>'+
		'</nav>';
		  
		navigation.append(navbarDark);
		
}

