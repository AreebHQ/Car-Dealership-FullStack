$(document).ready(function () {
    
	loadNavigationBar();
	loadInventory();
	

});





function gotoPurchase(vehicleId) {
	
	  $('#errorMessages').empty();
      $('#searchResultTemplate').hide(); //hide search cards
	  $('#newVehiclesHeader').hide();
	
     var detailCardTemplate = $('#vehicleDetailTemplate');
	 var purchaseFormTemplate = $('#purchaseFormTemplate');
	 
	 
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
			   '<h3 class="col-12">Purchase Vehicle</h3>'+
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
					'</div></div></div> ';
				
				detailCardTemplate.append(searchDetail);
				
			
   var purchaseForm = 	'<div class="col-lg-10">'+
					'<div class="form-row">'+
						'<div class="col-md-6 form-group"> Name:'+
							'<input type="text" name="name" class="form-control" id="name" placeholder="Name" /> </div>'+
						'<div class="col-md-6 form-group"> Phone:'+
							'<input type="text" class="form-control" name="phone" id="phone" placeholder="Phone #" /> </div>'+
					'</div>'+
					'<div class="form-row">'+
						'<div class="col-md-6 form-group"> Email:'+
							'<input type="text" class="form-control" name="email" id="email" placeholder="Email" /> </div>'+
					'</div>'+
					'<div class="form-row">'+
						'<div class="col-md-6 form-group"> Street 1:'+
							'<input type="text" name="street1" class="form-control" id="street1" placeholder="Street One" /> </div>'+
						'<div class="col-md-6 form-group"> Street 2:'+
							'<input type="text" class="form-control" name="street2" id="street2" placeholder="Street Two" /> </div>'+
					'</div>'+
					'<div class="form-row">'+
						'<div class="col-md-6 form-group"> City:'+
						'	<input type="text" name="city" class="form-control" id="city" placeholder="City" /> </div>'+
						'<div class="col-md-6 form-group"> State:'+
							'<select class="form-control" id="state" name="state">'+
								'<option value="">N/A</option>'+
								'<option value="AK">Alaska</option>'+
								'<option value="AL">Alabama</option>'+
								'<option value="AR">Arkansas</option>'+
								'<option value="AZ">Arizona</option>'+
								'<option value="CA">California</option>'+
								'<option value="CO">Colorado</option>'+
								'<option value="CT">Connecticut</option>'+
								'<option value="DC">District of Columbia</option>'+
								'<option value="DE">Delaware</option>'+
								'<option value="FL">Florida</option>'+
								'<option value="GA">Georgia</option>'+
								'<option value="HI">Hawaii</option>'+
								'<option value="IA">Iowa</option>'+
								'<option value="ID">Idaho</option>'+
								'<option value="IL">Illinois</option>'+
								'<option value="IN">Indiana</option>'+
								'<option value="KS">Kansas</option>'+
								'<option value="KY">Kentucky</option>'+
								'<option value="LA">Louisiana</option>'+
								'<option value="MA">Massachusetts</option>'+
							'	<option value="MD">Maryland</option>'+
							'	<option value="ME">Maine</option>'+
							'	<option value="MI">Michigan</option>'+
							'	<option value="MN">Minnesota</option>'+
							'	<option value="MO">Missouri</option>'+
							'	<option value="MS">Mississippi</option>'+
							'	<option value="MT">Montana</option>'+
							'	<option value="NC">North Carolina</option>'+
							'	<option value="ND">North Dakota</option>'+
							'	<option value="NE">Nebraska</option>'+
							'	<option value="NH">New Hampshire</option>'+
							'	<option value="NJ">New Jersey</option>'+
							'	<option value="NM">New Mexico</option>'+
							'	<option value="NV">Nevada</option>'+
							'	<option value="NY">New York</option>'+
							'	<option value="OH">Ohio</option>'+
							'	<option value="OK">Oklahoma</option>'+
							'	<option value="OR">Oregon</option>'+
							'	<option value="PA">Pennsylvania</option>'+
							'	<option value="PR">Puerto Rico</option>'+
							'	<option value="RI">Rhode Island</option>'+
							'	<option value="SC">South Carolina</option>'+
							'	<option value="SD">South Dakota</option>'+
							'	<option value="TN">Tennessee</option>'+
							'	<option value="TX">Texas</option>'+
							'	<option value="UT">Utah</option>'+
							'	<option value="VA">Virginia</option>'+
							'	<option value="VT">Vermont</option>'+
							'	<option value="WA">Washington</option>'+
							'	<option value="WI">Wisconsin</option>'+
							'	<option value="WV">West Virginia</option>'+
							'	<option value="WY">Wyoming</option>'+
							'</select>'+
				'		</div>'+
				'	</div>'+
				'	<div class="form-row">'+
						'<div class="col-md-6 form-group"> Zip Code:'+
							'<input type="text" class="form-control" name="zip" id="zip" placeholder="Zip" /> </div>'+
					'</div> </div>'+
				'	<div class"col-12"> <hr class="rounded"> </div>'+
				'	<div class="form-row col-10">'+
				'		<div class="col-md-6 form-group"> Purchase Price:'+
				'			<input type="number" name="purchasePrice" class="form-control" id="purchasePrice" placeholder="Purchase Price" /> </div>'+
				'		<div class="col-md-6 form-group"> Purchase Type:'+
				'			<select class="form-control" id="purchaseType" name="purchaseType">'+
				'				<option value="DealerFinance">Dealer Finance</option>'+
				'				<option value="BankFinance">Bank Finance</option>'+
				'				<option value="Cash">Cash</option>'+
				'			</select>'+
				'		</div>'+
				'	</div>'+
				'<button type="submit" onclick="addPurchase('+vehicleId+')" id="addPurchase" class="btn btn-secondary col-2">Save</button>';
				
				
				
				purchaseFormTemplate.append(purchaseForm);
				
				
	            $('#vehicleDetailTemplate').show();
				 $('#purchaseFormTemplate').show();
            
        },
        error: function() {
             $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.'));
        }
    })
				
   
	
}




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
						'<a href="#" onclick="gotoPurchase(' + vehicleId + ')" class="btn btn-secondary float-right align-self-baseline col-6">Purchase</a></div>'+	  
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
			'<a href="#" class="btn btn-secondary float-right align-self-baseline col-6" type="button" onclick="gotoPurchase(' + vehicleId + ')">Purchase</a></div>'+	  
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
			'  <li class="nav-item ">'+
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
			 	 ' <li class="nav-item active">'+
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




function addPurchase(vehicleId) {
		
		
		 var name = document.getElementById("name").value;
		 var email = document.getElementById("email").value;
		 var phone = document.getElementById("phone").value;
		 var street1 = document.getElementById("street1").value;
		 var street2 = document.getElementById("street2").value;
		 var city =  document.getElementById("city").value;
		 var state =  $('#state').find("option:selected").text();
		 var zip =  document.getElementById("zip").value;
		 var purchasePrice =  document.getElementById("purchasePrice").value;
		 var purchaseType =   $('#purchaseType').find("option:selected").text();
			console.log('vehicleID: ' + vehicleId );
			console.log('name: ' + name );
			console.log('state: ' + state );
			console.log('purchaseType: ' + purchaseType );
	        console.log('purchasePrice: ' + purchasePrice );
	   
	   
		   var input = {"name": name,
						"email": email,
						"phone": phone,
						"street": street1 + ' ' +street2,
						"city": city,
						"state": state,
						"zip": zip,
						"price": purchasePrice,
						"purchaseType": purchaseType}
        $.ajax({
           type: 'POST',
           url: 'http://localhost:8080/sales/purchase/'+vehicleId,
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
        });
		location.reload();
     
}













