$(document).ready(function () {
  
	showAddVehicleForm();
});



function showAddVehicleForm()
{
	var makeFormTemplate = $('#makeTemplate');
	var makeTemplate = '<select class="form-control" id="make" name="make">';
	
	 $.ajax({
        type: 'GET',
        url:'http://localhost:8080/vehicleMake',		
        success: function(allVehicleMake) {        
		$.each(allVehicleMake, function(index, vehicleMake){	
			   makeTemplate+='<option value="'+vehicleMake.id+'">'+vehicleMake.name+'</option>';
            })		
			 makeFormTemplate.append(makeTemplate);			
        },
        error: function() {

        }
    })				
	//==========================================================
	
	var modelFormTemplate = $('#modelTemplate');
	var modelTemplate = '<select class="form-control" id="model" name="model">';	
	 $.ajax({
        type: 'GET',
        url:'http://localhost:8080/vehicleModel',		
        success: function(allVehicleModel) {       
		$.each(allVehicleModel, function(index, vehicleModel){	
			   modelTemplate+='<option value="'+vehicleModel.id+'">'+vehicleModel.name+'</option>';
            })	
			 modelFormTemplate.append(modelTemplate);		
        },
        error: function() {
        }
    })
	//===========================================================
	
	var bodyFormTemplate = $('#bodyTemplate');
	var bodyTemplate = '<select class="form-control" id="body" name="body">';	
	 $.ajax({
        type: 'GET',
        url:'http://localhost:8080/vehicleBody',		
        success: function(allVehicleBody) {       
		$.each(allVehicleBody, function(index, vehicleBody){	
			   bodyTemplate+='<option value="'+vehicleBody.id+'">'+vehicleBody.name+'</option>';
            })	
			 bodyFormTemplate.append(bodyTemplate);		
        },
        error: function() {
        }
    })
	//===========================================================
		
	var colorFormTemplate = $('#colorTemplate');
	var colorTemplate = '<select class="form-control" id="color" name="color">';	
	 $.ajax({
        type: 'GET',
        url:'http://localhost:8080/vehicleColors',		
        success: function(allVehicleColors) {       
		$.each(allVehicleColors, function(index, vehicleColor){	
			   colorTemplate+='<option value="'+vehicleColor.id+'">'+vehicleColor.name+'</option>';
            })	
			 colorFormTemplate.append(colorTemplate);		
        },
        error: function() {
        }
    })
	//===========================================================
	
	var interiorFormTemplate = $('#interiorTemplate');
	var interiorTemplate = '<select class="form-control" id="interior" name="interior">';	
	 $.ajax({
        type: 'GET',
        url:'http://localhost:8080/vehicleColors',		
        success: function(allVehicleColors) {       
		$.each(allVehicleColors, function(index, vehicleColor){	
			   interiorTemplate+='<option value="'+vehicleColor.id+'">'+vehicleColor.name+'</option>';
            })	
			 interiorFormTemplate.append(interiorTemplate);		
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

