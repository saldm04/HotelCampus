

	let addMenu1IsClick = true;

	const addMenu1 = document.getElementById("addServizio");
	
	function showMenuServizi(){
	
	
		if(addMenu1IsClick){
			addMenu1IsClick = false;
			addMenu1.className = "formAnimation aggiungiServizio";
					
	
		}else{
			addMenu1IsClick = true;
			addMenu1.className = "aggiungiServizio";
		}	
	}
		
		
	let addMenu2IsClick = true;
	
	const addMenu2 = document.getElementById("aggiungiCamera");
	
	function showMenuCamere(){
		
		
		if(addMenu2IsClick){
			addMenu2IsClick = false;
			addMenu2.className = "formAnimation aggiungiCamera";		
	
		}else{
			addMenu2IsClick = true;
			addMenu2.className = "aggiungiCamera";		
			
}
}

    $(document).ready(function() {
        $('th').click(function() {
            var table = $(this).parents('table').eq(0);
            var rows = table.find('tr:gt(0)').toArray().sort(comparator($(this).index()));

            this.asc = !this.asc;
            if (!this.asc) {
                rows = rows.reverse();
            }
            for (var i = 0; i < rows.length; i++) {
                table.append(rows[i]);
            }
        });
    });

    function comparator(index) {
        return function(a, b) {
            var valA = getCellValue(a, index),
                valB = getCellValue(b, index);
            return $.isNumeric(valA) && $.isNumeric(valB) ?
                valA - valB :
                valA.toString().localeCompare(valB);
        };
    }

    function getCellValue(row, index) {
        return $(row).children('td').eq(index).text();
    }
	

