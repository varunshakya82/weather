$(document).ready(function() {  
$( "#getDetails" ).click(function() {
var city=$( "#cName" ).val();

$.ajax({
         type: 'GET',   
         url:'/bin/aviva/weather', 
         data:{'value1' : city},          //passing values to servlet
         success: function(response){
         var weatherdata=JSON.parse(response);
         $("#Wdetails1").text("Temperature in Centigrate--->"+weatherdata['TempInCenti'])
         $("#Wdetails2").text("Temperature in Farehnite--->"+weatherdata['TempInFareh'])
         }
       });

    });

  });