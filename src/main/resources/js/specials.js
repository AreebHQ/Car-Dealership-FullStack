$(document).ready(function () {
    
	loadNavigationBar();
	loadSpecials();
	


});


function loadSpecials(){

     var specialCardTemplate = $('#specialTemplate');
	 
	  $.ajax({
        type: 'GET',
        url:'http://localhost:8080/home/specials',
        success: function(allSpecials) {
        
		$.each(allSpecials, function(index, special){
			    var specialId = special.id;
                var title =  special.title;
                var description =  special.description;
				
			
		var specialCard = '<div class="card"> <div class="row">'+
		'<div class="col-2">'+
			'<img class="card-img-top"  src="../images/dollar.jpg" alt="Card image cap"></img> </div>'+
		'<div class="col-10 ">'+
			'<br>  <h3>'+ title +' </h3> '+
			' '+description+'  <br>'+
		'</div> </div> </div>';
	  
				
				specialCardTemplate.append(specialCard);
	
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
			  ' <li class="nav-item active">'+
			 '   <a class="nav-link" href="./specials.html">Specials</a>'+
			 ' </li>'+
			 ' <li class="nav-item">'+
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
















