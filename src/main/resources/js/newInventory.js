$(document).ready(function () {
    
	loadNavigationBar();
	loadNewInventory();


});



function showVehicleDetail(vehicleId) {
	
	  $('#errorMessages').empty();
      $('#searchResultTemplate').hide(); //hide search cards
	  $('#newVehiclesHeader').hide();
	
     var detailCardTemplate = $('#vehicleDetailTemplate');
	 
	  $.ajax({
        type: 'GET',
        url:'http://localhost:8080/Inventory/details/'+vehicleId,
		
        success: function(featuredVehicle, status) {
				
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
				var description = featuredVehicle.description;
				
				if(mileage == 0) {mileage = "New";}
   
				var searchDetail = '<div class="row">'+
			   '<h3 class="col-12">Vehicle Detail</h3>'+
			  ' </div> '+
			   ' <hr class="rounded">'+
				'<div class="card"> <div class="row"> <div class="col-3"> <br>'+
					  '<h5 class="card-title d-flex justify-content-center">'+ year +' '+make+' '+model+'</h5>'+
					  '<img class="card-img-top" src="'+imageLink+'" alt="Card image cap"></img> </div>'+
				 ' <div class="col-3"> <br><br><br>'+
				'<div class="d-flex justify-content-center ">'+
					'<h6 class="card-title"> <b> Body Style: </b> '+body+' <br><br> '+
			 	'<b> Trans: </b>  '+transmission+'<br><br>'+
					    '<b>  Color:  </b> '+color+'  </h6> </div>'+
			 '</div>		  '+
					 ' <div class="col-3"> <br><br><br>'+
					'<div class="d-flex justify-content-center ">'+
					'<h6 class="card-title"> <b> Interior: </b> '+interior+'  <br><br> '+
				 '	<b>   Mileage: </b>  '+mileage+' <br><br>'+
				  '  <b>  VIN#:  </b> '+vin+' </h6> </div>'+
				 ' </div>	'+	
				' <div class="col-3">		'+
						'<br><br><br>'+
						'<h5 class="card-title text-right"><b> Sale Price: </b> '+salePrice+' <br><br> <b> MRSP: </b>  '+MRSP+'</h5>'+
						'</div>'+
			'  <div class="row"> <div class="col-3"></div>'+
					'<div class="col-9"><b> Description: &nbsp;&nbsp;</b>'+description+'  </div>'+
				'<br><br> </div>'+
				 ' <div class="col-12">'+
				 ' <a href="./contactUs.html" id="contactUs"  class="btn btn-secondary float-right align-self-baseline" type="submit">Contact Us</a>'+	  
					'</div></div></div> ';
				
				detailCardTemplate.append(searchDetail);
	            $('#vehicleDetailTemplate').show();
            
        },
        error: function() {
             $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
        }
    })
				
   
	
}








function loadNewInventory() {

     var searchCardTemplate = $('#searchResultTemplate');
	 
	  $.ajax({
        type: 'GET',
        url:'http://localhost:8080/Inventory/new',
		
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
					  '<img class="card-img-top" src="'+imageLink+'" alt="Card image cap"></img>'+
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
						'<a href="#" onclick="showVehicleDetail(' + vehicleId + ')" class="btn btn-secondary float-right align-self-baseline col-6" type="submit">Details</a></div>'+	  
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
 
 $('#searchResultTemplate').show();
 
 
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
		   url:'http://localhost:8080/Inventory/searchNewInventory',
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
					  '<img class="card-img-top" src="'+imageLink+'" alt="Card image cap"></img>'+
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
						'<a href="#"  onclick="showVehicleDetail(' + vehicleId + ')"  class="btn btn-primary float-right align-self-baseline col-6" type="submit">Details</a></div>'+	  
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
			'  <li class="nav-item ">'+
			 '   <a class="nav-link" href="./index.html">Home </a>'+
			'  </li>'+
			'  <li class="nav-item active">'+
			  '  <a class="nav-link" href="./newInventory.html">New Inventory</a>'+
			 ' </li>'+
			 ' <li class="nav-item ">'+
			 '   <a class="nav-link" href="./usedInventory.html">Used Inventory </a>'+
			 ' </li>'+
			  ' <li class="nav-item">'+
			 '   <a class="nav-link" href="./specials.html">Specials</a>'+
			 ' </li>'+
			 	 ' <li class="nav-item ">'+
			 '   <a class="nav-link" href="./contactUs.html">Contact</a>'+
			'  </li>'+
			 	 ' <li class="nav-item">'+
			 '   <a class="nav-link" href="./sales.html">Sales</a>'+
			'  </li>'+
			 	 ' <li class="nav-item ">'+
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
















