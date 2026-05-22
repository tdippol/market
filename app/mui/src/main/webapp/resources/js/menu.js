
$('form.ax-menu-container').append('<div id="menu_pageContainer"><ul class="pages_stepContainer"></ul></div>');

$( document ).ready(function() {

	//Ricerca di tutte le voci di menu
	var $items=$("div.ui-menu>ul.ui-menu-list").eq(0).children('li');
	
	var currentPage=1;
	
	//Recupero della dimensione del container
	var menu_width=getMenuContainerWidth();
	if(menu_width>0){
		//Mappatura pagine e disegno dello stepper
		var map_pages = calculatePageMap($items,menu_width);

		//Impostazione di default alla pagina 1 oppure a quella salvata in session storage
		var initialPage=1;
		if(checkStorageEnabled()){
			if(getCurrentPageToSessionStorage()!=null){
				initialPage=getCurrentPageToSessionStorage();
			}
		}
		showItemPerPage(map_pages,initialPage);

	}

	/***************************************************************************************/
	/***************************************************************************************/
	/***************************************************************************************/
	//Calcolo dimensione del menu container
	function getMenuContainerWidth(){
		if($('form.ax-menu-container>div.ui-menu').width()<=0){
			$('#menu_pageContainer').attr('style','display: none !important');
			$('.menuPagBtn').attr('style','display: none !important');
			//LT("Menu Mobile -> No pagination");
			return 0;
		}
		
		$('#menu_pageContainer').attr('style','display: visible !important');
		$('.menuPagBtn').attr('style','display: visible !important');
		return $('form.ax-menu-container>div.ui-menu').width()-20;		
	}

	//Definizione degli item per ogni pagina
	function calculatePageMap($items,menu_width){
			var map_pages = new Map();
			
			var menu_page=1;
			var sum=0;
			
			var arr_items = [];
			$items.each( function(i,item) {
				$item = $(this);
				/*#####*/ //console.log($item);/*#####*/
				//console.log($item.width());
				
				
				if((sum=sum+$item.width())>=menu_width){
					arr_items=[];
					menu_page++;
					sum=0;
				}
				
				arr_items.push(i);
				map_pages.set(menu_page,arr_items);
				
				/*#####*/ //console.log("Item width: "+$item.width()+" - Incremental Sum: "+sum+" (wrap="+menu_width+")" );/*#####*/
			});
			
			if(map_pages.size>1){
				drawPaginationStepList(map_pages);
			}else{
				$('#menu_pageContainer').attr('style','display: none !important');
				$('.menuPagBtn').attr('style','display: none !important');
			}
			
			return map_pages;
		}
	
	//Aggiunta al dom degli step page
	function drawPaginationStepList(map_pages){
	
		$('#menu_pageContainer>ul.pages_stepContainer>li').remove();
		for(i=1;i<=map_pages.size;i++){
			var li = $('<li/>')
						.addClass('changePage')
						.attr('data-pageToShow', i);
			if(i==1){
				li.addClass("active");
			}
			li.appendTo($('#menu_pageContainer>ul.pages_stepContainer'));
		}
	}

	//Stampa degli item per la pagina 
	function showItemPerPage(map_pages,pageToShow){
	
		setCurrentPage(parseInt(pageToShow));
		
		$('#menu_pageContainer>ul.pages_stepContainer>li').removeClass("active");
		$('#menu_pageContainer>ul.pages_stepContainer>li[data-pageToShow="'+pageToShow+'"]').addClass("active");
		
		if(map_pages.has(parseInt(pageToShow)))
		{
			$items.each( function(i) {
				$item = $(this);
				$item.attr('style','display: none !important');
			});
			
			$items.each( function(i) {
				$item = $(this);
				//console.log(map_pages);
				if(map_pages.get(parseInt(pageToShow)).includes(i)){
					$item.attr('style','display: visible !important');
				}
			},map_pages,pageToShow);
		}
	}

	function setCurrentPage(pageToShow){
		currentPage=pageToShow;
		
		//saveToSessionStorage
		if(checkStorageEnabled()){
			setCurrentPageToSessionStorage(currentPage);
		}
	}
	
	//Refresh pagination after window resize
	$(window).on('resize', function(){
		var menu_width=getMenuContainerWidth();
		if(menu_width>0){
			createPaginationStep()
			map_pages = calculatePageMap($items,menu_width);
			showItemPerPage(map_pages,1);
		}else{
			destroyPaginationStep();
		}
		
});
	

	
	
	//Evento on click per il cambio pagina
	$(document).on("click", ".changePage" , function() {
		var pageToShow=$(this).attr('data-pageToShow');
		showItemPerPage(map_pages,pageToShow);
	});
	
	$(document).on("click", "#menuPagNext" , function() {
		var nextPage=1;
		if(currentPage<map_pages.size){
			nextPage=currentPage+1;
		}
		showItemPerPage(map_pages,nextPage);
	});
	$(document).on("click", "#menuPagPrev" , function() {
		var prevPage=currentPage-1;
		if(currentPage==1){
			prevPage=map_pages.size;
		}
		showItemPerPage(map_pages,prevPage);
	});



	function destroyPaginationStep(){
		$('#menu_pageContainer').remove();
	}
	function createPaginationStep(){
		if (! $( "form.ax-menu-container>#menu_pageContainer" ).length ) {
			$('form.ax-menu-container').append('<div id="menu_pageContainer"><ul class="pages_stepContainer"></ul></div>');
		}
	}



	/*
		Session Storage Current Page
	*/
	function setCurrentPageToSessionStorage(currentPage){
		sessionStorage.setItem("currentPage", currentPage);
	}
	function getCurrentPageToSessionStorage(){
		return sessionStorage.getItem("currentPage");
	}
	function checkStorageEnabled(){
		if (typeof(Storage) !== "undefined")
			return true;
		else
			return false;
	}

	
	

});


