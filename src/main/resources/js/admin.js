$(document).ready(function () {
    
	loadNavigationBar();
	loadInventory();
	showHideSearchHeader();

   $('#addForm').hide();
   $('#addVehicleHeader').hide();
   $('#editVehicleHeader').hide();
   var imageSrc = '';
});





function loadInventory() {

     var searchCardTemplate = $('#searchResultTemplate');
	 
	  $.ajax({
        type: 'GET',
        url:'http://localhost:8080/sales/index',
		
        success: function(allFeaturedVehicles) {
        
		$.each(allFeaturedVehicles, function(index, featuredVehicle){
			  var vehicleId = featuredVehicle.id;
               var year =  featuredVehicle.year;
                var make =  featuredVehicle.make.name;
				var model = featuredVehicle.model.name;
				var salePrice =  '$'+featuredVehicle.salePrice;
				var MRSP =  '$'+featuredVehicle.mrspPrice;
                var imageLink = featuredVehicle.image;	
				var body =  featuredVehicle.body.name;
			    var transmission =  featuredVehicle.transmission;
				var color =  featuredVehicle.bodyColor.name;
				var interior =  featuredVehicle.interiorColor.name;
				var mileage =  featuredVehicle.mileage;
				var vin =  featuredVehicle.vinNumber;
				
				if(mileage == 0) {mileage = "New";}
			
		var searchCard = ' <div class="card"> <div class="row">'+
				  '<div class="col-3"> <br>'+
					  '<h5 class="card-title d-flex justify-content-center">'+ year +' '+make+' '+model+'</h5>'+
					  '<img class="card-img-top" id="image" src="'+imageLink+'" alt="Card image cap"></img>'+
			 ' </div>'+
				 ' <div class="col-3"> <br><br><br>'+
				'<div class="d-flex justify-content-center ">'+
					'<h6 class="card-title"> <b> Body Style: </b> '+body+' <br><br> '+
			 	'<b>   Trans: </b>  '+transmission+'<br><br>'+
					   ' <b>  Color:  </b> '+color+'  </h6> </div>'+
			 ' </div>'+			  
					 ' <div class="col-3"> <br><br><br>'+
					'<div class="d-flex justify-content-center ">'+
					'<h6 class="card-title"> <b> Interior: </b> '+interior+'  <br><br> '+
				' 	<b>   Mileage: </b>  '+mileage+' <br><br>'+
				   ' <b>  VIN#:  </b> '+vin+' </h6> </div>'+
				'  </div>'+			
				 ' <div class="col-3">'+		
						'<br><br><br>'+
						'<h5 class="card-title text-right"><b> Sale Price: </b> '+salePrice+' <br><br> <b> MRSP: </b>  '+MRSP+'</h5>'+
						'<input type="hidden" id="'+vehicleId+'">'+
						'<a href="#" onclick="editVehicle(' + vehicleId + ')" class="btn btn-secondary float-right align-self-baseline col-6">Edit</a></div>'+	  
			'</div> </div>  ';
	  
				
				searchCardTemplate.append(searchCard);
	
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



function loadSearch() {
  
	 var minPrice;
	 var maxPrice;
	 var minYear;
	 var maxYear;
	 var searchInput;
	 	
		minYear = $('#minYear').find("option:selected").text();
		maxYear = $('#maxYear').find("option:selected").text();
		minPrice = $('#minPrice').find("option:selected").text();
		maxPrice = $('#maxPrice').find("option:selected").text();
		searchInput = document.getElementById("searchBar").value;
	   
		   var input = {"minYear": minYear,
						"maxYear": maxYear,
						"minPrice": minPrice,
						"maxPrice": maxPrice,
						"searchInput": searchInput }
		

   $.ajax({
           type: 'POST',
		   url:'http://localhost:8080/admin/vehicles',
            contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			 data: JSON.stringify(input),
           success: function(response) {
            
             $('#searchResultTemplate').empty();
			 var searchCardTemplate = $('#searchResultTemplate');
			  
			  $.each(response, function(index, featuredVehicle){
				var vehicleId = featuredVehicle.id;
               var year =  featuredVehicle.year;
                var make =  featuredVehicle.make.name;
				var model = featuredVehicle.model.name;
				var salePrice =  '$'+featuredVehicle.salePrice;
				var MRSP =  '$'+featuredVehicle.mrspPrice;
                var imageLink = featuredVehicle.image;	
				var body =  featuredVehicle.body.name;
			    var transmission =  featuredVehicle.transmission;
				var color =  featuredVehicle.bodyColor.name;
				var interior =  featuredVehicle.interiorColor.name;
				var mileage =  featuredVehicle.mileage;
				var vin =  featuredVehicle.vinNumber;
				
				if(mileage == 0) {mileage = "New";}
			
		var searchCard = ' <div class="card"> <div class="row">'+
				  '<div class="col-3"> <br>'+
					  '<h5 class="card-title d-flex justify-content-center">'+ year +' '+make+' '+model+'</h5>'+
					  '<img class="card-img-top" id="image" src="'+imageLink+'" alt="Card image cap"></img>'+
			 ' </div>'+
				 ' <div class="col-3"> <br><br><br>'+
				'<div class="d-flex justify-content-center ">'+
					'<h6 class="card-title"> <b> Body Style: </b> '+body+' <br><br> '+
			 	'<b>   Trans: </b>  '+transmission+'<br><br>'+
					   ' <b>  Color:  </b> '+color+'  </h6> </div>'+
			 ' </div>'+			  
					 ' <div class="col-3"> <br><br><br>'+
					'<div class="d-flex justify-content-center ">'+
					'<h6 class="card-title"> <b> Interior: </b> '+interior+'  <br><br> '+
				' 	<b>   Mileage: </b>  '+mileage+' <br><br>'+
				   ' <b>  VIN#:  </b> '+vin+' </h6> </div>'+
				'  </div>'+			
				 ' <div class="col-3">'+		
		      '<br><br><br>'+
			'<h5 class="card-title text-right"><b> Sale Price: </b> '+salePrice+' <br><br> <b> MRSP: </b>  '+MRSP+'</h5>'+
			'<a href="#" class="btn btn-secondary float-right align-self-baseline col-6" type="button" onclick="editVehicle(' + vehicleId + ')">Edit</a></div>'+	  
			'</div> </div>  ';
	  
				
				searchCardTemplate.append(searchCard);
	
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
		
		/* <li class="nav-item active">'+
			 '   <a class="nav-link disabled">Users</a>'+
			'  </li>'+*/
}



function showVehicleForm() {
  
	  $('#editBtnTemp').hide();
	  $('#searchHeaderTemplate').hide(); //hide search header
	  $('#searchResultTemplate').hide(); //hide search result
      $('#searchResultHeader').hide(); //hide search result header
	  $('#addVehicleHeader').show();
	  $('#addBtnTemp').show();

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
	var colorTemplate = '<select class="form-control" id="bodyColor" name="color">';	
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
	var interiorTemplate = '<select class="form-control" id="interiorColor" name="interior">';	
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
	
	  $('#addVehiclesHeader').show();
	  $('#addForm').show();
	  
	
	
}


function showHideSearchHeader() {
	
	 var searchHeaderCardTemplate = $('#searchHeaderTemplate');
	 var searchResultHeaderTemplate = $('#searchResultHeader');
	  
	  
	 	var searchResultHeader = ' <br>'+
			  '<div class="row">'+
			  ' <h3 class="col-12">Search Result</h3>'+
			  ' </div> '+
			  '  <hr class="rounded">'+
		' </div>';
	
	 var searchHeader =  '<br>'+
			 ' <div class="row">'+
			 '  <h3 class="col-12">Admin</h3>'+
			 '  <a class="col-12" onclick="showVehicleForm()" href="#"><h5>Add a new Vehicle</h5></a>'+
			 '  </div> '+
			   ' <hr class="rounded">	 '+
	 '<div class="form-group row justify-content-center">'+
					' <div class="form-outline col-4">'+
				'	 <label for="ex2"> Quick Search </label>'+
				'		 <input type="search" id="searchBar" class="form-control" placeholder="Enter Make - Model" aria-label="Search" />'+
				'	 </div>'+
				'	  <div class="col-3">'+
				'		<label for="ex2"> Price </label><br>'+
				'		<select class="btn btn-secondary dropdown-toggle" id="minPrice" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'+
				'			<option selected="selected" value="0">Min</option>'+
				'			<option value="1">5000</option>'+
				'			<option value="2">10000</option>'+
				'			<option value="3">15000</option>'+
				'			<option value="4">20000</option>'+
				'			<option value="5">25+</option>'+
				'		</select>'+
				'		<select class="btn btn-secondary dropdown-toggle" id="maxPrice" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'+
				'			<option selected="selected" value="0">Max</option>'+
				'		    <option value="1">5000</option>'+
				'			<option value="2">10000</option>'+
				'			<option value="3">15000</option>'+
				'			<option value="4">20000</option>'+
				'			<option value="5">25+</option>'+
				'		</select>'+
				'		</div> '+
				'	  <div class="col-3">'+
				'		<label for="ex2"> Year </label><br>'+
				'		<select class="btn btn-secondary dropdown-toggle" id="minYear" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'+
				'			<option selected="selected dropdown-menu" value="0">Min</option>'+
				'			<option value="1">2000</option>'+
				'			<option value="2">2005</option>'+
				'			<option value="3">2010</option>'+
				'			<option value="4">2015</option>'+
				'			<option value="5">2020+</option>'+
				'		</select>'+
				'		<select class="btn btn-secondary dropdown-toggle" id="maxYear" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'+
				'			<option selected="dropdown-menu" value="0">Max</option>'+
				'			<option value="1">2000</option>'+
				'			<option value="2">2005</option>'+
				'			<option value="3">2010</option>'+
				'			<option value="4">2015</option>'+
				'			<option value="5">2020+</option>'+
				'		</select>'+
				'		</div>    '+
				'		</div>'+
				'		 <div class="row justify-content-center">'+
				'		 <button class="btn btn-secondary col-3" type="submit" id="search-input" onclick="loadSearch()">Search</button>'+
				'	</div>';
			
	searchHeaderCardTemplate.append(searchHeader);
	searchResultHeaderTemplate.append(searchResultHeader);
}


function setEditFormValues(vehicleId)
{

	 
	  $.ajax({
        type: 'GET',
        url:'http://localhost:8080/Inventory/details/'+vehicleId,		
        success: function(vehicle, status) {    
			
			if(vehicle.type=='New')
			{$("#type").val(1);} else {$("#type").val(2);}
			
			$("#year").val(vehicle.year);
			$("#make").val(vehicle.make.id).change();
			$("#model").val(vehicle.model.id).change();
			$("#body").val(vehicle.body.id).change();
			$("#transmission").val(vehicle.transmission);
			$("#bodyColor").val(vehicle.bodyColor.id).change();
			$("#interiorColor").val(vehicle.interiorColor.id).change();
			$("#mileage").val(vehicle.mileage);
			$("#vin").val(vehicle.vinNumber);
			$("#salePrice").val(vehicle.salePrice);
			$("#mrspPrice").val(vehicle.mrspPrice);
			$("#description").val(vehicle.description);
			
			
			console.log('year: ' + vehicle.year);
			console.log('make: ' + vehicle.make.id);
			console.log('model: ' + vehicle.model.id);
			console.log('body: ' + vehicle.body.id);
			console.log('type: ' + vehicle.type);
			console.log('transmission: ' + vehicle.transmission);
			console.log('bodyColor: ' + vehicle.bodyColor.id);
			console.log('interiorColor: ' + vehicle.interiorColor.id);
				console.log('mileage: ' + vehicle.mileage);
				console.log('vinNumber: ' + vehicle.vinNumber);
				console.log('salePrice: ' + vehicle.salePrice);
				console.log('mrspPrice: ' + vehicle.mrspPrice);
				console.log('description: ' + vehicle.description);
        },
        error: function() {
        }
    })
}

function getImage(vehicleId)
{
	
	$.ajax({
        type: 'GET',
        url:'http://localhost:8080/Inventory/details/'+vehicleId,		
        success: function(vehicle, status) {    
			
			imageSrc = vehicle.image;

        },
        error: function() {
        }
    })

}

function editVehicle(vehicleId) {
     
	  showVehicleForm();

	  $('#searchHeaderTemplate').hide(); //hide search header
	  $('#searchResultTemplate').hide(); //hide search result
      $('#searchResultHeader').hide(); //hide search result header
	  $('#editVehicleHeader').show(); //show edit vehice header
	 
	  
	  $('#addVehicleHeader').hide();
	  $('#addBtnTemp').hide();
	  $('#editBtnTemp').show();
	  setEditFormValues(vehicleId);
	  getImage(vehicleId);
		 
		
	  $('#editBtn').click(function(){
		  
		  
		var imageFile  = document.getElementById("imageFile").value;
		 if(imageFile == 'undefined' || imageFile == '')
		 {
			 imageFile = imageSrc;

		 }
		
		var make = parseInt(document.getElementById("make").value);
		var model = document.getElementById("model").value;
		 var type = $('#type').find("option:selected").text();
		 var body =  document.getElementById("body").value;
		 var year = document.getElementById("year").value;
		 var transmission = document.getElementById("transmission").value;
		 var bodyColor = document.getElementById("bodyColor").value;
		 var interiorColor =  document.getElementById("interiorColor").value;
		 var mileage = document.getElementById("mileage").value;
		 var vinNumber = document.getElementById("vin").value;
		 var mrspPrice = document.getElementById("mrspPrice").value;
		 var salePrice =  document.getElementById("salePrice").value;
		 var description  = document.getElementById("description").value;
		 
		
		 
		   console.log('vehicleId: ' + vehicleId );
			console.log('make: ' + make );
			console.log('model: ' + model );
			console.log('type: ' + type );
				console.log('body: ' + body );
			console.log('year: ' + year );
			console.log('transmission: ' + transmission );
				console.log('bodyColor: ' + bodyColor );
			console.log('interiorColor: ' + interiorColor );
			console.log('mileage: ' + mileage );
			console.log('vinNumber: ' + vinNumber );
			console.log('mrspPrice: ' + mrspPrice );
			console.log('salePrice: ' + salePrice );
			console.log('description: ' + description );
			console.log('imageFile: ' + imageFile );
			
			
			   var input = {"make": make,
						"model": model,
						"type": type,
						"body": body,
						"year": year,
						"transmission": transmission,
						"bodyColor": bodyColor,
						"interiorColor": interiorColor,
						"mileage": mileage,
						"vinNumber": vinNumber,
						"mrspPrice": mrspPrice,
						"salePrice": salePrice,
						"description": description,
						"image": imageFile
						}
		
		

        $.ajax({
           type: 'POST',
           url: 'http://localhost:8080/admin/editVehicle/'+vehicleId,
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

	});
	
	
	$('#deleteBtn').click(function(){
		  
		
        $.ajax({
           type: 'POST',
           url: 'http://localhost:8080/admin/deleteVehicle/'+vehicleId,
		   contentType : 'application/json; charset=utf-8',
		   dataType: 'json',
          
           success: function() {
			   
			   console.log('Vehicle Deleted: ' + vehicleId );
				location.reload();
    
           },
           error: function () {
               $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.')); 
           }
        })

	});
  
}



function addVehicle(){
	
		  
		 var make = parseInt(document.getElementById("make").value);
		 var model = document.getElementById("model").value;
		 var type = $('#type').find("option:selected").text();
		 var body =  document.getElementById("body").value;
		 var year = document.getElementById("year").value;
		 var transmission = document.getElementById("transmission").value;
		 var bodyColor = document.getElementById("bodyColor").value;
		 var interiorColor =  document.getElementById("interiorColor").value;
		 var mileage = document.getElementById("mileage").value;
		 var vinNumber = document.getElementById("vin").value;
		 var mrspPrice = document.getElementById("mrspPrice").value;
		 var salePrice =  document.getElementById("salePrice").value;
		 var description  = document.getElementById("description").value;
		 var imageFile  = document.getElementById("imageFile").value;
		
			console.log('make: ' + make );
			console.log('model: ' + model );
			console.log('type: ' + type );
				console.log('body: ' + body );
			console.log('year: ' + year );
			console.log('transmission: ' + transmission );
				console.log('bodyColor: ' + bodyColor );
			console.log('interiorColor: ' + interiorColor );
			console.log('mileage: ' + mileage );
			console.log('vinNumber: ' + vinNumber );
			console.log('mrspPrice: ' + mrspPrice );
			console.log('salePrice: ' + salePrice );
			console.log('description: ' + description );
			console.log('imageFile: ' + imageFile );
			
			
			   var input = {"make": make,
						"model": model,
						"type": type,
						"body": body,
						"year": year,
						"transmission": transmission,
						"bodyColor": bodyColor,
						"interiorColor": interiorColor,
						"mileage": mileage,
						"vinNumber": vinNumber,
						"mrspPrice": mrspPrice,
						"salePrice": salePrice,
						"description": description,
						"image": imageFile
						}

        $.ajax({
           type: 'POST',
           url: 'http://localhost:8080/admin/addVehicle',
		   contentType : 'application/json; charset=utf-8',
		   dataType: 'json',
           data: JSON.stringify(input),
          
           success: function() {
			   
			   
        
           
           },
           error: function () {
               $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.')); 
           }
        })

	  location.reload();
}









