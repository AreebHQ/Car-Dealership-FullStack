$(document).ready(function () {
    
	loadNavigationBar();
	loadNewInventory();
	

  
});


function loadNewInventory() {

     var searchCardTemplate = $('#searchResultTemplate');
	 
	  $.ajax({
        type: 'GET',
        url:'http://localhost:8080/Inventory/used',
		
        success: function(allFeaturedVehicles) {
        
		$.each(allFeaturedVehicles, function(index, featuredVehicle){
			
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
						'<a href="#" class="btn btn-primary float-right align-self-baseline col-6">Details</a></div>'+	  
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
		   url:'http://localhost:8080/Inventory/searchUsedInventory',
            contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			 data: JSON.stringify(input),
           success: function(response) {
            
             $('#searchResultTemplate').empty();
			 var searchCardTemplate = $('#searchResultTemplate');
			  
			  $.each(response, function(index, featuredVehicle){
			
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
			'<a href="#" class="btn btn-primary float-right align-self-baseline col-6" type="button" onclick="showVehicleDetail()">Details</a></div>'+	  
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
			 ' <li class="nav-item active">'+
			 '   <a class="nav-link" href="./usedInventory.html">Used Inventory </a>'+
			 ' </li>'+
			  ' <li class="nav-item">'+
			 '   <a class="nav-link" href="#">Specials</a>'+
			 ' </li>'+
			 ' <li class="nav-item">'+
			 '   <a class="nav-link disabled">Contact</a>'+
			'  </li>'+
		   ' </ul>'+
		 ' </div>'+
		'</nav>';
		  
		navigation.append(navbarDark);
}
















