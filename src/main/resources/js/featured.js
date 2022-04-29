$(document).ready(function () {
    
    loadFeaturedVehicles();
	loadNavigationBar();
    
});


function loadNavigationBar() {
	
      var navigation = $('#navigation');
		   
		 var navbarDark =   '<nav class="navbar navbar-expand-lg navbar-dark bg-dark">'+
		  '<a class="navbar-brand" href="#"> <img src="../images/homeicon.jpeg" alt="Logo" style="width:40px;"></a>'+
		  ' <ul class="navbar-nav">'+
			'  <li class="nav-item active">'+
			 '   <a class="nav-link" href="./index.html">Home <span class="sr-only">(current)</span></a>'+
			'  </li>'+
			'  <li class="nav-item">'+
			  '  <a class="nav-link" href="./newInventory.html">New Inventory</a>'+
			 ' </li>'+
			 ' <li class="nav-item">'+
			 '   <a class="nav-link" href="./usedInventory.html">Used Inventory</a>'+
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




function clearFeaturedVehicles() {
    $('#cardTemplate').empty();
}


function loadFeaturedVehicles() {

     var cardTemplate = $('#cardTemplate');
	 
	  $.ajax({
        type: 'GET',
        url:'http://localhost:8080/featuredVehicles',
		
        success: function(allFeaturedVehicles) {
        
		$.each(allFeaturedVehicles, function(index, featuredVehicle){
			
               var year =  featuredVehicle.year;
                var makeModel =  featuredVehicle.make.name + ' ' + featuredVehicle.model.name;
				var price =  '$'+featuredVehicle.salePrice;
                var imageLink = featuredVehicle.image;				
			
					 var card = '<div class="col-3 card-deck text-center">';
					      card+= '<img class="card-img-top" src="'+imageLink+'" alt="Card image cap">';
						  card+= '<div class="card-body">';
						  card+= '<h5 class="card-title">' + year + '</h5>';
						  card+= '<h6 class="card-subtitle mb-2 text-muted">' + makeModel +'</h6>';
						  card+= '<h6 class="card-subtitle mb-2 text-muted">'+ price +'</h6>';
						  card+= '</div>'; 
 
					  
				cardTemplate.append(card);
	
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

