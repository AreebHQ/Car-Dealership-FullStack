$(document).ready(function () {
  
	showAddVehicleForm();
});



function showAddVehicleForm()
{
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

	//===========================================================
	
	
	
}

function check()
{
	
	var make = document.getElementById("make").value;
	var model = document.getElementById("model").value;
	var type = document.getElementById("type").value;
	var body = document.getElementById("body").value;
	var transmission = document.getElementById("transmission").value;
	var color = document.getElementById("color").value;
	var interior = document.getElementById("interior").value;
	alert(make+''+model+''+type+''+body+''+transmission+''+color+''+interior);
	 
	 
	  $.ajax({
        type: 'GET',
        url:'http://localhost:8080/Inventory/details/3',		
        success: function(vehicle, status) {    
			
			if(vehicle.type=='New')
			{$("#type").val(1);} else {$("#type").val(2);}
			
			$("#year").val(vehicle.year);
			$("#make").val(vehicle.make.id);
			$("#model").val(vehicle.model.id);
			$("#body").val(vehicle.body.id);
			$("#transmission").val(vehicle.transmission);
			$("#color").val(vehicle.bodyColor.id);
			$("#interior").val(vehicle.interiorColor.id);
			$("#mileage").val(vehicle.mileage);
			$("#vin").val(vehicle.vinNumber);
			$("#salePrice").val(vehicle.salePrice);
			$("#mrsp").val(vehicle.mrspPrice);
			$("#description").val(vehicle.description);
        },
        error: function() {
        }
    })
}

